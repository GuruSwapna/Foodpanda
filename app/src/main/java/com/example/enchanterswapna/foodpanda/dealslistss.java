package com.example.enchanterswapna.foodpanda;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by enchanterswapna on 12/7/17.
 */

public class dealslistss implements Parcelable {

    String fimage;
    String foffer;

    public String getFimage() {
        return fimage;
    }

    public void setFimage(String fimage) {
        this.fimage = fimage;
    }

    public String getFoffer() {
        return foffer;
    }

    public void setFoffer(String foffer) {
        this.foffer = foffer;
    }

    protected dealslistss(Parcel in) {
        fimage = in.readString();
        foffer = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fimage);
        dest.writeString(foffer);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<dealslistss> CREATOR = new Creator<dealslistss>() {
        @Override
        public dealslistss createFromParcel(Parcel in) {
            return new dealslistss(in);
        }

        @Override
        public dealslistss[] newArray(int size) {
            return new dealslistss[size];
        }
    };
}
