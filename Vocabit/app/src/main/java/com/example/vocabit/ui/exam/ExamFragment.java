package com.example.vocabit.ui.exam;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.vocabit.BR;
import com.example.vocabit.R;
import com.example.vocabit.data.model.api.response.exam.ExamResponse;
import com.example.vocabit.data.model.api.response.practice.PracticeResponse;
import com.example.vocabit.databinding.FragmentExamBinding;
import com.example.vocabit.di.component.FragmentComponent;
import com.example.vocabit.ui.base.fragment.BaseFragment;
import com.example.vocabit.ui.practice.PracticeAdapter;

import java.util.List;

public class ExamFragment extends BaseFragment<FragmentExamBinding, ExamViewModel> {
    private ExamAdapter adapter;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        context = getContext();
        adapter = new ExamAdapter(context);

        // Thiết lập RecyclerView
        binding.rcvExam.setLayoutManager(new LinearLayoutManager(context));
        binding.rcvExam.setAdapter(adapter);

        // Lắng nghe dữ liệu từ ViewModel
        viewModel.getExamList().observe(getViewLifecycleOwner(), new Observer<List<ExamResponse>>() {
            @Override
            public void onChanged(List<ExamResponse> list) {
                // Cập nhật dữ liệu cho adapter, hiển thị header và buttons
                adapter.updateList(list);
            }
        });

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
