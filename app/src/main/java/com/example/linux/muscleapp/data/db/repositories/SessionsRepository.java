package com.example.linux.muscleapp.data.db.repositories;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.data.db.pojo.Session;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Salvador Muñoz
 * @version 1.0
 *
 * This class represents a database table session
 */

public class SessionsRepository {
    private ArrayList<Session> sessions;

    private static SessionsRepository instace;

    private SessionsRepository() {
        sessions = new ArrayList<>();
        initialize();
    }

    public static SessionsRepository getInstace(){
        if(instace == null)
            instace = new SessionsRepository();
        return  instace;
    }

    private void initialize (){
        add(new Session(1, 100000,R.drawable.bicho,"Ejercicios para penaltis","","02-10-2017"));
        add(new Session(2, 200000,R.drawable.indurain,"Ejercicios para bicicleta","","02-09-2017"));


    }
    public void add (Session session){
        sessions.add(session);
    }

    public ArrayList<Session> getSessions(){
        Collections.sort(sessions);
        return sessions;
    }
    public int getLastId(){
        return sessions.get(sessions.size()-1).getId();
    }
}
