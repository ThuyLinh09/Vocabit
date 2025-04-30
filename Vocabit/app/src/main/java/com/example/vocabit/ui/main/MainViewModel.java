package com.example.vocabit.ui.main;

import androidx.databinding.ObservableField;

import com.example.vocabit.MVVMApplication;
import com.example.vocabit.R;
import com.example.vocabit.data.Repository;
import com.example.vocabit.ui.base.activity.BaseViewModel;


public class MainViewModel extends BaseViewModel {

    public final ObservableField<Integer> selectedMenuItem = new ObservableField<>(R.id.practice);

    public MainViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }

    public void setSelectedMenuItem(int itemId) {
        selectedMenuItem.set(itemId);
    }

}
