package com.prangroup.VisitReporter.BusinessObjects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Manik on 4/21/2017.
 */
public class Zone implements Parcelable{
   private int zoneId;
   private String zoneName;

    public Zone() {
    }

    protected Zone(Parcel in) {
        zoneId = in.readInt();
        zoneName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(zoneId);
        dest.writeString(zoneName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Zone> CREATOR = new Creator<Zone>() {
        @Override
        public Zone createFromParcel(Parcel in) {
            return new Zone(in);
        }

        @Override
        public Zone[] newArray(int size) {
            return new Zone[size];
        }
    };

    public int getZoneId() {
        return zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }
}
