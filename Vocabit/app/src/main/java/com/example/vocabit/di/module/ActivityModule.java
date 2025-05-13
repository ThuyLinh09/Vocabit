package com.example.vocabit.di.module;

import android.content.Context;

import androidx.core.util.Supplier;
import androidx.lifecycle.ViewModelProvider;

import com.example.vocabit.MVVMApplication;
import com.example.vocabit.ViewModelProviderFactory;
import com.example.vocabit.data.Repository;
import com.example.vocabit.di.scope.ActivityScope;
import com.example.vocabit.ui.base.activity.BaseActivity;
import com.example.vocabit.ui.extraLetter.ExtraLetterQuestionActivity;
import com.example.vocabit.ui.extraLetter.ExtraLetterQuestionViewModel;
import com.example.vocabit.ui.fillQuestion.FillQuestionViewModel;
import com.example.vocabit.ui.imageQuestion.ImageQuestionViewModel;
import com.example.vocabit.ui.login.LoginViewModel;
import com.example.vocabit.ui.main.MainViewModel;
import com.example.vocabit.ui.matchQuestion.MatchQuestionViewModel;
import com.example.vocabit.utils.GetInfo;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private BaseActivity<?, ?> activity;

    public ActivityModule(BaseActivity<?, ?> activity) {
        this.activity = activity;
    }

    @Named("access_token")
    @Provides
    @ActivityScope
    String provideToken(Repository repository){
        return repository.getToken();
    }

    @Named("device_id")
    @Provides
    @ActivityScope
    String provideDeviceId( Context applicationContext){
        return GetInfo.getAll(applicationContext);
    }


    @Provides
    @ActivityScope
    MainViewModel provideMainViewModel(Repository repository, Context application) {
        Supplier<MainViewModel> supplier = () -> new MainViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<MainViewModel> factory = new ViewModelProviderFactory<>(MainViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(MainViewModel.class);
    }

    @Provides
    @ActivityScope
    LoginViewModel provideLoginViewModel(Repository repository, Context application) {
        Supplier<LoginViewModel> supplier = () -> new LoginViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<LoginViewModel> factory = new ViewModelProviderFactory<>(LoginViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(LoginViewModel.class);
    }

    @Provides
    @ActivityScope
    ImageQuestionViewModel provideImageQuestionViewModel(Repository repository, Context application) {
        Supplier<ImageQuestionViewModel> supplier = () -> new ImageQuestionViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<ImageQuestionViewModel> factory = new ViewModelProviderFactory<>(ImageQuestionViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(ImageQuestionViewModel.class);
    }

    @Provides
    @ActivityScope
    FillQuestionViewModel provideFillQuestionViewModel(Repository repository, Context application) {
        Supplier<FillQuestionViewModel> supplier = () -> new FillQuestionViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<FillQuestionViewModel> factory = new ViewModelProviderFactory<>(FillQuestionViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(FillQuestionViewModel.class);
    }

    @Provides
    @ActivityScope
    ExtraLetterQuestionViewModel provideExtraLetterQuestionViewModel(Repository repository, Context application) {
        Supplier<ExtraLetterQuestionViewModel> supplier = () -> new ExtraLetterQuestionViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<ExtraLetterQuestionViewModel> factory = new ViewModelProviderFactory<>(ExtraLetterQuestionViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(ExtraLetterQuestionViewModel.class);
    }

    @Provides
    @ActivityScope
    MatchQuestionViewModel provideMatchQuestionViewModel(Repository repository, Context application) {
        Supplier<MatchQuestionViewModel> supplier = () -> new MatchQuestionViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<MatchQuestionViewModel> factory = new ViewModelProviderFactory<>(MatchQuestionViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(MatchQuestionViewModel.class);
    }

}
