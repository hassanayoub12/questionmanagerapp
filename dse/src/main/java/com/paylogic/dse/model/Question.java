package com.paylogic.dse.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.annotation.processing.Generated;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Document(indexName = "tender")
public class Question implements Serializable {
    @Id
    private Long id;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date tenderResponseDate;

    private String client;

//    @JsonFormat(pattern="yyyy-MM-dd")
//    private Date updateDate;

    private String question;

    private String response;

    private String category;

    private String compliance;

    private String comments;

    public Question(){}

    public Question(Long id,String client,String question,String response,String category,String compliance,String comments,Date tenderResponseDate){
        this.id=id;
        this.client=client;
        this.question=question;
        this.response=response;
        this.category=category;
        this.compliance=compliance;
        this.comments=comments;
        this.tenderResponseDate=tenderResponseDate;


    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTenderResponseDate() {
        return tenderResponseDate;
    }

    public void setTenderResponseDate(Date tenderResponseDate) {
        this.tenderResponseDate = tenderResponseDate;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }


//    public Date getUpdateDate() {
//        return updateDate;
//    }


  /* public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }*/

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

    public String getCompliance() {
        return compliance;
    }

    public void setCompliance(String compliance) {
        this.compliance = compliance;
    }

    public String getComments(){return comments;}

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString(){
        return "Question{"+
                "id="+id+
                ",client='"+client+'\''+
                ",question='"+question+'\''+
                ",response="+response+'\''+
                ",category='"+category+'\''+
                ",compliance='"+compliance+'\''+
                ",comments='"+comments+'\''+
                ",tenderResponseDate='"+tenderResponseDate+'\''+

                '}';
    }

}
