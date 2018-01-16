package com.example.linux.muscleapp.data.db.repositories;

import com.example.linux.muscleapp.R;
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

    private User currentUser;
    private UsersRepository(){
        users = new ArrayList<>();
        initialize();
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

    private void initialize(){
        add(new User( 1,"yo@yo.com","yo","yo","Malaga","21-05-1994", R.drawable.no_photo));
        add(new User( 2,"cr7@cr7.com","Cristiano","cris","cr7","21-05-1994",R.drawable.bicho));
        add(new User( 3,"indurain@cr7.com","Indurain","indurain","su","21-05-1994",R.drawable.indurain));

    }
    public boolean validateCredentials(String email,String pass){
        boolean res = false;
        for(int i = 0; i< users.size();i++){
            if(users.get(i).getEmail().equals(email) && users.get(i).getPass().equals(pass))
                res = true;
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
