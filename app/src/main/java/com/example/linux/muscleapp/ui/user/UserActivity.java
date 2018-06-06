package com.example.linux.muscleapp.ui.user;

import android.content.Intent;
import android.app.FragmentTransaction;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.afollestad.materialcamera.MaterialCamera;
import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.data.db.pojo.Excersice;
import com.example.linux.muscleapp.data.db.pojo.Favourite;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.SessionDate;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.ui.about.AboutUsActivity;
import com.example.linux.muscleapp.ui.comment.contract.CommentsContract;
import com.example.linux.muscleapp.ui.comment.fragment.CommentListFragment;
import com.example.linux.muscleapp.ui.comment.presenter.CommentsPresenterImp;
import com.example.linux.muscleapp.ui.excersice.fragment.SeeExcersiceFragment;
import com.example.linux.muscleapp.ui.favourite.fragment.FavouritesFragment;
import com.example.linux.muscleapp.ui.session.MainActivity;
import com.example.linux.muscleapp.ui.session.SessionActivity;
import com.example.linux.muscleapp.ui.session.fragment.CheckPassDialog;
import com.example.linux.muscleapp.ui.session.fragment.MainListFragment;
import com.example.linux.muscleapp.ui.session.fragment.SeeSessionFragment;
import com.example.linux.muscleapp.ui.session.fragment.SeedatesFragment;
import com.example.linux.muscleapp.ui.setting.SettingsFragment;
import com.example.linux.muscleapp.ui.today.fragment.TodayFragment;
import com.example.linux.muscleapp.ui.user.fragment.FromOptionDialogFragment;
import com.example.linux.muscleapp.ui.user.fragment.SearchUserFragment;
import com.example.linux.muscleapp.ui.user.fragment.UserProfileFragment;
import com.example.linux.muscleapp.ui.utils.GlobalVariables;
import com.example.linux.muscleapp.ui.utils.UriConverter;

import java.io.File;
import java.util.ArrayList;

