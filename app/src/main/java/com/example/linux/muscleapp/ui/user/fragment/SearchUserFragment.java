package com.example.linux.muscleapp.ui.user.fragment;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.SearchEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.adapters.UserAdapter;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.ui.user.contract.UserContract;
import com.example.linux.muscleapp.ui.user.presenter.UserPresenter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchUserFragment extends Fragment implements UserContract.View{
    public static final String TAG ="searchUsers";
    private ListView ltvUsers;
    private UserAdapter adapter;
    private ProgressDialog progressDialog;
    private User current;
    private UserContract.Presenter presenter;
    private EditText edtName;
    private ImageView imgSearch;
    private boolean isFiltered;
    private SearchUserListener callback;

    public  interface SearchUserListener{
        void goUserProfile(User user);
    }

    public SearchUserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callback = (SearchUserListener) activity;
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
        if(getArguments() != null)
            current = getArguments().getParcelable("current");
        adapter = new UserAdapter(getContext(),current);
        ltvUsers.setAdapter(adapter);
        presenter.getUsers(current.getId());

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edtName.getText().toString().isEmpty()) {
                    presenter.getFilteredUsers(current.getId(),edtName.getText().toString());
                    isFiltered = true;
                }else if(isFiltered)
                    presenter.getUsers(current.getId());
            }
        });
        ltvUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                User temp = (User) adapterView.getItemAtPosition(i);
                callback.goUserProfile(temp);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_user, container, false);

        ltvUsers = (ListView) view.findViewById(R.id.ltvUsers);
        edtName = (EditText) view.findViewById(R.id.edtUserName);
        imgSearch = (ImageView) view.findViewById(R.id.imgSearch);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getResources().getString(R.string.downloading));

        presenter = new UserPresenter(this);
        // Inflate the layout for this fragment
        isFiltered = false;
        return view;
    }

    @Override
    public void openDialog() {
        progressDialog.show();
    }

    @Override
    public void fillUsers(ArrayList<User> users) {
        adapter.clear();
        adapter.addAll(users);

    }

    @Override
    public void closeDialog() {
        progressDialog.dismiss();
    }
}
