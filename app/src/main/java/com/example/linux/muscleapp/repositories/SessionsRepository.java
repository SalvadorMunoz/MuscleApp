package com.example.linux.muscleapp.repositories;

import com.example.linux.muscleapp.pojo.Session;

import java.util.ArrayList;

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
        add(new Session("Ejercicios para penaltis","02-10-2017","Juan",null,null));
        add(new Session("Ejercicios para bicicleta","02-09-2017","Raquel",null,null));

    }
    public void add (Session session){
        sessions.add(session);
    }

    public ArrayList<Session> getSessions(){
        return sessions;
    }
}
