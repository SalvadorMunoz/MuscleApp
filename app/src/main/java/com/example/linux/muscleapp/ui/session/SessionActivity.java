package com.example.linux.muscleapp.ui.session;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.ui.dates.fragment.AddSessionDateFragment;
import com.example.linux.muscleapp.ui.excersice.fragment.AddExcersiceFragment;
import com.example.linux.muscleapp.ui.session.fragment.AddSessionFragment;

public class SessionActivity extends AppCompatActivity implements AddSessionFragment.AddSessionListener, AddExcersiceFragment.AddExcersiceListener{
    AddSessionFragment addSessionFragment;
    AddSessionDateFragment addSessionDateFragment;
    AddExcersiceFragment addExcersiceFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        User user = getIntent().getParcelableExtra("user");

        addSessionFragment = (AddSessionFragment) getSupportFragmentManager().findFragmentByTag(AddSessionFragment.TAG);
        if(addSessionFragment == null){
            Bundle bundle = new Bundle();
            bundle.putParcelable("currentUser",user);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            addSessionFragment = AddSessionFragment.newInstance(bundle);
            transaction.add(android.R.id.content,addSessionFragment,AddSessionFragment.TAG).commit();
        }
    }

    @Override
    public void goAddDate() {
        addSessionDateFragment = (AddSessionDateFragment) getSupportFragmentManager().findFragmentByTag(AddSessionDateFragment.TAG);
        if(addSessionDateFragment == null){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            addSessionDateFragment = AddSessionDateFragment.newInstance(null);
            transaction.addToBackStack(null);
            transaction.replace(android.R.id.content,addSessionDateFragment,AddSessionDateFragment.TAG).commit();
        }

    }

    @Override
    public void goAddExcersice() {
        addExcersiceFragment = (AddExcersiceFragment) getSupportFragmentManager().findFragmentByTag(AddExcersiceFragment.TAG);
        if(addExcersiceFragment == null){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            addExcersiceFragment = AddExcersiceFragment.newInstance(null);
            transaction.addToBackStack(null);
            transaction.replace(android.R.id.content,addExcersiceFragment,AddExcersiceFragment.TAG).commit();
        }
    }

    @Override
    public void goBack() {
        getSupportFragmentManager().popBackStack();
    }
}
