package com.parg.bartian.domain;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by ganga_r on 10/15/2016.
 */

@Root(strict = false)
public class Stations {
    public List<Station> getStations() {
        return stations;
    }

    @ElementList(entry = "stations", required = false)
    private List<Station> stations;

}
