package com.paylogic.dse.model;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "file")
public class Files {

    private Long id;
    private String client;
    private String question;
    private String response;
    private String category;
    private String comments;

    public Files(){}


    public Files(Long id,String client,String question,String response,String category,String comments){
        this.id=id;
        this.client=client;
        this.question=question;
        this.response=response;
        this.category=category;
        this.comments=comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getComments(){return comments;}

    public void setComments(String comments) {
        this.comments = comments;
    }
}
