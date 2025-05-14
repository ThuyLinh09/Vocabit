package com.example.vocabit.ui.matchQuestion;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vocabit.R;

public class MatchQuestionActivity extends AppCompatActivity {
    public static final String EXTRA_UNIT = "extra_unit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_question); // vẫn giữ layout activity

        int unit = getIntent().getIntExtra(EXTRA_UNIT, -1);
        if (unit < 0) {
            Toast.makeText(this, "Thiếu unit", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        if (savedInstanceState == null) {
            boolean isExamMode = getIntent().getBooleanExtra("IS_EXAM_MODE", false);
            MatchQuestionFragment fragment = MatchQuestionFragment.newInstance(unit, isExamMode);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
