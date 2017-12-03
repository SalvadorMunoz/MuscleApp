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
import com.example.linux.muscleapp.data.db.repositories.ExcersiceRepository;
import com.example.linux.muscleapp.ui.dates.contract.AddDateContract;
import com.example.linux.muscleapp.ui.excersice.contract.ExcersiceContract;
import com.example.linux.muscleapp.ui.session.contract.SessionContract;

import java.util.ArrayList;

/**
 * @author Salvador Muñoz
 * @version 1.0
 *
 * This class is the excersice list adapter
 */

public class ExcersicesAdapter extends ArrayAdapter<Excersice>{
    private SessionContract.AddSessionPresenter presenter;
    private clickItem clickItem;
    public ExcersicesAdapter(@NonNull Context context,SessionContract.AddSessionPresenter presenter) {
        super(context, R.layout.item_excersice, new ArrayList<Excersice>());
        this.presenter = presenter;
        this.clickItem = new clickItem();
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
            holder.delete.setOnClickListener(clickItem);
            holder.delete.setTag(position);

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
    class clickItem implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            presenter.deleteExcersice((Integer) view.getTag());
        }
    }
}
