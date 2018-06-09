package com.parg.bartian.domain;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by ganga_r on 5/27/2017.
 */

@Root(strict = false, name = "etd")
public class Etd {

    @Element(name = "destination")
    private String destination;

    @Element(name = "abbreviation")
    private String abbreviation;

    @Element(name = "limited")
    private int limited;

    @ElementList(entry = "estimate", inline=true, required = false)
    private List<Estimate> estimateList;

    public String getDestination() {
        return destination;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public int getLimited() {
        return limited;
    }

    public List<Estimate> getEstimateList() {
        return estimateList;
    }

    /*@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeString(destination);
        out.writeString(abbreviation);
        out.writeInt(limited);
    }

    public Etd(Parcel in) {
        destination = in.readString();
        abbreviation = in.readString();
        limited = in.readInt();
    }

    public static final Parcelable.Creator<Etd> CREATOR
            = new Parcelable.Creator<Etd>() {
        public Etd createFromParcel(Parcel in) {
            return new Etd(in);
        }

        public Etd[] newArray(int size) {
            return new Etd[size];
        }
    };*/

}
