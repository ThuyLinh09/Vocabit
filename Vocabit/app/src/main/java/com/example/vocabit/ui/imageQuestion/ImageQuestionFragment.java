package com.example.vocabit.ui.imageQuestion;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;

import com.example.vocabit.BR;
import com.example.vocabit.R;
import com.example.vocabit.data.model.api.response.imageQuestion.ImageQuestionResponse;
import com.example.vocabit.databinding.FragmentImageQuestionBinding;
import com.example.vocabit.databinding.FragmentMatchQuestionBinding;
import com.example.vocabit.di.component.FragmentComponent;
import com.example.vocabit.ui.base.fragment.BaseFragment;
import com.example.vocabit.ui.matchQuestion.MatchQuestionViewModel;
import com.example.vocabit.ui.practice.ResultPracticeFragment;

public class ImageQuestionFragment extends BaseFragment<FragmentImageQuestionBinding, ImageQuestionViewModel> {


    public static ImageQuestionFragment newInstance(int unit) {
        ImageQuestionFragment fragment = new ImageQuestionFragment();
        Bundle args = new Bundle();
        args.putInt("unit", unit);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_image_question; // Đảm bảo bạn có layout này
    }

    @Override
    protected void performDataBinding() {
        binding.setImageQuestionViewModel(viewModel);
        binding.setVariable(getBindingVariable(), viewModel);
        binding.executePendingBindings();
    }

    @Override
    public int getBindingVariable() {
        return BR.imageQuestionViewModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        int unit = getArguments() != null ? getArguments().getInt("unit", -1) : -1;
        if (unit < 0) {
            Toast.makeText(requireContext(), "Thiếu thông tin unit", Toast.LENGTH_SHORT).show();
            return;
        }

        viewModel.start(unit);

        viewModel.getCurrentQuestion().observe(getViewLifecycleOwner(), new Observer<ImageQuestionResponse>() {
            @Override
            public void onChanged(ImageQuestionResponse question) {
                if (question == null) {
                    Toast.makeText(requireContext(), "Hoàn thành tất cả câu hỏi", Toast.LENGTH_SHORT).show();
                    int score = viewModel.getScore().getValue() != null ? viewModel.getScore().getValue() : 0;
                    requireActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, ResultPracticeFragment.newInstance(score))
                            .addToBackStack(null)
                            .commit();
                    return;
                }
                binding.btnOption1.setText(question.getOptions().get(0));
                binding.btnOption2.setText(question.getOptions().get(1));
                binding.btnOption3.setText(question.getOptions().get(2));
                binding.btnOption4.setText(question.getOptions().get(3));

                setButtonClickListener(binding.btnOption1, 0);
                setButtonClickListener(binding.btnOption2, 1);
                setButtonClickListener(binding.btnOption3, 2);
                setButtonClickListener(binding.btnOption4, 3);
            }
        });
        viewModel.getScore().observe(getViewLifecycleOwner(), score -> {
            binding.tvScore.setText("Điểm: " + score);
        });
        viewModel.getAnswerResult().observe(getViewLifecycleOwner(), correct -> {
            if (correct == null) return;
            String msg = correct ? "Đúng rồi!" : "Sai rồi!";
            Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show();
        });
    }
    private void setButtonClickListener(android.widget.Button button, int index) {
        button.setOnClickListener(v -> {
            // Disable tất cả các button để tránh bấm liên tục
            disableAllButtons();

            viewModel.selectAnswer(index);
            boolean isCorrect = Boolean.TRUE.equals(viewModel.getAnswerResult().getValue());

            if (isCorrect) {
                button.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), android.R.color.holo_green_light));
            } else {
                button.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), android.R.color.holo_red_light));
            }

            // Đợi 1 giây rồi chuyển câu tiếp theo và reset màu
            binding.getRoot().postDelayed(() -> {
                resetButtonStyles();
                viewModel.loadNext();
            }, 1000);
        });
    }
    private void disableAllButtons() {
        binding.btnOption1.setEnabled(false);
        binding.btnOption2.setEnabled(false);
        binding.btnOption3.setEnabled(false);
        binding.btnOption4.setEnabled(false);
    }

    private void resetButtonStyles() {
        binding.btnOption1.setEnabled(true);
        binding.btnOption2.setEnabled(true);
        binding.btnOption3.setEnabled(true);
        binding.btnOption4.setEnabled(true);

        // Reset về màu gốc
        int white = ContextCompat.getColor(requireContext(), android.R.color.white);
        binding.btnOption1.setBackgroundTintList(ColorStateList.valueOf(white));
        binding.btnOption2.setBackgroundTintList(ColorStateList.valueOf(white));
        binding.btnOption3.setBackgroundTintList(ColorStateList.valueOf(white));
        binding.btnOption4.setBackgroundTintList(ColorStateList.valueOf(white));
    }
}
