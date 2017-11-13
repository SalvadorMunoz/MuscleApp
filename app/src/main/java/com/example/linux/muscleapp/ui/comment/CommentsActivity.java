package com.example.linux.muscleapp.ui.comment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.adapters.CommentsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentsActivity extends AppCompatActivity {

    @BindView(R.id.lstComments) ListView listView;
    CommentsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        ButterKnife.bind(this);
        int idSession = getIntent().getIntExtra("idSession",0);
        adapter = new CommentsAdapter(this,idSession);
        listView.setAdapter(adapter);
    }
}
