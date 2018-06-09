package com.parg.bartian.domain;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by ganga_r on 10/23/2016.
 */

@Root(strict = false)
public class Request {

    @ElementList(entry = "trip", inline=true, type=Trip.class, name = "request", required = false)
    private List<Trip> trip;


    public List<Trip> getTrip() {
        return trip;
    }
}
