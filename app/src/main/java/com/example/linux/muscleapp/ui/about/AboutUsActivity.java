package com.example.linux.muscleapp.ui.about;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.ui.utils.GlobalVariables;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View about = new AboutPage(this).isRTL(false)
                .setImage(R.drawable.logo)
                .setDescription("MuscleApp es una red social enfocada en el mundo deportivo. Con ella podrás crear y compartir tus sesiones de entramiento, y añadir las de otros usuarios")
                .addItem(new Element().setTitle("Version 1.0"))
                .addGroup("Contacta conmigo")
                .addGitHub("SalvadorMunoz","Git hub")
                .addEmail("salvador.munoz.mendez@gmail.com","Contacta por email")
                .addWebsite("https://muscleapp.club/","Web")
                .create();

        setContentView(about);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        GlobalVariables.setFromAboutUs();
    }
}
