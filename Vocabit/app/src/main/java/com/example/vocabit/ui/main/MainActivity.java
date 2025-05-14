package com.example.vocabit.ui.main;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import com.example.vocabit.BR;
import androidx.fragment.app.Fragment;
import com.example.vocabit.ui.base.activity.BaseActivity;
import com.example.vocabit.R;
import com.example.vocabit.databinding.ActivityMainBinding;
import com.example.vocabit.di.component.ActivityComponent;
import com.example.vocabit.ui.exam.ExamFragment;
import com.example.vocabit.ui.practice.PracticeFragment;
import com.example.vocabit.ui.profile.ProfileFragment;
import com.example.vocabit.ui.rank.RankFragment;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {
    private Fragment practiceFragment = new PracticeFragment();
    private Fragment examFragment = new ExamFragment();
    private Fragment rankFragment = new RankFragment();
    private Fragment profileFragment = new ProfileFragment();
    private Fragment active;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        viewBinding.setMainActivity(this);
        viewBinding.setMainViewModel(viewModel);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
        active = practiceFragment;
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, practiceFragment, "PRACTICE")
                .commit();
        viewBinding.menu.setOnItemSelectedListener(item -> {
            int menuId = item.getItemId();
            viewModel.setSelectedMenuItem(menuId);

            if (menuId == R.id.exam) {
                if (getSupportFragmentManager().findFragmentByTag("EXAM") == null) {
                    getSupportFragmentManager().beginTransaction()
                            .hide(active)
                            .add(R.id.fragment_container, examFragment, "EXAM")
                            .commit();
                } else {
                    getSupportFragmentManager().beginTransaction()
                            .hide(active)
                            .show(examFragment)
                            .commit();
                }
                active = examFragment;
                return true;

            } else if (menuId == R.id.practice) {
                if (getSupportFragmentManager().findFragmentByTag("PRACTICE") == null) {
                    getSupportFragmentManager().beginTransaction()
                            .hide(active)
                            .add(R.id.fragment_container, practiceFragment, "PRACTICE")
                            .commit();
                } else {
                    getSupportFragmentManager().beginTransaction()
                            .hide(active)
                            .show(practiceFragment)
                            .commit();
                }
                active = practiceFragment;
                return true;

            }else if (menuId == R.id.rank) {
                if (getSupportFragmentManager().findFragmentByTag("RANK") == null) {
                    getSupportFragmentManager().beginTransaction()
                            .hide(active)
                            .add(R.id.fragment_container, rankFragment, "RANK")
                            .commit();
                } else {
                    getSupportFragmentManager().beginTransaction()
                            .hide(active)
                            .show(rankFragment)
                            .commit();
                }
                active = rankFragment;
                return true;

            } else if (menuId == R.id.profile) {
                if (getSupportFragmentManager().findFragmentByTag("PROFILE") == null) {
                    getSupportFragmentManager().beginTransaction()
                            .hide(active)
                            .add(R.id.fragment_container, profileFragment, "PROFILE")
                            .commit();
                } else {
                    getSupportFragmentManager().beginTransaction()
                            .hide(active)
                            .show(profileFragment)
                            .commit();
                }
                active = profileFragment;
                return true;

            } else {
                // default case nếu muốn xử lý gì đó
            }
            return false;
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int getBindingVariable() {
        return BR.mainViewModel;
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }
}