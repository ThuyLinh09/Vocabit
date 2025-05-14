package com.example.vocabit.ui.fillQuestion;


import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.example.vocabit.R;


public class FillQuestionActivity extends AppCompatActivity {
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
            FillQuestionFragment fragment = FillQuestionFragment.newInstance(unit, isExamMode);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}