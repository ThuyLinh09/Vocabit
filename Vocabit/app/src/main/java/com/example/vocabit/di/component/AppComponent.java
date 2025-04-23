package com.example.vocabit.di.component;


import android.app.Application;
import android.content.Context;

import com.example.vocabit.data.Repository;
import com.example.vocabit.data.remote.ApiService;
import com.example.vocabit.di.module.AppModule;
import com.example.vocabit.MVVMApplication;
import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(MVVMApplication app);

    Repository getRepository();

    Context getContext();

    ApiService getApiService();
    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