public class UserActivity extends AppCompatActivity implements FavouritesFragment.SeeDetailsListener,CheckPassDialog.CheckDialogListener, SearchUserFragment.SearchUserListener,UserProfileFragment.SeeDetailsListener,FromOptionDialogFragment.ImageOptionListener {
    private User current;
    private Toolbar toolbar;
    private SettingsFragment settingsFragment;
    private FavouritesFragment favouritesFragment;
    private CommentListFragment commentListFragment;
    private CommentsContract.CommentsPresenter commentsPresenter;
    private CheckPassDialog checkPassDialog;
    private TodayFragment todayFragment;
    private SearchUserFragment searchUserFragment;
    private UserProfileFragment userProfileFragment;
    private FromOptionDialogFragment fromOptionDialogFragment;
    private static final int OPEN_CAMERA=1;
    private static final int OPEN_GALLERY=2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        current = getIntent().getParcelableExtra("current");
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        int option = getIntent().getIntExtra("mode",-1);
        switch (option){
            case GlobalVariables.OPEN_PROFILE:
                goUserProfile(current,GlobalVariables.OPEN_PROFILE);
                GlobalVariables.fromSelectImage= false;
                break;
            case GlobalVariables.OPEN_FAVOURITES:
                goFavourites();
                break;
            case GlobalVariables.OPEN_TODAY:
                goToday();
                break;
            case GlobalVariables.OPEN_SEARCH_USER:
                goSeachUser();
                break;
            case GlobalVariables.OPEN_SETTINGS:
                goSettings();
                break;
            case GlobalVariables.OPEN_ABOUTUS:
                startActivity(new Intent(UserActivity.this, AboutUsActivity.class));
                break;

        }
    }
    private void goFavourites(){
        favouritesFragment = (FavouritesFragment) getSupportFragmentManager().findFragmentByTag(FavouritesFragment.TAG);

        if(favouritesFragment == null){
            Bundle bundle = new Bundle();
            bundle.putParcelable("current",current);
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            favouritesFragment = FavouritesFragment.newInstance(bundle);
            transaction.add(R.id.userContent,favouritesFragment,FavouritesFragment.TAG).commit();
        }
    }

    private void goToday(){
        todayFragment = (TodayFragment) getSupportFragmentManager().findFragmentByTag(TodayFragment.TAG);

        if(todayFragment == null){
            Bundle bundle = new Bundle();
            bundle.putParcelable("current",current);
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            todayFragment = TodayFragment.newInstance(bundle);
            transaction.add(R.id.userContent,todayFragment,TodayFragment.TAG).commit();
        }
    }
    private void goSeachUser(){
        searchUserFragment = (SearchUserFragment) getSupportFragmentManager().findFragmentByTag(SearchUserFragment.TAG);

        if(searchUserFragment == null){
            Bundle bundle = new Bundle();
            bundle.putParcelable("current",current);
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            searchUserFragment = SearchUserFragment.newInstance(bundle);
            transaction.add(R.id.userContent,searchUserFragment,SearchUserFragment.TAG).commit();
        }
    }

    private void goSettings(){

            Bundle bundle = new Bundle();
            bundle.putParcelable("current",current);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            settingsFragment = SettingsFragment.getInstace(bundle);


            transaction.add(R.id.userContent,settingsFragment).commit();


    }

    @Override
    protected void onResume() {
        super.onResume();
        if(GlobalVariables.fromAboutUs) {
            finish();
            GlobalVariables.fromAboutUs=false;
        }
    }

    @Override
    public void goComments(int session, ArrayList<User> usernames) {
        commentListFragment = (CommentListFragment) getSupportFragmentManager().findFragmentByTag(CommentListFragment.TAG);
        if(commentListFragment == null){
            Bundle bundle = new Bundle();
            bundle.putParcelable("current",current);
            bundle.putInt("session",session);
            bundle.putParcelableArrayList("usernames",usernames);
            commentListFragment = CommentListFragment.newInstance(bundle);
            commentListFragment.show(getSupportFragmentManager(),CommentListFragment.TAG);

        }
        commentsPresenter = new CommentsPresenterImp(commentListFragment);
        commentListFragment.setPresenter(commentsPresenter);
    }

    @Override
    public void openSession(Session session) {
        Intent intent = new Intent(UserActivity.this, SessionActivity.class);
        intent.putExtra("current",session);
        intent.putExtra("mode",GlobalVariables.OPEN_SEE);
        startActivity(intent);
    }
    @Override
    public void checkSessionPassword(Session session) {
        checkPassDialog = (CheckPassDialog) getSupportFragmentManager().findFragmentByTag(CheckPassDialog.TAG);
        GlobalVariables.fromMain = false;
        if(checkPassDialog == null){
            Bundle bundle = new Bundle();
            bundle.putParcelable("current",session);
            checkPassDialog = CheckPassDialog.getInstance(bundle);
            checkPassDialog.show(getSupportFragmentManager(),CheckPassDialog.TAG);

        }

    }

    @Override
    public void openSelectImage() {
        fromOptionDialogFragment = (FromOptionDialogFragment) getSupportFragmentManager().findFragmentByTag(FromOptionDialogFragment.TAG);
        if(fromOptionDialogFragment == null){
            fromOptionDialogFragment = FromOptionDialogFragment.getInstance(null);
            fromOptionDialogFragment.show(getSupportFragmentManager(),FromOptionDialogFragment.TAG);

        }
    }

    @Override
    public void seeSession(Session session, int mode) {
        Intent intent = new Intent(UserActivity.this, SessionActivity.class);
        intent.putExtra("current",session);
        intent.putExtra("mode",GlobalVariables.OPEN_SEE);
        startActivity(intent);
    }

    @Override
    public void goUserProfile(User user,int from) {
        userProfileFragment = (UserProfileFragment) getSupportFragmentManager().findFragmentByTag(UserProfileFragment.TAG);
        if(userProfileFragment == null){
            Bundle bundle = new Bundle();
            bundle.putParcelable("current",current);
            if(from != GlobalVariables.OPEN_PROFILE)
                bundle.putParcelable("clicked",user);
            userProfileFragment = UserProfileFragment.newInstance(bundle);
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if(from != GlobalVariables.OPEN_PROFILE) {
                transaction.addToBackStack(null);
                transaction.replace(R.id.userContent, userProfileFragment, UserProfileFragment.TAG).commit();
            }else
                transaction.add(R.id.userContent,userProfileFragment,UserProfileFragment.TAG).commit();


        }
    }


    @Override
    public void openCamera() {
        File saveFolder = new File(Environment.getExternalStorageDirectory(), "muscleapp");
        new MaterialCamera(this).maxAllowedFileSize(1024 * 1024 * 5)
                .primaryColor(getResources().getColor(R.color.colorPrimary))
                .saveDir(saveFolder)
                .qualityProfile(MaterialCamera.QUALITY_480P).
                showPortraitWarning(false)
                .stillShot() //
                .start(OPEN_CAMERA);

    }

    @Override
    public void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), OPEN_GALLERY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
            GlobalVariables.imgPath =  UriConverter.getRealPathFromURI(this,data.getData());

    }
}
