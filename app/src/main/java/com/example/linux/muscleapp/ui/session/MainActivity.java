package com.example.linux.muscleapp.ui.session;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.linux.muscleapp.MuscleAppApplication;
import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.data.prefs.AppPreferencesHelper;
import com.example.linux.muscleapp.ui.comment.contract.CommentsContract;
import com.example.linux.muscleapp.ui.comment.fragment.CommentListFragment;
import com.example.linux.muscleapp.ui.comment.presenter.CommentsPresenterImp;
import com.example.linux.muscleapp.ui.login.LogInActivity;
import com.example.linux.muscleapp.ui.session.contract.MainContract;
import com.example.linux.muscleapp.ui.session.contract.SessionContract;
import com.example.linux.muscleapp.ui.session.fragment.CheckPassDialog;
import com.example.linux.muscleapp.ui.session.fragment.MainListFragment;
import com.example.linux.muscleapp.ui.session.presenter.MainListPresenterImp;
import com.example.linux.muscleapp.ui.session.presenter.MainPresenter;
import com.example.linux.muscleapp.ui.user.UserActivity;
import com.example.linux.muscleapp.ui.utils.GlobalVariables;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author Salvador Muñoz
 * @version 4.0
 *
 * This class is the main activity has got the sessions list, navigation drawer, add session button...
 */
public class MainActivity extends AppCompatActivity  implements MainContract.View,MainListFragment.MainListListener,CheckPassDialog.CheckDialogListener {
    MainListFragment mainListFragment;
    CommentListFragment commentListFragment;
    CheckPassDialog checkPassDialog;
    SessionContract.MainPresenter mainPresenter;
    CommentsContract.CommentsPresenter commentsPresenter;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private CircleImageView civCurrentUser;
    private TextView txvCurrentUserName, txvCurrentUserEmail;
    private View header;

    private User current;

    private  MainContract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        presenter = new MainPresenter(this);

        drawerLayout = (DrawerLayout) findViewById(R.id.navDraw);
        navigationView = (NavigationView) findViewById(R.id.navView);
        header = navigationView.getHeaderView(0);
        txvCurrentUserEmail = (TextView) header.findViewById(R.id.txvNavEmail);
        txvCurrentUserName = (TextView) header.findViewById(R.id.txvNavUser);
        civCurrentUser = (CircleImageView) header.findViewById(R.id.civCurrentUser);

        if(AppPreferencesHelper.newInstance().getInitialize() == false){
            AppPreferencesHelper.newInstance().setInitialize(true);
            AppPreferencesHelper.newInstance().setNumVideo(0);
        }


        presenter.getCurrentUser(AppPreferencesHelper.newInstance().getCurrentUser());


    }

    @Override
    protected void onResume() {
        super.onResume();
        mainListFragment = (MainListFragment) getSupportFragmentManager().findFragmentByTag(MainListFragment.TAG);

        if(mainListFragment == null){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            mainListFragment = MainListFragment.newInstance(null);
            transaction.add(R.id.mainContent,mainListFragment,MainListFragment.TAG).commit();
        }
        mainPresenter = new MainListPresenterImp(mainListFragment);
        mainListFragment.setPresenter(mainPresenter);    }

    @Override
    public void goComments(User current, int idSession, ArrayList<User> usernames) {
        commentListFragment = (CommentListFragment) getSupportFragmentManager().findFragmentByTag(CommentListFragment.TAG);
        if(commentListFragment == null){
            Bundle bundle = new Bundle();
            bundle.putParcelable("current",current);
            bundle.putInt("session",idSession);
            bundle.putParcelableArrayList("usernames",usernames);
            commentListFragment = CommentListFragment.newInstance(bundle);
            commentListFragment.show(getSupportFragmentManager(),CommentListFragment.TAG);

        }
        commentsPresenter = new CommentsPresenterImp(commentListFragment);
        commentListFragment.setPresenter(commentsPresenter);
    }

    @Override
    public void addSession(User current, int mode) {
        Intent intent = new Intent(MainActivity.this, SessionActivity.class);
        intent.putExtra("user",current);
        intent.putExtra("mode",mode);
        startActivity(intent);
    }

    @Override
    public void checkSessionPassword(Session session) {
        checkPassDialog = (CheckPassDialog) getSupportFragmentManager().findFragmentByTag(CheckPassDialog.TAG);
        if(checkPassDialog == null){
            Bundle bundle = new Bundle();
            bundle.putParcelable("current",session);
            checkPassDialog = CheckPassDialog.getInstance(bundle);
            checkPassDialog.show(getSupportFragmentManager(),CheckPassDialog.TAG);

        }

    }

    @Override
    public void seeSession(Session session, int mode) {
        Intent intent = new Intent(MainActivity.this, SessionActivity.class);
        intent.putExtra("current",session);
        intent.putExtra("mode",mode);
        startActivity(intent);
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else {
            if(MuscleAppApplication.getContex().getAppPreferencesHelper().getRemember() == false)
                startActivity(new Intent(MainActivity.this, LogInActivity.class));
            else {
                finish();
                super.onBackPressed();
            }
        }
    }

    private  void setUpNavigationDrawer(){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                switch (item.getItemId()){
                    case R.id.actionFavourites:
                        intent.putExtra("mode",GlobalVariables.OPEN_FAVOURITES);
                        break;
                    case R.id.actionAccountSettings:
                        intent.putExtra("mode", GlobalVariables.OPEN_SETTINGS);
                        break;
                    case R.id.actionAboutUs:
                        intent.putExtra("mode",GlobalVariables.OPEN_ABOUTUS);
                }

                intent.putExtra("current",current);
                startActivity(intent);
                return true;
            }
        });
    }


    @Override
    public void setCurrentUser(User currentUser) {
        current = currentUser;
        txvCurrentUserName.setText(current.getName());
        txvCurrentUserEmail.setText(current.getEmail());
        civCurrentUser.setImageResource(R.drawable.no_photo);
        setUpNavigationDrawer();
    }


}
