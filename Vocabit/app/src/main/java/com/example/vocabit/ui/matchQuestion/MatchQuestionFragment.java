package com.example.vocabit.ui.matchQuestion;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.vocabit.BR;
import com.example.vocabit.R;
import com.example.vocabit.databinding.FragmentMatchQuestionBinding;
import com.example.vocabit.di.component.FragmentComponent;
import com.example.vocabit.ui.base.fragment.BaseFragment;
import com.example.vocabit.ui.practice.ResultPracticeFragment;

public class MatchQuestionFragment extends BaseFragment<FragmentMatchQuestionBinding, MatchQuestionViewModel> {

    private WordAdapter englishAdapter;
    private WordAdapter vietnameseAdapter;

    public static MatchQuestionFragment newInstance(int unit, boolean isExamMode) {
        MatchQuestionFragment fragment = new MatchQuestionFragment();
        Bundle args = new Bundle();
        args.putInt("unit", unit);
        args.putBoolean("isExamMode", isExamMode);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_match_question; // Đảm bảo bạn có layout này
    }

    @Override
    protected void performDataBinding() {
        binding.setMatchQuestionViewModel(viewModel);
        binding.setVariable(getBindingVariable(), viewModel);
        binding.executePendingBindings();
    }

    @Override
    public int getBindingVariable() {
        return BR.matchQuestionViewModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        int unit = getArguments() != null ? getArguments().getInt("unit", -1) : -1;
        boolean isExamMode = getArguments() != null && getArguments().getBoolean("isExamMode", false); // Lấy giá trị từ Bundle

        if (unit < 0) {
            Toast.makeText(requireContext(), "Thiếu unit", Toast.LENGTH_SHORT).show();
            return;
        }

        englishAdapter = new WordAdapter(word -> viewModel.onEnglishClicked(word));
        vietnameseAdapter = new WordAdapter(word -> viewModel.onVietnameseClicked(word));

        binding.recyclerEnglish.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerVietnamese.setLayoutManager(new LinearLayoutManager(requireContext()));

        binding.recyclerEnglish.setAdapter(englishAdapter);
        binding.recyclerVietnamese.setAdapter(vietnameseAdapter);

        viewModel.getEnglishList().observe(getViewLifecycleOwner(), englishAdapter::submitList);
        viewModel.getVietnameseList().observe(getViewLifecycleOwner(), vietnameseAdapter::submitList);
        viewModel.getMatchedEnglish().observe(getViewLifecycleOwner(), englishAdapter::setMatchedWords);
        viewModel.getMatchedVietnamese().observe(getViewLifecycleOwner(), vietnameseAdapter::setMatchedWords);
        viewModel.getAllCorrect().observe(getViewLifecycleOwner(), correct -> {
            if (Boolean.TRUE.equals(correct)) {
                int score = viewModel.getScore().getValue() != null ? viewModel.getScore().getValue() : 0;
                if (isExamMode) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("SCORE", score);
                    requireActivity().setResult(RESULT_OK, resultIntent);
                    requireActivity().finish();
                } else {
                    requireActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, ResultPracticeFragment.newInstance(score))
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

        viewModel.getCurrentQuestion().observe(getViewLifecycleOwner(), question -> {
            if (question == null) {
                Toast.makeText(requireContext(), "Không có câu hỏi", Toast.LENGTH_SHORT).show();
                requireActivity().onBackPressed();
            }
        });

        viewModel.getFeedbackEnglish().observe(getViewLifecycleOwner(), pair -> {
            if (pair == null) englishAdapter.clearFeedback();
            else englishAdapter.showFeedback(pair.first, pair.second);
        });

        viewModel.getFeedbackVietnamese().observe(getViewLifecycleOwner(), pair -> {
            if (pair == null) vietnameseAdapter.clearFeedback();
            else vietnameseAdapter.showFeedback(pair.first, pair.second);
        });
        viewModel.getScore().observe(getViewLifecycleOwner(), score -> {
            binding.tvScore.setText("Score: " + score);
        });
        viewModel.start(unit);
    }
}
