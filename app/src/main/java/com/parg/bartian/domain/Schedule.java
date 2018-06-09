package com.parg.bartian.domain;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by ganga_r on 10/23/2016.
 */

@Root(strict = false, name = "schedule")
public class Schedule {

    public Schedule() {}

    @Element(name = "date")
    private String date;

    @Element(name = "time")
    private String time;


    @Element(name = "request", required = false)
    private Request request;

    public Request getRequest() {
        return request;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
