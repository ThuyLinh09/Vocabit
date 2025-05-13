package com.example.VicabitBE.service;

import com.example.VicabitBE.entity.Word;
import com.example.VicabitBE.repositories.WordRepository;
import com.example.VicabitBE.dto.request.WordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordService {
    @Autowired
    private WordRepository wordRepository;

    public Word createWord(WordRequest wordRequest) {
        Word word = new Word();
        word.setWord(wordRequest.getWord());
        word.setMeaning(wordRequest.getMeaning());
        word.setImageUrl(wordRequest.getImageUrl());
        return wordRepository.save(word);
    }
    public List<Word> findAll() {
        return wordRepository.findAll();
    }
}
