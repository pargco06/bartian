package com.parg.bartian.domain;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by ganga_r on 10/15/2016.
 */

@Root(strict = false)
public class Elevator {
    public Elevator(){}

    public String getDate() {
        return date;
    }

    @Element(name = "date")
    private String date;

    @Element(name = "bsa")
    private Bsa bsa;


    public Bsa getBsa() {
        return bsa;
    }
}
