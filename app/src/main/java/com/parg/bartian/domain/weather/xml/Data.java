package com.parg.bartian.domain.weather.xml;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by ganga_r on 1/28/2017.
 */

@Root(strict = false)
public class Data {

    @ElementList(name = "parameters", inline=true, required = false)
    private List<Parameters> parameters;

    public List<Parameters> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameters> parameters) {
        this.parameters = parameters;
    }
}
