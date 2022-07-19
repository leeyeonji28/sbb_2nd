package com.mysite.sbb.Question.service;

import com.mysite.sbb.Question.dao.QuestionRepository;
import com.mysite.sbb.Question.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getList(){
        return questionRepository.findAll();
    }
}
