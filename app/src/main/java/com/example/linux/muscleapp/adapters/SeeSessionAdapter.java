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
import com.example.linux.muscleapp.data.db.pojo.Excersice;
import com.example.linux.muscleapp.ui.session.contract.SessionContract;

import java.util.ArrayList;

/**
 * Created by linux on 11/01/18.
 */

public class SeeSessionAdapter  extends ArrayAdapter<Excersice>{
    public SeeSessionAdapter(@NonNull Context context) {
        super(context, R.layout.item_excersice, new ArrayList<Excersice>());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        ExcersiceHolder holder = new ExcersiceHolder();
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_excersice,null);

            holder.excersice = (TextView) view.findViewById(R.id.txvItemExcersice);
            holder.delete = (ImageView) view.findViewById(R.id.imgDelete);
            holder.delete.setVisibility(View.GONE);

            view.setTag(holder);
        }else
            holder = (ExcersiceHolder) view.getTag();

        holder.excersice.setText(getItem(position).getName());
        return view;
    }

    class ExcersiceHolder{
        TextView excersice;
        ImageView delete;
    }

}
