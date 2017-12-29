package com.example.linux.muscleapp.ui.session;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.ui.comment.contract.CommentsContract;
import com.example.linux.muscleapp.ui.comment.fragment.CommentListFragment;
import com.example.linux.muscleapp.ui.comment.presenter.CommentsPresenterImp;
import com.example.linux.muscleapp.ui.session.contract.SessionContract;
import com.example.linux.muscleapp.ui.session.fragment.MainListFragment;
import com.example.linux.muscleapp.ui.session.presenter.MainPresenterImp;

/**
 * @author Salvador Muñoz
 * @version 4.0
 *
 * This class is the main activity has got the sessions list, navigation drawer, add session button...
 */
public class MainActivity extends AppCompatActivity  implements MainListFragment.MainListListener {
    MainListFragment mainListFragment;
    CommentListFragment commentListFragment;
    SessionContract.MainPresenter mainPresenter;
    CommentsContract.CommentsPresenter commentsPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mainListFragment = (MainListFragment) getSupportFragmentManager().findFragmentByTag(MainListFragment.TAG);

        if(mainListFragment == null){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            mainListFragment = MainListFragment.newInstance(null);
            transaction.add(android.R.id.content,mainListFragment,MainListFragment.TAG).commit();
        }
        mainPresenter = new MainPresenterImp(mainListFragment);
        mainListFragment.setPresenter(mainPresenter);

    }

    @Override
    public void goComments(User current, int idSession) {
        commentListFragment = (CommentListFragment) getSupportFragmentManager().findFragmentByTag(CommentListFragment.TAG);
        if(commentListFragment == null){
            Bundle bundle = new Bundle();
            bundle.putParcelable("current",current);
            bundle.putInt("session",idSession);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            commentListFragment = CommentListFragment.newInstance(bundle);
            transaction.addToBackStack(null);
            transaction.replace(android.R.id.content,commentListFragment,CommentListFragment.TAG).commit();
        }
        commentsPresenter = new CommentsPresenterImp(commentListFragment);
        commentListFragment.setPresenter(commentsPresenter);
    }

    @Override
    public void addSession(User current) {
        Intent intent = new Intent(MainActivity.this, SessionActivity.class);
        intent.putExtra("user",current);
        startActivity(intent);
    }
}
