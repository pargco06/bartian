package com.parg.bartian.domain;

/**
 * Created by ganga_r on 10/23/2016.
 */

public class Fare {

    private String name;
    private String amount;
    private String classType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassType() {
        return classType;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }
}
