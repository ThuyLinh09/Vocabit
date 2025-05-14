package com.example.vocabit.ui.matchQuestion;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;

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
import com.example.vocabit.data.model.api.response.imageQuestion.ImageQuestionResponse;
import com.example.vocabit.data.model.api.response.matchQuestion.MatchQuestionResponse;
import com.example.vocabit.ui.base.activity.BaseViewModel;
import com.example.vocabit.ui.base.fragment.BaseFragment;
import com.example.vocabit.ui.base.fragment.BaseFragmentViewModel;
import com.example.vocabit.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;

public class MatchQuestionViewModel extends BaseFragmentViewModel {
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<MatchQuestionResponse> currentQuestion = new MutableLiveData<>();
    private final MutableLiveData<List<String>> englishList = new MutableLiveData<>();
    private final MutableLiveData<List<String>> vietnameseList = new MutableLiveData<>();
    private final MutableLiveData<Set<String>> matchedEnglish = new MutableLiveData<>(new HashSet<>());
    private final MutableLiveData<Set<String>> matchedVietnamese = new MutableLiveData<>(new HashSet<>());
    private final MutableLiveData<Boolean> allCorrect = new MutableLiveData<>();
    private final MutableLiveData<Pair<String, Boolean>> feedbackEnglish = new MutableLiveData<>();
    private final MutableLiveData<Pair<String, Boolean>> feedbackVietnamese = new MutableLiveData<>();

    public LiveData<Pair<String, Boolean>> getFeedbackEnglish() {
        return feedbackEnglish;
    }
    public LiveData<Pair<String, Boolean>> getFeedbackVietnamese() {
        return feedbackVietnamese;
    }
    private List<MatchQuestionResponse> questionList;
    public LiveData<MatchQuestionResponse> getCurrentQuestion() {
        return currentQuestion;
    }

    private int currentIndex = 0;
    private String selectedEnglish = null;
    private String selectedVietnamese = null;

    @Inject
    public MatchQuestionViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }

    public LiveData<List<String>> getEnglishList() {
        return englishList;
    }

    public LiveData<List<String>> getVietnameseList() {
        return vietnameseList;
    }

    public LiveData<Set<String>> getMatchedEnglish() {
        return matchedEnglish;
    }

    public LiveData<Set<String>> getMatchedVietnamese() {
        return matchedVietnamese;
    }

    public LiveData<Boolean> getAllCorrect() {
        return allCorrect;
    }
    private final MutableLiveData<Integer> score = new MutableLiveData<>(0);

    public LiveData<Integer> getScore() {
        return score;
    }

    public void start(int unit) {
        fetchQuestions(unit);
    }


    private void fetchQuestions(int unit) {
        showLoading();
        disposables.add(repository.getApiService().getMatchQuestions(unit)
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
                .subscribe(response -> {
                    hideLoading();
                    questionList = response.getResult();
                    currentIndex = 0;
                    loadQuestion();
                }, error -> {
                    hideLoading();
                    Timber.e(error);
                }));
    }

    private void loadQuestion() {
        if (currentIndex >= questionList.size()) {
            currentQuestion.setValue(null);
            return;
        }

        MatchQuestionResponse q = questionList.get(currentIndex);
        currentQuestion.setValue(q);
        englishList.setValue(new ArrayList<>(q.getChoices_en()));
        vietnameseList.setValue(new ArrayList<>(q.getChoices_vn()));
        matchedEnglish.setValue(new HashSet<>());
        matchedVietnamese.setValue(new HashSet<>());
        allCorrect.setValue(false);
        selectedEnglish = null;
        selectedVietnamese = null;
    }

    public void onEnglishClicked(String word) {
        if (matchedEnglish.getValue().contains(word)) return;
        selectedEnglish = word;
        checkPair();
    }

    public void onVietnameseClicked(String word) {
        if (matchedVietnamese.getValue().contains(word)) return;
        selectedVietnamese = word;
        checkPair();
    }

    private void checkPair() {
        if (selectedEnglish != null && selectedVietnamese != null) {
            MatchQuestionResponse q = currentQuestion.getValue();
            if (q == null) return;

            Map<String, String> map = q.getCorrectMatches();
            boolean isCorrect = map.get(selectedEnglish).equals(selectedVietnamese);

            feedbackEnglish.setValue(new Pair<>(selectedEnglish, isCorrect));
            feedbackVietnamese.setValue(new Pair<>(selectedVietnamese, isCorrect));

            if (isCorrect) {
                matchedEnglish.getValue().add(selectedEnglish);
                matchedVietnamese.getValue().add(selectedVietnamese);
                matchedEnglish.postValue(matchedEnglish.getValue());
                matchedVietnamese.postValue(matchedVietnamese.getValue());
                score.setValue(score.getValue() + 10);
                if (matchedEnglish.getValue().size() == englishList.getValue().size()) {
                    allCorrect.postValue(true);
                }
            }

            // Reset sau 500ms
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                feedbackEnglish.setValue(null);
                feedbackVietnamese.setValue(null);
                selectedEnglish = null;
                selectedVietnamese = null;
            }, 200);
        }
    }


    public void loadNext() {
        currentIndex++;
        if (currentIndex >= questionList.size()) {
            currentQuestion.setValue(null); // Đã hết câu hỏi
        } else {
            loadQuestion(); // Load câu hỏi tiếp theo
        }
    }


    @Override
    protected void onCleared() {
        disposables.clear();
        super.onCleared();
    }
}

