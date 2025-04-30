package com.example.vocabit.di.component;


import com.example.vocabit.di.module.FragmentModule;
import com.example.vocabit.di.scope.FragmentScope;
import com.example.vocabit.ui.exam.ExamFragment;
import com.example.vocabit.ui.practice.PracticeFragment;
import com.example.vocabit.ui.profile.ProfileFragment;

import dagger.Component;

@FragmentScope
@Component(modules = {FragmentModule.class},dependencies = AppComponent.class)
public interface FragmentComponent {
    void inject(PracticeFragment profileFragment);
    void inject(ExamFragment examFragment);
    void inject(ProfileFragment profileFragment);

}
