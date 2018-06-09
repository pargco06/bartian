
package com.parg.bartian.domain.weather.json;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CurrentObservation implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("elev")
    @Expose
    private String elev;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("Date")
    @Expose
    private String Date;
    @SerializedName("Temp")
    @Expose
    private String Temp;
    @SerializedName("Dewp")
    @Expose
    private String Dewp;
    @SerializedName("Relh")
    @Expose
    private String Relh;
    @SerializedName("Winds")
    @Expose
    private String Winds;
    @SerializedName("Windd")
    @Expose
    private String Windd;
    @SerializedName("Gust")
    @Expose
    private String Gust;
    @SerializedName("Weather")
    @Expose
    private String Weather;
    @SerializedName("Weatherimage")
    @Expose
    private String Weatherimage;
    @SerializedName("Visibility")
    @Expose
    private String Visibility;
    @SerializedName("Altimeter")
    @Expose
    private String Altimeter;
    @SerializedName("SLP")
    @Expose
    private String SLP;
    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("WindChill")
    @Expose
    private String WindChill;
    public final static Parcelable.Creator<CurrentObservation> CREATOR = new Creator<CurrentObservation>() {


        @SuppressWarnings({
            "unchecked"
        })
        public CurrentObservation createFromParcel(Parcel in) {
            CurrentObservation instance = new CurrentObservation();
            instance.id = ((String) in.readValue((String.class.getClassLoader())));
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            instance.elev = ((String) in.readValue((String.class.getClassLoader())));
            instance.latitude = ((String) in.readValue((String.class.getClassLoader())));
            instance.longitude = ((String) in.readValue((String.class.getClassLoader())));
            instance.Date = ((String) in.readValue((String.class.getClassLoader())));
            instance.Temp = ((String) in.readValue((String.class.getClassLoader())));
            instance.Dewp = ((String) in.readValue((String.class.getClassLoader())));
            instance.Relh = ((String) in.readValue((String.class.getClassLoader())));
            instance.Winds = ((String) in.readValue((String.class.getClassLoader())));
            instance.Windd = ((String) in.readValue((String.class.getClassLoader())));
            instance.Gust = ((String) in.readValue((String.class.getClassLoader())));
            instance.Weather = ((String) in.readValue((String.class.getClassLoader())));
            instance.Weatherimage = ((String) in.readValue((String.class.getClassLoader())));
            instance.Visibility = ((String) in.readValue((String.class.getClassLoader())));
            instance.Altimeter = ((String) in.readValue((String.class.getClassLoader())));
            instance.SLP = ((String) in.readValue((String.class.getClassLoader())));
            instance.timezone = ((String) in.readValue((String.class.getClassLoader())));
            instance.state = ((String) in.readValue((String.class.getClassLoader())));
            instance.WindChill = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public CurrentObservation[] newArray(int size) {
            return (new CurrentObservation[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The elev
     */
    public String getElev() {
        return elev;
    }

    /**
     * 
     * @param elev
     *     The elev
     */
    public void setElev(String elev) {
        this.elev = elev;
    }

    /**
     * 
     * @return
     *     The latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * 
     * @param latitude
     *     The latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * 
     * @return
     *     The longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * 
     * @param longitude
     *     The longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * 
     * @return
     *     The Date
     */
    public String getDate() {
        return Date;
    }

    /**
     * 
     * @param Date
     *     The Date
     */
    public void setDate(String Date) {
        this.Date = Date;
    }

    /**
     * 
     * @return
     *     The Temp
     */
    public String getTemp() {
        return Temp;
    }

    /**
     * 
     * @param Temp
     *     The Temp
     */
    public void setTemp(String Temp) {
        this.Temp = Temp;
    }

    /**
     * 
     * @return
     *     The Dewp
     */
    public String getDewp() {
        return Dewp;
    }

    /**
     * 
     * @param Dewp
     *     The Dewp
     */
    public void setDewp(String Dewp) {
        this.Dewp = Dewp;
    }

    /**
     * 
     * @return
     *     The Relh
     */
    public String getRelh() {
        return Relh;
    }

    /**
     * 
     * @param Relh
     *     The Relh
     */
    public void setRelh(String Relh) {
        this.Relh = Relh;
    }

    /**
     * 
     * @return
     *     The Winds
     */
    public String getWinds() {
        return Winds;
    }

    /**
     * 
     * @param Winds
     *     The Winds
     */
    public void setWinds(String Winds) {
        this.Winds = Winds;
    }

    /**
     * 
     * @return
     *     The Windd
     */
    public String getWindd() {
        return Windd;
    }

    /**
     * 
     * @param Windd
     *     The Windd
     */
    public void setWindd(String Windd) {
        this.Windd = Windd;
    }

    /**
     * 
     * @return
     *     The Gust
     */
    public String getGust() {
        return Gust;
    }

    /**
     * 
     * @param Gust
     *     The Gust
     */
    public void setGust(String Gust) {
        this.Gust = Gust;
    }

    /**
     * 
     * @return
     *     The Weather
     */
    public String getWeather() {
        return Weather;
    }

    /**
     * 
     * @param Weather
     *     The Weather
     */
    public void setWeather(String Weather) {
        this.Weather = Weather;
    }

    /**
     * 
     * @return
     *     The Weatherimage
     */
    public String getWeatherimage() {
        return Weatherimage;
    }

    /**
     * 
     * @param Weatherimage
     *     The Weatherimage
     */
    public void setWeatherimage(String Weatherimage) {
        this.Weatherimage = Weatherimage;
    }

    /**
     * 
     * @return
     *     The Visibility
     */
    public String getVisibility() {
        return Visibility;
    }

    /**
     * 
     * @param Visibility
     *     The Visibility
     */
    public void setVisibility(String Visibility) {
        this.Visibility = Visibility;
    }

    /**
     * 
     * @return
     *     The Altimeter
     */
    public String getAltimeter() {
        return Altimeter;
    }

    /**
     * 
     * @param Altimeter
     *     The Altimeter
     */
    public void setAltimeter(String Altimeter) {
        this.Altimeter = Altimeter;
    }

    /**
     * 
     * @return
     *     The SLP
     */
    public String getSLP() {
        return SLP;
    }

    /**
     * 
     * @param SLP
     *     The SLP
     */
    public void setSLP(String SLP) {
        this.SLP = SLP;
    }

    /**
     * 
     * @return
     *     The timezone
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     * 
     * @param timezone
     *     The timezone
     */
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    /**
     * 
     * @return
     *     The state
     */
    public String getState() {
        return state;
    }

    /**
     * 
     * @param state
     *     The state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 
     * @return
     *     The WindChill
     */
    public String getWindChill() {
        return WindChill;
    }

    /**
     * 
     * @param WindChill
     *     The WindChill
     */
    public void setWindChill(String WindChill) {
        this.WindChill = WindChill;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(elev);
        dest.writeValue(latitude);
        dest.writeValue(longitude);
        dest.writeValue(Date);
        dest.writeValue(Temp);
        dest.writeValue(Dewp);
        dest.writeValue(Relh);
        dest.writeValue(Winds);
        dest.writeValue(Windd);
        dest.writeValue(Gust);
        dest.writeValue(Weather);
        dest.writeValue(Weatherimage);
        dest.writeValue(Visibility);
        dest.writeValue(Altimeter);
        dest.writeValue(SLP);
        dest.writeValue(timezone);
        dest.writeValue(state);
        dest.writeValue(WindChill);
    }

    public int describeContents() {
        return  0;
    }

}
