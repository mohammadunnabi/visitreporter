package com.prangroup.VisitReporter.Model;

import android.content.ContentResolver;
import android.database.Cursor;

import com.prangroup.VisitReporter.BusinessObjects.District;
import com.prangroup.VisitReporter.BusinessObjects.DistrictList;
import com.prangroup.VisitReporter.BusinessObjects.Thana;
import com.prangroup.VisitReporter.BusinessObjects.ThanaList;
import com.prangroup.VisitReporter.DataStore.DataContract;

/**
 * Created by Manik on 4/21/2017.
 */
public class GeoModel {
    ContentResolver contentResolver;

    public GeoModel(ContentResolver contentResolver) {
        this.contentResolver=contentResolver;
    }

    public DistrictList getDisrict(){
        DistrictList districtList=new DistrictList();
        String [] projecion={
               "DISTINCT "+ DataContract.GeoEntry.DISTRICT_ID,
                DataContract.GeoEntry.DISTRICT_NAME,

        };
        Cursor cursor= contentResolver.query(DataContract.GeoEntry.CONTENT_URI,projecion,null,null,null);
        if (cursor.moveToFirst()) {
            do{
                District district=new District();
                district.setDistrictId(cursor.getInt(0));
                district.setDistrictName(cursor.getString(1));

                districtList.add(district);
            } while (cursor.moveToNext());
        }
        return districtList;
    }
    public ThanaList getThana(){
        ThanaList thanaList=new ThanaList();
        String [] projecion={
                DataContract.GeoEntry.DISTRICT_ID,
                DataContract.GeoEntry.THANA_ID,
                DataContract.GeoEntry.THANA_NAME,

        };
        Cursor cursor= contentResolver.query(DataContract.GeoEntry.CONTENT_URI,projecion,null,null,null);
        if (cursor.moveToFirst()) {
            do{
                Thana thana=new Thana();
                thana.setDistrictId(cursor.getInt(0));
                thana.setThanaId(cursor.getInt(1));
                thana.setThanaName(cursor.getString(2));

                thanaList.add(thana);
            } while (cursor.moveToNext());
        }
        return thanaList;
    }
}
