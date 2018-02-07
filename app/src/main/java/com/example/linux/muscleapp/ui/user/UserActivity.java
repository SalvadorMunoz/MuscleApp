package com.example.linux.muscleapp.ui.user;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.ui.about.AboutUsActivity;
import com.example.linux.muscleapp.ui.setting.SettingsFragment;
import com.example.linux.muscleapp.ui.utils.GlobalVariables;

public class UserActivity extends AppCompatActivity {
    private User current;
    private Toolbar toolbar;
    private SettingsFragment settingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        current = getIntent().getParcelableExtra("current");
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        switch (getIntent().getIntExtra("mode",-1)){
            case GlobalVariables.OPEN_SETTINGS:
                goSettings();
                break;
            case GlobalVariables.OPEN_ABOUTUS:
                startActivity(new Intent(UserActivity.this, AboutUsActivity.class));
                break;

        }
    }

    private void goSettings(){

            Bundle bundle = new Bundle();
            bundle.putParcelable("current",current);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            settingsFragment = SettingsFragment.getInstace(bundle);


            transaction.add(R.id.ctnSettings,settingsFragment).commit();


    }

    @Override
    protected void onResume() {
        super.onResume();
        if(GlobalVariables.fromAboutUs)
            finish();
    }
}
