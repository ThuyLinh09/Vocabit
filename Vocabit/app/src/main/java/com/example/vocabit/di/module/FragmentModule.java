package com.example.vocabit.di.module;

import android.content.Context;

import androidx.core.util.Supplier;
import androidx.lifecycle.ViewModelProvider;

import com.example.vocabit.data.Repository;
import com.example.vocabit.di.scope.FragmentScope;
import com.example.vocabit.ui.base.fragment.BaseFragment;

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

//    @Provides
//    @FragmentScope
//    ProfileViewModel provideProfileViewModel(Repository repository, Context application) {
//        Supplier<ProfileViewModel> supplier = () -> new ProfileViewModel(repository, (MVVMApplication)application);
//        ViewModelProviderFactory<ProfileViewModel> factory = new ViewModelProviderFactory<>(ProfileViewModel.class, supplier);
//        return new ViewModelProvider(fragment, factory).get(ProfileViewModel.class);
//    }
//    @Provides
//    @FragmentScope
//    PhoneViewModel providePhoneViewModel(Repository repository, Context application) {
//        Supplier<PhoneViewModel> supplier = () -> new PhoneViewModel(repository, (MVVMApplication)application);
//        ViewModelProviderFactory<PhoneViewModel> factory = new ViewModelProviderFactory<>(PhoneViewModel.class, supplier);
//        return new ViewModelProvider(fragment, factory).get(PhoneViewModel.class);
//    }
//    @Provides
//    @FragmentScope
//    MessageViewModel provideMessageViewModel(Repository repository, Context application) {
//        Supplier<MessageViewModel> supplier = () -> new MessageViewModel(repository, (MVVMApplication)application);
//        ViewModelProviderFactory<MessageViewModel> factory = new ViewModelProviderFactory<>(MessageViewModel.class, supplier);
//        return new ViewModelProvider(fragment, factory).get(MessageViewModel.class);
//    }
}
