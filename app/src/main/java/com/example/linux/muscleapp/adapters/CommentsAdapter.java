package com.example.linux.muscleapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.data.db.pojo.Commentary;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.data.db.repositories.CommentsRepository;

import java.util.ArrayList;

/**
 * @author Salvador Muñoz
 * @version 1.0
 *
 * This class is an adapter for comment list view
 */

public class CommentsAdapter extends ArrayAdapter<Commentary>{
    private ArrayList<User>usernames;

    public CommentsAdapter(@NonNull Context context,ArrayList<User>usernames) {
        super(context, R.layout.item_comments, new ArrayList<Commentary>());
        this.usernames = usernames;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        CommentaryHolder holder = new CommentaryHolder();
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_comments,null);

            holder.user = (TextView) view.findViewById(R.id.txvCommentUser);
            holder.content = (TextView) view.findViewById(R.id.txvCommentContent);
            holder.date = (TextView) view.findViewById(R.id.txvCommentDate);

            view.setTag(holder);
        }else
            holder = (CommentaryHolder) view.getTag();

        holder.user.setText(getName(getItem(position).getUser()));
        holder.content.setText(getItem(position).getContent());
        holder.date.setText(format(getItem(position).getCommentDate()));

        return view;
    }

    private String getName(int user){
        String res="";
        for(int i = 0;i< usernames.size();i++){
            if(usernames.get(i).getId() == user)
                res=usernames.get(i).getName();
        }


        return  res;
    }

    private String format(String date){
        String tmp = date.split(" ")[0];
        String [] tmp1 = tmp.split("-");

        return tmp1[2]+"-"+tmp1[1]+"-"+tmp1[0];
    }

    class CommentaryHolder{
        TextView user, content,date;
    }
}
