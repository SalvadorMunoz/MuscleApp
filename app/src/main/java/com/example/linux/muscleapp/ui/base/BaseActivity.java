package com.example.linux.muscleapp.ui.base;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;

import com.example.linux.muscleapp.R;

/**
 * Created by linux on 14/11/17.
 */

public class BaseActivity extends AppCompatActivity{
    public void showSnackBar(int resource){
        Snackbar.make(findViewById(android.R.id.content),getResources().getString(resource),Snackbar.LENGTH_SHORT).show();
    }

    public void errorTextInputLayout(TextInputLayout til, int resource){
        til.setError(getResources().getString(resource));
    }
}
