package com.paylogic.dse.service;

import com.paylogic.dse.model.Files;
import com.paylogic.dse.repository.FilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilesService {
    private final FilesRepository filesRepository;
    private com.paylogic.dse.repository.FilesRepository FilesRepository;

    @Autowired
    public FilesService(com.paylogic.dse.repository.FilesRepository filesRepository) {
        this.filesRepository = filesRepository;
    }

    public Files updateFiles (Files file){
        return filesRepository.save(file);
    }

    public List<Files> getAllFiles(){
        return filesRepository.findAll();
    }

    public Iterable<Files> saves(List<Files> files) {
        return filesRepository.saveAll(files);
    }


}
