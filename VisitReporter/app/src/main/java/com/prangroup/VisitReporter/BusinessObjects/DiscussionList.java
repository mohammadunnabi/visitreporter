package com.prangroup.VisitReporter.BusinessObjects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Manik on 4/21/2017.
 */
public class DiscussionList implements Parcelable {
    ArrayList<Discussion> discussionArray=new ArrayList<>();

    public DiscussionList() {
    }

    protected DiscussionList(Parcel in) {
        discussionArray = in.createTypedArrayList(Discussion.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(discussionArray);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DiscussionList> CREATOR = new Creator<DiscussionList>() {
        @Override
        public DiscussionList createFromParcel(Parcel in) {
            return new DiscussionList(in);
        }

        @Override
        public DiscussionList[] newArray(int size) {
            return new DiscussionList[size];
        }
    };

    public ArrayList<Discussion> getDiscussionArray() {
        return discussionArray;
    }

    public void setDiscussionArray(ArrayList<Discussion> discussionArray) {
        this.discussionArray.clear();
        for (Discussion dis:discussionArray) {
            this.discussionArray.add(dis);
        }
    }

    public void add(Discussion discussion) {
        this.discussionArray.add(discussion);
    }
    public void remove(Discussion discussion){
                Iterator<Discussion> discussionArray = this.discussionArray.iterator();
                while (discussionArray.hasNext()) {
                    Discussion discus = discussionArray.next();
                    if (discus.getDiscussionPoint().equalsIgnoreCase(discussion.getDiscussionPoint())) {
                        discussionArray.remove();
                    }
                }
    }
}
