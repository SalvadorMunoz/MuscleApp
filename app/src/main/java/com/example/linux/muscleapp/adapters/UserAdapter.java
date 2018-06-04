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
import com.example.linux.muscleapp.data.db.pojo.User;

import org.w3c.dom.Text;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by linux on 26/05/18.
 */

public class UserAdapter extends ArrayAdapter<User> {
    private User current;
    private int hideCount= 0;

    public UserAdapter(@NonNull Context context,User current) {
        super(context, R.layout.item_userlist);
        this.current = current;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        UserHolder holder = new UserHolder();
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_userlist, null);

                holder.userImage = (CircleImageView) view.findViewById(R.id.civUser);
                holder.userName = (TextView) view.findViewById(R.id.txvListUserName);


            view.setTag(holder);
        }else
            holder = (UserHolder) view.getTag();
        if(current.getId() != getItem(position).getId()) {
            holder.userName.setText(getItem(position).getName());
        }else{
            view.setVisibility(View.GONE);
            hideCount++;
        }

        return view;
    }

    class UserHolder{
        CircleImageView userImage;
        TextView userName;
    }
}
