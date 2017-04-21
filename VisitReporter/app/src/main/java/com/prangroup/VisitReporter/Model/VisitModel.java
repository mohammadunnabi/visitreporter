package com.prangroup.VisitReporter.Model;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;

import com.prangroup.VisitReporter.BusinessObjects.Visit;
import com.prangroup.VisitReporter.BusinessObjects.VisitList;
import com.prangroup.VisitReporter.DataStore.DataContract;

/**
 * Created by Manik on 4/20/2017.
 */
public class VisitModel {
    ContentResolver contentResolver;

    public VisitModel(ContentResolver contentResolver) {
        this.contentResolver=contentResolver;
    }
    public void insertVisit(Visit visit) {
        ContentValues values = new ContentValues();
        values.put(DataContract.VisitEntry.VISIT_NUMBER, visit.getVisitNumber());
        values.put(DataContract.VisitEntry.PURPOSE, visit.getPurpose());
        values.put(DataContract.VisitEntry.AREA, visit.getArea());
        values.put(DataContract.VisitEntry.VISIT_DAYS, visit.getVisitDays());
        values.put(DataContract.VisitEntry.CREATE_DATE, visit.getCreateDate());
        values.put(DataContract.VisitEntry.CREATE_DATE_TIME, visit.getCreateDateTime());
        values.put(DataContract.VisitEntry.IS_SYNCED, 0);
        values.put(DataContract.VisitEntry.IS_END, 0);
        contentResolver.insert(DataContract.VisitEntry.CONTENT_URI, values);
    }
    public void updateVisit(Visit visit) {
        ContentValues values = new ContentValues();
        values.put(DataContract.VisitEntry.END_DATE, visit.getEndDate());
        values.put(DataContract.VisitEntry.END_DATE_TIME, visit.getEndDateTime());
        values.put(DataContract.VisitEntry.COMMENT, visit.getComment());
        values.put(DataContract.VisitEntry.IS_END, 1);
        values.put(DataContract.VisitEntry.IS_SYNCED, 0);
        contentResolver.update(DataContract.VisitEntry.CONTENT_URI, values,DataContract.VisitEntry.VISIT_NUMBER+"='"+visit.getVisitNumber()+"'",null);
    }
    public VisitList getVisits(){
        VisitList visitList=new VisitList();
        String [] projecion={
                DataContract.VisitEntry.VISIT_NUMBER,
                DataContract.VisitEntry.PURPOSE,
                DataContract.VisitEntry.AREA,
                DataContract.VisitEntry.VISIT_DAYS,
                DataContract.VisitEntry.CREATE_DATE,
                DataContract.VisitEntry.CREATE_DATE_TIME,
                DataContract.VisitEntry.END_DATE,
                DataContract.VisitEntry.END_DATE_TIME,
                DataContract.VisitEntry.COMMENT,
                DataContract.VisitEntry.IS_END,

        };
        Cursor cursor= contentResolver.query(DataContract.VisitEntry.CONTENT_URI,projecion,null,null,null);
        if (cursor.moveToFirst()) {
            do{
                Visit visit=new Visit();
                visit.setVisitNumber(cursor.getString(0));
                visit.setPurpose(cursor.getString(1));
                visit.setArea(cursor.getString(2));
                visit.setVisitDays(cursor.getInt(3));
                visit.setCreateDate(cursor.getString(4));
                visit.setCreateDateTime(cursor.getString(5));
                visit.setEndDate(cursor.getString(6));
                visit.setEndDateTime(cursor.getString(7));
                visit.setComment(cursor.getString(8));
                visit.setEnd(cursor.getInt(9)==0?false:true);
                visitList.add(visit);
            } while (cursor.moveToNext());
        }
        return visitList;
    }


}
