package com.example.vocabit.ui.practice;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.vocabit.MVVMApplication;
import com.example.vocabit.data.Repository;
import com.example.vocabit.data.model.api.response.practice.PracticeResponse;
import com.example.vocabit.ui.base.fragment.BaseFragmentViewModel;
import com.example.vocabit.utils.NetworkUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;

public class PracticeViewModel extends BaseFragmentViewModel {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MutableLiveData<List<PracticeResponse>> practiceList = new MutableLiveData<>();
    public LiveData<List<PracticeResponse>> getPracticeList() {
        return practiceList;
    }

    @Inject
    public PracticeViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
        fetchPracticeList();
    }

    private void fetchPracticeList() {
        showLoading();
        compositeDisposable.add(repository.getApiService().getPractices()
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
                    practiceList.setValue(response.getResult());
                }, throwable -> {
                    hideLoading();
                    Timber.e(throwable);
                }));
    }


}
