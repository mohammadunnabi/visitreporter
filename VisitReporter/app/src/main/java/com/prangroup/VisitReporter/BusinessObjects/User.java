package com.prangroup.VisitReporter.BusinessObjects;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Manik on 4/21/2017.
 */
public class User {
    Context context;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    private static final String IS_LOGIN = "is_login";
    private static final String USER_ID = "userId";
    private static final String STAFF_ID = "staffId";
    private static final String FULL_NAME = "full_name";
    private static final String MOBILE = "mobile";
    private static final String DEPARTMENT = "department";
    private static final String GROUP = "group";
    private static final String COMPANY = "company";
    public User(Context context) {
        this.context=context;
        sharedpreferences  = context.getSharedPreferences("userSession", 0);
        editor= sharedpreferences.edit();
    }
    public void crateUser(int userId, int staffId, String fullName, String mobile, String department, String group, String company){
        editor.putInt(USER_ID, userId);
        editor.putInt(STAFF_ID, staffId);
        editor.putString(FULL_NAME, fullName);
        editor.putString(MOBILE, mobile);
        editor.putString(DEPARTMENT, department);
        editor.putString(GROUP, group);
        editor.putString(COMPANY, company);
        editor.putBoolean(IS_LOGIN, true);
        editor.commit();
    }

    public int getUserId() {
        return sharedpreferences.getInt(USER_ID, 0);
    }
    public int getStaffId() {
         return sharedpreferences.getInt(STAFF_ID, 0);
    }
    public String getFullName() {
        return sharedpreferences.getString(FULL_NAME, null);
    }
    public String getMobile() {
      return sharedpreferences.getString(MOBILE, null);
    }
    public String getDepartment() {
        return sharedpreferences.getString(DEPARTMENT, null);
    }
    public String getGroup() {
        return sharedpreferences.getString(GROUP, null);
    }
    public String getCompany() {
        return sharedpreferences.getString(COMPANY, null);
    }
    public void clearUser(){
        editor.clear();
        editor.commit();
    }
    public boolean isLogin(){
        return sharedpreferences.getBoolean(IS_LOGIN, false);
    }
}
