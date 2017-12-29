package com.example.linux.muscleapp.data.db.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Salvador Muñoz
 * @version 1.0
 *
 * This class is a session's excersice
 */

public class Excersice implements Parcelable {
    int id,session;
    String name,muscle,url, typeTime;
    int series, repetitions, time;

    public Excersice(int id, int session, String name, String muscle, String url, String typeTime, int series, int repetitions, int time) {
        this.id = id;
        this.session = session;
        this.name = name;
        this.muscle = muscle;
        this.url = url;
        this.typeTime = typeTime;
        this.series = series;
        this.repetitions = repetitions;
        this.time = time;
    }

    protected Excersice(Parcel in) {
        id = in.readInt();
        session = in.readInt();
        name = in.readString();
        muscle = in.readString();
        url = in.readString();
        typeTime = in.readString();
        series = in.readInt();
        repetitions = in.readInt();
        time = in.readInt();
    }

    public static final Creator<Excersice> CREATOR = new Creator<Excersice>() {
        @Override
        public Excersice createFromParcel(Parcel in) {
            return new Excersice(in);
        }

        @Override
        public Excersice[] newArray(int size) {
            return new Excersice[size];
        }
    };

    public int getId() {
        return id;
    }

    public int getSession() {
        return session;
    }

    public String getName() {
        return name;
    }

    public String getMuscle() {
        return muscle;
    }

    public String getUrl() {
        return url;
    }

    public String getTypeTime() {
        return typeTime;
    }

    public int getSeries() {
        return series;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public int getTime() {
        return time;
    }

    public void setSession(int session) {
        this.session = session;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(session);
        parcel.writeString(name);
        parcel.writeString(muscle);
        parcel.writeString(url);
        parcel.writeString(typeTime);
        parcel.writeInt(series);
        parcel.writeInt(repetitions);
        parcel.writeInt(time);
    }
}
