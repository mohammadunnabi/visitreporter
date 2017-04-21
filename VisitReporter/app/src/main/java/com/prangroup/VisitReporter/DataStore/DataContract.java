package com.prangroup.VisitReporter.DataStore;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Ritu on 6/23/2016.
 */
public class DataContract {
    public static final String AUTHORITY = "com.prangroup.VisitReporter.DataStore";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PATH_VISIT = "tbl_visit";
    public static final String PATH_GEO = "tbl_geo";
    public static final String PATH_ZONE = "tbl_zone";
    public static final String PATH_REPORT = "tbl_report";
    public static final String PATH_DISCUSSION = "tbl_discussion";
    public static final String PATH_IMAGE = "tbl_image";

    public static Uri getURI(String tableName) {
        return BASE_CONTENT_URI.buildUpon().appendPath(tableName).build();
    }


    public static class VisitEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_VISIT).build();
        public static final String TABLE_NAME = "tbl_visit";
        public static final String USER_ID = "user_id";
        public static final String VISIT_NUMBER = "visit_number";
        public static final String PURPOSE = "purpose";
        public static final String AREA = "area";
        public static final String VISIT_DAYS = "visit_days";
        public static final String CREATE_DATE = "create_date";
        public static final String CREATE_DATE_TIME = "create_date_time";
        public static final String END_DATE = "end_date";
        public static final String END_DATE_TIME = "end_date_time";
        public static final String COMMENT = "comment";
        public static final String IS_END = "is_end";
        public static final String IS_SYNCED = "is_synced";

    }
    public static class GeoEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_GEO).build();
        public static final String TABLE_NAME = "tbl_geo";
        public static final String COLUMN_ID = "column_id";
        public static final String DISTRICT_ID = "district_id";
        public static final String DISTRICT_NAME = "district_name";
        public static final String THANA_ID = "thana_id";
        public static final String THANA_NAME = "thana_name";
        public static final String TOKEN = "token";
    }
    public static class ZoneEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_ZONE).build();
        public static final String TABLE_NAME = "tbl_zone";
        public static final String COLUMN_ID = "column_id";
        public static final String ZONE_NAME = "zone_name";
        public static final String ZONE_ID = "zone_id";
        public static final String TOKEN = "token";
    }
    public static class ReportEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_REPORT).build();
        public static final String TABLE_NAME = "tbl_report";
        public static final String VISIT_NUMBER = "visit_number";
        public static final String USER_ID = "user_id";
        public static final String DISTRICT_ID = "district_id";
        public static final String THANA_ID = "thana_id";
        public static final String ZONE_ID = "zone_id";
        public static final String BAZAR_NAME = "bazar_name";
        public static final String PROPRIETOR_NAME = "proprietor_name";
        public static final String PROPRIETOR_CODE = "proprietor_code";
        public static final String PROPRIETOR_ADDRESS = "proprietor_address";
        public static final String PROPRIETOR_OWNER = "proprietor_owner";
        public static final String PROPRIETOR_CONTRACT = "proprietor_contact";
        public static final String LAT = "lat";
        public static final String LON = "lon";
        public static final String IS_SYNCED = "is_synced";
    }
    public static class DiscussionEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_DISCUSSION).build();
        public static final String TABLE_NAME = "tbl_discussion";
        public static final String VISIT_NUMBER = "visit_number";
        public static final String DISCUSSION_POINT = "discussion_point";
        public static final String ACTION = "action";
        public static final String RESPONSIBLE = "responsible";
        public static final String DEADLINE = "deadline";
        public static final String PICTURE = "picture";
        public static final String IS_SYNCED = "is_synced";
    }
    public static class ImageEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_IMAGE).build();
        public static final String TABLE_NAME = "tbl_image";
        public static final String IMAGE_NAME = "image_name";
        public static final String IMAGE_PATH = "image_path";
        public static final String IS_SYNCED = "is_synced";
    }



}
