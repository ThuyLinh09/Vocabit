package com.example.vocabit.ui.extraLetter;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.Observable;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.vocabit.BR;

import com.example.vocabit.R;
import com.example.vocabit.databinding.ActivityExtraLetterQuestionBinding;
import com.example.vocabit.di.component.ActivityComponent;
import com.example.vocabit.ui.base.activity.BaseActivity;

import java.util.ArrayList;

public class ExtraLetterQuestionActivity extends BaseActivity<ActivityExtraLetterQuestionBinding, ExtraLetterQuestionViewModel> {
    public static final String EXTRA_UNIT = "extra_unit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        viewBinding.setViewModel(viewModel);
        viewBinding.setLifecycleOwner(this);

        int unit = getIntent().getIntExtra(EXTRA_UNIT, -1);
        if (unit < 0) {
            Toast.makeText(this, "Thiếu unit", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        viewModel.start(unit);

        viewModel.getAnswerResult().observe(this, correct -> {
            if (correct == null) return;
            Toast.makeText(this, correct ? "Đúng rồi!" : "Sai rồi!", Toast.LENGTH_SHORT).show();
            viewBinding.getRoot().postDelayed(() -> viewModel.loadNext(), 800);
        });
        LetterAdapter adapter = new LetterAdapter(new ArrayList<>(), index -> viewModel.onLetterClicked(index));
        viewBinding.recyclerLetters.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        viewBinding.recyclerLetters.setAdapter(adapter);

        viewModel.letterList.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                adapter.setLetters(viewModel.letterList.get());
            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_extra_letter_question;
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
