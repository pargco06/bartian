package com.parg.bartian.domain;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;

import java.util.List;

/**
 * Created by ganga_r on 10/23/2016.
 */

@Root(strict = false)
@Convert(FareConverter.class)
public class Fares {
    @ElementList(entry = "fare", inline=true, required = false)
    private List<Fare> fareList;

    public List<Fare> getFareList() {
        return fareList;
    }

    public void setFareList(List<Fare> fareList) {
        this.fareList = fareList;
    }
}
