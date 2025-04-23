package com.example.vocabit.ui.base.activity;

public interface BaseCallback {
    void doError(Throwable error);
    void doSuccess();
    void doFail();
}
