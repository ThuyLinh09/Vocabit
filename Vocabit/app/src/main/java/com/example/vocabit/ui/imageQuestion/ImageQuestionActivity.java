package com.example.vocabit.ui.imageQuestion;


import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import com.example.vocabit.BR;
import com.example.vocabit.R;
import com.example.vocabit.data.model.api.response.imageQuestion.ImageQuestionResponse;
import com.example.vocabit.di.component.ActivityComponent;
import com.example.vocabit.ui.base.activity.BaseActivity;
import com.example.vocabit.ui.matchQuestion.MatchQuestionFragment;

public class ImageQuestionActivity extends AppCompatActivity {
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
            ImageQuestionFragment fragment = ImageQuestionFragment.newInstance(unit);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}