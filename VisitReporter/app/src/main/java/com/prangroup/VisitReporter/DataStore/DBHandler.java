package com.prangroup.VisitReporter.DataStore;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ritu on 12/23/2016.
 */
public class DBHandler extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "rfl.db";
    private static  int DATABASE_VERSION = 1;
    Context context;
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_VISIT_TABLE = "CREATE TABLE " +
                DataContract.VisitEntry.TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                DataContract.VisitEntry.VISIT_NUMBER  + " TEXT," +
                DataContract.VisitEntry.PURPOSE + " TEXT," +
                DataContract.VisitEntry.AREA + " TEXT," +
                DataContract.VisitEntry.CREATE_DATE + " TEXT," +
                DataContract.VisitEntry.CREATE_DATE_TIME + " TEXT," +
                DataContract.VisitEntry.END_DATE + " TEXT," +
                DataContract.VisitEntry.END_DATE_TIME + " TEXT," +
                DataContract.VisitEntry.COMMENT + " TEXT," +
                DataContract.VisitEntry.VISIT_DAYS + " INTEGER," +
                DataContract.VisitEntry.IS_END + " TEXT," +
                DataContract.VisitEntry.IS_SYNCED + " INTEGER )";
        String CREATE_GEO_TABLE = "CREATE TABLE " +
                DataContract.GeoEntry.TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                DataContract.GeoEntry.COLUMN_ID  + " INTEGER," +
                DataContract.GeoEntry.DISTRICT_ID + " INTEGER," +
                DataContract.GeoEntry.DISTRICT_NAME + " TEXT," +
                DataContract.GeoEntry.THANA_ID + " INTEGER," +
                DataContract.GeoEntry.THANA_NAME + " TEXT," +
                DataContract.GeoEntry.TOKEN + " TEXT )";
        String CREATE_ZONE_TABLE = "CREATE TABLE " +
                DataContract.ZoneEntry.TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                DataContract.ZoneEntry.COLUMN_ID  + " INTEGER," +
                DataContract.ZoneEntry.ZONE_ID + " INTEGER," +
                DataContract.ZoneEntry.ZONE_NAME + " TEXT," +
                DataContract.ZoneEntry.TOKEN + " TEXT )";
        String CREATE_REPORT_TABLE = "CREATE TABLE " +
                DataContract.ReportEntry.TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                DataContract.ReportEntry.VISIT_NUMBER  + " INTEGER," +
                DataContract.ReportEntry.USER_ID + " INTEGER," +
                DataContract.ReportEntry.DISTRICT_ID + " INTEGER," +
                DataContract.ReportEntry.THANA_ID + " INTEGER," +
                DataContract.ReportEntry.ZONE_ID + " INTEGER," +
                DataContract.ReportEntry.BAZAR_NAME + " TEXT," +
                DataContract.ReportEntry.PROPRIETOR_NAME + " TEXT," +
                DataContract.ReportEntry.PROPRIETOR_CODE + " TEXT," +
                DataContract.ReportEntry.PROPRIETOR_ADDRESS + " TEXT," +
                DataContract.ReportEntry.PROPRIETOR_OWNER + " TEXT," +
                DataContract.ReportEntry.PROPRIETOR_CONTRACT + " TEXT," +
                DataContract.ReportEntry.LAT + " TEXT," +
                DataContract.ReportEntry.LON + " TEXT," +
                DataContract.ReportEntry.IS_SYNCED + " INTEGER )";

        String CREATE_DISCUSSION_TABLE = "CREATE TABLE " +
                DataContract.DiscussionEntry.TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                DataContract.DiscussionEntry.VISIT_NUMBER  + " TEXT," +
                DataContract.DiscussionEntry.DISCUSSION_POINT + " TEXT," +
                DataContract.DiscussionEntry.ACTION + " TEXT," +
                DataContract.DiscussionEntry.RESPONSIBLE + " TEXT," +
                DataContract.DiscussionEntry.DEADLINE + " TEXT," +
                DataContract.DiscussionEntry.PICTURE + " TEXT," +
                DataContract.ReportEntry.IS_SYNCED + " INTEGER )";

        String CREATE_IMAGE_TABLE = "CREATE TABLE " +
                DataContract.ImageEntry.TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                DataContract.ImageEntry.IMAGE_NAME  + " TEXT," +
                DataContract.ImageEntry.IMAGE_PATH + " TEXT," +
                DataContract.ImageEntry.IS_SYNCED + " INTEGER )";

        db.execSQL(CREATE_VISIT_TABLE);
        db.execSQL(CREATE_GEO_TABLE);
        db.execSQL(CREATE_ZONE_TABLE);
        db.execSQL(CREATE_REPORT_TABLE);
        db.execSQL(CREATE_DISCUSSION_TABLE);
        db.execSQL(CREATE_IMAGE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DataContract.VisitEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DataContract.GeoEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DataContract.ZoneEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DataContract.ReportEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DataContract.DiscussionEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DataContract.ImageEntry.TABLE_NAME);
        onCreate(db);
    }



}
