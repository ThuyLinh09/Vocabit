package com.example.vocabit.ui.rank;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.vocabit.MVVMApplication;
import com.example.vocabit.data.Repository;
import com.example.vocabit.data.model.api.response.rank.RankResponse;
import com.example.vocabit.ui.base.fragment.BaseFragmentViewModel;
import com.example.vocabit.utils.NetworkUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;

public class RankViewModel extends BaseFragmentViewModel {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final MutableLiveData<List<RankResponse>> rankList = new MutableLiveData<>();
    public LiveData<List<RankResponse>> getRankList() {
        return rankList;
    }

    // Unit và class level được chọn từ người dùng
    public int selectedUnit = 1;
    public long selectedClassLevel = 1;

    @Inject
    public RankViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
        fetchRankList(); // Có thể gọi tự động hoặc sau khi chọn
    }

    public void fetchRankList() {
        showLoading();

        compositeDisposable.add(repository.getApiService().getLeaderboard(selectedUnit)
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
                    rankList.setValue(response.getResult());
                }, throwable -> {
                    hideLoading();
                    Timber.e(throwable);
                }));
    }

    @Override
    public void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}