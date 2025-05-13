package com.example.vocabit.data.model.api.response.fillQuestion;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FillQuestionResponse {
    String sentence;
    List<String> options;
    String correctOption;
    public int getCorrectIndex() {
        if (options != null) {
            for (int i = 0; i < options.size(); i++) {
                if (options.get(i).equals(correctOption)) {
                    return i;
                }
            }
        }
        return -1;
    }
}
