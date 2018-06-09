package com.parg.bartian.domain;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class Advisory {

    @Element(name = "bsa")
    private Bsa bsa;

    public Bsa getBsa() {
        return bsa;
    }
}
