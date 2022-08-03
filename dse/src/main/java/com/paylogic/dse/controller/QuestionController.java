package com.paylogic.dse.controller;



import com.paylogic.dse.model.Files;
import com.paylogic.dse.model.Question;
import com.paylogic.dse.service.FilesService;
import com.paylogic.dse.service.QuestionService;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.record.Record;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParser;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping({"/question", "/file"})

public class QuestionController {
    private final QuestionService questionService;
    private final FilesService filesService;

    public QuestionController(QuestionService questionService, FilesService filesService) {
        this.questionService = questionService;
        this.filesService = filesService;
    }

    @GetMapping("/all/files")
    public ResponseEntity<List<Files>> getAllFiles() {
        List<Files> file = filesService.getAllFiles();
        return ResponseEntity.ok(file);
    }



    @GetMapping("/all")
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> question = questionService.getAllQuestions();
        return ResponseEntity.ok(question);
    }




    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("id") Long id ){
        questionService.deleteQuestion(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @PostMapping("/add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        Question newQuestion= questionService.addQuestion (question);
        return new ResponseEntity<>(newQuestion,HttpStatus.CREATED);
    }

    @PostMapping("/getQuestion")
    public ResponseEntity<List<Question>> getQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(questionService.getByQuestion(question));
    }


    @PostMapping ("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws Exception{
        List<Question> questions = new ArrayList<>();
        InputStream inputStream =file.getInputStream();
        String originalName=file.getOriginalFilename();
        CsvParserSettings setting = new CsvParserSettings();
        setting.setHeaderExtractionEnabled(true);
        CsvParser parser = new CsvParser(setting);
        List<Record> parseAllRecords = parser.parseAllRecords(inputStream);
        parseAllRecords.forEach(record -> {
            Question question = new Question();
            question.setId(Long.parseLong(record.getString("id")));
            question.setClient(record.getString("client"));
            question.setQuestion(record.getString("question"));
            question.setResponse(record.getString("response"));
            question.setCategory(record.getString("category"));
            question.setComments(record.getString("comments"));
            questions.add(question);
        });
        questionService.saves(questions);
        return new ResponseEntity<String>(originalName,HttpStatus.OK);

    }

    @PostMapping ("/upload/file")
    public ResponseEntity<String> upl(@RequestParam("file") MultipartFile file) throws Exception{
        List<Files> files = new ArrayList<>();
        InputStream inputStream =file.getInputStream();
        String originalName=file.getOriginalFilename();
        CsvParserSettings setting = new CsvParserSettings();
        setting.setHeaderExtractionEnabled(true);
        CsvParser parser = new CsvParser(setting);
        List<Record> parseAllRecords = parser.parseAllRecords(inputStream);
        parseAllRecords.forEach(record -> {
            Files filess = new Files();
            filess.setId(Long.parseLong(record.getString("id")));
            filess.setClient(record.getString("client"));
            filess.setQuestion(record.getString("question"));
            filess.setResponse(record.getString("response"));
            filess.setCategory(record.getString("category"));
            filess.setComments(record.getString("comments"));
            files.add(filess);
        });
        filesService.saves(files);
        return new ResponseEntity<String>(originalName,HttpStatus.OK);

    }



    @PostMapping("/postCategory")
    public ResponseEntity<List<Question>> getQuestionCategory(@RequestBody Question questionDto) {
        return ResponseEntity.ok(questionService.getByCategory(questionDto));
    }

    @PostMapping("/postClient")
    public ResponseEntity<List<Question>> getQuestionClient(@RequestBody Question questionDto) {
        return ResponseEntity.ok(questionService.getByClient(questionDto));
    }


    @PutMapping("/update")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
        Question updateQuestion = questionService.updateQuestion(question);
        return new ResponseEntity<>(updateQuestion,HttpStatus.OK);
    }

    @PutMapping("/update/file")
    public ResponseEntity<Files> updateQuestion(@RequestBody Files file){
        Files updateFile = filesService.updateFiles(file);
        return new ResponseEntity<>(updateFile,HttpStatus.OK);
    }

}




