package com.example.linux.muscleapp.ui.comment.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.adapters.CommentsAdapter;
import com.example.linux.muscleapp.data.db.pojo.Commentary;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.ui.comment.contract.CommentsContract;

import java.util.ArrayList;


public class CommentListFragment extends DialogFragment implements CommentsContract.CommentsView {
    public static final String TAG ="comments";
    private EditText edtWrite;
    private FloatingActionButton fbtSend;
    private ListView listView;
    private  CommentsContract.CommentsPresenter presenter;
    private CommentsAdapter adapter;
    private CommentListListener callback;

    private int session ;
    private String username ;

    public interface CommentListListener{
        void reload();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callback = (CommentListListener) activity;
    }

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

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return createDialog();
    }
    private Dialog createDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View root = inflater.inflate(R.layout.fragment_comment_list,null);
        builder.setView(root);

        edtWrite = (EditText) root.findViewById(R.id.edtWriteComment);
        fbtSend = (FloatingActionButton) root.findViewById(R.id.fbtSend);
        listView = (ListView) root.findViewById(android.R.id.list);

        session = getArguments().getInt("session");
        username = ((User)getArguments().getParcelable("current")).getName();

        if(getArguments()!=null){
            adapter = new CommentsAdapter(getContext(),session);
        }
        listView.setAdapter(adapter);
        fbtSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.addComment(session,username,edtWrite.getText().toString());
                edtWrite.setText("");
            }
        });
        presenter.fillComments(session);

        return builder.create();

    }



    @Override
    public void fillComments(ArrayList<Commentary> comments) {
        adapter.clear();
        adapter.addAll(comments);
        listView.setSelection(listView.getAdapter().getCount()-1);

    }



    @Override
    public void setPresenter(CommentsContract.CommentsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

//
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        adapter = null;
        presenter.onDestroy();
        callback.reload();
    }
}
