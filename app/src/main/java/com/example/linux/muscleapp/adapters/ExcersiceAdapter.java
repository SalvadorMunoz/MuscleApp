package com.example.linux.muscleapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.linux.muscleapp.R;
import com.pkmmte.view.CircularImageView;

import java.util.ArrayList;

/**
 * Created by linux on 15/01/18.
 */

public class ExcersiceAdapter extends RecyclerView.Adapter<ExcersiceAdapter.ExcersiceHolder>{
    private ArrayList<String> datas;
    public ExcersiceAdapter(ArrayList<String> datas){
        this.datas = datas;
    }

    @Override
    public ExcersiceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_excersice,parent,false);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ExcersiceHolder(view);    }

    @Override
    public void onBindViewHolder(ExcersiceHolder holder, int position) {
        holder.delete.setVisibility(View.GONE);
        holder.res.setText(datas.get(position));

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class ExcersiceHolder extends RecyclerView.ViewHolder  {
        private TextView res;
        private ImageView delete;

        public ExcersiceHolder(View itemView) {
            super(itemView);
            res = (TextView) itemView.findViewById(R.id.txvItemExcersice);
            delete = (ImageView) itemView.findViewById(R.id.imgDelete);
        }
    }
}
