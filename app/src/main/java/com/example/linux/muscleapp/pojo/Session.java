package com.example.linux.muscleapp.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * @author Salvador Muñoz
 * @version 1.0
 * This class is a training session
 */

public class Session implements Parcelable{
    int id;
    String name, creationDate, creator;
    ArrayList<String> trainingDates;
    ArrayList<Excersice> excersices;

    public Session(String name, String creationDate, String creator, ArrayList<String> trainingDates, ArrayList<Excersice> excersices) {
        this.name = name;
        this.creationDate = creationDate;
        this.creator = creator;
        this.trainingDates = trainingDates;
        this.excersices = excersices;
    }

    protected Session(Parcel in) {
        id = in.readInt();
        name = in.readString();
        creationDate = in.readString();
        creator = in.readString();
        trainingDates = in.createStringArrayList();
    }

    public static final Creator<Session> CREATOR = new Creator<Session>() {
        @Override
        public Session createFromParcel(Parcel in) {
            return new Session(in);
        }

        @Override
        public Session[] newArray(int size) {
            return new Session[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getCreator() {
        return creator;
    }

    public ArrayList<String> getTrainingDates() {
        return trainingDates;
    }

    public ArrayList<Excersice> getExcersices() {
        return excersices;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(creationDate);
        parcel.writeString(creator);
        parcel.writeStringList(trainingDates);
    }
}
