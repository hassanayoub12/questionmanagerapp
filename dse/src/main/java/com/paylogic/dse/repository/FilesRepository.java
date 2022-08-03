package com.paylogic.dse.repository;

import com.paylogic.dse.model.Files;
import com.paylogic.dse.model.Question;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilesRepository extends ElasticsearchRepository<Files, String> {
    List<Files> findAll();

}
