package com.parg.bartian.domain.weather.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by ganga_r on 1/28/2017.
 */

@Root(strict = false)
public class Temperature {
    @Element(name = "name")
    private String name;

    @Element(name = "value")
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
