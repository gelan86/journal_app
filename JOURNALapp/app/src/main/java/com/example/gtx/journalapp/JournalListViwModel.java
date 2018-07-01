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

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;




class JournalListViwModel extends ViewModel {


    private static final DatabaseReference dataRef =
            FirebaseDatabase.getInstance().getReference().child("Journal");

    private List<Journal> mList = new ArrayList<>();

    @NonNull
    public LiveData<List<Journal>> getJournalLiveData(){
        FirebaseQueryLiveData mLiveData = new FirebaseQueryLiveData(dataRef);

        //noinspection UnnecessaryLocalVariable
        LiveData<List<Journal>> mJournalLiveData =
                Transformations.map(mLiveData, new Deserializer());

        return mJournalLiveData;
    }

    private class Deserializer implements Function<DataSnapshot, List<Journal>>{

        @Override
        public List<Journal> apply(DataSnapshot dataSnapshot) {
            mList.clear();
            for(DataSnapshot snap : dataSnapshot.getChildren()){
                Journal msg = snap.getValue(Journal.class);
                mList.add(msg);
            }
            return mList;
        }
    }




    public void createAndSendToDataBase(String mdate, String mtitle,String mdetail){



        String id=dataRef.push().getKey();

        Journal journal =new Journal(id,mdate,mtitle,mdetail);
        if (id!=null){
        dataRef.child(id).setValue(journal);}


    }

    public  void deletejouran(String id){
        dataRef.child(id).removeValue();
    }


}


