package com.example.vocabit.ui.exam;


import android.os.CountDownTimer;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.vocabit.MVVMApplication;
import com.example.vocabit.data.Repository;
import com.example.vocabit.data.model.api.request.exam.ExamResultRequest;
import com.example.vocabit.data.model.api.response.exam.ExamResponse;
import com.example.vocabit.data.remote.ApiService;
import com.example.vocabit.ui.base.activity.BaseViewModel;
import com.example.vocabit.utils.JwtUtils;
import com.example.vocabit.utils.NetworkUtils;

import java.util.Arrays;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;

public class ExamDetailViewModel extends BaseViewModel {

    public final MutableLiveData<String> currentPart = new MutableLiveData<>();
    private ExamResponse exam;
    private int currentIndex = 0;
    private List<String> parts;
    private long examStartTime;
    private final MutableLiveData<Integer> totalScore = new MutableLiveData<>();
    private final MutableLiveData<Long> duration = new MutableLiveData<>();
    private CountDownTimer countDownTimer;
    private final MutableLiveData<String> countdownTime = new MutableLiveData<>();
    private final long examDurationInMillis = 30 * 60 * 1000; // 30 phút

    public ExamDetailViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
        totalScore.setValue(0);  // Khởi tạo điểm số
        duration.setValue(0L);    // Khởi tạo thời gian
    }

    public void setExam(ExamResponse exam) {
        this.exam = exam;
        this.parts = Arrays.asList(exam.getPart1(), exam.getPart2(), exam.getPart3(), exam.getPart4());
    }

    public void startExam() {
        if (exam != null && !parts.isEmpty()) {
            currentIndex = 0;
            examStartTime = System.currentTimeMillis(); // Bắt đầu tính giờ
            currentPart.setValue(parts.get(currentIndex));
            startCountdownTimer();
        }
    }
    private void startCountdownTimer() {
        countDownTimer = new CountDownTimer(examDurationInMillis, 1000) {
            public void onTick(long millisUntilFinished) {
                long minutes = (millisUntilFinished / 1000) / 60;
                long seconds = (millisUntilFinished / 1000) % 60;
                countdownTime.postValue(String.format("%02d:%02d", minutes, seconds));
            }

            public void onFinish() {
                countdownTime.postValue("00:00");
                autoSubmitExam(); // Gọi khi hết giờ
            }
        }.start();
    }
    public LiveData<String> getCountdownTime() {
        return countdownTime;
    }
    private void autoSubmitExam() {
        // Tính thời gian làm bài thực tế
        long durationTime = System.currentTimeMillis() - examStartTime;
        duration.setValue(durationTime);
        currentPart.setValue(null); // Kết thúc bài
        submitExamResult(); // Gửi kết quả như thường
    }


    public void addScore(int score) {
        // Cập nhật điểm số
        int currentTotalScore = totalScore.getValue() != null ? totalScore.getValue() : 0;
        totalScore.setValue(currentTotalScore + score); // Cộng điểm vào tổng điểm
    }

    public void moveToNextPart() {
        if (currentIndex < parts.size()) {
            currentIndex++;
            if (currentIndex < parts.size()) {
                currentPart.setValue(parts.get(currentIndex)); // Chuyển đến phần tiếp theo
            } else {
                currentPart.setValue(null); // Kết thúc bài kiểm tra

                // Tính toán thời gian đã qua
                long durationTime = System.currentTimeMillis() - examStartTime;
                duration.setValue(durationTime);
                submitExamResult(); // Gửi kết quả bài thi
            }
        }
    }

    private void submitExamResult() {
        // Tạo đối tượng ExamResultRequest từ thông tin có sẵn
        Repository repository = ((MVVMApplication) getApplication()).getRepository();
        String token = repository.getSharedPreferences().getToken();
        String username = JwtUtils.getUsernameFromToken(token);
        ExamResultRequest resultRequest = new ExamResultRequest();
        resultRequest.setUsername(username);  // Lấy username từ token
        resultRequest.setUnit(exam.getUnit()); // Lấy ID bài kiểm tra
        resultRequest.setScore(totalScore.getValue()); // Điểm bài thi
        resultRequest.setDurationInSeconds(duration.getValue()/1000); // Thời gian làm bài thi

        // Gửi kết quả qua API
        compositeDisposable.add(repository.getApiService().submitExamResult(resultRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(throwable -> throwable.flatMap((Function<Throwable, ObservableSource<?>>) throwable1 -> {
                    if (NetworkUtils.checkNetworkError(throwable1)) {
                        hideLoading();
                        return application.showDialogNoInternetAccess();
                    } else {
                        return io.reactivex.rxjava3.core.Observable.error(throwable1);
                    }
                }))
                .subscribe(
                        response -> {
                            hideLoading();
                            if (response.getCode() == 1000) {
                                // Thông báo kết quả thành công
                                Toast.makeText(application.getApplicationContext(), "Kết quả bài thi đã được gửi!", Toast.LENGTH_SHORT).show();
                            } else {
                                // Thông báo lỗi
                                Toast.makeText(application.getApplicationContext(), "Có lỗi xảy ra khi gửi kết quả!", Toast.LENGTH_SHORT).show();
                            }
                        }, throwable -> {
                            hideLoading();
                            Timber.e(throwable);
                            Toast.makeText(application.getApplicationContext(), "Có lỗi khi gửi kết quả bài thi.", Toast.LENGTH_SHORT).show();
                        }
                ));
    }

    public String getFormattedDuration() {
        long seconds = duration.getValue() != null ? duration.getValue() / 1000 : 0;
        return seconds + " seconds";
    }

    // Getter cho LiveData
    public LiveData<Integer> getTotalScore() {
        return totalScore;
    }

    public LiveData<Long> getDuration() {
        return duration;
    }

    public int getUnit() {
        return exam != null ? exam.getUnit() : 0;
    }
}






