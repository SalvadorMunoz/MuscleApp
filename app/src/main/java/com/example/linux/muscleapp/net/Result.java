package com.example.linux.muscleapp.net;

import com.example.linux.muscleapp.data.db.pojo.Excersice;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.SessionDate;
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
    ArrayList<Excersice> excersices;
    ArrayList<SessionDate> sessionDates;
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

    public ArrayList<Excersice> getExcersices(){return  excersices;}

    public void setExcersices(ArrayList<Excersice> excersices){this.excersices = excersices;}

    public ArrayList<SessionDate> getSessionDates(){return  sessionDates;}

    public void setSessionDatess(ArrayList<SessionDate> sessionDates){this.sessionDates = sessionDates;}

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }


}

