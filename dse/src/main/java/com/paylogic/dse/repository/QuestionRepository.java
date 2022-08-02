package com.paylogic.dse.repository;

import com.paylogic.dse.model.Question;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuestionRepository extends ElasticsearchRepository<Question, String> {
    List<Question> findAll();
    List<Question> findByQuestionContaining(String question);

    List<Question> findByQuestion(String question);
    List<Question> findByClient(String client);
    List<Question> findByCategory(String category);


    void deleteQuestionById(Long id);


}




















