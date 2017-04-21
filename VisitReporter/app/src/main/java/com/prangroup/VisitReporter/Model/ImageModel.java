package com.prangroup.VisitReporter.Model;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;

import com.prangroup.VisitReporter.BusinessObjects.ImageSync;
import com.prangroup.VisitReporter.DataStore.DataContract;

import java.util.HashMap;

/**
 * Created by Manik on 9/17/2016.
 */
public class ImageModel {
    ContentResolver contentResolver;

    public ImageModel(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }
    public void saveImage(String path){
       String imageName = path.substring(path.lastIndexOf("/") + 1, path.length());
        ContentValues values = new ContentValues();
        values.put(DataContract.ImageEntry.IMAGE_NAME, imageName);
        values.put(DataContract.ImageEntry.IMAGE_PATH, path);
        values.put(DataContract.ImageEntry.IS_SYNCED, 0);
        contentResolver.insert(DataContract.ImageEntry.CONTENT_URI, values);
    }


    public HashMap<String,ImageSync> getImages(){
        HashMap<String,ImageSync>  imageHashMap=new HashMap<>();
        String [] projection={
                DataContract.ImageEntry.IMAGE_NAME,
                DataContract.ImageEntry.IMAGE_PATH
        };
        Cursor cursor= contentResolver.query(DataContract.ImageEntry.CONTENT_URI,projection,DataContract.ImageEntry.IS_SYNCED+"=0",null,null);
        if (cursor.moveToFirst()) {
            do{
                ImageSync imageSync=new ImageSync(cursor.getString(0),cursor.getString(1));
                imageHashMap.put(cursor.getString(0),imageSync);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return imageHashMap;
    }

    public void updateSynced(String imageName) {
        ContentValues values = new ContentValues();
        values.put(DataContract.ImageEntry.IS_SYNCED, 1);
        contentResolver.update(DataContract.ImageEntry.CONTENT_URI, values, DataContract.ImageEntry.IMAGE_NAME + "='" + imageName + "'", null);
    }
}
