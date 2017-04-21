package com.prangroup.VisitReporter.Model;

import android.content.ContentResolver;
import android.content.ContentValues;

import com.prangroup.VisitReporter.BusinessObjects.Discussion;
import com.prangroup.VisitReporter.BusinessObjects.Entry;
import com.prangroup.VisitReporter.DataStore.DataContract;

/**
 * Created by Manik on 4/21/2017.
 */
public class EntryModel {
    ContentResolver contentResolver;

    public EntryModel(ContentResolver contentResolver) {
        this.contentResolver=contentResolver;
    }

    public void insertEntry(Entry entry,int userid) {
        ContentValues values = new ContentValues();
        values.put(DataContract.ReportEntry.VISIT_NUMBER, entry.getVisit().getVisitNumber());
        values.put(DataContract.ReportEntry.USER_ID, userid);
        values.put(DataContract.ReportEntry.DISTRICT_ID, entry.getDistrict().getDistrictId());
        values.put(DataContract.ReportEntry.THANA_ID, entry.getThana().getThanaId());
        values.put(DataContract.ReportEntry.ZONE_ID, entry.getZone().getZoneId());
        values.put(DataContract.ReportEntry.BAZAR_NAME, entry.getBazarName());
        values.put(DataContract.ReportEntry.PROPRIETOR_NAME, entry.getProprietorName());
        values.put(DataContract.ReportEntry.PROPRIETOR_CODE, entry.getProprietorCode());
        values.put(DataContract.ReportEntry.PROPRIETOR_ADDRESS, entry.getProprietorAddress());
        values.put(DataContract.ReportEntry.PROPRIETOR_OWNER, entry.getProprietorOwnerName());
        values.put(DataContract.ReportEntry.PROPRIETOR_CONTRACT, entry.getProprietorContact());
        values.put(DataContract.ReportEntry.LAT, entry.getLat());
        values.put(DataContract.ReportEntry.LON, entry.getLon());
        values.put(DataContract.ReportEntry.IS_SYNCED, 0);
        contentResolver.insert(DataContract.ReportEntry.CONTENT_URI, values);
    }
    public void insertDiscussion(Entry entry) {
        for (Discussion discussion:entry.getDiscussionList().getDiscussionArray() ) {
            ContentValues values = new ContentValues();
            values.put(DataContract.DiscussionEntry.VISIT_NUMBER,entry.getVisit().getVisitNumber());
            values.put(DataContract.DiscussionEntry.DISCUSSION_POINT, discussion.getDiscussionPoint());
            values.put(DataContract.DiscussionEntry.ACTION, discussion.getAction());
            values.put(DataContract.DiscussionEntry.RESPONSIBLE, discussion.getResponsible());
            values.put(DataContract.DiscussionEntry.DEADLINE, discussion.getDeadline());
            values.put(DataContract.DiscussionEntry.PICTURE, discussion.getPicture());
            values.put(DataContract.DiscussionEntry.IS_SYNCED, 0);
            contentResolver.insert(DataContract.DiscussionEntry.CONTENT_URI, values);
        }

    }
}
