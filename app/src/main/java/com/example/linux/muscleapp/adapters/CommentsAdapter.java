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
import com.example.linux.muscleapp.data.db.repositories.CommentsRepository;

import java.util.ArrayList;

/**
 * @author Salvador Muñoz
 * @version 1.0
 *
 * This class is an adapter for comment list view
 */

public class CommentsAdapter extends ArrayAdapter<Commentary>{

    public CommentsAdapter(@NonNull Context context, int resource, ArrayList<Commentary> comments) {
        super(context, R.layout.item_comments, comments);
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

        holder.user.setText(getItem(position).getUser());
        holder.content.setText(getItem(position).getContent());
        holder.date.setText(getItem(position).getDate());

        return view;
    }



    class CommentaryHolder{
        TextView user, content,date;
    }
}
