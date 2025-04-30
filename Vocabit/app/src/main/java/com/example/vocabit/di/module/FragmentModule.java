package com.example.vocabit.di.module;

import android.content.Context;

import androidx.core.util.Supplier;
import androidx.lifecycle.ViewModelProvider;

import com.example.vocabit.MVVMApplication;
import com.example.vocabit.ViewModelProviderFactory;
import com.example.vocabit.data.Repository;
import com.example.vocabit.di.scope.FragmentScope;
import com.example.vocabit.ui.base.fragment.BaseFragment;
import com.example.vocabit.ui.exam.ExamViewModel;
import com.example.vocabit.ui.practice.PracticeViewModel;
import com.example.vocabit.ui.profile.ProfileViewModel;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;


@Module
public class FragmentModule {

    private BaseFragment<?, ?> fragment;

    public FragmentModule(BaseFragment<?, ?> fragment) {
        this.fragment = fragment;
    }


    @Provides
    @FragmentScope
    BaseFragment<?, ?> provideBaseFragment() {
        return fragment;
    }
    @Named("access_token")
    @Provides
    @FragmentScope
    String provideToken(Repository repository) {
        return repository.getToken();
    }

    @Provides
    @FragmentScope
    ProfileViewModel provideProfileViewModel(Repository repository, Context application) {
        Supplier<ProfileViewModel> supplier = () -> new ProfileViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<ProfileViewModel> factory = new ViewModelProviderFactory<>(ProfileViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(ProfileViewModel.class);
    }
    @Provides
    @FragmentScope
    PracticeViewModel providePracticeViewModel(Repository repository, Context application) {
        Supplier<PracticeViewModel> supplier = () -> new PracticeViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<PracticeViewModel> factory = new ViewModelProviderFactory<>(PracticeViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(PracticeViewModel.class);
    }
    @Provides
    @FragmentScope
    ExamViewModel provideExamViewModel(Repository repository, Context application) {
        Supplier<ExamViewModel> supplier = () -> new ExamViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<ExamViewModel> factory = new ViewModelProviderFactory<>(ExamViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(ExamViewModel.class);
    }
}
