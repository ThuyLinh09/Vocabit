package com.example.vocabit.ui.practice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.vocabit.BR;
import com.example.vocabit.R;
import com.example.vocabit.databinding.FragmentPracticeBinding;
import com.example.vocabit.di.component.FragmentComponent;
import com.example.vocabit.ui.base.fragment.BaseFragment;

public class PracticeFragment extends BaseFragment<FragmentPracticeBinding, PracticeViewModel> {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Thiết lập RecyclerView với PracticeAdapter
        binding.recyclerViewMessages.setLayoutManager(new LinearLayoutManager(requireContext()));
        PracticeAdapter adapter = new PracticeAdapter(requireContext(), 9);
        binding.recyclerViewMessages.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public int getBindingVariable() {
        return BR.practiceViewModel;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_practice;
    }

    @Override
    protected void performDataBinding() {
        binding.setPracticeFragment(this);
        binding.setVariable(getBindingVariable(), viewModel);
        binding.executePendingBindings();
    }

    @Override
    protected void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }
}
