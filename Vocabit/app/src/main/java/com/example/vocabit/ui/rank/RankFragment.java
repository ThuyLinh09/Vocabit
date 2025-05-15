package com.example.vocabit.ui.rank;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.vocabit.BR;
import com.example.vocabit.R;
import com.example.vocabit.data.model.api.response.rank.RankResponse;
import com.example.vocabit.databinding.FragmentRankBinding;
import com.example.vocabit.di.component.FragmentComponent;
import com.example.vocabit.ui.base.fragment.BaseFragment;

import java.util.Arrays;
import java.util.List;

public class RankFragment extends BaseFragment<FragmentRankBinding, RankViewModel> {

    private RankAdapter rankAdapter;

    private List<String> unitList = Arrays.asList("Unit 1", "Unit 2", "Unit 3");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Gán adapter cho Spinner Unit
        ArrayAdapter<String> unitAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, unitList);
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerUnit.setAdapter(unitAdapter);


        // Thiết lập listener cho Spinner Unit
        binding.spinnerUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Cập nhật giá trị selectedUnit khi người dùng chọn Unit
                viewModel.selectedUnit = position + 1; // Giả sử giá trị unit là 1, 2, 3...
                fetchRankList(); // Gọi lại hàm lấy bảng xếp hạng
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });


        // Khởi tạo RecyclerView
        rankAdapter = new RankAdapter();
        binding.recyclerViewRank.setAdapter(rankAdapter);
        binding.recyclerViewRank.setLayoutManager(new LinearLayoutManager(getContext()));

        // Observer dữ liệu Rank
        viewModel.getRankList().observe(getViewLifecycleOwner(), new Observer<List<RankResponse>>() {
            @Override
            public void onChanged(List<RankResponse> rankList) {
                if (rankList != null && !rankList.isEmpty()) {
                    rankAdapter.setRankData(rankList);
                }
            }
        });

        return binding.getRoot();
    }

    @Override
    public int getBindingVariable() {
        return BR.rankViewModel;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_rank;
    }

    @Override
    protected void performDataBinding() {
        binding.setRankViewModel(viewModel);
        binding.setVariable(getBindingVariable(), viewModel);
        binding.executePendingBindings();
    }

    @Override
    protected void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }

    private void fetchRankList() {
        // Gọi API hoặc xử lý lấy dữ liệu bảng xếp hạng
        viewModel.fetchRankList();
    }
}
