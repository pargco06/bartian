
package com.parg.bartian.domain.weather.json;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Data implements Parcelable {

    @SerializedName("temperature")
    @Expose
    private List<String> temperature = new ArrayList<String>();

    @SerializedName("weather")
    @Expose
    private List<String> weather = new ArrayList<String>();

    @SerializedName("iconLink")
    @Expose
    private List<String> iconLink = new ArrayList<String>();

    @SerializedName("text")
    @Expose
    private List<String> text = new ArrayList<String>();

    @SerializedName("currentobservation")
    @Expose
    private CurrentObservation currentObservation;

    public final static Parcelable.Creator<Data> CREATOR = new Creator<Data>() {
        @SuppressWarnings({
            "unchecked"
        })
        public Data createFromParcel(Parcel in) {
            Data instance = new Data();
            in.readList(instance.temperature, (java.lang.String.class.getClassLoader()));
            in.readList(instance.weather, (java.lang.String.class.getClassLoader()));
            in.readList(instance.iconLink, (java.lang.String.class.getClassLoader()));
            in.readList(instance.text, (java.lang.String.class.getClassLoader()));
            instance.currentObservation = ((CurrentObservation) in.readValue((CurrentObservation.class.getClassLoader())));
            return instance;
        }

        public Data[] newArray(int size) {
            return (new Data[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The temperature
     */
    public List<String> getTemperature() {
        return temperature;
    }

    /**
     * 
     * @param temperature
     *     The temperature
     */
    public void setTemperature(List<String> temperature) {
        this.temperature = temperature;
    }

    /**
     * 
     * @return
     *     The weather
     */
    public List<String> getWeather() {
        return weather;
    }

    /**
     * 
     * @param weather
     *     The weather
     */
    public void setWeather(List<String> weather) {
        this.weather = weather;
    }

    /**
     * 
     * @return
     *     The iconLink
     */
    public List<String> getIconLink() {
        return iconLink;
    }

    /**
     * 
     * @param iconLink
     *     The iconLink
     */
    public void setIconLink(List<String> iconLink) {
        this.iconLink = iconLink;
    }

    /**
     * 
     * @return
     *     The text
     */
    public List<String> getText() {
        return text;
    }

    /**
     * 
     * @param text
     *     The text
     */
    public void setText(List<String> text) {
        this.text = text;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(temperature);
        dest.writeList(weather);
        dest.writeList(iconLink);
        dest.writeList(text);
        dest.writeValue(currentObservation);
    }

    public int describeContents() {
        return  0;
    }

}
