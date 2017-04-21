package com.prangroup.VisitReporter.BusinessObjects;

import java.util.ArrayList;

/**
 * Created by Manik on 4/21/2017.
 */
public class ThanaList {
    ArrayList<Thana> thanaArray = new ArrayList<>();

    public ArrayList<Thana> getThanaArray() {
        return this.thanaArray;
    }

    public ArrayList<Thana> getThanaArrayDistrict(int districtId) {
        ArrayList<Thana> thanaArrayList=new ArrayList<>();
        for (Thana thana : this.thanaArray) {
            if (districtId==thana.getDistrictId()){
                thanaArrayList.add(thana);
            }
        }
        return thanaArrayList;
    }

    public void setThanaArray(ArrayList<Thana> thanaArray) {
        this.thanaArray.clear();
        for (Thana thana : thanaArray) {
            this.thanaArray.add(thana);
        }
    }

    public void add(Thana thana) {
        this.thanaArray.add(thana);
    }
}
