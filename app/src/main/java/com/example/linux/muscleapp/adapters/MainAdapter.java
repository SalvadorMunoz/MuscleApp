package com.example.linux.muscleapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.linux.muscleapp.CommentsActivity;
import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.pojo.Session;
import com.example.linux.muscleapp.repositories.CommentsRepository;
import com.example.linux.muscleapp.repositories.SessionsRepository;
import com.pkmmte.view.CircularImageView;

import java.util.ArrayList;


/**
 * @author Salvador Muñoz
 * @version 2.0
 *
 * This class is an adapter for the recycler view of the main activity
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.SessionHolder>{
    //Sessions repository
    private ArrayList<Session>sessions;

    //Class listener
    clickItem listener;

    /**
     * Empty constructor add a session from the repository
     */
    public MainAdapter(){
        sessions = SessionsRepository.getInstace().getSessions();
        listener = new clickItem();
    }

    /**
     * Inflate item layout when holder is created
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public SessionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_mainlist,null);
        return new SessionHolder(view);
    }

    /**
     * Set item content with the array list atributte
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(SessionHolder holder, int position) {
        int id = sessions.get(position).getId();

        holder.name.setText(sessions.get(position).getName());
        holder.result.setText(sessions.get(position).getCreator()+", "+sessions.get(position).getCreationDate());
        holder.image.setImageResource(sessions.get(position).getUrl());
        holder.numComments.setText(String.valueOf(CommentsRepository.getInstace().getSize(id)));
        holder.comments.setOnClickListener(listener);
        holder.comments.setTag(sessions.get(position));

    }

    /**
     * Make the same number of the item that array list
     * @return
     */
    @Override
    public int getItemCount() {
        return sessions.size();
    }

    static class SessionHolder extends RecyclerView.ViewHolder{
        private TextView name,result,numComments;
        private CircularImageView image;
        private ImageView comments;

        public SessionHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.txvSessionName);
            result = (TextView) itemView.findViewById(R.id.txvSessionRes);
            image = (CircularImageView) itemView.findViewById(R.id.civItemSessionImage);
            numComments = (TextView) itemView.findViewById(R.id.txvNumComments);
            comments = (ImageView) itemView.findViewById(R.id.imgItemComments);
        }
    }

    class clickItem implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intent= null;
            int session = ((Session)view.getTag()).getId();

            switch (view.getId()){
                case R.id.imgItemComments:
                    intent = new Intent(view.getContext(), CommentsActivity.class);
                    intent.putExtra("idSession",session);
                    break;
            }
            if(intent != null)
                view.getContext().startActivity(intent);
        }
    }
}
