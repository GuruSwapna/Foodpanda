package com.example.enchanterswapna.foodpanda;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by enchanterswapna on 13/7/17.
 */

public class dealivlist implements Parcelable {
    String ptypes;
    String pptypes;
    String pcost;

    public String getPtypes() {
        return ptypes;
    }

    public void setPtypes(String ptypes) {
        this.ptypes = ptypes;
    }

    public String getPptypes() {
        return pptypes;
    }

    public void setPptypes(String pptypes) {
        this.pptypes = pptypes;
    }

    public String getPcost() {
        return pcost;
    }

    public void setPcost(String pcost) {
        this.pcost = pcost;
    }

    protected dealivlist(Parcel in) {
        ptypes = in.readString();
        pptypes = in.readString();
        pcost = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ptypes);
        dest.writeString(pptypes);
        dest.writeString(pcost);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<dealivlist> CREATOR = new Creator<dealivlist>() {
        @Override
        public dealivlist createFromParcel(Parcel in) {
            return new dealivlist(in);
        }

        @Override
        public dealivlist[] newArray(int size) {
            return new dealivlist[size];
        }
    };
}
