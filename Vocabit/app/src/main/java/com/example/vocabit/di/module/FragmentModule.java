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
import com.example.vocabit.ui.extraLetter.ExtraLetterQuestionViewModel;
import com.example.vocabit.ui.fillQuestion.FillQuestionViewModel;
import com.example.vocabit.ui.imageQuestion.ImageQuestionViewModel;
import com.example.vocabit.ui.matchQuestion.MatchQuestionViewModel;
import com.example.vocabit.ui.practice.PracticeViewModel;
import com.example.vocabit.ui.practice.ResultPracticeViewModel;
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

    @Provides
    @FragmentScope
    MatchQuestionViewModel provideMatchQuestionViewModel(Repository repository, Context application) {
        Supplier<MatchQuestionViewModel> supplier = () -> new MatchQuestionViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<MatchQuestionViewModel> factory = new ViewModelProviderFactory<>(MatchQuestionViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(MatchQuestionViewModel.class);
    }

    @Provides
    @FragmentScope
    ImageQuestionViewModel provideImageQuestionViewModel(Repository repository, Context application) {
        Supplier<ImageQuestionViewModel> supplier = () -> new ImageQuestionViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<ImageQuestionViewModel> factory = new ViewModelProviderFactory<>(ImageQuestionViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(ImageQuestionViewModel.class);
    }

    @Provides
    @FragmentScope
    FillQuestionViewModel provideFillQuestionViewModel(Repository repository, Context application) {
        Supplier<FillQuestionViewModel> supplier = () -> new FillQuestionViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<FillQuestionViewModel> factory = new ViewModelProviderFactory<>(FillQuestionViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(FillQuestionViewModel.class);
    }

    @Provides
    @FragmentScope
    ExtraLetterQuestionViewModel provideExtraLetterQuestionViewModel(Repository repository, Context application) {
        Supplier<ExtraLetterQuestionViewModel> supplier = () -> new ExtraLetterQuestionViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<ExtraLetterQuestionViewModel> factory = new ViewModelProviderFactory<>(ExtraLetterQuestionViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(ExtraLetterQuestionViewModel.class);
    }

    @Provides
    @FragmentScope
    ResultPracticeViewModel provideResultPracticeViewModel(Repository repository, Context application) {
        Supplier<ResultPracticeViewModel> supplier = () -> new ResultPracticeViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<ResultPracticeViewModel> factory = new ViewModelProviderFactory<>(ResultPracticeViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(ResultPracticeViewModel.class);
    }

}
