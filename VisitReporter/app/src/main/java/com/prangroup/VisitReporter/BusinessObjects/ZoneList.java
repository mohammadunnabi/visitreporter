package com.prangroup.VisitReporter.BusinessObjects;

import java.util.ArrayList;

/**
 * Created by Manik on 4/21/2017.
 */
public class ZoneList {

    ArrayList<Zone> zoneArray =new ArrayList<>();

    public ArrayList<Zone> getZoneArray() {
        return  this.zoneArray;
    }

    public void setZoneArray(ArrayList<Zone> zoneArray) {
        this.zoneArray.clear();
        for (Zone zone: zoneArray) {
            this.zoneArray.add(zone);
        }
    }
    public void add(Zone zone) {
        this.zoneArray.add(zone);
    }
}
