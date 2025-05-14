package com.example.vocabit.ui.practice;

import androidx.databinding.ObservableField;

import com.example.vocabit.MVVMApplication;
import com.example.vocabit.data.Repository;
import com.example.vocabit.ui.base.fragment.BaseFragmentViewModel;

import javax.inject.Inject;

public class ResultPracticeViewModel extends BaseFragmentViewModel {
    public final ObservableField<Integer> score = new ObservableField<>();

    @Inject
    public ResultPracticeViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }

    public void setScore(int s) {
        score.set(s);
    }
}
