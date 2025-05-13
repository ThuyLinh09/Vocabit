package com.example.vocabit;

import android.app.Application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.cloudinary.android.MediaManager;
import com.example.vocabit.data.Repository;
import com.example.vocabit.di.component.AppComponent;
import com.example.vocabit.di.component.DaggerAppComponent;
import com.example.vocabit.utils.DialogUtils;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import lombok.Getter;
import lombok.Setter;

import timber.log.Timber;

public class MVVMApplication extends Application implements LifecycleObserver {
    @Setter
    private AppCompatActivity currentActivity;

    @Getter
    private AppComponent appComponent;
    private Boolean inBackground;
    @Override
    public void onCreate() {
        super.onCreate();
        initCloudinary();
//        // Enable firebase log
//        FirebaseCrashlytics firebaseCrashlytics = FirebaseCrashlytics.getInstance();
//        firebaseCrashlytics.setCrashlyticsCollectionEnabled(true);


//        if (BuildConfig.DEBUG) {
//            Timber.plant(new MyTimberDebugTree());
//        }else{
//            Timber.plant(new MyTimberReleaseTree(firebaseCrashlytics));
//        }

        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build();
        appComponent.inject(this);

        // Init Toasty
        Toasty.Config.getInstance()
                .allowQueue(false)
                .apply();
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
//        insertMock();
//        startOrderSchedule();
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onAppBackgrounded() {
        //App in background
        Timber.d("APP IN BACKGROUND");
        inBackground = true;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onAppForegrounded() {
        // App in foreground
        Timber.d("APP IN FOREGROUND");
        inBackground = false;
    }


//    public void getUser(){
//        appComponent.getRepository().getRoomService().userDao().loadAll()
//                .subscribeOn(Schedulers.io())
//                .subscribe();
//    }


    public PublishSubject<Integer> showDialogNoInternetAccess(){
        final PublishSubject<Integer> subject = PublishSubject.create();
        currentActivity.runOnUiThread(() ->
                DialogUtils.dialogConfirm(currentActivity, currentActivity.getResources().getString(R.string.newtwork_error),
                        currentActivity.getResources().getString(R.string.newtwork_error_button_retry),
                        (dialogInterface, i) -> subject.onNext(1), currentActivity.getResources().getString(R.string.newtwork_error_button_exit),
                        (dialogInterface, i) -> System.exit(0))
        );
        return subject;
    }

    private void initCloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dxxx4z4nu");      // Thay bằng cloud name của bạn
        config.put("api_key", "662658556213936");   // Thay bằng API Key thật
        config.put("api_secret", "DR0svq4aB7k8CX3GCzQpATeeI1Q"); // Thay bằng API Secret thật
        //config.put("upload_preset", "your_upload_preset"); // Nếu dùng preset

        MediaManager.init(this, config);
    }


    public Repository getRepository() {
        return appComponent.getRepository();
    }

}
