package com.example.vocabit.ui.exam;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.vocabit.MVVMApplication;
import com.example.vocabit.data.Repository;
import com.example.vocabit.data.model.api.response.exam.ExamResponse;
import com.example.vocabit.data.model.api.response.practice.PracticeResponse;
import com.example.vocabit.ui.base.fragment.BaseFragmentViewModel;
import com.example.vocabit.utils.NetworkUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;

public class ExamViewModel extends BaseFragmentViewModel {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MutableLiveData<List<ExamResponse>> examList = new MutableLiveData<>();
    public LiveData<List<ExamResponse>> getExamList() {
        return examList;
    }



    @Inject
    public ExamViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
        fetchMessageList();
    }

    private void fetchMessageList() {
        showLoading();
        compositeDisposable.add(repository.getApiService().getExams()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(throwable ->
                        throwable.flatMap(throwable1 -> {
                            if (NetworkUtils.checkNetworkError(throwable1)) {
                                hideLoading();
                                return application.showDialogNoInternetAccess();
                            } else {
                                return io.reactivex.rxjava3.core.Observable.error(throwable1);
                            }
                        })
                )
                .subscribe(response -> {
                    hideLoading();
                    examList.setValue(response.getResult());
                }, throwable -> {
                    hideLoading();
                    Timber.e(throwable);
                }));
    }



}
