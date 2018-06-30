package com.example.gtx.journalapp;


import com.google.firebase.database.Exclude;

@SuppressWarnings("unused")
public class Journal {
    @Exclude
private String  id ;
private String title;
private String detail;
private String date;


public  Journal(){}


    public Journal (String id ,String date,String title,String detail ){

  //  super();
        this.id=id ;
         this .date=date;
         this.title=title;
         this.detail=detail;

    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDate(String date){
        this.date=date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
