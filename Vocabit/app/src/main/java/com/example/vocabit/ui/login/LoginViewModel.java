package com.example.vocabit.ui.login;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.vocabit.MVVMApplication;
import com.example.vocabit.R;
import com.example.vocabit.data.Repository;
import com.example.vocabit.data.model.api.request.login.LoginRequest;
import com.example.vocabit.data.model.api.request.login.ReLoginRequest;
import com.example.vocabit.ui.base.activity.BaseViewModel;
import com.example.vocabit.utils.NetworkUtils;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

import retrofit2.HttpException;
import timber.log.Timber;

public class LoginViewModel extends BaseViewModel {
    private final Context context;
    public final MutableLiveData<String> phoneNumber = new MutableLiveData<>("");
    public final MutableLiveData<String> password = new MutableLiveData<>("");
    public final ObservableField<Boolean> isFormValid = new ObservableField<>(false);
    public final MutableLiveData<Boolean> loginSuccess = new MutableLiveData<>();
    public LiveData<Boolean> getLoginSuccess() {
        return loginSuccess;
    }

    public LoginViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
        this.context = application.getApplicationContext();

        MediatorLiveData<Boolean> formValidLiveData = new MediatorLiveData<>();

        Observer<String> formObserver = value -> formValidLiveData.setValue(validateForm());

        formValidLiveData.addSource(phoneNumber, formObserver);
        formValidLiveData.addSource(password, formObserver);
        formValidLiveData.observeForever(isFormValid::set);

        checkExistingToken();
    }
    private void checkExistingToken() {
        String token = repository.getSharedPreferences().getToken();
        if (!TextUtils.isEmpty(token)) {
            loginSuccess.setValue(true);
        }
    }
    private boolean validateForm() {
        return !TextUtils.isEmpty(phoneNumber.getValue()) && !TextUtils.isEmpty(password.getValue());
    }

    public void onLoginClicked() {
        String pass = password.getValue();
        String phone = phoneNumber.getValue();
        doLogin(pass, phone);

    }
    private void doLogin(String password, String phoneNumber) {
        LoginRequest request = new LoginRequest(phoneNumber, password);
        compositeDisposable.add(repository.getApiService().login(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(throwable ->
                        throwable.flatMap((Function<Throwable, ObservableSource<?>>) throwable1 -> {
                            if (NetworkUtils.checkNetworkError(throwable1)) {
                                hideLoading();
                                return application.showDialogNoInternetAccess();
                            } else {
                                return io.reactivex.rxjava3.core.Observable.error(throwable1);
                            }
                        })
                )
                .subscribe(
                        response -> {
                            hideLoading();
                            repository.getSharedPreferences().setToken(response.getToken());
                            Toast.makeText(context, context.getString(R.string.login_success), Toast.LENGTH_SHORT).show();
                            loginSuccess.setValue(true);
                        }, throwable -> {
                            hideLoading();
                            Timber.e(throwable);
                            handleLoginError(throwable);
                        }));

    }
    private void handleLoginError(Throwable throwable) {
        if (throwable instanceof HttpException && ((HttpException) throwable).code() == 400) {
            Toast.makeText(context, context.getString(R.string.incorrect_info), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, context.getString(R.string.login_fail), Toast.LENGTH_SHORT).show();
        }
    }
}
