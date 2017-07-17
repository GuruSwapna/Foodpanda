package com.example.enchanterswapna.foodpanda;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by enchanterswapna on 17/7/17.
 */

public class userdetailslist implements Parcelable {

    String ffname;
    String flname;
    String femail;
    String fnumber;

    public String getFfname() {
        return ffname;
    }

    public void setFfname(String ffname) {
        this.ffname = ffname;
    }

    public String getFlname() {
        return flname;
    }

    public void setFlname(String flname) {
        this.flname = flname;
    }

    public String getFemail() {
        return femail;
    }

    public void setFemail(String femail) {
        this.femail = femail;
    }

    public String getFnumber() {
        return fnumber;
    }

    public void setFnumber(String fnumber) {
        this.fnumber = fnumber;
    }

    protected userdetailslist(Parcel in) {
        ffname = in.readString();
        flname = in.readString();
        femail = in.readString();
        fnumber = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ffname);
        dest.writeString(flname);
        dest.writeString(femail);
        dest.writeString(fnumber);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<userdetailslist> CREATOR = new Creator<userdetailslist>() {
        @Override
        public userdetailslist createFromParcel(Parcel in) {
            return new userdetailslist(in);
        }

        @Override
        public userdetailslist[] newArray(int size) {
            return new userdetailslist[size];
        }
    };
}
