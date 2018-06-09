package com.parg.bartian.domain;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by ganga_r on 5/27/2017.
 */

@Root(strict = false, name = "estimate")
public class Estimate {

    @Element(name = "minutes")
    private String minutes;

    @Element(name = "platform")
    private int platform;

    @Element(name = "direction")
    private String direction;

    @Element(name = "length")
    private int length;

    @Element(name = "hexcolor")
    private String hexcolor;

    @Element(name = "bikeflag")
    private int bikeflag;

    public String getMinutes() {
        return minutes;
    }

    public int getPlatform() {
        return platform;
    }

    public String getDirection() {
        return direction;
    }

    public int getLength() {
        return length;
    }

    public String getHexcolor() {
        return hexcolor;
    }

    public int getBikeflag() {
        return bikeflag;
    }

    /*@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeString(minutes);
        out.writeInt(platform);
        out.writeString(direction);
        out.writeInt(length);
        out.writeString(hexcolor);
        out.writeInt(bikeflag);
    }

    private Estimate(Parcel in) {
        minutes = in.readString();
        platform = in.readInt();
        direction = in.readString();
        length = in.readInt();
        hexcolor = in.readString();
        bikeflag = in.readInt();
    }

    public static final Parcelable.Creator<Estimate> CREATOR
            = new Parcelable.Creator<Estimate>() {
        public Estimate createFromParcel(Parcel in) {
            return new Estimate(in);
        }

        public Estimate[] newArray(int size) {
            return new Estimate[size];
        }
    };*/

}
