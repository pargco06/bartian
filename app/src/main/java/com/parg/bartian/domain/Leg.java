package com.parg.bartian.domain;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by ganga_r on 10/23/2016.
 */

@Root(strict = false)
public class Leg {

    @Attribute
    private int order;

    @Attribute
    private String transfercode;

    @Attribute
    private String origin;

    @Attribute
    private String destination;

    @Attribute
    private String origTimeMin;

    @Attribute
    private String origTimeDate;

    @Attribute
    private String destTimeMin;

    @Attribute
    private String destTimeDate;

    @Attribute
    private String bikeflag;

    @Attribute
    private String trainHeadStation;

    public String getBikeflag() {
        return bikeflag;
    }

    public int getOrder() {
        return order;
    }

    public String getTransferCode() {
        return transfercode;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getOriginTimeMin() {
        return origTimeMin;
    }

    public String getOrigTimeDate() {
        return origTimeDate;
    }

    public String getDestTimeMin() {
        return destTimeMin;
    }

    public String getDestTimeDate() {
        return destTimeDate;
    }

    public String getTransfercode() {
        return transfercode;
    }

    public String getOrigTimeMin() {
        return origTimeMin;
    }

    public String getTrainHeadStation() {
        return trainHeadStation;
    }
}
