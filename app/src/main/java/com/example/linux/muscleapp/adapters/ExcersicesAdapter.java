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
import com.example.linux.muscleapp.pojo.Excersice;
import com.example.linux.muscleapp.repositories.ExcersiceRepository;

/**
 * @author Salvador Muñoz
 * @version 1.0
 *
 * This class is the excersice list adapter
 */

public class ExcersicesAdapter extends ArrayAdapter<Excersice>{

    public ExcersicesAdapter(@NonNull Context context) {
        super(context, R.layout.item_excersice, ExcersiceRepository.getInstance().getExcersices());
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
