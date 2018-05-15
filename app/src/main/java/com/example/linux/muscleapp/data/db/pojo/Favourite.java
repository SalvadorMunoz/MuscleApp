package com.example.linux.muscleapp.data.db.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by linux on 13/05/18.
 */

public class Favourite  implements Parcelable{
    private int session;
    private int follower;

    public Favourite(int session, int follower) {
        this.session = session;
        this.follower = follower;
    }

    protected Favourite(Parcel in) {
        session = in.readInt();
        follower = in.readInt();
    }

    public static final Creator<Favourite> CREATOR = new Creator<Favourite>() {
        @Override
        public Favourite createFromParcel(Parcel in) {
            return new Favourite(in);
        }

        @Override
        public Favourite[] newArray(int size) {
            return new Favourite[size];
        }
    };

    public int getSession() {
        return session;
    }

    public void setSession(int session) {
        this.session = session;
    }

    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(session);
        parcel.writeInt(follower);
    }
}
