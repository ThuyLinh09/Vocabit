package com.example.vocabit.ui.practice;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.vocabit.BR;
import com.example.vocabit.R;
import com.example.vocabit.databinding.FragmentResultPracticeBinding;
import com.example.vocabit.di.component.FragmentComponent;
import com.example.vocabit.ui.base.fragment.BaseFragment;

public class ResultPracticeFragment extends BaseFragment<FragmentResultPracticeBinding, ResultPracticeViewModel> {

    private static final String ARG_SCORE = "score";

    public static ResultPracticeFragment newInstance(int score) {
        Bundle args = new Bundle();
        args.putInt(ARG_SCORE, score);
        ResultPracticeFragment fragment = new ResultPracticeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_result_practice;
    }

    @Override
    protected void performDataBinding() {
        binding.setViewModel(viewModel);
        binding.setVariable(getBindingVariable(), viewModel);
        binding.executePendingBindings();
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        int score = getArguments() != null ? getArguments().getInt(ARG_SCORE) : 0;
        viewModel.setScore(score);
        binding.btnBack.setOnClickListener(v -> {
            requireActivity().finish();
        });

    }
}
