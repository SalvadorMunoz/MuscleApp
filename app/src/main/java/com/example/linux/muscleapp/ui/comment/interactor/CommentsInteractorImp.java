package com.example.linux.muscleapp.ui.comment.interactor;

import android.os.AsyncTask;

import com.example.linux.muscleapp.data.db.pojo.Commentary;
import com.example.linux.muscleapp.data.db.repositories.CommentsRepository;
import com.example.linux.muscleapp.ui.comment.interactor.CommentsInteractor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by linux on 18/11/17.
 */

public class CommentsInteractorImp implements CommentsInteractor {

    private  OnLoadFinish onLoadFinish;
    private Commentary current;

    public CommentsInteractorImp(OnLoadFinish onLoadFinish) {
        this.onLoadFinish = onLoadFinish;
    }

    @Override
    public void fillComments(int resource) {
        new LoadCommentariesTask().execute(resource);
    }

    @Override
    public void addComment(int resource, int user, String message) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        current = new Commentary(1,resource,user,message,dateFormat.format(date),0);
        new InsertCommentTask().execute(resource);

    }
    class LoadCommentariesTask extends AsyncTask<Integer,Void,ArrayList<Commentary>>{
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected ArrayList<Commentary> doInBackground(Integer... integers) {
            return CommentsRepository.getInstace().getComments(integers[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<Commentary> commentaries) {
            onLoadFinish.onLoadFinish(commentaries);
        }
    }
    class  InsertCommentTask extends AsyncTask<Integer,Void,ArrayList<Commentary>>{

        @Override
        protected ArrayList<Commentary> doInBackground(Integer... integers) {
            CommentsRepository.getInstace().add(current);
            return CommentsRepository.getInstace().getComments(integers[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<Commentary> commentaries) {
            super.onPostExecute(commentaries);
            onLoadFinish.onLoadFinish(commentaries);
        }
    }


}
