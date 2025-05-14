package com.example.vocabit.ui.userinfo;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.example.vocabit.MVVMApplication;
import com.example.vocabit.data.Repository;
import com.example.vocabit.data.model.api.response.info.UserResponse;
import com.example.vocabit.data.remote.ApiService;
import com.example.vocabit.utils.JwtUtils;
import com.example.vocabit.utils.SharedPreferencesManager;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UserInfoViewModel extends ViewModel {

    public final ObservableField<String> fullName = new ObservableField<>("");
    public final ObservableField<String> email = new ObservableField<>("");
    public final ObservableField<String> avatarUrl = new ObservableField<>("");
    public final ObservableField<String> classLevel = new ObservableField<>("");

    private final Repository repository;
    private final ApiService apiService;
    private final Context context;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public UserInfoViewModel(Context context) {
        this.context = context.getApplicationContext();
        this.repository = ((MVVMApplication) this.context).getRepository();
        this.apiService = repository.getApiService();

        loadUserInfo();
    }

    private void loadUserInfo() {
        String token = repository.getSharedPreferences().getToken();
        if (token == null) {
            Toast.makeText(context, "Bạn chưa đăng nhập", Toast.LENGTH_SHORT).show();
            return;
        }

        String username = JwtUtils.getUsernameFromToken(token);

        compositeDisposable.add(apiService.getInfoUser(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::bindUserInfo,
                        throwable -> {
                            Log.e("UserInfoViewModel", "Lỗi khi tải thông tin", throwable);
                            Toast.makeText(context, "Lỗi khi tải thông tin", Toast.LENGTH_SHORT).show();
                        }));
    }

    private void bindUserInfo(UserResponse user) {
        fullName.set(user.getName());
        email.set(user.getEmail());
        avatarUrl.set(user.getAvatar());
        classLevel.set(user.getClassLevel().toString());
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}

