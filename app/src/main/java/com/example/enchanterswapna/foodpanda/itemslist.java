package com.example.enchanterswapna.foodpanda;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by enchanterswapna on 12/7/17.
 */

public class itemslist implements Parcelable {
    String item_name;
    String item_text;
    String item_cost;
    String item_time;
    String item_image;

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_text() {
        return item_text;
    }

    public void setItem_text(String item_text) {
        this.item_text = item_text;
    }

    public String getItem_cost() {
        return item_cost;
    }

    public void setItem_cost(String item_cost) {
        this.item_cost = item_cost;
    }

    public String getItem_time() {
        return item_time;
    }

    public void setItem_time(String item_time) {
        this.item_time = item_time;
    }

    public String getItem_image() {
        return item_image;
    }

    public void setItem_image(String item_image) {
        this.item_image = item_image;
    }

    protected itemslist(Parcel in) {
        item_name = in.readString();
        item_text = in.readString();
        item_cost = in.readString();
        item_time = in.readString();
        item_image = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(item_name);
        dest.writeString(item_text);
        dest.writeString(item_cost);
        dest.writeString(item_time);
        dest.writeString(item_image);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<itemslist> CREATOR = new Creator<itemslist>() {
        @Override
        public itemslist createFromParcel(Parcel in) {
            return new itemslist(in);
        }

        @Override
        public itemslist[] newArray(int size) {
            return new itemslist[size];
        }
    };
}
