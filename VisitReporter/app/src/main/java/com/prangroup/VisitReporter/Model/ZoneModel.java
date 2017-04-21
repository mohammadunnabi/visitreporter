package com.prangroup.VisitReporter.Model;

import android.content.ContentResolver;
import android.database.Cursor;

import com.prangroup.VisitReporter.BusinessObjects.Zone;
import com.prangroup.VisitReporter.BusinessObjects.ZoneList;
import com.prangroup.VisitReporter.DataStore.DataContract;

/**
 * Created by Manik on 4/21/2017.
 */
public class ZoneModel {
    ContentResolver contentResolver;
    public ZoneModel(ContentResolver contentResolver) {
        this.contentResolver=contentResolver;
    }

    public ZoneList getZone(){
        ZoneList zoneList=new ZoneList();
        String [] projecion={
                "DISTINCT "+ DataContract.ZoneEntry.ZONE_ID,
                DataContract.ZoneEntry.ZONE_NAME,
        };
        Cursor cursor= contentResolver.query(DataContract.ZoneEntry.CONTENT_URI,projecion,null,null,null);
        if (cursor.moveToFirst()) {
            do{
                Zone zone=new Zone();
                zone.setZoneId(cursor.getInt(0));
                zone.setZoneName(cursor.getString(1));
                zoneList.add(zone);
            } while (cursor.moveToNext());
        }
        return zoneList;
    }
}
