package com.example.VicabitBE.controller;

import com.example.VicabitBE.entity.Word;
import com.example.VicabitBE.dto.request.WordRequest;
import com.example.VicabitBE.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WordController {
    @Autowired
    private WordService wordService;

    @PostMapping("/words")
    public Word words(@RequestBody WordRequest word) {
        return wordService.createWord(word);
    }

    @GetMapping("/words")
    public List<Word> words() {
        return wordService.findAll();
    }
}
