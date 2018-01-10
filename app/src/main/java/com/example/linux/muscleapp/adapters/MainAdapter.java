package com.example.linux.muscleapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.data.db.repositories.CommentsRepository;
import com.example.linux.muscleapp.data.db.repositories.UsersRepository;
import com.example.linux.muscleapp.ui.session.fragment.MainListFragment;
import com.example.linux.muscleapp.ui.utils.SessionTmpDates;
import com.pkmmte.view.CircularImageView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * @author Salvador Muñoz
 * @version 2.0
 *
 * This class is an adapter for the recycler view of the main activity
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.SessionHolder>{
    //Sessions repository
    private ArrayList<Session>sessions;
    private User current;
    private MainListFragment.MainListListener callback;
    private static final  int USER_KEY = 0;
    private static final  int POS_KEY = 1;


    //Class listener
    clickItem listener;

    /**
     * Empty constructor add a session from the repository
     */
    public MainAdapter(ArrayList<Session> sessions, User current, MainListFragment.MainListListener callback){
        this.sessions = sessions;
        this.current = current;
        listener = new clickItem();
        this.callback = callback;
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
        View view = inflater.inflate(R.layout.item_mainlist,parent,false);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
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
        holder.result.setText(UsersRepository.getInstance().getNameFronId(sessions.get(position).getUser())+", "+formatDate(sessions.get(position).getCreationDate()));
        holder.image.setImageResource(sessions.get(position).getUrlImage());
        holder.numComments.setOnClickListener(listener);
        holder.numComments.setTag(sessions.get(position));


    }

    private String  formatDate(Date date){
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
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

        public SessionHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.txvSessionName);
            result = (TextView) itemView.findViewById(R.id.txvSessionRes);
            image = (CircularImageView) itemView.findViewById(R.id.civItemSessionImage);
            numComments = (TextView) itemView.findViewById(R.id.txvNumComments);
        }
    }

    class clickItem implements View.OnClickListener{
        @Override
        public void onClick(View view) {


            switch (view.getId()){
                case R.id.txvNumComments:
                   callback.goComments(current,((Session)view.getTag()).getId());
                    break;
            }
            /*if(intent != null)
                view.getContext().startActivity(intent);*/
        }
    }
}
