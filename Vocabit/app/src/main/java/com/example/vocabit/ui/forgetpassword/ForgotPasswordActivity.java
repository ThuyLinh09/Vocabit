package com.example.vocabit.ui.forgetpassword;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.databinding.DataBindingUtil;

import com.example.vocabit.R;
import com.example.vocabit.databinding.ActivityForgotPasswordBinding;
import com.example.vocabit.ui.login.LoginActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    private ForgotPasswordViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityForgotPasswordBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password);
        viewModel = new ViewModelProvider(this).get(ForgotPasswordViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        viewModel.getIsEmailValid().observe(this, valid -> {
            // Nếu muốn thay đổi UI khác, có thể xử lý thêm ở đây
        });

        viewModel.email.observe(this, email -> {
            // Có thể hiển thị trạng thái hoặc kiểm tra email hợp lệ theo yêu cầu
        });

        setupNavigation();
    }

    private void setupNavigation() {
        viewModel.getIsEmailValid().observe(this, isValid -> {
            // Tuỳ chọn thêm nếu bạn muốn xử lý thêm khi email hợp lệ
        });

        viewModel.onBackToLoginClicked = () -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        };
    }
}