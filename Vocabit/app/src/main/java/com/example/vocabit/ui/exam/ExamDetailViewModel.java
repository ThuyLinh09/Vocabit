package com.example.vocabit.ui.exam;

import androidx.lifecycle.MutableLiveData;

import com.example.vocabit.MVVMApplication;
import com.example.vocabit.data.Repository;
import com.example.vocabit.data.model.api.response.exam.ExamResponse;
import com.example.vocabit.ui.base.activity.BaseViewModel;

import java.util.Arrays;
import java.util.List;

public class ExamDetailViewModel extends BaseViewModel {

    public final MutableLiveData<String> currentPart = new MutableLiveData<>();
    private ExamResponse exam;
    private int currentIndex = 0;
    private List<String> parts;
    private long examStartTime;
    private int totalScore = 0;

    public ExamDetailViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }

    public void setExam(ExamResponse exam) {
        this.exam = exam;
        this.parts = Arrays.asList(exam.getPart1(), exam.getPart2(), exam.getPart3(), exam.getPart4());
    }

    public void startExam() {
        if (exam != null && !parts.isEmpty()) {
            currentIndex = 0;
            examStartTime = System.currentTimeMillis(); // bắt đầu tính giờ
            currentPart.setValue(parts.get(currentIndex));
        }
    }
    public void addScore(int score) {
        totalScore += score;
    }


    public void moveToNextPart() {
        currentIndex++;
        if (currentIndex < parts.size()) {
            currentPart.setValue(parts.get(currentIndex));
        } else {
            currentPart.setValue(null);
            long duration = System.currentTimeMillis() - examStartTime;
            // TODO: chuyển sang màn hình kết quả, truyền totalScore và duration
        }
    }


    public int getUnit() {
        return exam != null ? exam.getUnit() : 0;
    }
}
