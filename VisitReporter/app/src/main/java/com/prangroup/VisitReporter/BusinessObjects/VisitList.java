package com.prangroup.VisitReporter.BusinessObjects;

import java.util.ArrayList;

/**
 * Created by Manik on 4/20/2017.
 */
public class VisitList {
    ArrayList<Visit> visitArray=new ArrayList<>();

    public ArrayList<Visit> getVisitArray() {
        return visitArray;
    }

    public void setVisitArray(ArrayList<Visit> visitArray) {
        this.visitArray.clear();
        for (Visit vi:visitArray) {
            this.visitArray.add(vi);
        }
    }
    public void add(Visit visit) {
        this.visitArray.add(visit);
    }
}
