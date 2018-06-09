package com.parg.bartian.domain.weather.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by ganga_r on 1/28/2017.
 */

@Root(strict = false)
public class Parameters {

    @ElementList(entry = "temperature", inline=true, type=Temperature.class, required = false)
    private List<Temperature> temperature;

    @Element(name="conditions-icon", required = false)
    private ConditionsIcon conditionsIcon;

    public List<Temperature> getTemperature() {
        return temperature;
    }

    public void setTemperature(List<Temperature> temperature) {
        this.temperature = temperature;
    }

    public ConditionsIcon getConditionsIcon() {
        return conditionsIcon;
    }

    public void setConditionsIcon(ConditionsIcon conditionsIcon) {
        this.conditionsIcon = conditionsIcon;
    }
}
