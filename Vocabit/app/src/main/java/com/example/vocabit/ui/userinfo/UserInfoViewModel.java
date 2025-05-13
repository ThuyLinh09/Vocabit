package com.example.vocabit.ui.userinfo;

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
import com.example.vocabit.ui.base.activity.BaseViewModel;
import com.example.vocabit.utils.NetworkUtils;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

import retrofit2.HttpException;
import timber.log.Timber;

public class UserInfoViewModel extends BaseViewModel {
    private final Context context;

    public final MutableLiveData<String> fullName = new MutableLiveData<>("");
    public final MutableLiveData<String> email = new MutableLiveData<>("");
    public final MutableLiveData<String> avatarUrl = new MutableLiveData<>("");

    public final ObservableField<Boolean> isFormValid = new ObservableField<>(false);
    public final MutableLiveData<Boolean> navigateToEdit = new MutableLiveData<>(false);

    public LiveData<Boolean> getNavigateToEdit() {
        return navigateToEdit;
    }

    public UserInfoViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
        this.context = application.getApplicationContext();

        MediatorLiveData<Boolean> formValidLiveData = new MediatorLiveData<>();

        Observer<String> formObserver = value -> formValidLiveData.setValue(validateForm());

        formValidLiveData.addSource(fullName, formObserver);
        formValidLiveData.addSource(email, formObserver);
        formValidLiveData.observeForever(isFormValid::set);
    }

    private boolean validateForm() {
        return !TextUtils.isEmpty(fullName.getValue()) && !TextUtils.isEmpty(email.getValue());
    }

    public void onEditInfoClicked() {
        navigateToEdit.setValue(true);
    }

    public void doneNavigating() {
        navigateToEdit.setValue(false);
    }

    public void updateUserInfo(String newName, String newEmail) {
        fullName.setValue(newName);
        email.setValue(newEmail);
    }

    public void updateAvatarUrl(String newAvatarUrl) {
        avatarUrl.setValue(newAvatarUrl);
    }
}
