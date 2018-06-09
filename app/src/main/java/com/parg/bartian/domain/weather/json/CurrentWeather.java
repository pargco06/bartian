package com.parg.bartian.domain.weather.json;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ganga_r on 2/2/2017.
 */

public class CurrentWeather {

    @SerializedName("data")
    @Expose
    private Data data;

    @SerializedName("currentobservation")
    @Expose
    private CurrentObservation currentObservation;

    public final static Parcelable.Creator<CurrentWeather> CREATOR = new Parcelable.Creator<CurrentWeather>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CurrentWeather createFromParcel(Parcel in) {
            CurrentWeather instance = new CurrentWeather();
            instance.data = ((Data) in.readValue((Data.class.getClassLoader())));
            instance.currentObservation = ((CurrentObservation) in.readValue((CurrentObservation.class.getClassLoader())));
            return instance;
        }

        public CurrentWeather[] newArray(int size) {
            return (new CurrentWeather[size]);
        }

    }
            ;

    /**
     *
     * @return
     *     The data
     */
    public Data getData() {
        return data;
    }

    /**
     *
     * @param data
     *     The data
     */
    public void setData(Data data) {
        this.data = data;
    }

    /**
     *
     * @return
     *     The currentObservation
     */
    public CurrentObservation getCurrentObservation() {
        return currentObservation;
    }

    /**
     *
     * @param currentObservation
     *     The currentObservation
     */
    public void setCurrentObservation(CurrentObservation currentObservation) {
        this.currentObservation = currentObservation;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(data);
        dest.writeValue(currentObservation);
    }

    public int describeContents() {
        return  0;
    }
}
