package com.prangroup.VisitReporter.BusinessObjects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Manik on 4/20/2017.
 */
public class Visit implements Parcelable {
    private String visitNumber;
    private String purpose;
    private String area;
    private int visitDays;
    private String createDate;
    private String createDateTime;
    private String endDate;
    private String endDateTime;
    private String comment;
    private boolean isEnd;

    public Visit() {
    }


    protected Visit(Parcel in) {
        visitNumber = in.readString();
        purpose = in.readString();
        area = in.readString();
        visitDays = in.readInt();
        createDate = in.readString();
        createDateTime = in.readString();
        endDate = in.readString();
        endDateTime = in.readString();
        comment = in.readString();
        isEnd = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(visitNumber);
        dest.writeString(purpose);
        dest.writeString(area);
        dest.writeInt(visitDays);
        dest.writeString(createDate);
        dest.writeString(createDateTime);
        dest.writeString(endDate);
        dest.writeString(endDateTime);
        dest.writeString(comment);
        dest.writeByte((byte) (isEnd ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Visit> CREATOR = new Creator<Visit>() {
        @Override
        public Visit createFromParcel(Parcel in) {
            return new Visit(in);
        }

        @Override
        public Visit[] newArray(int size) {
            return new Visit[size];
        }
    };

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getVisitNumber() {
        return visitNumber;
    }

    public void setVisitNumber(String visitNumber) {
        this.visitNumber = visitNumber;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getVisitDays() {
        return visitDays;
    }

    public void setVisitDays(int visitDays) {
        this.visitDays = visitDays;
    }

    public String getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }
}
