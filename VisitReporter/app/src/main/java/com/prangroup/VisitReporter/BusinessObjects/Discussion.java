package com.prangroup.VisitReporter.BusinessObjects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Manik on 4/21/2017.
 */
public class Discussion implements Parcelable {
    private String discussionPoint;
    private String action;
    private String responsible;
    private String deadline;
    private String picture;
    private String picturePath;

    public Discussion() {
    }

    protected Discussion(Parcel in) {
        discussionPoint = in.readString();
        action = in.readString();
        responsible = in.readString();
        deadline = in.readString();
        picture = in.readString();
        picturePath = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(discussionPoint);
        dest.writeString(action);
        dest.writeString(responsible);
        dest.writeString(deadline);
        dest.writeString(picture);
        dest.writeString(picturePath);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Discussion> CREATOR = new Creator<Discussion>() {
        @Override
        public Discussion createFromParcel(Parcel in) {
            return new Discussion(in);
        }

        @Override
        public Discussion[] newArray(int size) {
            return new Discussion[size];
        }
    };

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getDiscussionPoint() {
        return discussionPoint;
    }

    public void setDiscussionPoint(String discussionPoint) {
        this.discussionPoint = discussionPoint;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
