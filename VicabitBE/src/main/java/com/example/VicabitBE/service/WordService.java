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
        word.setExample(wordRequest.getExample());
        word.setMeaning(wordRequest.getMeaning());
        word.setAudioUrl(wordRequest.getAudioUrl());
        word.setPronunciation(wordRequest.getPronunciation());
        word.setDifficultyLevel(wordRequest.getDifficultyLevel());
        word.setImageUrl(wordRequest.getImageUrl());
        word.setPartOfSpeech(wordRequest.getPartOfSpeech());
        return wordRepository.save(word);
    }
    public List<Word> findAll() {
        return wordRepository.findAll();
    }
}
