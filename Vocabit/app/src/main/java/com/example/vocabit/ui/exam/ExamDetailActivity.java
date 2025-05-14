package com.example.vocabit.ui.exam;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.vocabit.BR;
import com.example.vocabit.R;
import com.example.vocabit.data.model.api.response.exam.ExamResponse;
import com.example.vocabit.databinding.ActivityExamDetailBinding;
import com.example.vocabit.di.component.ActivityComponent;
import com.example.vocabit.ui.base.activity.BaseActivity;
import com.example.vocabit.ui.extraLetter.ExtraLetterQuestionActivity;
import com.example.vocabit.ui.fillQuestion.FillQuestionActivity;
import com.example.vocabit.ui.imageQuestion.ImageQuestionActivity;
import com.example.vocabit.ui.matchQuestion.MatchQuestionActivity;
import com.google.gson.Gson;

import timber.log.Timber;

public class ExamDetailActivity extends BaseActivity<ActivityExamDetailBinding, ExamDetailViewModel> {

    private boolean isHandlingPart = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding.setViewModel(viewModel);
        viewBinding.setLifecycleOwner(this);

        String examJson = getIntent().getStringExtra("EXTRA_EXAM");
        ExamResponse exam = new Gson().fromJson(examJson, ExamResponse.class);
        if (exam != null) {
            viewModel.setExam(exam);
        } else {
            Timber.e("ExamResponse is null!");
            finish();
            return;
        }

        viewModel.currentPart.observe(this, partType -> {
            if (partType == null || isHandlingPart) return;

            isHandlingPart = true; // Đánh dấu đang xử lý part này
            Intent intent = null;
            int unit = viewModel.getUnit();

            switch (partType) {
                case "IMAGE_TO_TEXT":
                    intent = new Intent(this, ImageQuestionActivity.class);
                    intent.putExtra(ImageQuestionActivity.EXTRA_UNIT, unit);
                    intent.putExtra("IS_EXAM_MODE", true);
                    break;
                case "FILL_IN_BLANK":
                    intent = new Intent(this, FillQuestionActivity.class);
                    intent.putExtra(FillQuestionActivity.EXTRA_UNIT, unit);
                    intent.putExtra("IS_EXAM_MODE", true);
                    break;
                case "EXTRA_LETTER":
                    intent = new Intent(this, ExtraLetterQuestionActivity.class);
                    intent.putExtra(ExtraLetterQuestionActivity.EXTRA_UNIT, unit);
                    intent.putExtra("IS_EXAM_MODE", true);
                    break;
                case "MATCHING":
                    intent = new Intent(this, MatchQuestionActivity.class);
                    intent.putExtra(MatchQuestionActivity.EXTRA_UNIT, unit);
                    intent.putExtra("IS_EXAM_MODE", true);
                    break;
            }

            if (intent != null) {
                startActivityForResult(intent, 100);
            }
        });

        // Khởi động phần đầu tiên (sau khi observer đã sẵn sàng)
        viewModel.startExam();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            isHandlingPart = false; // Đã hoàn thành part, cho phép chạy part tiếp theo
            viewModel.moveToNextPart(); // Cập nhật LiveData để gọi sang phần tiếp theo
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_exam_detail;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }
}

