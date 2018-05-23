package com.example.linux.muscleapp.ui.favourite.presenter;

import com.example.linux.muscleapp.data.db.pojo.Favourite;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.ui.favourite.contract.FavouriteContract;
import com.example.linux.muscleapp.ui.favourite.interactor.FavouritesInteractor;
import com.example.linux.muscleapp.ui.favourite.interactor.FavouritesInteractorImp;

import java.util.ArrayList;

/**
 * Created by linux on 23/05/18.
 */

public class FavouritesPresenter implements FavouriteContract.Presenter,FavouritesInteractor.OnLoadFavourites {
    private FavouriteContract.View view;
    private FavouritesInteractorImp interactor;

    public FavouritesPresenter(FavouriteContract.View view) {
        this.view = view;
        interactor = new FavouritesInteractorImp(this);
    }

    @Override
    public void getFavourites(int currentUser) {
        interactor.getFavourites(currentUser);
    }

    @Override
    public void deleteFavourite(Favourite current) {
        interactor.deleteFavourite(current);
    }

    @Override
    public void openDialog() {
        view.openDialog();
    }

    @Override
    public void closeDialog() {
        view.closeDialog();
    }

    @Override
    public void fillFavourites(ArrayList<Session> favourites, ArrayList<User> usernames) {
        view.fillFavourites(favourites,usernames);
    }

    @Override
    public void removeFromList(int session) {
        view.removeFromList(session);
    }
}
