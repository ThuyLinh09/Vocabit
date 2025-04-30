package com.example.vocabit.ui.exam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.vocabit.BR;
import com.example.vocabit.R;
import com.example.vocabit.databinding.FragmentExamBinding;
import com.example.vocabit.di.component.FragmentComponent;
import com.example.vocabit.ui.base.fragment.BaseFragment;

public class ExamFragment extends BaseFragment<FragmentExamBinding, ExamViewModel> {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Thiết lập RecyclerView với PracticeAdapter
        binding.rcvExam.setLayoutManager(new LinearLayoutManager(requireContext()));
        ExamAdapter adapter = new ExamAdapter(requireContext());
        binding.rcvExam.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public int getBindingVariable() {
        return BR.examViewModel;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_exam;
    }

    @Override
    protected void performDataBinding() {
        binding.setExamFragment(this);
        binding.setVariable(getBindingVariable(), viewModel);
        binding.executePendingBindings();
    }

    @Override
    protected void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }
}
