package com.example.gtx.journalapp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



@SuppressWarnings("StatementWithEmptyBody")
public class MainActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN= 1;
 private String mdate;
 private String mtitle;
 private String  mdetail;

 private RecyclerView mrcyclerview;
 private JournalAdapter mjournaladapter;
 private List<Journal> mlistofjournal;

    //firebase variables
   // private DatabaseReference mdatabasereferance;


//firebaeAuth
private FirebaseAuth mfirebaseouth;
private FirebaseAuth.AuthStateListener mfirebaseouthlistener;

   private JournalListViwModel mviewmodel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mlistofjournal=new ArrayList<>();

        //intilize recycler
        mrcyclerview= findViewById(R.id.myrecycler);
        mrcyclerview.setHasFixedSize(true);
        mrcyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mrcyclerview.setItemAnimator(new DefaultItemAnimator());
       mrcyclerview.addItemDecoration(new DividerItemDecoration(getApplicationContext(),LinearLayoutManager.VERTICAL));

// initialize firebase

        //mdatabasereferance=FirebaseDatabase.getInstance().getReference().child("Journal");
        mfirebaseouth=FirebaseAuth.getInstance();




//itializing viewmodel and liedata
        mviewmodel= ViewModelProviders.of(this).get(JournalListViwModel.class);
        final LiveData<List<Journal>>livedata=mviewmodel.getJournalLiveData();



        //preparing and sending data from  UI to be sent to fire base

        Intent journaldataintent=getIntent();
        if (journaldataintent.hasExtra(Intent.EXTRA_TEXT)) {
            ArrayList<String> jdata
                    = journaldataintent.getStringArrayListExtra(Intent.EXTRA_TEXT);
            mdate = jdata.get(0);
            mtitle = jdata.get(1);
            mdetail = jdata.get(2);

            mviewmodel.createAndSendToDataBase(mdate,mtitle,mdetail);

        }

    //livedata observer

        livedata.observe(this, new Observer<List<Journal>>() {
            @Override
            public void onChanged(@Nullable List<Journal> list) {
                mjournaladapter=new JournalAdapter(list);
                mrcyclerview.setAdapter(mjournaladapter);
            }
        });




   // Authenticathing user for google sign in

        mfirebaseouthlistener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user =firebaseAuth.getCurrentUser();
                if(user!=null){







                }
                else {


                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(Arrays.asList(
                                            new AuthUI.IdpConfig.EmailBuilder().build(),
                                            new AuthUI.IdpConfig.GoogleBuilder().build()

                                    ))
                                    .build(),
                            RC_SIGN_IN);

                }


            }
        };

    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= R.id.action_add;
        int logoutid=R.id.action_logout;
Context context=MainActivity.this;
        if(item.getItemId()==id){
            Intent intent= new Intent(context ,Writejournal.class);
            startActivity(intent);
        } else  if (item.getItemId()==logoutid){
AuthUI.getInstance().signOut(this);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== RC_SIGN_IN){
            if(resultCode==RESULT_OK){
                Toast.makeText(this,"your are loged in ",Toast.LENGTH_LONG).show();
            }else if (resultCode==RESULT_CANCELED){
                Toast.makeText(this,"sign in canceled",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mfirebaseouthlistener!=null){
        mfirebaseouth.removeAuthStateListener(mfirebaseouthlistener);}

    }

    @Override
    protected void onResume() {


        super.onResume();
        mfirebaseouth.addAuthStateListener(mfirebaseouthlistener);
    }




}
