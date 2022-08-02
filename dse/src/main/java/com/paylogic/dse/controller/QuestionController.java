package com.paylogic.dse.controller;



import com.paylogic.dse.model.Question;
import com.paylogic.dse.service.QuestionService;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.record.Record;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParser;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
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
            question.setCompliance(record.getString("compliance"));
            question.setComments(record.getString("comments"));
            questions.add(question);
        });
        questionService.saves(questions);
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




}

