package com.parg.bartian.domain;

/**
 * Created by ganga_r on 5/28/2017.
 */

public class ScheduleRealTimeInformation {
    private ScheduleDetail scheduleDetail;
    private RealTimeInformation realTimeInformation;

    public ScheduleRealTimeInformation(ScheduleDetail scheduleDetail, RealTimeInformation realTimeInformation) {
        this.scheduleDetail = scheduleDetail;
        this.realTimeInformation = realTimeInformation;
    }

    public ScheduleDetail getScheduleDetail() {
        return scheduleDetail;
    }

    public RealTimeInformation getRealTimeInformation() {
        return realTimeInformation;
    }
}
