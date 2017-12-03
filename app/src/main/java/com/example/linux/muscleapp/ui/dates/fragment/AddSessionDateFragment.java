package com.example.linux.muscleapp.ui.dates.fragment;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.adapters.SessionDatesAdapter;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.SessionDate;
import com.example.linux.muscleapp.ui.dates.contract.AddDateContract;
import com.example.linux.muscleapp.ui.dates.presenter.AddSessionDatePresenter;
import com.example.linux.muscleapp.ui.session.fragment.AddSessionFragment;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddSessionDateFragment extends ListFragment implements AddDateContract.View{
    public static final String TAG="addDate";
    private    FloatingActionButton fbtAdd;
    DatePickerListener dplistener;
    private SessionDatesAdapter adapter;
    private AddDateContract.Presenter presenter;


    public AddSessionDateFragment() {
        // Required empty public constructor
    }
    public static AddSessionDateFragment newInstance(Bundle bundle){
        AddSessionDateFragment addSessionDateFragment = new AddSessionDateFragment();
        if(bundle != null)
            addSessionDateFragment.setArguments(bundle);
        return  addSessionDateFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_session_date,container,false);
        fbtAdd = (FloatingActionButton) root.findViewById(R.id.fbtAddDate);
        presenter = new AddSessionDatePresenter(this);

        adapter = new SessionDatesAdapter(getActivity(),presenter);
        dplistener = new DatePickerListener();

        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.loadDates();
        fbtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create datepicker (context activity, style, listener,year, month,day)
                DatePickerDialog dpdDate = new DatePickerDialog(getActivity(),
                        R.style.DatePicker,dplistener,
                        Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                dpdDate.show();
            }
        });
        getListView().setAdapter(adapter);
    }

    @Override
    public void loadDates(ArrayList<SessionDate> dates) {
        adapter.clear();
        adapter.addAll(dates);
    }

    class DatePickerListener implements DatePickerDialog.OnDateSetListener{

        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            SessionDate sessionDate = new SessionDate(i2,i1,i,-1);
            presenter.addDate(sessionDate);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
