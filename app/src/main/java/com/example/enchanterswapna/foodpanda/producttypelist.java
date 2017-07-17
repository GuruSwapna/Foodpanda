package com.example.enchanterswapna.foodpanda;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by enchanterswapna on 15/7/17.
 */

public class producttypelist implements Parcelable {
    String productname;
    String productcat;
    String productcost;

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductcat() {
        return productcat;
    }

    public void setProductcat(String productcat) {
        this.productcat = productcat;
    }

    public String getProductcost() {
        return productcost;
    }

    public void setProductcost(String productcost) {
        this.productcost = productcost;
    }

    protected producttypelist(Parcel in) {
        productname = in.readString();
        productcat = in.readString();
        productcost = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productname);
        dest.writeString(productcat);
        dest.writeString(productcost);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<producttypelist> CREATOR = new Creator<producttypelist>() {
        @Override
        public producttypelist createFromParcel(Parcel in) {
            return new producttypelist(in);
        }

        @Override
        public producttypelist[] newArray(int size) {
            return new producttypelist[size];
        }
    };
}
