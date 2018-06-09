package com.parg.bartian.domain;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by ganga_r on 10/15/2016.
 */

@Root(strict = false, name = "station")
public class Station {
    public Station() {}
    @Element(name = "name", required = false)
    private String name;

    @Element(name = "abbr", required = false)
    private String abbr;

    @Element(name = "gtfs_latitude", required = false)
    private double latitude;

    @Element(name = "gtfs_longitude", required = false)
    private double longitude;

    @Element(name = "address", required = false)
    private String address;

    @Element(name = "city", required = false)
    private String city;

    @Element(name = "county", required = false)
    private String county;

    @Element(name = "state", required = false)
    private String state;

    @Element(name = "zipcode", required = false)
    private String zipcode;

    @ElementList(entry = "etd", inline=true, required = false)
    private List<Etd> etdList;


    public String getZipcode() {
        return zipcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCounty() {
        return county;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return name;
    }

    public List<Etd> getEtdList() {
        return etdList;
    }


    /*@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeString(name);
        out.writeString(abbr);
        out.writeDouble(latitude);
        out.writeDouble(longitude);
        out.writeString(address);
        out.writeString(city);
        out.writeString(county);
        out.writeString(state);
        out.writeString(zipcode);
        out.writeTypedList(etdList);
    }

    public Station(Parcel in) {
        name = in.readString();
        abbr = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        address = in.readString();
        city = in.readString();
        county = in.readString();
        state = in.readString();
        zipcode = in.readString();
        in.readTypedList(etdList, Etd.CREATOR);
    }

    public static final Parcelable.Creator<Station> CREATOR
            = new Parcelable.Creator<Station>() {
        public Station createFromParcel(Parcel in) {
            return new Station(in);
        }

        public Station[] newArray(int size) {
            return new Station[size];
        }
    };*/
}
