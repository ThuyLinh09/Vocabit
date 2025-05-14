package com.example.vocabit.di.component;


import com.example.vocabit.di.module.FragmentModule;
import com.example.vocabit.di.scope.FragmentScope;
import com.example.vocabit.ui.exam.ExamFragment;
import com.example.vocabit.ui.extraLetter.ExtraLetterQuestionFragment;
import com.example.vocabit.ui.fillQuestion.FillQuestionFragment;
import com.example.vocabit.ui.imageQuestion.ImageQuestionFragment;
import com.example.vocabit.ui.matchQuestion.MatchQuestionFragment;
import com.example.vocabit.ui.practice.PracticeFragment;
import com.example.vocabit.ui.practice.ResultPracticeFragment;
import com.example.vocabit.ui.profile.ProfileFragment;

import dagger.Component;

@FragmentScope
@Component(modules = {FragmentModule.class},dependencies = AppComponent.class)
public interface FragmentComponent {
    void inject(PracticeFragment profileFragment);
    void inject(ExamFragment examFragment);
    void inject(ProfileFragment profileFragment);
    void inject(MatchQuestionFragment matchQuestionFragment);
    void inject(ImageQuestionFragment imageQuestionFragment);
    void inject(FillQuestionFragment fillQuestionFragment);
    void inject(ExtraLetterQuestionFragment extraLetterQuestionFragment);
    void inject(ResultPracticeFragment resultPracticeFragment);

}
