package com.example.vocabit.ui.exam;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.vocabit.MVVMApplication;
import com.example.vocabit.data.Repository;
import com.example.vocabit.ui.base.fragment.BaseFragmentViewModel;

import javax.inject.Inject;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class ExamViewModel extends BaseFragmentViewModel {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MutableLiveData<Integer> currentPage = new MutableLiveData<>(0);
    private final MutableLiveData<Integer> totalPages = new MutableLiveData<>(null);

    public LiveData<Integer> getCurrentPage() {
        return currentPage;
    }

    public LiveData<Integer> getTotalPages() {
        return totalPages;
    }



    @Inject
    public ExamViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
        fetchMessageList(0, 20, null);
    }

    public void fetchMessageList(int page, int size, Runnable onDone) {
        if (page == 0) {
            totalPages.setValue(null);
        }

        Integer totalPagesValue = totalPages.getValue();
        if (totalPagesValue != null && page >= totalPagesValue) {
            if (onDone != null) onDone.run();
            return;
        }

        showLoading();

    }


}
