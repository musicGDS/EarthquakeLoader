package com.example.android.quakereport;

/**
 * Created by GDS on 01/11/2017.
 */

public class Earthquake {
    private String mLocation;
    private Long mTimeInMilliseconds;
    private Double mMagnitude;
    private String mUrl;

    /**
     * Constructs a new {@link Earthquake} object.
     *
     * @param magnitude is the magnitude (size) of the earthquake
     * @param place is the city location of the earthquake
     * @param timeInMilliseconds is the time in milliseconds (from the Epoch) when the
     *  earthquake happened
     * @param url link to the website of the item
     */

    public Earthquake (Double magnitude, String place, Long timeInMilliseconds, String url) {
        mLocation = place;
        mTimeInMilliseconds = timeInMilliseconds;
        mMagnitude = magnitude;
        mUrl = url;
    }

    public String getPlace () {
        return mLocation;
    }

    public Long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public Double getMagnitude() {return mMagnitude;}

    public String getUrl () {return mUrl;}

}
