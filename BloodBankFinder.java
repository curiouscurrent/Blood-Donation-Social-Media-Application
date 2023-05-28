package com.anusha.coffee;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;



import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class BloodBankFinder {

    private static final String BLOOD_DONOR_API_URL = "https://api.blood-donor.in/bloodbanks/nearby?lat=%s&lng=%s&distance=%s&api_key=%s";
    private static final String OPENSTREETMAP_API_URL = "https://nominatim.openstreetmap.org/search?q=%s&format=json";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your location: ");
        String location = scanner.nextLine();

        // Use OpenStreetMap API to get latitude and longitude of the location
        String latLong = getLatLongFromAddress(location);

        // Set the search parameters for Blood Donor API
        String distance = "10"; // search within 10 km
        String apiKey = "your_api_key_here";

        try {
            URL url = new URL(String.format(BLOOD_DONOR_API_URL, latLong.split(",")[0], latLong.split(",")[1], distance, apiKey));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            InputStream inputStream = conn.getInputStream();
            String response = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();

            System.out.println(response);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Returns the latitude and longitude of a location using the OpenStreetMap API.
     */
    private static String getLatLongFromAddress(String location) {
        String latLong = "";
        try {
            URL url = new URL(String.format(OPENSTREETMAP_API_URL, location));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            InputStream inputStream = conn.getInputStream();
            String response = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();

            if (response.length() > 2) { // check if response is not empty array
                // Get the first result from the array and extract latitude and longitude
                response = response.substring(1, response.length() - 1); // remove brackets
                String[] tokens = response.split(",");
                String lat = tokens[10].substring(tokens[10].indexOf(":") + 1);
                String lng = tokens[11].substring(tokens[11].indexOf(":") + 1);
                latLong = lat + "," + lng;
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return latLong;
    }
}

