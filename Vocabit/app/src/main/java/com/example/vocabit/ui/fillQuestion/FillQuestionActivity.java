package com.example.vocabit.ui.fillQuestion;


import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import com.example.vocabit.BR;
import com.example.vocabit.R;
import com.example.vocabit.data.model.api.response.fillQuestion.FillQuestionResponse;
import com.example.vocabit.data.model.api.response.imageQuestion.ImageQuestionResponse;
import com.example.vocabit.databinding.ActivityFillQuestionBinding;
import com.example.vocabit.di.component.ActivityComponent;
import com.example.vocabit.ui.base.activity.BaseActivity;

public class FillQuestionActivity extends BaseActivity<ActivityFillQuestionBinding, FillQuestionViewModel> {
    public static final String EXTRA_UNIT = "extra_unit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        viewBinding.setFillQuestionActivity(this);
        viewBinding.setFillQuestionViewModel(viewModel);

        int unit = getIntent().getIntExtra(EXTRA_UNIT, -1);
        if (unit < 0) {
            Toast.makeText(this, "Thiếu thông tin unit", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        viewModel.start(unit);

        viewModel.getCurrentQuestion().observe(this, new Observer<FillQuestionResponse>() {
            @Override
            public void onChanged(FillQuestionResponse question) {
                if (question == null) {
                    Toast.makeText(FillQuestionActivity.this, "Hoàn thành tất cả câu hỏi", Toast.LENGTH_SHORT).show();
                    finish();
                    return;
                }
                viewBinding.btnOption1.setText(question.getOptions().get(0));
                viewBinding.btnOption2.setText(question.getOptions().get(1));
                viewBinding.btnOption3.setText(question.getOptions().get(2));
                viewBinding.btnOption4.setText(question.getOptions().get(3));

                setButtonClickListener(viewBinding.btnOption1, 0);
                setButtonClickListener(viewBinding.btnOption2, 1);
                setButtonClickListener(viewBinding.btnOption3, 2);
                setButtonClickListener(viewBinding.btnOption4, 3);

            }
        });

        viewModel.getAnswerResult().observe(this, correct -> {
            if (correct == null) return;
            String msg = correct ? "Đúng rồi!" : "Sai rồi!";
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        });
    }
    private void setButtonClickListener(android.widget.Button button, int index) {
        button.setOnClickListener(v -> {
            // Disable tất cả các button để tránh bấm liên tục
            disableAllButtons();

            viewModel.selectAnswer(index);
            boolean isCorrect = Boolean.TRUE.equals(viewModel.getAnswerResult().getValue());

            if (isCorrect) {
                button.setBackgroundTintList(ContextCompat.getColorStateList(this, android.R.color.holo_green_light));
            } else {
                button.setBackgroundTintList(ContextCompat.getColorStateList(this, android.R.color.holo_red_light));
            }

            // Đợi 1 giây rồi chuyển câu tiếp theo và reset màu
            viewBinding.getRoot().postDelayed(() -> {
                resetButtonStyles();
                viewModel.loadNext();
            }, 1000);
        });
    }
    private void disableAllButtons() {
        viewBinding.btnOption1.setEnabled(false);
        viewBinding.btnOption2.setEnabled(false);
        viewBinding.btnOption3.setEnabled(false);
        viewBinding.btnOption4.setEnabled(false);
    }

    private void resetButtonStyles() {
        viewBinding.btnOption1.setEnabled(true);
        viewBinding.btnOption2.setEnabled(true);
        viewBinding.btnOption3.setEnabled(true);
        viewBinding.btnOption4.setEnabled(true);

        // Reset về màu gốc
        int white = ContextCompat.getColor(this, android.R.color.white);
        viewBinding.btnOption1.setBackgroundTintList(ColorStateList.valueOf(white));
        viewBinding.btnOption2.setBackgroundTintList(ColorStateList.valueOf(white));
        viewBinding.btnOption3.setBackgroundTintList(ColorStateList.valueOf(white));
        viewBinding.btnOption4.setBackgroundTintList(ColorStateList.valueOf(white));
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_fill_question;
    }

    @Override
    public int getBindingVariable() {
        return BR.fillQuestionViewModel;
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }
}