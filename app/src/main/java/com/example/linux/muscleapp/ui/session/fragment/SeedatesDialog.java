package com.example.linux.muscleapp.ui.session.fragment;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.adapters.SeeDatesAdapter;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.SessionDate;
import com.example.linux.muscleapp.ui.session.contract.SessionContract;
import com.example.linux.muscleapp.ui.session.presenter.SeeDatesPresenterImp;
import com.example.linux.muscleapp.ui.utils.GlobalVariables;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SeedatesDialog extends DialogFragment implements SessionContract.SeeDatesView{
    public static final String TAG = "seedates";
    private int session ;
    private ListView listView;
    private SeeDatesAdapter adapter;
    private SessionContract.SeeDatesPresenter presenter;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public static SeedatesDialog getInstance(Bundle bundle) {
        SeedatesDialog seedatesDialog = new SeedatesDialog();
        if (bundle != null)
            seedatesDialog.setArguments(bundle);

        return seedatesDialog;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View root = inflater.inflate(R.layout.fragment_seedates_dialog, null);

        listView = (ListView) root.findViewById(R.id.ltvSeeDates);
        adapter = new SeeDatesAdapter(getActivity());
        presenter = new SeeDatesPresenterImp(this);

        if (getArguments() != null) {
            session = getArguments().getInt("current");
        }

        listView.setAdapter(adapter);
        presenter.getDates(session);

        builder.setView(root);
        return builder.create();
    }

    @Override
    public void fillDates(ArrayList<SessionDate> dates) {
        adapter.clear();
        adapter.addAll(dates);
    }
}
