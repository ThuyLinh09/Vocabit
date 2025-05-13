package com.example.vocabit.di.component;

import com.example.vocabit.di.module.ActivityModule;
import com.example.vocabit.di.scope.ActivityScope;
import com.example.vocabit.ui.extraLetter.ExtraLetterQuestionActivity;
import com.example.vocabit.ui.fillQuestion.FillQuestionActivity;
import com.example.vocabit.ui.imageQuestion.ImageQuestionActivity;
import com.example.vocabit.ui.login.LoginActivity;
import com.example.vocabit.ui.main.MainActivity;
import com.example.vocabit.ui.matchQuestion.MatchQuestionActivity;

import dagger.Component;
@ActivityScope
@Component(modules = {ActivityModule.class}, dependencies = AppComponent.class)
public interface ActivityComponent {
    void inject(LoginActivity loginActivity);
    void inject(MainActivity mainActivity);
    void inject(ImageQuestionActivity imageQuestionActivity);
    void inject(FillQuestionActivity fillQuestionActivity);
    void inject(ExtraLetterQuestionActivity extraLetterQuestionActivity);
    void inject(MatchQuestionActivity matchQuestionActivity);

}

