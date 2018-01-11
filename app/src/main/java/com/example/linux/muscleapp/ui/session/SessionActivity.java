package com.example.linux.muscleapp.ui.session;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.ui.comment.fragment.CommentListFragment;
import com.example.linux.muscleapp.ui.dates.fragment.AddSessionDateFragment;
import com.example.linux.muscleapp.ui.excersice.fragment.AddExcersiceFragment;
import com.example.linux.muscleapp.ui.session.fragment.AddSessionFragment;
import com.example.linux.muscleapp.ui.session.fragment.CheckPassDialog;
import com.example.linux.muscleapp.ui.session.fragment.SeeSessionFragment;
import com.example.linux.muscleapp.ui.session.fragment.SeedatesDialog;
import com.example.linux.muscleapp.ui.utils.GlobalVariables;

public class SessionActivity extends AppCompatActivity implements AddSessionFragment.AddSessionListener, AddExcersiceFragment.AddExcersiceListener,SeeSessionFragment.SeeSessionListener{
    AddSessionFragment addSessionFragment;
    AddSessionDateFragment addSessionDateFragment;
    AddExcersiceFragment addExcersiceFragment;
    SeeSessionFragment seeSessionFragment;
    SeedatesDialog seedatesDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        switch (getIntent().getIntExtra("mode",-1)){
            case GlobalVariables.OPEN_ADD:
                User user = getIntent().getParcelableExtra("user");
                openAddSession(user);
                break;
            case GlobalVariables.OPEN_SEE:
                Session session = getIntent().getParcelableExtra("current");
                openSeeSession(session);
                break;
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
    public void goAddExcersice(User current) {
        addExcersiceFragment = (AddExcersiceFragment) getSupportFragmentManager().findFragmentByTag(AddExcersiceFragment.TAG);
        if(addExcersiceFragment == null){
            Bundle bundle = new Bundle();
            bundle.putParcelable("currentUser", current);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            addExcersiceFragment = AddExcersiceFragment.newInstance(bundle);
            transaction.addToBackStack(null);
            transaction.replace(android.R.id.content,addExcersiceFragment,AddExcersiceFragment.TAG).commit();
        }
    }

    @Override
    public void goMain() {
        finish();
    }

    @Override
    public void goBack() {
        getSupportFragmentManager().popBackStack();
    }

    void openAddSession(User user){
        addSessionFragment = (AddSessionFragment) getSupportFragmentManager().findFragmentByTag(AddSessionFragment.TAG);
        if(addSessionFragment == null){
            Bundle bundle = new Bundle();
            bundle.putParcelable("currentUser",user);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            addSessionFragment = AddSessionFragment.newInstance(bundle);
            transaction.add(android.R.id.content,addSessionFragment,AddSessionFragment.TAG).commit();
        }
    }
    void openSeeSession(Session session){
        seeSessionFragment = (SeeSessionFragment) getSupportFragmentManager().findFragmentByTag(SeeSessionFragment.TAG);
        if(addExcersiceFragment == null){
            Bundle bundle = new Bundle();
            bundle.putParcelable("current", session);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            seeSessionFragment = SeeSessionFragment.getInstance(bundle);
            transaction.replace(android.R.id.content,seeSessionFragment,SeeSessionFragment.TAG).commit();
        }

    }

    @Override
    public void seeDates(int sessionId) {
        seedatesDialog = (SeedatesDialog) getSupportFragmentManager().findFragmentByTag(SeedatesDialog.TAG);
        if(seedatesDialog == null){
            Bundle bundle = new Bundle();
            bundle.putInt("current",sessionId);
            seedatesDialog = SeedatesDialog.getInstance(bundle);
            seedatesDialog.show(getSupportFragmentManager(),SeedatesDialog.TAG);

        }
    }
}
