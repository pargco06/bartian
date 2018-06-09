package com.parg.bartian.domain;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by ganga_r on 10/23/2016.
 */

@Root(strict = false)
public class Trip {

    public Trip() {}

    @Attribute(required = false)
    private String origTimeMin;

    @Attribute(name = "origTimeDate")
    private String origTimeDate;

    @Attribute(name = "destTimeMin")
    private String destTimeMin;

    @Attribute(name = "destTimeDate")
    private String destTimeDate;

    @Attribute(name = "tripTime")
    private String tripTime;

    @Attribute(name = "co2", required = false)
    private String carbonDiOxide;

    @ElementList(entry = "leg", inline=true, type=Leg.class, name = "trip", required = false)
    private List<Leg> leg;

    @Element(name = "fares", required = false)
    private Fares fares;

    public String getOrigTimeMin() {
        return origTimeMin;
    }

    public Fares getFares() {
        return fares;
    }

    public String getOrigTimeDate() {
        return origTimeDate;
    }

    public String getDestTimeMin() {
        return destTimeMin;
    }

    public String getDestTimeDate() {
        return destTimeDate;
    }

    public String getTripTime() {
        return tripTime;
    }

    public String getCarbonDiOxide() {
        return carbonDiOxide;
    }

    public List<Leg> getLeg() {
        return leg;
    }

    /*

    @Attribute(name = "origTimeDate")
    private String origTimeDate;

    @Attribute(name = "destTimeMin")
    private String destTimeMin;

    @Attribute(name = "destTimeDate")
    private String destTimeDate;

    @Attribute(name = "tripTime")
    private String tripTime;

    @Attribute(name = "co2", required = false)
    private String carbonDiOxide;

    */
}
