package com.parg.bartian.domain.weather.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by ganga_r on 1/29/2017.
 */

@Root(strict = false)
public class ConditionsIcon {
    @Element(name="icon-link", required = false)
    private String conditionsIconLink;

    public String getConditionsIconLink() {
        return conditionsIconLink;
    }

    public void setConditionsIconLink(String conditionsIconLink) {
        this.conditionsIconLink = conditionsIconLink;
    }
}
