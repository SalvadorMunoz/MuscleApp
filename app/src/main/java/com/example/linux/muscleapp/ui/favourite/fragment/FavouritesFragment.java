package com.example.linux.muscleapp.ui.favourite.fragment;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.adapters.FavouritesAdapter;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.ui.favourite.contract.FavouriteContract;
import com.example.linux.muscleapp.ui.favourite.presenter.FavouritesPresenter;
import com.example.linux.muscleapp.ui.session.contract.SessionContract;
import com.example.linux.muscleapp.ui.session.fragment.MainListFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavouritesFragment extends ListFragment implements FavouriteContract.View{
    public static final String TAG = "favouritesSessions";
    private ArrayList<Session> favouriteSessions;
    private ProgressDialog progressDialog;
    private FavouriteContract.Presenter presenter;
    private User current;
    private FavouritesAdapter adapter;
    private SeeDetailsListener callback;
    private TextView noSession;

    public interface SeeDetailsListener{
        void goComments(int session,ArrayList<User> usernames);
        void openSession(Session session);
        void checkSessionPassword(Session session);
    }

    public FavouritesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callback = (SeeDetailsListener) activity;
    }

    public static FavouritesFragment newInstance(Bundle bundle){
        FavouritesFragment favouritesFragment = new FavouritesFragment();
        if(bundle != null)
            favouritesFragment.setArguments(bundle);
        return  favouritesFragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_favourites, container, false);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getActivity().getResources().getString(R.string.downloading));

        noSession = (TextView) view.findViewById(R.id.txvNoFavourites);

        if(getArguments() != null)
            current = getArguments().getParcelable("current");

        presenter = new FavouritesPresenter(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getActivity().getResources().getString(R.string.action_favourites));

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getFavourites(current.getId());

    }

    @Override
    public void openDialog() {
        progressDialog.show();

    }

    @Override
    public void fillFavourites(ArrayList<Session> favourites,ArrayList<User> usernames) {

        if(favourites.size() == 0){
            getListView().setVisibility(View.GONE);
            noSession.setVisibility(View.VISIBLE);
        }else{
            getListView().setVisibility(View.VISIBLE);
            noSession.setVisibility(View.GONE);
            favouriteSessions = favourites;
            adapter = new FavouritesAdapter(getContext(),presenter,current,callback,usernames,favourites);
            getListView().setAdapter(adapter);
        }

    }

    @Override
    public void removeFromList(int session) {
        for(int i = 0; i < favouriteSessions.size();i++){
            if(favouriteSessions.get(i).getId() == session){
                adapter.remove(favouriteSessions.get(i));
                adapter.notifyDataSetChanged();
            }
        }

    }

    @Override
    public void closeDialog() {
        progressDialog.dismiss();
    }


}
