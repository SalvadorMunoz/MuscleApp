package com.example.linux.muscleapp.ui.comment.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.adapters.CommentsAdapter;
import com.example.linux.muscleapp.data.db.pojo.Commentary;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.ui.comment.contract.CommentsContract;

import java.util.ArrayList;


public class CommentListFragment extends ListFragment implements CommentsContract.CommentsView {
    public static final String TAG ="comments";
    EditText edtWrite;
    FloatingActionButton fbtSend;
    private  CommentsContract.CommentsPresenter presenter;
    private CommentsAdapter adapter;

    private int session ;
    private String username ;


    public static CommentListFragment newInstance(Bundle b){
        CommentListFragment commentListFragment = new CommentListFragment();
        if(b != null)
            commentListFragment.setArguments(b);
        return  commentListFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_comment_list,container,false);

        edtWrite = (EditText) root.findViewById(R.id.edtWriteComment);
        fbtSend = (FloatingActionButton) root.findViewById(R.id.fbtSend);

        session = getArguments().getInt("session");
        username = ((User)getArguments().getParcelable("current")).getName();


        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        if(getArguments()!=null){
            adapter = new CommentsAdapter(getContext(),session);
        }
        setListAdapter(adapter);
        fbtSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.addComment(session,username,edtWrite.getText().toString());
            }
        });
        presenter.fillComments(session);

        //Set focus on the last list view item
        getListView().setSelection(getListView().getAdapter().getCount()-1);

    }

    @Override
    public void fillComments(ArrayList<Commentary> comments) {
        adapter.clear();
        adapter.addAll(comments);
    }



    @Override
    public void setPresenter(CommentsContract.CommentsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter = null;
        presenter.onDestroy();
    }
}
