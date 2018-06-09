package com.parg.bartian.domain;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by ganga_r on 10/26/2016.
 */

@Root(strict = false)
public class ScheduleDetail {

    @Element(name = "schedule", required = false)
    private Schedule schedule;

    public Schedule getSchedule() {
        return schedule;
    }
}
