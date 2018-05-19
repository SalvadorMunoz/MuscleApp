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
import com.example.linux.muscleapp.ui.session.contract.SessionContract;
import com.example.linux.muscleapp.ui.session.fragment.MainListFragment;

import com.example.linux.muscleapp.ui.session.presenter.MainListPresenterImp;
import com.example.linux.muscleapp.ui.utils.GlobalVariables;
import com.example.linux.muscleapp.ui.utils.Sha256Generator;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * @author Salvador Muñoz
 * @version 2.0
 *
 * This class is an adapter for the recycler view of the main activity
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.SessionHolder>{
    //Sessions repository
    private ArrayList<Session>sessions;
    private ArrayList<User> usernames;
    private User current;
    private MainListFragment.MainListListener callback;
    private MainListPresenterImp presenter;


    //Class listener
    clickItem listener;

    /**
     * Empty constructor add a session from the repository
     */
    public MainAdapter(ArrayList<Session> sessions, ArrayList<User>usernames, User current, MainListFragment.MainListListener callback, SessionContract.MainView view){
        this.sessions = sessions;
        this.current = current;
        listener = new clickItem();
        this.callback = callback;
        this.usernames = usernames;
        presenter = new MainListPresenterImp(view);
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

        holder.name.setText(sessions.get(position).getName());
        holder.result.setText(getName(sessions.get(position).getUser())+", "+format(sessions.get(position).getCreationDate()));
        holder.image.setImageResource(R.drawable.no_photo);
        holder.name.setOnClickListener(listener);
        holder.name.setTag(sessions.get(position));
        holder.numComments.setOnClickListener(listener);
        holder.numComments.setTag(sessions.get(position));
        holder.favourite.setTag(sessions.get(position));
        holder.favourite.setOnClickListener(listener);

    }

    private  String getName(int id){
        String res= "";

        for (int i = 0; i< usernames.size();i++){
            if(usernames.get(i).getId() == id)
                res = usernames.get(i).getName();

        }
        return  res;
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
        private ImageView favourite;
        private CircleImageView image;

        public SessionHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.txvSessionName);
            result = (TextView) itemView.findViewById(R.id.txvSessionRes);
            image = (CircleImageView) itemView.findViewById(R.id.civItemSessionImage);
            numComments = (TextView) itemView.findViewById(R.id.txvNumComments);
            favourite = (ImageView) itemView.findViewById(R.id.imgFollow);
        }
    }

    private String format(String date){
        String tmp = date.split(" ")[0];
        String [] tmp1 = tmp.split("-");

        return tmp1[2]+"-"+tmp1[1]+"-"+tmp1[0];
    }

    class clickItem implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            String shaRes = Sha256Generator.bin2hex(Sha256Generator.getHash(""));

            switch (view.getId()){
                case R.id.txvNumComments:
                   callback.goComments(current,((Session)view.getTag()).getId(),usernames);
                    break;
                case R.id.txvSessionName:
                    Session tmp = (Session)view.getTag();
                    if(tmp.getPass().equalsIgnoreCase(shaRes))
                        callback.seeSession(tmp, GlobalVariables.OPEN_SEE);
                    else
                        callback.checkSessionPassword(tmp);
                    break;
                case R.id.imgFollow:
                        Session temp = (Session) view.getTag();
                        presenter.setFavourite(temp.getId(),current.getId());
                    break;
            }
        }
    }
}
