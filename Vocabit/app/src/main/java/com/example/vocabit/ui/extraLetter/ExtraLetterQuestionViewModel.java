package com.example.vocabit.ui.extraLetter;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.vocabit.MVVMApplication;
import com.example.vocabit.data.Repository;
import com.example.vocabit.data.model.api.response.ResponseWrapper;
import com.example.vocabit.data.model.api.response.extraLetter.ExtraLetterQuestionResponse;
import com.example.vocabit.data.model.api.response.extraLetter.LetterWrapper;
import com.example.vocabit.ui.base.activity.BaseViewModel;
import com.example.vocabit.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;

public class ExtraLetterQuestionViewModel extends BaseViewModel {
    private final Repository repository;
    private final CompositeDisposable disposables = new CompositeDisposable();

    private final MutableLiveData<ExtraLetterQuestionResponse> currentQuestion = new MutableLiveData<>();
    private final MutableLiveData<Boolean> answerResult = new MutableLiveData<>();

    private List<ExtraLetterQuestionResponse> questionList;
    private int currentIndex = -1;

    public final ObservableField<List<LetterWrapper>> letterList = new ObservableField<>();
    private int selectedIndex = -1;

    @Inject
    public ExtraLetterQuestionViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
        this.repository = repository;
    }

    public LiveData<ExtraLetterQuestionResponse> getCurrentQuestion() {
        return currentQuestion;
    }

    public LiveData<Boolean> getAnswerResult() {
        return answerResult;
    }

    public void start(int unit) {
        fetchAllQuestions(unit);
    }


    private void fetchAllQuestions(int unit) {
        showLoading();
        disposables.add(
                repository.getApiService().getExtraLetterQuestions(unit)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .retryWhen(errors -> errors.flatMap(error -> {
                            if (NetworkUtils.checkNetworkError(error)) {
                                hideLoading();
                                return application.showDialogNoInternetAccess();
                            } else {
                                return io.reactivex.rxjava3.core.Observable.error(error);
                            }
                        }))
                        .subscribe(
                                (ResponseWrapper<List<ExtraLetterQuestionResponse>> resp) -> {
                                    hideLoading();
                                    questionList = resp.getResult();
                                    if (questionList != null && !questionList.isEmpty()) {
                                        currentIndex = 0;
                                        updateCurrentQuestion();
                                    }
                                },
                                throwable -> {
                                    hideLoading();
                                    Timber.e(throwable);
                                }
                        )
        );
    }

    private void updateCurrentQuestion() {
        ExtraLetterQuestionResponse q = questionList.get(currentIndex);
        currentQuestion.setValue(q);
        selectedIndex = -1;

        List<LetterWrapper> letters = new ArrayList<>();
        for (char c : q.getIncorrectWord().toCharArray()) {
            letters.add(new LetterWrapper(String.valueOf(c)));
        }
        letterList.set(letters);
    }

    public void onLetterClicked(int index) {
        List<LetterWrapper> list = letterList.get();
        if (list == null || index < 0 || index >= list.size()) return;

        if (selectedIndex == index) {
            // Toggle lại
            list.get(index).setVisible(true);
            selectedIndex = -1;
        } else {
            if (selectedIndex != -1) list.get(selectedIndex).setVisible(true);
            list.get(index).setVisible(false);
            selectedIndex = index;
        }

        letterList.set(new ArrayList<>(list));
    }

    public void submitAnswer() {
        checkAnswer(); // hoặc xử lý gì đó khi nộp
    }

    private void checkAnswer() {
        List<LetterWrapper> list = letterList.get();
        if (list == null) return;

        StringBuilder sb = new StringBuilder();
        for (LetterWrapper l : list) {
            if (l.isVisible()) sb.append(l.getLetter());
        }

        ExtraLetterQuestionResponse q = currentQuestion.getValue();
        if (q != null && sb.toString().equals(q.getCorrectWord())) {
            answerResult.setValue(true);
        } else {
            answerResult.setValue(false);
        }
    }

    public void loadNext() {
        if (questionList == null || ++currentIndex >= questionList.size()) {
            currentQuestion.setValue(null);
            letterList.set(null);
            return;
        }
        updateCurrentQuestion();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}

