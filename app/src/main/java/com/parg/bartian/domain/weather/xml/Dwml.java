package com.parg.bartian.domain.weather.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by ganga_r on 1/29/2017.
 */

@Root(strict = false, name="dwml")
public class Dwml {

    @Element(name = "data")
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
