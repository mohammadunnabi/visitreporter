package com.prangroup.VisitReporter.DataStore;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;


/**
 * Created by Ritu on 6/23/2016.
 */
public class DataProvider extends ContentProvider {
    DBHandler db;
    static final int VISIT = 102;
    static final int GEO = 103;
    static final int ZONE = 104;
    static final int REPORT = 105;
    static final int DISCUSSION = 106;
    static final int IMAGE = 107;


    private static final UriMatcher sURIMatcher = buildUriMatcher();
    @Override
    public boolean onCreate() {
        db = new DBHandler(getContext());
        return true;
    }
    static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = DataContract.AUTHORITY;
        matcher.addURI(authority, DataContract.PATH_VISIT, VISIT);
        matcher.addURI(authority, DataContract.PATH_GEO, GEO);
        matcher.addURI(authority, DataContract.PATH_ZONE, ZONE);
        matcher.addURI(authority, DataContract.PATH_REPORT, REPORT);
        matcher.addURI(authority, DataContract.PATH_DISCUSSION, DISCUSSION);
        matcher.addURI(authority, DataContract.PATH_IMAGE, IMAGE);

        return matcher;
    }
    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        String groupBy = null;
        String limit = null;
        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case VISIT:
                queryBuilder.setTables(DataContract.VisitEntry.TABLE_NAME);
                break;
            case GEO:
                queryBuilder.setTables(DataContract.GeoEntry.TABLE_NAME);
                break;
            case ZONE:
                queryBuilder.setTables(DataContract.ZoneEntry.TABLE_NAME);
                break;
            case REPORT:
                queryBuilder.setTables(DataContract.ReportEntry.TABLE_NAME);
                break;
            case DISCUSSION:
                queryBuilder.setTables(DataContract.DiscussionEntry.TABLE_NAME);
                break;
            case IMAGE:
                queryBuilder.setTables(DataContract.ImageEntry.TABLE_NAME);
                break;


            default:
                throw new IllegalArgumentException("Unknown URI");
        }
        Cursor cursor = queryBuilder.query(db.getReadableDatabase(), projection, selection, selectionArgs, groupBy, null, sortOrder, limit);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase writableDb = db.getWritableDatabase();
        long id;
        switch (sURIMatcher.match(uri)) {
            case VISIT:
                id = writableDb.insert(DataContract.VisitEntry.TABLE_NAME, null, values);
                break;
            case GEO:
                id = writableDb.insert(DataContract.GeoEntry.TABLE_NAME, null, values);
                break;
            case ZONE:
                id = writableDb.insert(DataContract.ZoneEntry.TABLE_NAME, null, values);
                break;
            case REPORT:
                id = writableDb.insert(DataContract.ReportEntry.TABLE_NAME, null, values);
                break;
            case DISCUSSION:
                id = writableDb.insert(DataContract.DiscussionEntry.TABLE_NAME, null, values);
                break;
            case IMAGE:
                id = writableDb.insert(DataContract.ImageEntry.TABLE_NAME, null, values);
                break;

            default:
                throw new IllegalArgumentException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);

        return Uri.parse(uri.getPath() + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        SQLiteDatabase writableDb = db.getWritableDatabase();
        switch (sURIMatcher.match(uri)) {
            case VISIT:
                count = writableDb.delete(DataContract.VisitEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case GEO:
                count = writableDb.delete(DataContract.GeoEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case ZONE:
                count = writableDb.delete(DataContract.ZoneEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case REPORT:
                count = writableDb.delete(DataContract.ReportEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case DISCUSSION:
                count = writableDb.delete(DataContract.DiscussionEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case IMAGE:
                count = writableDb.delete(DataContract.ImageEntry.TABLE_NAME, selection, selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count = 0;
        SQLiteDatabase writableDb = db.getWritableDatabase();
        switch (sURIMatcher.match(uri)) {
            case VISIT:
                count = writableDb.update(DataContract.VisitEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case GEO:
                count = writableDb.update(DataContract.GeoEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case ZONE:
                count = writableDb.update(DataContract.ZoneEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case REPORT:
                count = writableDb.update(DataContract.ReportEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case DISCUSSION:
                count = writableDb.update(DataContract.DiscussionEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case IMAGE:
                count = writableDb.update(DataContract.ImageEntry.TABLE_NAME, values, selection, selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }



}
