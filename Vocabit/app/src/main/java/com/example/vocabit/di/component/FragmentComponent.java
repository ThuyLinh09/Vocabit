package com.example.vocabit.di.component;


import com.example.vocabit.di.module.FragmentModule;
import com.example.vocabit.di.scope.FragmentScope;
import com.example.vocabit.ui.base.fragment.BaseFragment;

import dagger.Component;

@FragmentScope
@Component(modules = {FragmentModule.class},dependencies = AppComponent.class)
public interface FragmentComponent {
//    void inject(ProfileFragment profileFragment);
//    void inject(PhoneFragment phoneFragment);
//    void inject(MessageFragment messageFragment);
}
