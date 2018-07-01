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
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class JournalAdapter extends RecyclerView.Adapter< JournalAdapter.JournalViewHolder>   {
    private static final String LOG_TAG = "GELAN ";

    private String mid;



private List<Journal> mjournallistarry;
  public  JournalAdapter(List<Journal>list){
     this.mjournallistarry=list;

    }



    @NonNull
    @Override
    public JournalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_jouranal,parent,false);
        return new JournalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final JournalAdapter. JournalViewHolder holder, int position) {
      Journal jodata =mjournallistarry.get(position);

           holder.datetv.setText(jodata.getDate());
           holder.titletv.setText(jodata.getTitle());
           holder.detailtv.setText(jodata.getDetail());
        String date=jodata.getDate();
        String title=jodata.getTitle();
        String detail=jodata.getDetail();

        //data tobe update or delete
           mid = jodata.getId();
        Log.d(LOG_TAG, "LOOK here gelan " + mid);
        final ArrayList<String>datatoedt=new ArrayList<>();
        datatoedt.add(date);
        datatoedt.add(title);
        datatoedt.add(detail);
        datatoedt.add(mid);

holder.mitemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {





        Intent i= new Intent(v.getContext(),Writejournal.class);
        i.putStringArrayListExtra(Intent.EXTRA_TEXT,datatoedt);
        v.getContext().startActivity(i);



    }
});
holder.mitemView.setOnLongClickListener(new View.OnLongClickListener() {
    @Override
    public boolean onLongClick(View v) {


        Intent i= new Intent(v.getContext(),ViewJournal.class);
        i.putStringArrayListExtra(Intent.EXTRA_TEXT,datatoedt);
        v.getContext().startActivity(i);


        return true;}
});

    }

    @SuppressWarnings("CanBeFinal")
    class JournalViewHolder extends RecyclerView.ViewHolder  {
                     TextView datetv;
                     TextView titletv;
                     TextView detailtv;
        View mitemView;
        JournalViewHolder(View itemView) {
            super(itemView);
            mitemView=itemView;

             datetv= mitemView.findViewById(R.id.tv_date_card);
             titletv= mitemView.findViewById(R.id.tv_title_cardv);
            detailtv= mitemView.findViewById(R.id.tv_detail_card);

        }





    }

    @Override
    public int getItemCount() {
        return mjournallistarry.size();
    }




}
