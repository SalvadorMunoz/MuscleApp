package com.example.linux.muscleapp.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.example.linux.muscleapp.data.db.repositories.FavouriteRepository;
import com.example.linux.muscleapp.net.SessionService;
import com.example.linux.muscleapp.ui.favourite.contract.FavouriteContract;
import com.example.linux.muscleapp.ui.favourite.fragment.FavouritesFragment;
import com.example.linux.muscleapp.ui.utils.GlobalVariables;
import com.example.linux.muscleapp.ui.utils.Sha256Generator;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by linux on 23/05/18.
 */

public class FavouritesAdapter extends ArrayAdapter<Session>{
    private Context context;
    private ClickItem listener;
    private FavouriteContract.Presenter presenter;
    private User current;
    private FavouritesFragment.SeeDetailsListener callback;
    private ArrayList<User> usernames;

    public FavouritesAdapter(@NonNull Context context, FavouriteContract.Presenter presenter, User current, FavouritesFragment.SeeDetailsListener callback, ArrayList<User> usernames, ArrayList<Session> favourites) {
        super(context, R.layout.item_mainlist,favourites);
        this.context = context;
        listener = new ClickItem();
        this.presenter = presenter;
        this.current = current;
        this.callback = callback;
        this.usernames = usernames;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        FavouriteHolder holder = new FavouriteHolder();
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
            holder = (FavouriteHolder) view.getTag();
        holder.sessionName.setText(getItem(position).getName());
        holder.sessionName.setTag(getItem(position));
        holder.sessionName.setOnClickListener(listener);
        holder.result.setText(getName(getItem(position).getUser())+", "+format(getItem(position).getCreationDate()));
        holder.follow.setImageResource(R.drawable.ic_unfollow);
        holder.follow.setTag(getItem(position));
        holder.follow.setOnClickListener(listener);
        holder.commentaries.setTag(getItem(position));
        holder.commentaries.setOnClickListener(listener);
        return view;
    }

    private  String getName(int id){
        String res= "";

        for (int i = 0; i< usernames.size();i++){
            if(usernames.get(i).getId() == id)
                res = usernames.get(i).getName();

        }
        return  res;
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

    private  void openDialog(final int sesion){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(getContext().getResources().getString(R.string.remove_favourite_title)).setMessage(getContext().getResources().getString(R.string.remove_favourite_message)).
                setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        presenter.deleteFavourite(new Favourite(sesion,current.getId()));
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.create().show();
    }
    class FavouriteHolder {
        TextView sessionName,result,commentaries;
        ImageView follow;
        CircleImageView userImg;
    }


    class ClickItem implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            String shaRes = Sha256Generator.bin2hex(Sha256Generator.getHash(""));

            Session session = (Session) view.getTag();

            switch (view.getId()){
                case R.id.imgFollow:
                    openDialog(session.getId());
                    break;
                case R.id.txvNumComments:
                    callback.goComments(session.getId(),usernames);
                    break;
                case R.id.txvSessionName:
                    if(session.getPass().equalsIgnoreCase(shaRes))
                        callback.openSession(session);
                    else
                        callback.checkSessionPassword(session);
                    break;

            }
        }
    }

}
