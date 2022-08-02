package com.paylogic.dse.service;

import com.paylogic.dse.model.Question;
import com.paylogic.dse.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }



    public Question addQuestion(Question question){
        return questionRepository.save(question);
    }



    public List<Question> getByQuestion(Question questions){
        String question=questions.getQuestion();
        return questionRepository.findByQuestion(question);
    }

    public List<Question> getByQuestionContaining(Question questionDTO){
        String question=questionDTO.getQuestion();
        return questionRepository.findByQuestionContaining(question);
    }
    public List<Question> getByClient(Question questionDTO){
        String question=questionDTO.getClient();
        return questionRepository.findByClient(question);
    }

    public List<Question> getByCategory(Question questionDTO){
        String category=questionDTO.getCategory();
        return questionRepository.findByCategory(category);
    }

    public Question updateQuestion(Question question){
        return questionRepository.save(question);
    }

    public void deleteQuestion(Long id){
        questionRepository.deleteQuestionById(id);
    }

    public List<Question> getAllQuestions(){
        return questionRepository.findAll();
    }




    public Iterable<Question> saves(List<Question> questions) {
        return questionRepository.saveAll(questions);
    }
}





























