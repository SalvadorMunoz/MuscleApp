package com.example.linux.muscleapp.ui.setting;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Switch;

import com.example.linux.muscleapp.MuscleAppApplication;
import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.data.db.pojo.User;

/**
 * Created by linux on 13/11/17.
 */

public class SettingsFragment extends PreferenceFragment {
    private SwitchPreference remember;
    private User current;

    public SettingsFragment() {


    }
    public static SettingsFragment getInstace(Bundle bundle){
        SettingsFragment settingsFragment = new SettingsFragment();

        if(bundle!=null)
            settingsFragment.setArguments( bundle);
        return  settingsFragment;
    }




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.action_setting));

        if(getArguments()!= null)
            current = getArguments().getParcelable("current");

        remember = (SwitchPreference) findPreference("rememberUser");
        boolean state = MuscleAppApplication.getContex().getAppPreferencesHelper().getRemember();
        remember.setChecked(state);



        remember.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                Boolean res = (Boolean) o;
                remember.setChecked(res);
                MuscleAppApplication.getContex().getAppPreferencesHelper().setRemember(res);

                return false;
            }
        });

    }

}
