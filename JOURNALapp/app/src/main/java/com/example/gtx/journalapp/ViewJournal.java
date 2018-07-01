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

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

@SuppressWarnings("ConstantConditions")
public class ViewJournal extends AppCompatActivity {
 private TextView mtvdate;
 private TextView mtitle;
 private TextView mdetail;
 private Button mgohomebotton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_journal);
        mtvdate=findViewById(R.id.tv_datev);
        mtitle=findViewById(R.id.tv_titlev);
        mdetail=findViewById(R.id.tv_detailv);
        mgohomebotton=findViewById(R.id.button_home);



        Intent intentfromrcycler=getIntent();

        if (intentfromrcycler.hasExtra(Intent.EXTRA_TEXT)) {
            getSupportActionBar().setTitle("EDIT YOUR JOURNAL");

            ArrayList<String> rycleredata
                    = intentfromrcycler.getStringArrayListExtra(Intent.EXTRA_TEXT);


            mtvdate.setText(rycleredata.get(0));
            mtitle.setText(rycleredata.get(1));
            mdetail.setText(rycleredata.get(2));

        }





        mgohomebotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(ViewJournal.this,MainActivity.class);

                startActivity(i);
            }
        });

    }

}
