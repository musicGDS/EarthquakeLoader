package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by GDS on 01/11/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        // Get the data item for this position
        Earthquake earthquake = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        //Date Object

        Date dateObject = new Date(earthquake.getTimeInMilliseconds());

        // Find the TextView with view ID date
        TextView dateView = (TextView) convertView.findViewById(R.id.list_item_date);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        Log.e("EartqukeAdapter", formattedDate);
        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeView = (TextView) convertView.findViewById(R.id.list_item_time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        Log.e("EartqukeAdapter", formattedTime);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);

        // Location Objects

        String mLocation = earthquake.getPlace();
        String primaryLocation;
        String locationOffset;

        boolean isOffset = mLocation.contains("of");

        if (isOffset) {
            int offsetEnd = mLocation.indexOf("f");
            String offsetString = mLocation.substring(0, offsetEnd + 1);
            locationOffset = offsetString;
            primaryLocation = mLocation.substring(offsetEnd + 1, mLocation.length());
        }else {
            locationOffset ="Near by";
            primaryLocation = mLocation;
        }

        TextView offsetView = (TextView) convertView.findViewById(R.id.list_item_location_offset);
        offsetView.setText(locationOffset);

        TextView locationView = (TextView) convertView.findViewById(R.id.list_item_primary_location);
        locationView.setText(primaryLocation);

        // Magnitude

        DecimalFormat formatter = new DecimalFormat("0.0");
        // Format the magnitude to show 1 decimal place
        String formattedMagnitude = formatMagnitude(earthquake.getMagnitude());
        // Display the magnitude of the current earthquake in that TextView


        TextView magnitudeView = (TextView) convertView.findViewById(R.id.list_item_magnitude);
        magnitudeView.setText(formattedMagnitude);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(earthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        // Return the completed view to render on screen
        return convertView;
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

}
