package com.example.vocabit.di.component;

import com.example.vocabit.di.module.ActivityModule;
import com.example.vocabit.di.scope.ActivityScope;
import com.example.vocabit.ui.login.LoginActivity;
import com.example.vocabit.ui.main.MainActivity;

import dagger.Component;
@ActivityScope
@Component(modules = {ActivityModule.class}, dependencies = AppComponent.class)
public interface ActivityComponent {
    void inject(LoginActivity loginActivity);
    void inject(MainActivity mainActivity);

}

