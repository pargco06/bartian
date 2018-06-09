package com.parg.bartian.domain;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by ganga_r on 5/28/2017.
 */

@Root(strict = false)
public class RealTimeInformation {

    @Element(name = "date")
    private String date;

    @Element(name = "time")
    private String time;

    @Element(name = "station", required = false)
    private Station station;

    public Station getStation() {
        return station;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
