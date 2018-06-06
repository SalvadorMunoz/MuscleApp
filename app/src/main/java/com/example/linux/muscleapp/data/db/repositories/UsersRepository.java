package com.example.linux.muscleapp.data.db.repositories;

import android.database.Cursor;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.data.db.dao.LocalUserDao;
import com.example.linux.muscleapp.data.db.dao.UserDao;
import com.example.linux.muscleapp.data.db.pojo.User;

import java.util.ArrayList;

/**
 * @author Salvador Muñoz
 * @version 1.0
 *
 * This class is a user repository
 */

public class UsersRepository {
    private ArrayList<User>users;
    private static UsersRepository instance;
    private UserDao userDao;
    private LocalUserDao localUserDao;

    private User currentUser;
    private UsersRepository(){
        users = new ArrayList<>();
        userDao = new UserDao();
        localUserDao = new LocalUserDao();
    }

    public static UsersRepository getInstance(){
        if(instance == null)
            instance = new UsersRepository();
        return  instance;
    }

    public void add(User user){
        userDao.insertUser(user);
    }


    public boolean validateCredentials(String email,String pass){
        boolean res = false;
        if(userDao.loadActual(email,pass).size()!=0)
            res = true;
        return res;
    }
    public boolean userExists(String email){
        boolean res = false;
        if(userDao.loadActual(email).size()!=0)
            res = true;
        return res;
    }

    public ArrayList<User> getAllUsers(){
        return userDao.getAllUsers();
    }

    public User getLocalCurrentUser(String email) {
        return localUserDao.loadCurrent(email).get(0);
    }
    public User getCurrentUser(String email){
        currentUser =  userDao.loadActual(email).get(0);

        return currentUser;
    }

    public void setCurrentUser(String email) {
        currentUser = userDao.loadActual(email).get(0);
    }

    public ArrayList<User> getFilteredUsers(User user){
        return userDao.getFilteredUsers(user);
    }


    public ArrayList<User> getNameFronId(int current){
        return userDao.LoadNameFromId(current);
    }

    public void sendConfirmEmail(String email){
        userDao.sendConfirmEmail(email);
    }

    public void sendRecoveryEmail(String email){
        userDao.sendRecoveryEmail(email);
    }
}
