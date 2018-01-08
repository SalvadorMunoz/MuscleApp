package com.example.linux.muscleapp.ui.login.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.data.prefs.AppPreferencesHelper;
import com.example.linux.muscleapp.ui.login.contract.LoginContract;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment implements LoginContract.LoginView {
    public  static  final  String  TAG ="signin";

    private EditText edtEmail, edtPass;
    private TextInputLayout tilEmail,tilPass;

    private Button btnSignin;
    private TextView txvSignUp;

    private LoginListener callback;

    public interface LoginListener{
        void goSignUp();
        void goMain();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callback = (LoginListener) activity;
    }

    private LoginContract.LoginPresenter presenter;


    public static SignInFragment newInstance(Bundle bundle) {
        SignInFragment signInFragment = new SignInFragment();

        if(bundle != null)
            signInFragment.setArguments(bundle);

        return signInFragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(AppPreferencesHelper.newInstance().getRemember() == true)
            callback.goMain();

        View root = inflater.inflate(R.layout.fragment_sign_in,container,false);

        edtEmail = (EditText) root.findViewById(R.id.edtLoginEmail);
        edtPass = (EditText) root.findViewById(R.id.edtLoginPass);
        btnSignin = (Button) root.findViewById(R.id.btnLoginSignIn);
        txvSignUp = (TextView) root.findViewById(R.id.txvLoginSignUp);

        tilEmail = (TextInputLayout) root.findViewById(R.id.tilLoginEmail);
        tilPass = (TextInputLayout) root.findViewById(R.id.tilLoginPass);


        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tilEmail.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edtPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tilPass.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        txvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               callback.goSignUp();
            }
        });
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.validateCredentials(edtEmail.getText().toString(),edtPass.getText().toString());
            }
        });

        return root;

    }

    @Override
    public void setPresenter(LoginContract.LoginPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void goMainActivity() {
        callback.goMain();
    }

    @Override
    public void setEmptyEmail() {
        tilEmail.setError(getResources().getString(R.string.err_emptyemail));
    }

    @Override
    public void setEmptyPass() {
        tilPass.setError(getResources().getString(R.string.err_emptypass));
    }

    @Override
    public void setSigninError() {
        Snackbar.make(getActivity().findViewById(android.R.id.content),getResources().getString(R.string.err_signin),Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }


}
