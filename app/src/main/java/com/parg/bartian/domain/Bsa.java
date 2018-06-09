package com.parg.bartian.domain;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.w3c.dom.CharacterData;

import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by ganga_r on 10/15/2016.
 */

@Element(name = "bsa")
@Root(strict = false)
public class Bsa {

    @Element(name = "description")
    private String description;

    public String getDescription() {
        return description;
    }

}
