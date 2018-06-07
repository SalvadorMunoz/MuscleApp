package com.example.linux.muscleapp.ui.user.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.adapters.SessionListAdapter;
import com.example.linux.muscleapp.adapters.UserProfileAdapter;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.ui.user.contract.ProfileContract;
import com.example.linux.muscleapp.ui.user.presenter.ProfilePresenter;
import com.example.linux.muscleapp.ui.utils.GlobalVariables;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileFragment extends Fragment  implements ProfileContract.View{
    public static final String TAG ="userProfile";
    private TextView txvName,txvAge, txvResidence;
    private ListView ltvSessions;
    private FloatingActionButton fbtEdit;
    private User current,clicked;
    private ProfileContract.Presenter presenter;
    private UserProfileAdapter adapter;
    private SeeDetailsListener callback;
    private ArrayList<Session> sessions;

    public interface SeeDetailsListener{
        void goComments(int session, ArrayList<User> usernames);
        void openSession(Session session);
        void checkSessionPassword(Session session);
    }

    public UserProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callback = (SeeDetailsListener) activity;
    }

    public static UserProfileFragment newInstance(Bundle bundle){
        UserProfileFragment userProfileFragment = new UserProfileFragment();

        if(bundle!= null)
            userProfileFragment.setArguments(bundle);

        return userProfileFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        txvName = (TextView) view.findViewById(R.id.txvProfileName);
        txvAge = (TextView) view.findViewById(R.id.txvProfileAge);
        txvResidence = (TextView) view.findViewById(R.id.txvProfileResidence);
        ltvSessions = (ListView) view.findViewById(R.id.ltvProfileSessions);
        fbtEdit = (FloatingActionButton) view.findViewById(R.id.fbtEditImage);

        presenter = new ProfilePresenter(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        if(getArguments() !=null){
            current = getArguments().getParcelable("current");
            clicked = getArguments().getParcelable("clicked");
        }

        if(clicked != null) {
            GlobalVariables.fromMyProfile = false;
            txvName.setText(clicked.getName());
            txvAge.setText(String.valueOf(getAge(clicked.getBornDate())) + " " + getResources().getString(R.string.years));
            txvResidence.setText(clicked.getResidence());
            presenter.getSessions(clicked.getId());
        }else {
            GlobalVariables.fromMyProfile = true;

            fbtEdit.setVisibility(View.VISIBLE);
            txvName.setText(current.getName());
            txvAge.setText(String.valueOf(getAge(current.getBornDate())) + " " + getResources().getString(R.string.years));
            txvResidence.setText(current.getResidence());
            presenter.getSessions(current.getId());
        }
    }
    private long getAge(String bornDate)  {
        long ageInMillis = 0;


        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = format.parse(bornDate);
            ageInMillis = new Date().getTime() - date.getTime();
            Date age = new Date(ageInMillis);
            ageInMillis = age.getYear()-70;
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return  ageInMillis;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();


    }


    @Override
    public void fillSessions(ArrayList<Session> sessions, ArrayList<Boolean> favourites, ArrayList<User> usernames) {
        this.sessions = sessions;
        adapter = new UserProfileAdapter(getContext(),current,usernames,sessions,favourites,presenter,callback);
        SessionListAdapter adapter1 = new SessionListAdapter(getContext(),current,usernames,sessions,null,null,favourites);
        ltvSessions.setAdapter(adapter);

    }

    @Override
    public void removeFromList(int id) {
        for(int i = 0; i < sessions.size();i++){
            if(sessions.get(i).getId() == id){
                adapter.remove(sessions.get(i));
                adapter.notifyDataSetChanged();
            }
        }
    }
}
