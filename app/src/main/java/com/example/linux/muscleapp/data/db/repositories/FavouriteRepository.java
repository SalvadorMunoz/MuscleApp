package com.example.linux.muscleapp.data.db.repositories;

import com.example.linux.muscleapp.data.db.dao.CommentaryDao;
import com.example.linux.muscleapp.data.db.dao.FavouriteDao;
import com.example.linux.muscleapp.data.db.pojo.Commentary;
import com.example.linux.muscleapp.data.db.pojo.Favourite;

import java.util.ArrayList;

/**
 * Created by linux on 13/05/18.
 */

public class FavouriteRepository {
    private static FavouriteRepository instance;
    private FavouriteDao favouriteDao;

    private FavouriteRepository(){
        favouriteDao =new FavouriteDao();
    }


    public static FavouriteRepository getInstace() {
        if(instance == null)
            instance = new FavouriteRepository();
        return instance;
    }


    public void add(Favourite favourite){
        favouriteDao.insert(favourite);
    }


}
