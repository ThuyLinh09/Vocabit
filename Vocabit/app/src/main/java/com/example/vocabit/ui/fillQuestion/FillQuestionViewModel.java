package com.example.vocabit.ui.fillQuestion;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.vocabit.MVVMApplication;
import com.example.vocabit.data.Repository;
import com.example.vocabit.data.model.api.response.ResponseWrapper;
import com.example.vocabit.data.model.api.response.fillQuestion.FillQuestionResponse;
import com.example.vocabit.data.model.api.response.imageQuestion.ImageQuestionResponse;
import com.example.vocabit.ui.base.activity.BaseViewModel;
import com.example.vocabit.ui.base.fragment.BaseFragmentViewModel;
import com.example.vocabit.utils.NetworkUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;

public class FillQuestionViewModel extends BaseFragmentViewModel {
    private final Repository repository;
    private final CompositeDisposable disposables = new CompositeDisposable();

    private final MutableLiveData<FillQuestionResponse> currentQuestion = new MutableLiveData<>();
    private final MutableLiveData<Boolean> answerResult = new MutableLiveData<>();

    private List<FillQuestionResponse> questionList;
    private int currentIndex = -1;

    // 🔥 Thêm ObservableField để binding tự động
    public final ObservableField<String> sentence = new ObservableField<>();
    public final ObservableField<String> option1 = new ObservableField<>();
    public final ObservableField<String> option2 = new ObservableField<>();
    public final ObservableField<String> option3 = new ObservableField<>();
    public final ObservableField<String> option4 = new ObservableField<>();
    private final MutableLiveData<Integer> score = new MutableLiveData<>(0);

    public LiveData<Integer> getScore() {
        return score;
    }
    @Inject
    public FillQuestionViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
        this.repository = repository;
    }

    public void start(int unit) {
        fetchAllQuestions(unit);
    }

    public LiveData<FillQuestionResponse> getCurrentQuestion() {
        return currentQuestion;
    }

    public LiveData<Boolean> getAnswerResult() {
        return answerResult;
    }

    private void fetchAllQuestions(int unit) {
        showLoading();
        disposables.add(
                repository.getApiService()
                        .getFillQuestions(unit)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .retryWhen(errors ->
                                errors.flatMap(error -> {
                                    if (NetworkUtils.checkNetworkError(error)) {
                                        hideLoading();
                                        return application.showDialogNoInternetAccess();
                                    } else {
                                        return io.reactivex.rxjava3.core.Observable.error(error);
                                    }
                                })
                        )
                        .subscribe(
                                (ResponseWrapper<List<FillQuestionResponse>> resp) -> {
                                    hideLoading();
                                    questionList = resp.getResult();
                                    if (questionList != null && !questionList.isEmpty()) {
                                        currentIndex = 0;
                                        updateCurrentQuestion(questionList.get(0));
                                    }
                                },
                                throwable -> {
                                    hideLoading();
                                    Timber.e(throwable);
                                }
                        )
        );
    }

    private void updateCurrentQuestion(FillQuestionResponse q) {
        currentQuestion.setValue(q);
        try {
            // Load bitmap từ URL (đơn giản nhất, ví dụ xài Glide's FutureTarget)
            sentence.set(q.getSentence());
            List<String> options = q.getOptions();
            if (options != null && options.size() >= 4) {
                option1.set(options.get(0));
                option2.set(options.get(1));
                option3.set(options.get(2));
                option4.set(options.get(3));
            } else {
                option1.set(null);
                option2.set(null);
                option3.set(null);
                option4.set(null);
            }
        } catch (Exception e) {
            Timber.e(e);
        }
    }


    public void selectAnswer(int index) {
        FillQuestionResponse q = currentQuestion.getValue();
        if (q == null) return;
        boolean correct = q.getCorrectOption().equals(q.getOptions().get(index));
        onAnswerChecked(correct);
    }
    private void onAnswerChecked(boolean isCorrect) {
        answerResult.setValue(isCorrect);
        if (isCorrect) {
            Integer current = score.getValue();
            score.setValue((current != null ? current : 0) + 10);
        }
    }

    public void loadNext() {
        if (questionList == null || questionList.isEmpty()) return;
        currentIndex++;
        if (currentIndex < questionList.size()) {
            updateCurrentQuestion(questionList.get(currentIndex));
        } else {
            // Hết câu hỏi → clear luôn dữ liệu hiển thị (nếu muốn)
            currentQuestion.setValue(null);
            sentence.set(null);
            option1.set(null);
            option2.set(null);
            option3.set(null);
            option4.set(null);
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}

