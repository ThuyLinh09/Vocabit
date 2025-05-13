package com.example.vocabit.ui.register;

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
import com.example.vocabit.data.model.api.request.register.RegisterRequest;
import com.example.vocabit.ui.base.activity.BaseViewModel;
import com.example.vocabit.utils.NetworkUtils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.HttpException;
import timber.log.Timber;

public class RegisterViewModel extends BaseViewModel {

    private final Context context;

    public final MutableLiveData<String> username = new MutableLiveData<>("");
    public final MutableLiveData<String> password = new MutableLiveData<>("");
    public final MutableLiveData<String> name = new MutableLiveData<>("");
    public final MutableLiveData<String> email = new MutableLiveData<>("");
    public final MutableLiveData<String> avatarUrl = new MutableLiveData<>("");
    public final MutableLiveData<String> classLevel = new MutableLiveData<>(""); // Thêm trường classLevel

    public final ObservableField<Boolean> isFormValid = new ObservableField<>(false);
    public final MutableLiveData<Boolean> registerSuccess = new MutableLiveData<>();
    public LiveData<Boolean> getRegisterSuccess() {
        return registerSuccess;
    }

    public final MutableLiveData<Void> chooseAvatarEvent = new MutableLiveData<>();

    public RegisterViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
        this.context = application.getApplicationContext();

        MediatorLiveData<Boolean> formValidLiveData = new MediatorLiveData<>();
        Observer<String> formObserver = value -> formValidLiveData.setValue(validateForm());

        formValidLiveData.addSource(username, formObserver);
        formValidLiveData.addSource(password, formObserver);
        formValidLiveData.addSource(name, formObserver);
        formValidLiveData.addSource(email, formObserver);
        formValidLiveData.addSource(avatarUrl, formObserver);
        formValidLiveData.addSource(classLevel, formObserver); // Thêm classLevel vào formValidLiveData

        formValidLiveData.observeForever(isFormValid::set);
    }

    private boolean validateForm() {
        return !TextUtils.isEmpty(username.getValue()) &&
                !TextUtils.isEmpty(password.getValue()) &&
                !TextUtils.isEmpty(name.getValue()) &&
                !TextUtils.isEmpty(email.getValue()) &&
                !TextUtils.isEmpty(avatarUrl.getValue())&&
                !TextUtils.isEmpty(classLevel.getValue());
    }

    public void onRegisterClicked() {
        if (!validateForm()) {
            Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        RegisterRequest request = new RegisterRequest(
                username.getValue(),
                password.getValue(),
                name.getValue(),
                email.getValue(),
                avatarUrl.getValue(),
                Long.parseLong(classLevel.getValue()) // Chuyển classLevel thành Long
        );

        doRegister(request);
    }

    private void doRegister(RegisterRequest request) {
        showLoading();
        compositeDisposable.add(repository.getApiService().register(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(throwable -> throwable.flatMap((Function<Throwable, ObservableSource<?>>) throwable1 -> {
                    if (NetworkUtils.checkNetworkError(throwable1)) {
                        hideLoading();
                        return application.showDialogNoInternetAccess();
                    } else {
                        return io.reactivex.rxjava3.core.Observable.error(throwable1);
                    }
                }))
                .subscribe(response -> {
                    hideLoading();
                    Toast.makeText(context, context.getString(R.string.register_success), Toast.LENGTH_SHORT).show();
                    registerSuccess.setValue(true);
                }, throwable -> {
                    hideLoading();
                    Timber.e(throwable);
                    handleRegisterError(throwable);
                }));
    }

    private void handleRegisterError(Throwable throwable) {
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            if (httpException.code() == 400) {
                Toast.makeText(context, context.getString(R.string.incorrect_info), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, context.getString(R.string.register_fail), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, context.getString(R.string.register_fail), Toast.LENGTH_SHORT).show();
        }
    }

    public void onChooseAvatarClicked() {
        chooseAvatarEvent.setValue(null);
    }
}
