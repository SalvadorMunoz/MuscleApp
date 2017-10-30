package com.example.linux.muscleapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.pojo.Session;
import com.example.linux.muscleapp.repositories.SessionsRepository;

import java.util.ArrayList;

/**
 * @author Salvador Muñoz
 * @version 1.0
 *
 * This class is an adapter for the recycler view of the main activity
 */

public class MainAdapter  extends RecyclerView.Adapter<MainAdapter.SessionHolder>{
    //Sessions repository
    private ArrayList<Session>sessions;

    /**
     * Empty constructor add a session from the repository
     */
    public MainAdapter(){
        sessions = SessionsRepository.getInstace().getSessions();
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
        holder.name.setText(sessions.get(position).getName());
        holder.result.setText(sessions.get(position).getCreator()+", "+sessions.get(position).getCreationDate());
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
        private TextView name,result;

        public SessionHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.txvSessionName);
            result = (TextView) itemView.findViewById(R.id.txvSessionRes);
        }
    }
}
