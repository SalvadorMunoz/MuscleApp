package com.example.linux.muscleapp.ui.user.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.SearchEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.linux.muscleapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchUserFragment extends Fragment {
    public static final String TAG ="searchUsers";


    public SearchUserFragment() {
        // Required empty public constructor
    }
    public static SearchUserFragment newInstance(Bundle bundle){
        SearchUserFragment searchUserFragment = new SearchUserFragment();
        if(bundle != null)
            searchUserFragment.setArguments(bundle);
        return  searchUserFragment;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.action_search_user));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_user, container, false);
    }

}
