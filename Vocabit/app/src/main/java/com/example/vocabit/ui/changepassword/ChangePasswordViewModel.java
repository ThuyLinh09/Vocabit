package com.example.vocabit.ui.changepassword;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import androidx.databinding.Observable;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.example.vocabit.MVVMApplication;
import com.example.vocabit.data.model.api.request.changepassword.ChangePasswordRequest;
import com.example.vocabit.data.remote.ApiService;
import com.example.vocabit.utils.JwtUtils;
import com.example.vocabit.utils.SharedPreferencesManager;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ChangePasswordViewModel extends ViewModel {
    public final ObservableField<String> newPassword = new ObservableField<>("");
    public final ObservableField<String> confirmPassword = new ObservableField<>("");
    public final ObservableBoolean isFormValid = new ObservableBoolean(false);

    private final SharedPreferencesManager prefs;
    private final ApiService apiService;
    private final Context context;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public ChangePasswordViewModel(Context context) {
        this.context = context;
        prefs = new SharedPreferencesManager(context);
        apiService = ((MVVMApplication) context.getApplicationContext()).getRepository().getApiService();

        Observer observer = new Observer();
        newPassword.addOnPropertyChangedCallback(observer);
        confirmPassword.addOnPropertyChangedCallback(observer);
    }

    private class Observer extends Observable.OnPropertyChangedCallback {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
            String pw = newPassword.get();
            String confirm = confirmPassword.get();
            isFormValid.set(pw != null && !pw.isEmpty() && pw.equals(confirm));
        }
    }

    public void onChangePasswordClicked() {
        String token = prefs.getToken();
        if (token == null) {
            Toast.makeText(context, "Bạn chưa đăng nhập", Toast.LENGTH_SHORT).show();
            return;
        }

        String username = JwtUtils.getUsernameFromToken(token);
        String password = newPassword.get();

        ChangePasswordRequest request = new ChangePasswordRequest(username, password);
        compositeDisposable.add(apiService.changePassword(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    Toast.makeText(context, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    if (context instanceof Activity) ((Activity) context).finish();
                }, throwable -> {
                    Toast.makeText(context, "Lỗi khi đổi mật khẩu", Toast.LENGTH_SHORT).show();
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
