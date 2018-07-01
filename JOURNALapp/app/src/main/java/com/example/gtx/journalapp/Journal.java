/*
 * Copyright 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
