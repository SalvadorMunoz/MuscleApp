package com.example.linux.muscleapp.data.db.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * @author Salvador Muñoz
 * @version 1.0
 *
 * This class is an user
 */

public class User implements Parcelable {
    int id;
    String email;
    String pass;
    String name;
    String borndate;
    String residence;
    int verify;
    String url;


    public User(int id, String email, String pass, String name, String borndate, String residence,int verify,String url) {
         this.id = id;
        this.email = email;
        this.name = name;
        this.pass = pass;
        this.residence = residence;
        this.borndate = borndate;
        this.verify = verify;
        this.url = url;
    }


    protected User(Parcel in) {
        id = in.readInt();
        email = in.readString();
        name = in.readString();
        pass = in.readString();
        residence = in.readString();
        borndate = in.readString();
        url = in.readString();
        verify = in.readInt() ;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(email);
        dest.writeString(name);
        dest.writeString(pass);
        dest.writeString(residence);
        dest.writeString(borndate);
        dest.writeString(url);
        dest.writeInt(verify);
    }

    @Override
    public int describeContents() {
        return 0;
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
        return borndate;
    }

    public void setBornDate(String bornDate) {
        this.borndate = bornDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBorndate() {
        return borndate;
    }

    public void setBorndate(String borndate) {
        this.borndate = borndate;
    }

    public int getVerify() {
        return verify;
    }

    public void setVerify(int verify) {
        this.verify = verify;
    }
}
