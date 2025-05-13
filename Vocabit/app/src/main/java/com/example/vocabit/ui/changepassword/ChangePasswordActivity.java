package com.example.vocabit.ui.changepassword;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vocabit.R;
import com.example.vocabit.data.Repository;
import com.example.vocabit.data.model.api.request.changepassword.ChangePasswordRequest;
import com.example.vocabit.data.remote.ApiService;
import com.example.vocabit.utils.JwtUtils;
import com.example.vocabit.utils.SharedPreferencesManager;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ChangePasswordActivity extends AppCompatActivity {
    private EditText etNewPassword;
    private Button btnChangePassword;

    private SharedPreferencesManager prefs;
    private ApiService apiService;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        etNewPassword = findViewById(R.id.etNewPassword);
        btnChangePassword = findViewById(R.id.btnChangePassword);

        prefs = new SharedPreferencesManager(this);
        apiService = ((com.example.vocabit.MVVMApplication) getApplication()).getRepository().getApiService();

        btnChangePassword.setOnClickListener(view -> changePassword());
    }

    private void changePassword() {
        String token = prefs.getToken();
        if (token == null) {
            Toast.makeText(this, "Bạn chưa đăng nhập", Toast.LENGTH_SHORT).show();
            return;
        }

        String username = JwtUtils.getUsernameFromToken(token);
        String newPassword = etNewPassword.getText().toString().trim();

        if (newPassword.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập mật khẩu mới", Toast.LENGTH_SHORT).show();
            return;
        }

        ChangePasswordRequest request = new ChangePasswordRequest(username, newPassword);
        compositeDisposable.add(apiService.changePassword(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    Toast.makeText(this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }, throwable -> {
                    Toast.makeText(this, "Lỗi khi đổi mật khẩu", Toast.LENGTH_SHORT).show();
                }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
