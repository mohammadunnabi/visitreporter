package com.prangroup.VisitReporter.BusinessObjects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Manik on 4/21/2017.
 */
public class Thana implements Parcelable {
    private int districtId;
    private int thanaId;
    private String thanaName;

    public Thana() {
    }

    protected Thana(Parcel in) {
        districtId = in.readInt();
        thanaId = in.readInt();
        thanaName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(districtId);
        dest.writeInt(thanaId);
        dest.writeString(thanaName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Thana> CREATOR = new Creator<Thana>() {
        @Override
        public Thana createFromParcel(Parcel in) {
            return new Thana(in);
        }

        @Override
        public Thana[] newArray(int size) {
            return new Thana[size];
        }
    };

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public int getThanaId() {
        return thanaId;
    }

    public void setThanaId(int thanaId) {
        this.thanaId = thanaId;
    }

    public String getThanaName() {
        return thanaName;
    }

    public void setThanaName(String thanaName) {
        this.thanaName = thanaName;
    }
}
