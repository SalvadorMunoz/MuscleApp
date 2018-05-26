package com.example.linux.muscleapp.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.ui.favourite.fragment.FavouritesFragment;
import com.example.linux.muscleapp.ui.session.contract.SessionContract;
import com.example.linux.muscleapp.ui.today.contract.TodaySessionContract;
import com.example.linux.muscleapp.ui.utils.Sha256Generator;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by linux on 26/05/18.
 */

public class SessionListAdapter extends ArrayAdapter<Session> {
private Context context;
private ClickItem listener;
private TodaySessionContract.Presenter presenter;
private User current;
private FavouritesFragment.SeeDetailsListener callback;
private ArrayList<User> usernames;
private ArrayList<Boolean> favourites;
private ArrayList<SessionListHolder> holders;



        //
public SessionListAdapter( Context context, User current, ArrayList<User> usernames, ArrayList<Session> sessions,TodaySessionContract.Presenter presenter,
                           FavouritesFragment.SeeDetailsListener callback, ArrayList<Boolean> favourites) {
        super(context, R.layout.item_mainlist,sessions);
        this.context = context;
        listener = new ClickItem();
        this.presenter = presenter;
        this.current = current;
        this.callback = callback;
        this.usernames = usernames;
        this.favourites = favourites;
        holders = new ArrayList<>();
        }

@Override
public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        SessionListHolder holder = new SessionListHolder();
        if (view == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.item_mainlist, null);
                holder.sessionName = (TextView) view.findViewById(R.id.txvSessionName);
                holder.commentaries = (TextView) view.findViewById(R.id.txvNumComments);
                holder.result = (TextView) view.findViewById(R.id.txvSessionRes);
                holder.userImg = (CircleImageView) view.findViewById(R.id.civItemSessionImage);
                holder.follow = (ImageView) view.findViewById(R.id.imgFollow);

                view.setTag(holder);
        }else
                holder = (SessionListHolder) view.getTag();

        holders.add(holder);
        holder.sessionName.setText(getItem(position).getName());
        holder.sessionName.setTag(getItem(position));
        holder.sessionName.setOnClickListener(listener);
        holder.result.setText(getItem(position).getUser()+", "+format(getItem(position).getCreationDate()));
        setFavouriteImage(holder,position);
        holder.follow.setTag(getItem(position));
        holder.follow.setOnClickListener(listener);
        holder.commentaries.setTag(getItem(position));
        holder.commentaries.setOnClickListener(listener);
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

private void setFavouriteImage(SessionListHolder holder, int position){
        if(favourites.get(position))
                holder.follow.setImageResource(R.drawable.ic_unfollow);
        else
                holder.follow.setImageResource(R.drawable.ic_follow);
        }


class SessionListHolder {
    TextView sessionName,result,commentaries;
    ImageView follow;
    CircleImageView userImg;
}


class ClickItem implements View.OnClickListener{

    @Override
    public void onClick(View view) {
            String shaRes = Sha256Generator.bin2hex(Sha256Generator.getHash(""));

            Session session = (Session) view.getTag();

            switch (view.getId()) {
                    case R.id.imgFollow:
                            int pos = getPosition(session);
                            favourites.set(pos,!favourites.get(pos));

                            setFavouriteImage(holders.get(pos),pos);
                            if(favourites.get(pos))
                                    presenter.setFavourite(session.getId(),current.getId());
                            else
                                    presenter.deleteFavourite(session.getId(), current.getId());

                            break;
                    case R.id.txvNumComments:
                            callback.goComments(session.getId(), usernames);
                            break;
                    case R.id.txvSessionName:
                            if (session.getPass().equalsIgnoreCase(shaRes))
                                    callback.openSession(session);
                            else
                                    callback.checkSessionPassword(session);
                            break;

            }
    }
    }
}


