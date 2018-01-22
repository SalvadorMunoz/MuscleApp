package com.example.linux.muscleapp.data.db.repositories;

import android.database.Cursor;

import com.example.linux.muscleapp.R;
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

    private User currentUser;
    private UsersRepository(){
        users = new ArrayList<>();
        userDao = new UserDao();
    }

    public static UsersRepository getInstance(){
        if(instance == null)
            instance = new UsersRepository();
        return  instance;
    }

    public void add(User user){
        users.add(user);
    }

    public ArrayList<User> getUsers(){
        return users;
    }


    public boolean validateCredentials(String email,String pass){
        boolean res = false;
        users.clear();
        Cursor cursor = userDao.loadActual(email,pass);
        if (cursor.moveToFirst()){
            res = true;
            do{
                users.add(new User(cursor.getInt(0),cursor.getString(1),cursor.getString(2),
                        cursor.getString(3),cursor.getString(4),cursor.getString(5),R.drawable.no_photo));
            }while (cursor.moveToNext());
        }
        return res;
    }
    public boolean userExists(String email){
        boolean res = false;
        for (int i = 0; i< users.size();i++){
            if (users.get(i).getEmail().equals(email))
                res = true;
        }
        return res;
    }

    public User getCurrentUser() {
        return currentUser;
    }
    public User getCurrentUser(String email){
        User tmp = null;

        for(int i = 0; i < users.size();i++){
            if(users.get(i).getEmail().equals(email))
                tmp=users.get(i);
        }

        return tmp;
    }

    public void setCurrentUser(String email) {
        for(int i = 0; i< users.size();i++) {
            if(users.get(i).getEmail().equals(email))
                this.currentUser = users.get(i);
        }
    }
    public int getLastId(){
        int res=-1;
        if(users.size()==0)
            res = 1;
        else{
            for(int i = 0;i < users.size();i++){
                if(users.get(i).getId() > res)
                    res = users.get(i).getId();
            }
        }
        return res;
    }

    public String getNameFronId(int id){
        String res="";
        for(int i = 0; i < users.size();i++){
            if(users.get(i).getId()== id)
                res = users.get(i).getName();
        }
        return  res;
    }
}
