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
