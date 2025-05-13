package com.example.vocabit.ui.practice;

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
import com.example.vocabit.data.model.api.response.practice.PracticeResponse;
import com.example.vocabit.databinding.FragmentPracticeBinding;
import com.example.vocabit.di.component.FragmentComponent;
import com.example.vocabit.ui.base.fragment.BaseFragment;

import java.util.List;

public class PracticeFragment extends BaseFragment<FragmentPracticeBinding, PracticeViewModel> {

    private PracticeAdapter adapter;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        context = requireContext();

        // Khởi tạo adapter và truyền context
        adapter = new PracticeAdapter(context);

        // Thiết lập RecyclerView
        binding.recyclerViewMessages.setLayoutManager(new LinearLayoutManager(context));
        binding.recyclerViewMessages.setAdapter(adapter);

        // Lắng nghe dữ liệu từ ViewModel
        viewModel.getPracticeList().observe(getViewLifecycleOwner(), new Observer<List<PracticeResponse>>() {
            @Override
            public void onChanged(List<PracticeResponse> list) {
                // Cập nhật dữ liệu cho adapter, hiển thị header và buttons
                adapter.updateList(list);
            }
        });

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
