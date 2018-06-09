package com.parg.bartian.ui.util;

import com.parg.bartian.domain.Etd;
import com.parg.bartian.domain.RealTimeInformation;
import com.parg.bartian.domain.Station;

import java.util.List;

/**
 * Created by ganga_r on 1/29/2017.
 */

public enum ScheduleUtil {
    INSTANCE;

    public static ScheduleUtil getInstance() {
        return INSTANCE;
    }
    public int getStationIndex(List<Station> stationList, String defaultStation) {
        for(int i=0; i<=stationList.size()-1;i++) {
            Station station = stationList.get(i);
            if(station!=null && defaultStation !=null && station.getAbbr().contentEquals(defaultStation)) {
                return i;
            }
        }
        return 0;
    }

    public String getRealTimeInfoDateAndTime(RealTimeInformation realTimeInformation) {
        return realTimeInformation.getDate()+" "+realTimeInformation.getTime();
    }

    public Etd getEtd(List<Etd> etdList, String stationAbbrevation) {
        if(etdList ==null) {
            return null;
        }
        for(Etd etd: etdList) {
            if(etd.getAbbreviation().contentEquals(stationAbbrevation)) {
                return etd;
            }
        }
        return null;
    }
}
