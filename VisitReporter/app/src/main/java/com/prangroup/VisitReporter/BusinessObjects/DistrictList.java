package com.prangroup.VisitReporter.BusinessObjects;

import java.util.ArrayList;

/**
 * Created by Manik on 4/21/2017.
 */
public class DistrictList {
    ArrayList<District> districtArray=new ArrayList<>();

    public ArrayList<District> getDistrictArray() {
        return districtArray;
    }

    public void setDistrictArray(ArrayList<District> districtArray) {
        this.districtArray.clear();
        for (District dis:districtArray             ) {
            this.districtArray.add(dis);
        }
    }

    public void add(District district) {
        this.districtArray.add(district);
    }
}
