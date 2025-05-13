package com.example.vocabit.ui.matchQuestion;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.vocabit.BR;
import com.example.vocabit.R;
import com.example.vocabit.databinding.ActivityMatchQuestionBinding;
import com.example.vocabit.di.component.ActivityComponent;
import com.example.vocabit.ui.base.activity.BaseActivity;

public class MatchQuestionActivity extends BaseActivity<ActivityMatchQuestionBinding, MatchQuestionViewModel> {
    public static final String EXTRA_UNIT = "extra_unit";

    private WordAdapter englishAdapter;
    private WordAdapter vietnameseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        viewBinding.setMatchQuestionViewModel(viewModel);

        int unit = getIntent().getIntExtra(EXTRA_UNIT, -1);
        if (unit < 0) {
            Toast.makeText(this, "Thiếu unit", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        englishAdapter = new WordAdapter(word -> viewModel.onEnglishClicked(word));
        vietnameseAdapter = new WordAdapter(word -> viewModel.onVietnameseClicked(word));

        viewBinding.recyclerEnglish.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        viewBinding.recyclerVietnamese.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        viewBinding.recyclerEnglish.setAdapter(englishAdapter);
        viewBinding.recyclerVietnamese.setAdapter(vietnameseAdapter);

        viewModel.getEnglishList().observe(this, englishAdapter::submitList);
        viewModel.getVietnameseList().observe(this, vietnameseAdapter::submitList);

        viewModel.getMatchedEnglish().observe(this, matched -> englishAdapter.setMatchedWords(matched));
        viewModel.getMatchedVietnamese().observe(this, matched -> vietnameseAdapter.setMatchedWords(matched));

        viewModel.getAllCorrect().observe(this, correct -> {
            if (Boolean.TRUE.equals(correct)) {
                viewModel.loadNext();
            }
        });

        viewModel.getFeedbackEnglish().observe(this, pair -> {
            if (pair == null) {
                englishAdapter.clearFeedback();
            } else {
                englishAdapter.showFeedback(pair.first, pair.second);
            }
        });

        viewModel.getFeedbackVietnamese().observe(this, pair -> {
            if (pair == null) {
                vietnameseAdapter.clearFeedback();
            } else {
                vietnameseAdapter.showFeedback(pair.first, pair.second);
            }
        });

        viewModel.start(unit);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_match_question;
    }

    @Override
    public int getBindingVariable() {
        return BR.matchQuestionViewModel;
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }
}