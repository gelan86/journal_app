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

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


@SuppressWarnings("ConstantConditions")
public class Writejournal extends AppCompatActivity {

    private String mobjid;
private TextView mdate;
private EditText mtitle;
private EditText mdetail;


private DatabaseReference mdatabasereferance;

   private JournalListViwModel viewmodel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         Button msavebutton;
        Button mdeletebutton;
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_writejournal);


        getSupportActionBar().setTitle("WRITE YOUR JOURNAL ");


       mdate= findViewById(R.id.textviw_date);
       mtitle= findViewById(R.id.edit_title);
       mdetail= findViewById(R.id.multiAutoCompleteTextView2);
       msavebutton= findViewById(R.id.Savebutton);
      // TextView textdate=(TextView)findViewById(R.id.textviw) ;
        mdeletebutton=findViewById(R.id.button_delete);

        mdeletebutton.setVisibility(View.INVISIBLE);


        Button mupdatebutton= findViewById(R.id.updatebutton);
        mupdatebutton.setVisibility(View.INVISIBLE);

        viewmodel= ViewModelProviders.of(this).get(JournalListViwModel.class);

        @SuppressLint("SimpleDateFormat") DateFormat dateFormat =
                                                new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        //noinspection RedundantStringToString
        final String mydate =dateFormat.format(date).toString();





        mdate.setText(mydate);



       Intent intentfromrcycler=getIntent();

       if (intentfromrcycler.hasExtra(Intent.EXTRA_TEXT)){
           getSupportActionBar().setTitle("EDIT YOUR JOURNAL");
           mdeletebutton.setVisibility(View.VISIBLE);
           mupdatebutton.setVisibility(View.VISIBLE);
           ArrayList<String> rycleredata
                   = intentfromrcycler.getStringArrayListExtra(Intent.EXTRA_TEXT);

           msavebutton.setVisibility(View.GONE);
           mdate.setText(rycleredata.get(0));
           mtitle.setText(rycleredata.get(1));
           mdetail.setText(rycleredata.get(2));
              mobjid=rycleredata.get(3);
           mupdatebutton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   String date=mdate.getText().toString();
                   String title=mtitle.getText().toString();
                   String detail=mdetail.getText().toString();
Journal journal=new Journal(mobjid,date,title,detail);
                   mdatabasereferance= FirebaseDatabase.getInstance().getReference().child("Journal");
                   mdatabasereferance.child(mobjid).setValue(journal);

                   Intent i = new Intent(Writejournal.this,MainActivity.class);
                   startActivity(i);

               }
           });


           mdeletebutton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   mdate.setText("");
                   mtitle.setText("");
                   mdetail.setText("");
                   viewmodel.deletejouran(mobjid);
                   Intent i =new Intent(Writejournal.this,MainActivity.class);
                   startActivity(i);

               }
           });



       }


       msavebutton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              // String date= mdate.getText().toString();
               String title= mtitle.getText().toString();
               String detail= mdetail.getText().toString();

               ArrayList<String> journaldata=new ArrayList<>();

               journaldata.add(mydate);
               journaldata.add(title);
               journaldata.add(detail);

               Context context=Writejournal.this;
               Intent senddatatomain= new Intent(context,MainActivity.class);
               senddatatomain.putStringArrayListExtra(Intent.EXTRA_TEXT,journaldata);
               startActivity(senddatatomain);

           }
       });
    }

}


