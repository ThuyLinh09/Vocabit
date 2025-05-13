package com.example.vocabit.ui.register;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.vocabit.MVVMApplication;
import com.example.vocabit.data.Repository;

import lombok.NonNull;

public class RegisterViewModelFactory implements ViewModelProvider.Factory {

    private final Repository repository;
    private final MVVMApplication application;

    public RegisterViewModelFactory(Repository repository, MVVMApplication application) {
        this.repository = repository;
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RegisterViewModel.class)) {
            return (T) new RegisterViewModel(repository, application);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
