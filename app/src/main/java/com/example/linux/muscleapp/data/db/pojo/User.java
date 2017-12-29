package com.example.linux.muscleapp.data.db.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Salvador Muñoz
 * @version 1.0
 *
 * This class is an user
 */

public class User implements Parcelable {
    int id;
    String email,name,pass,residence,bornDate;

    public User(String email, String name, String pass, String residence, String bornDate) {
        this.email = email;
        this.name = name;
        this.pass = pass;
        this.residence = residence;
        this.bornDate = bornDate;
    }

    public User(int id, String email, String name, String pass, String residence, String bornDate) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.pass = pass;
        this.residence = residence;
        this.bornDate = bornDate;
    }

    protected User(Parcel in) {
        id = in.readInt();
        email = in.readString();
        name = in.readString();
        pass = in.readString();
        residence = in.readString();
        bornDate = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(email);
        parcel.writeString(name);
        parcel.writeString(pass);
        parcel.writeString(residence);
        parcel.writeString(bornDate);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getBornDate() {
        return bornDate;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }

}
