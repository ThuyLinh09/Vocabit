package com.example.vocabit.ui.forgetpassword;



import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ForgotPasswordViewModel extends ViewModel {

    public MutableLiveData<String> email = new MutableLiveData<>("");

    private final MutableLiveData<Boolean> isEmailValid = new MutableLiveData<>(false);
    public LiveData<Boolean> getIsEmailValid() {
        return isEmailValid;
    }

    public Runnable onBackToLoginClicked;

    public void onSubmitClicked() {
        String currentEmail = email.getValue();
        if (currentEmail != null && android.util.Patterns.EMAIL_ADDRESS.matcher(currentEmail).matches()) {
            // Xử lý gửi email khôi phục mật khẩu
            // ...
        } else {
            isEmailValid.setValue(false);
        }
    }

    public void onBackClicked() {
        if (onBackToLoginClicked != null) {
            onBackToLoginClicked.run();
        }
    }
    public ForgotPasswordViewModel() {
        email.observeForever(input -> {
            if (input != null && android.util.Patterns.EMAIL_ADDRESS.matcher(input).matches()) {
                isEmailValid.setValue(true);
            } else {
                isEmailValid.setValue(false);
            }
        });
    }
}
