package com.prangroup.VisitReporter.BusinessObjects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Manik on 4/20/2017.
 */
public class Entry implements Parcelable {
    private Visit visit;
    private int visitType;
    private District district;
    private Thana thana;
    private Zone zone;
    private String bazarName;
    private String proprietorName;
    private String proprietorCode;
    private String proprietorAddress;
    private String proprietorOwnerName;
    private String proprietorContact;
    private String image;
    private String lat;
    private String lon;
    DiscussionList discussionList=new DiscussionList();

    public Entry() {
    }


    protected Entry(Parcel in) {
        visit = in.readParcelable(Visit.class.getClassLoader());
        visitType = in.readInt();
        district = in.readParcelable(District.class.getClassLoader());
        thana = in.readParcelable(Thana.class.getClassLoader());
        zone = in.readParcelable(Zone.class.getClassLoader());
        bazarName = in.readString();
        proprietorName = in.readString();
        proprietorCode = in.readString();
        proprietorAddress = in.readString();
        proprietorOwnerName = in.readString();
        proprietorContact = in.readString();
        image = in.readString();
        lat = in.readString();
        lon = in.readString();
        discussionList = in.readParcelable(DiscussionList.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(visit, flags);
        dest.writeInt(visitType);
        dest.writeParcelable(district, flags);
        dest.writeParcelable(thana, flags);
        dest.writeParcelable(zone, flags);
        dest.writeString(bazarName);
        dest.writeString(proprietorName);
        dest.writeString(proprietorCode);
        dest.writeString(proprietorAddress);
        dest.writeString(proprietorOwnerName);
        dest.writeString(proprietorContact);
        dest.writeString(image);
        dest.writeString(lat);
        dest.writeString(lon);
        dest.writeParcelable(discussionList, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Entry> CREATOR = new Creator<Entry>() {
        @Override
        public Entry createFromParcel(Parcel in) {
            return new Entry(in);
        }

        @Override
        public Entry[] newArray(int size) {
            return new Entry[size];
        }
    };

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Visit getVisit() {
        return visit;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
    }

    public int getVisitType() {
        return visitType;
    }

    public void setVisitType(int visitType) {
        this.visitType = visitType;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Thana getThana() {
        return thana;
    }

    public void setThana(Thana thana) {
        this.thana = thana;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public String getBazarName() {
        return bazarName;
    }

    public void setBazarName(String bazarName) {
        this.bazarName = bazarName;
    }

    public String getProprietorName() {
        return proprietorName;
    }

    public void setProprietorName(String proprietorName) {
        this.proprietorName = proprietorName;
    }

    public String getProprietorCode() {
        return proprietorCode;
    }

    public void setProprietorCode(String proprietorCode) {
        this.proprietorCode = proprietorCode;
    }

    public String getProprietorAddress() {
        return proprietorAddress;
    }

    public void setProprietorAddress(String proprietorAddress) {
        this.proprietorAddress = proprietorAddress;
    }

    public String getProprietorOwnerName() {
        return proprietorOwnerName;
    }

    public void setProprietorOwnerName(String proprietorOwnerName) {
        this.proprietorOwnerName = proprietorOwnerName;
    }

    public String getProprietorContact() {
        return proprietorContact;
    }

    public void setProprietorContact(String proprietorContact) {
        this.proprietorContact = proprietorContact;
    }

    public DiscussionList getDiscussionList() {
        return discussionList;
    }

    public void setDiscussionList(DiscussionList discussionList) {
        this.discussionList = discussionList;
    }
}
