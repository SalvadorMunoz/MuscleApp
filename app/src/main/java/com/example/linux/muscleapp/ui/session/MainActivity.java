package com.example.linux.muscleapp.ui.session;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.data.db.repositories.UsersRepository;
import com.example.linux.muscleapp.data.prefs.AppPreferences;
import com.example.linux.muscleapp.data.prefs.AppPreferencesHelper;
import com.example.linux.muscleapp.ui.comment.contract.CommentsContract;
import com.example.linux.muscleapp.ui.comment.fragment.CommentListFragment;
import com.example.linux.muscleapp.ui.comment.presenter.CommentsPresenterImp;
import com.example.linux.muscleapp.ui.session.contract.SessionContract;
import com.example.linux.muscleapp.ui.session.fragment.CheckPassDialog;
import com.example.linux.muscleapp.ui.session.fragment.MainListFragment;
import com.example.linux.muscleapp.ui.session.presenter.MainPresenterImp;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author Salvador Muñoz
 * @version 4.0
 *
 * This class is the main activity has got the sessions list, navigation drawer, add session button...
 */
public class MainActivity extends AppCompatActivity  implements MainListFragment.MainListListener,CheckPassDialog.CheckDialogListener {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

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

        if(AppPreferencesHelper.newInstance().getRemember())
            current=UsersRepository.getInstance().getCurrentUser(AppPreferencesHelper.newInstance().getCurrentUser());
        else{
            current=UsersRepository.getInstance().getCurrentUser();

        }
        txvCurrentUserName.setText(current.getName());
        txvCurrentUserEmail.setText(current.getEmail());
        civCurrentUser.setImageResource(R.drawable.no_photo);

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
        mainPresenter = new MainPresenterImp(mainListFragment);
        mainListFragment.setPresenter(mainPresenter);    }

    @Override
    public void goComments(User current, int idSession) {
        commentListFragment = (CommentListFragment) getSupportFragmentManager().findFragmentByTag(CommentListFragment.TAG);
        if(commentListFragment == null){
            Bundle bundle = new Bundle();
            bundle.putParcelable("current",current);
            bundle.putInt("session",idSession);
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
        else
            super.onBackPressed();
    }


}
