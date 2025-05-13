package com.example.vocabit.ui.profile;

import android.graphics.Bitmap;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.vocabit.MVVMApplication;
import com.example.vocabit.data.Repository;
import com.example.vocabit.ui.base.fragment.BaseFragmentViewModel;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class ProfileViewModel extends BaseFragmentViewModel {
    private CompositeDisposable compositeDisposable;
    public final MutableLiveData<Boolean> clickInfo = new MutableLiveData<>();
    public LiveData<Boolean> toInfo() {
        return clickInfo;
    }
    public final MutableLiveData<Boolean> clickUpdate = new MutableLiveData<>();
    public LiveData<Boolean> toUpdate() {
        return clickUpdate;
    }

    public final MutableLiveData<Boolean> clickLogout = new MutableLiveData<>();
    public LiveData<Boolean> toLogout() {
        return clickLogout;
    }
    public final ObservableField<String> fullname = new ObservableField<>("");
    public final ObservableField<String> avatarUrl = new ObservableField<>("");
    public final ObservableField<String> departmentName = new ObservableField<>("");

    public ObservableField<Bitmap> avatar = new ObservableField<>();
    private String url;

    public ProfileViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);

        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
    }


    public void onLogoutClicked() {
        repository.getSharedPreferences().setToken("");
        clickLogout.setValue(true);
    }


}
