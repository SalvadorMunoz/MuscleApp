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
import com.example.linux.muscleapp.data.db.repositories.SessionDatesRepository;
import com.example.linux.muscleapp.ui.dates.contract.AddDateContract;

import java.util.ArrayList;

/**
 * @author Salvador Muñoz
 * @version 1.0
 *
 * Date list adapter.
 */

public class SessionDatesAdapter extends ArrayAdapter<SessionDate> {
    private AddDateContract.Presenter presenter;

    public SessionDatesAdapter(@NonNull Context context, AddDateContract.Presenter presenter) {
        super(context, R.layout.item_date, new ArrayList<SessionDate>());
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        SessionDatesHolder holder = new SessionDatesHolder();
        ClickItem clickItem = new ClickItem();

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_date,null);

            holder.date = (TextView) view.findViewById(R.id.txvItemDate);
            holder.delete = (ImageView) view.findViewById(R.id.imgDelete);
            holder.delete.setOnClickListener(clickItem);
            holder.delete.setTag(position);

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
    class ClickItem implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            presenter.deleteDate((Integer) view.getTag());
        }
    }
}
