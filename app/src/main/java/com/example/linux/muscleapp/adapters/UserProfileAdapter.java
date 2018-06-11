package com.example.linux.muscleapp.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.data.db.pojo.Favourite;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.ui.user.contract.ProfileContract;
import com.example.linux.muscleapp.ui.user.fragment.UserProfileFragment;
import com.example.linux.muscleapp.ui.utils.GlobalVariables;
import com.example.linux.muscleapp.ui.utils.Sha256Generator;

import java.util.ArrayList;

/**
 * Created by linux on 4/06/18.
 */

public class UserProfileAdapter extends ArrayAdapter<Session> {
    private Context context;
    private ClickItem listener;
    private User current;
    private ArrayList<User> usernames;
    private ArrayList<Boolean> favourites;
    private ArrayList<Session> sessionsTmp;
    private ProfileContract.Presenter presenter;
    private UserProfileFragment.SeeDetailsListener callback;


    public UserProfileAdapter(Context context, User current, ArrayList<User> usernames, ArrayList<Session> sessions
                               , ArrayList<Boolean> favourites, ProfileContract.Presenter presenter, UserProfileFragment.SeeDetailsListener callback) {
        super(context, R.layout.item_profile_session,sessions);
        this.context = context;
        listener = new ClickItem();
        this.presenter = presenter;
        this.current = current;
        this.callback = callback;
        this.usernames = usernames;
        this.favourites = favourites;
        sessionsTmp = sessions;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        ProfileHolder holder = new ProfileHolder();
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_profile_session, null);
            holder.sessionName = (TextView) view.findViewById(R.id.txvProfileSessionName);
            holder.commentaries = (TextView) view.findViewById(R.id.txvProfileSessionComments);
            holder.result = (TextView) view.findViewById(R.id.txvProfileSessionRes);
            holder.follow = (ImageView) view.findViewById(R.id.imgProfileSessionFollow);
            holder.delete = (ImageView) view.findViewById(R.id.imgProfileDelete);

            view.setTag(holder);
        }else
            holder = (ProfileHolder) view.getTag();

        holder.id = getItem(position).getId();
        holder.sessionName.setText(getItem(position).getName());
        holder.sessionName.setTag(holder);
        holder.sessionName.setOnClickListener(listener);
        holder.result.setText(format(getItem(position).getCreationDate()));
        setFavouriteImage(holder,position);
        holder.follow.setTag(holder);
        holder.follow.setOnClickListener(listener);
        holder.commentaries.setTag(holder);
        holder.commentaries.setOnClickListener(listener);

        if(GlobalVariables.fromMyProfile) {
            holder.delete.setVisibility(View.VISIBLE);
            holder.delete.setTag(getItem(position));
            holder.delete.setOnClickListener(listener);
        }

        return view;
    }



    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    private String format(String date){
        String tmp = date.split(" ")[0];
        String [] tmp1 = tmp.split("-");

        return tmp1[2]+"-"+tmp1[1]+"-"+tmp1[0];
    }

    private void setFavouriteImage(ProfileHolder holder, int position){
        if(favourites.get(position))
            holder.follow.setImageResource(R.drawable.ic_unfollow);
        else
            holder.follow.setImageResource(R.drawable.ic_follow);
    }


    class ProfileHolder {
        TextView sessionName,result,commentaries;
        ImageView follow,delete;
        int id;
    }


    class ClickItem implements View.OnClickListener{
        private  void openDialog(final int sesion){
            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(getContext().getResources().getString(R.string.remove_session_title)).setMessage(getContext().getResources().getString(R.string.remove_session_message)).
                    setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            presenter.deleteSession(sesion);
                        }
                    }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            builder.create().show();
        }
        @Override
        public void onClick(View view) {
            String shaRes = Sha256Generator.bin2hex(Sha256Generator.getHash(""));



            switch (view.getId()) {
                case R.id.imgProfileSessionFollow:
                    ProfileHolder holder = (ProfileHolder) view.getTag();

                    int pos =-1;
                    for(int i =0; i< sessionsTmp.size();i++){
                        if(sessionsTmp.get(i).getId() == holder.id)
                            pos = i;
                    }
                    favourites.set(pos,!favourites.get(pos));


                    setFavouriteImage(holder,pos);
                    if(favourites.get(pos))
                        presenter.setFavourite(holder.id,current.getId());
                    else
                        presenter.deleteFavourite(holder.id, current.getId());

                    break;
                case R.id.txvProfileSessionComments:
                    ProfileHolder holder1 = (ProfileHolder) view.getTag();

                    callback.goComments(holder1.id, usernames);
                    break;
                case R.id.txvProfileSessionName:
                    ProfileHolder holder2 = (ProfileHolder) view.getTag();

                    String pass ="";
                    for(int i =0; i< sessionsTmp.size();i++){
                        if(sessionsTmp.get(i).getId() == holder2.id){
                            if (sessionsTmp.get(i).getPass().equalsIgnoreCase(shaRes))
                                callback.openSession(sessionsTmp.get(i));
                            else
                                callback.checkSessionPassword(sessionsTmp.get(i));
                        }
                    }
                    break;

                case R.id.imgProfileDelete:
                    Session session = (Session) view.getTag();
                    openDialog(session.getId());
                    break;
            }
        }
    }
}
