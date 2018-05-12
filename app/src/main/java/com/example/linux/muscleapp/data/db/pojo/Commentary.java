package com.example.linux.muscleapp.data.db.pojo;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Salvador Muñoz
 * @version 1.0
 *
 * This class is a comment
 */

public class Commentary implements Parcelable, Comparable{
    int id, session, user;
    String content, commentDate;
    int notify;
    public Commentary(int id, int idSession, int user, String content, String date,int notify) {
        this.id = id;
        this.session = idSession;
        this.user = user;
        this.content = content;
        this.commentDate =date;
        this.notify = notify;
    }

    protected Commentary(Parcel in) {
        id = in.readInt();
        session = in.readInt();
        user = in.readInt();
        content = in.readString();
        commentDate = in.readString();
        notify = in.readInt();
    }

    public static final Creator<Commentary> CREATOR = new Creator<Commentary>() {
        @Override
        public Commentary createFromParcel(Parcel in) {
            return new Commentary(in);
        }

        @Override
        public Commentary[] newArray(int size) {
            return new Commentary[size];
        }
    };

    public int getId() {
        return id;
    }

    public int getSession() {
        return session;
    }

    public int getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }

    public String getCommentDate() {
        return commentDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(session);
        parcel.writeInt(user);
        parcel.writeString(content);
        parcel.writeString(commentDate);
        parcel.writeInt(notify);
    }

    @Override
    public int compareTo(@NonNull Object o) {
        String tmp= ((Commentary)o).getCommentDate();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat thisDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        Date thisDate = null;
        try {
            date = dateFormat.parse(tmp);
            thisDate = thisDateFormat.parse(getCommentDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return thisDate.compareTo(date);
    }
}
