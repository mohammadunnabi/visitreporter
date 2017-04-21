package com.prangroup.VisitReporter.BusinessObjects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Manik on 4/21/2017.
 */
public class District implements Parcelable{
    private int districtId;
    private String districtName;

    public District() {
    }

    protected District(Parcel in) {
        districtId = in.readInt();
        districtName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(districtId);
        dest.writeString(districtName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<District> CREATOR = new Creator<District>() {
        @Override
        public District createFromParcel(Parcel in) {
            return new District(in);
        }

        @Override
        public District[] newArray(int size) {
            return new District[size];
        }
    };

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}
