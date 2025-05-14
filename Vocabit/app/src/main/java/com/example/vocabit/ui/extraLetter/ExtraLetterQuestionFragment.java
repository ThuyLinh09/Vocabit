package com.example.vocabit.ui.extraLetter;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.Observable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.vocabit.BR;
import com.example.vocabit.R;
import com.example.vocabit.data.model.api.response.fillQuestion.FillQuestionResponse;
import com.example.vocabit.databinding.FragmentExtraLetterQuestionBinding;
import com.example.vocabit.databinding.FragmentFillQuestionBinding;
import com.example.vocabit.di.component.FragmentComponent;
import com.example.vocabit.ui.base.fragment.BaseFragment;
import com.example.vocabit.ui.fillQuestion.FillQuestionViewModel;
import com.example.vocabit.ui.practice.ResultPracticeFragment;

import java.util.ArrayList;

public class ExtraLetterQuestionFragment extends BaseFragment<FragmentExtraLetterQuestionBinding, ExtraLetterQuestionViewModel> {

    private static boolean isExamMode = false;

    public static ExtraLetterQuestionFragment newInstance(int unit, boolean isExamMode) {
        ExtraLetterQuestionFragment fragment = new ExtraLetterQuestionFragment();
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
        return R.layout.fragment_extra_letter_question; // Đảm bảo bạn có layout này
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        int unit = args != null ? args.getInt("unit", -1) : -1;
        isExamMode = args != null && args.getBoolean("isExamMode", false);
        if (unit < 0) {
            Toast.makeText(requireContext(), "Thiếu thông tin unit", Toast.LENGTH_SHORT).show();
            return;
        }

        viewModel.start(unit);


        viewModel.getAnswerResult().observe(getViewLifecycleOwner(), correct -> {
            if (correct == null) return;
            String msg = correct ? "Đúng rồi!" : "Sai rồi!";
            Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show();
            binding.getRoot().postDelayed(() -> viewModel.loadNext(), 800);

        });
        viewModel.getCurrentQuestion().observe(getViewLifecycleOwner(), question -> {
            if (question == null) {
                int score = viewModel.getScore().getValue() != null ? viewModel.getScore().getValue() : 0;

                if (isExamMode) {
                    // ✅ Nếu là thi: quay lại activity để chuyển part
                    requireActivity().setResult(RESULT_OK);
                    requireActivity().finish();
                } else {
                    // ✅ Nếu là luyện tập: hiển thị kết quả như bình thường
                    Toast.makeText(requireContext(), "Hoàn thành tất cả câu hỏi", Toast.LENGTH_SHORT).show();
                    requireActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, ResultPracticeFragment.newInstance(score))
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
        viewModel.getScore().observe(getViewLifecycleOwner(), score -> {
            binding.tvScore.setText("Điểm: " + score);
        });


        LetterAdapter adapter = new LetterAdapter(new ArrayList<>(), index -> viewModel.onLetterClicked(index));
        binding.recyclerLetters.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerLetters.setAdapter(adapter);

        viewModel.letterList.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                adapter.setLetters(viewModel.letterList.get());
            }
        });
    }
}
