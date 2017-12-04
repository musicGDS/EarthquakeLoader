package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by GDS on 21/11/2017.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    /** Tag for log messages */
    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    private String mUrl;

    /**
     * Constructs a new {@link EarthquakeLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */

    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
        //Log.i("EarthquakeLoader", "Earthquake Loader is constructed");
    }


    @Override
    protected void onStartLoading(){
        forceLoad();
        //Log.i("EarthquakeLoader", "On start loading");
    }

    /**
     * This is on a background thread.
     */

    @Override
    public List<Earthquake> loadInBackground() {
        // Check if we have URLs
        if (mUrl == null){
            return null;
        }
        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<Earthquake> result = QueryUtils.fetchEarthquakeData(mUrl);
        //Log.i("EarthquakeLoader", "Load In Background");
        return result;
    }

}
