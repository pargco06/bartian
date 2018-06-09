package com.parg.bartian.domain;

import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ganga_r on 10/31/2016.
 */

public class FareConverter implements Converter<Fares> {

    @Override
    public Fares read(InputNode node) throws Exception {
        List<Fare> fareList = new ArrayList();
        InputNode child = node.getNext("fare");

        while( child != null) {
            if(!child.isEmpty()) {
//                Entry e = new Persister().read(Entry.class, child); // Let the Serializer read the Object
                Fare fare = new Fare();
                fare.setName(child.getAttribute("name").getValue());
                fare.setClassType(child.getAttribute("class").getValue());
                fare.setAmount(child.getAttribute("amount").getValue());
                fareList.add(fare);
            }
            child = node.getNext("fare");
        }
        Fares fares = new Fares();
        fares.setFareList(fareList);
        return fares;
    }

    @Override
    public void write(OutputNode node, Fares value) {}
}
