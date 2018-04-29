package com.example.linux.muscleapp.net;

import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.User;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by linux on 20/03/18.
 */

public class Result implements Serializable {
    boolean code;
    int status;
    String message;
    ArrayList<User> users;
    ArrayList<Session> sessions;
    int last;

    public boolean getCode() {
        return code;
    }

    public void setCode(boolean code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }


    public ArrayList<Session> getSessions(){return  sessions;}

    public void setSessions(ArrayList<Session> sessions){this.sessions = sessions;}

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }


}

