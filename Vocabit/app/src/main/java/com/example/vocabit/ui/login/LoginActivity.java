package com.example.vocabit.ui.login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;

import com.example.vocabit.BR;
import com.example.vocabit.R;
import com.example.vocabit.databinding.ActivityLoginBinding;
import com.example.vocabit.di.component.ActivityComponent;

import com.example.vocabit.ui.base.activity.BaseActivity;
import com.example.vocabit.ui.main.MainActivity;
import com.example.vocabit.ui.register.RegisterActivity;


public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {
    private static final int REQUEST_PERMISSIONS = 1;
    private static final int REQUEST_READ_SMS = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        viewBinding.setLoginActivity(this);
        viewBinding.setLoginViewModel(viewModel);

        viewModel.loginSuccess.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean success) {
                if (success) {
                    goToMainActivity();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS && (grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED)) {
            finish();
        }
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public int getBindingVariable() {
        return BR.loginViewModel;
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void navigateToRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

}
