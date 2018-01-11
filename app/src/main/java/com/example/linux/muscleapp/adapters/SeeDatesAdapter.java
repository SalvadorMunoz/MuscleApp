package com.example.linux.muscleapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.data.db.pojo.SessionDate;
import com.example.linux.muscleapp.ui.dates.contract.AddDateContract;

import java.util.ArrayList;

/**
 * Created by linux on 11/01/18.
 */

public class SeeDatesAdapter extends ArrayAdapter<SessionDate> {

    public SeeDatesAdapter(@NonNull Context context) {
        super(context, R.layout.item_date, new ArrayList<SessionDate>());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        SessionDatesHolder holder = new SessionDatesHolder();

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_date,null);

            holder.date = (TextView) view.findViewById(R.id.txvItemDate);
            holder.delete = (ImageView) view.findViewById(R.id.imgDelete);
            holder.delete.setVisibility(View.GONE);


            view.setTag(holder);
        }
        else
            holder = (SessionDatesHolder) view.getTag();

        holder.date.setText(String.format("%02d-%02d-%02d",getItem(position).getDay(),getItem(position).getMonth(),getItem(position).getYear()));

        return view;
    }

    class SessionDatesHolder{
        TextView date;
        ImageView delete;
    }

}
