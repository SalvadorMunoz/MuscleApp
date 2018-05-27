package com.example.linux.muscleapp.ui.user.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.data.db.pojo.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileFragment extends Fragment {
    public static final String TAG ="userProfile";
    private TextView txvName,txvAge, txvResidence;
    private ListView ltvSessions;
    private User current,clicked;

    public UserProfileFragment() {
        // Required empty public constructor
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
        // Inflate the layout for this fragment
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

        txvName.setText( clicked.getName());
        txvAge.setText(String.valueOf(getAge(clicked.getBornDate()))+" "+getResources().getString(R.string.years));
        txvResidence.setText(clicked.getResidence());
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


}
