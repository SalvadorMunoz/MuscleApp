package com.example.linux.muscleapp.ui.comment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.adapters.CommentsAdapter;
import com.example.linux.muscleapp.data.db.pojo.Commentary;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Salvador Muñoz
 * @version 2.0
 *
 * In this activity you can see the comments and write one
 */

public class CommentsActivity extends AppCompatActivity implements CommentsView {

    @BindView(R.id.lstComments) ListView listView;
    CommentsAdapter adapter;
    ArrayList<Commentary> comments;
    CommentsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        ButterKnife.bind(this);

        int idSession = getIntent().getIntExtra("idSession",0);


        presenter = new CommentsPresenterImp(this);
        presenter.fillComments(idSession);


        adapter = new CommentsAdapter(this,idSession,comments);
        listView.setAdapter(adapter);
    }

    @Override
    public void fillComments(ArrayList<Commentary> comments) {
        this.comments = comments;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
