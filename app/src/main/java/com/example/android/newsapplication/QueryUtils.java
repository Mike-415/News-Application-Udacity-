package com.example.android.newsapplication;

import android.text.TextUtils;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public final class QueryUtils {
    private static final String TAG = "QueryUtils";


    private QueryUtils() {
    }


    /**
     * Query the Guardian dataset and return a list of News objects
     * @param requestUrl represents the url and query string
     * @return a list of News instances
     */
    public static List<News> fetchNewsData(String requestUrl) {
        //Testing the loading indicator
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(TAG, "Problem making the HTTP request.", e);
        }
        List<News> newsList = extractNewsFromJson(jsonResponse);
        return newsList;
    }



    /**
     * Return a list of {@link News} objects that has been built up from
     * parsing the given JSON response.
     */
    private static List<News> extractNewsFromJson(String newsJSON) {
        if (TextUtils.isEmpty(newsJSON)) {
            return null;
        }
        List<News> newsList = new ArrayList<>();
        try {
            newsList = parseJSON(newsJSON);
        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }
        return newsList;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                Log.i(TAG, "makeHttpRequest: Response code is 200");
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(TAG, "makeHttpRequest: Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

//    private static void getInternetConnection(HttpURLConnection urlConnection){
//
//    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(TAG, "Problem building the URL ", e);
        }
        return url;
    }


    private static List<News> parseJSON(String jsonString) throws JSONException{
        List<News> newsList = new ArrayList<>();
        JSONObject root = new JSONObject(jsonString);
        JSONObject response = root.getJSONObject("response");
        JSONArray results = response.getJSONArray("results");
        for(int i = 0; i < results.length(); i++)
        {
            JSONObject result = results.getJSONObject(i);
            News news = getNewsFromResult(result);
            Log.i(TAG, "\nnews object: " + news + "\n");
            newsList.add(news);
        }
        return newsList;
    }


    private static News getNewsFromResult(JSONObject result) throws JSONException{
        String sectionName, title, thumbnailUrl = null, guardianUrl, author = null, date = null;
        JSONObject fields = result.getJSONObject("fields");
        sectionName = result.getString("sectionName");
        title = result.getString("webTitle");
        if(result.has("webPublicationDate"))
            date = formatDateAndTime(result.getString("webPublicationDate"));
        if(fields.has("thumbnail"))
            thumbnailUrl = fields.getString("thumbnail");
        JSONArray tags = result.getJSONArray("tags");
        if(tags.length() > 0){
            JSONObject tagObject = tags.getJSONObject(0);
            author = tagObject.getString("webTitle");
        }
        guardianUrl = result.getString("webUrl");
        return new News(title, sectionName, date, author, thumbnailUrl, guardianUrl);
    }

    private static String formatDateAndTime(String publicationDate){
        String[] dateAndTime = publicationDate.split("T");
        String formattedDate = formatDate(dateAndTime[0]);
        String formattedTime = formatTime(dateAndTime[1]);
        return formattedDate+"\n"+formattedTime;
    }



    private static String formatDate(String date){
        String[] dateUnits = date.split("-");
        String year = dateUnits[0];
        String month = getMonthString(Integer.valueOf(dateUnits[1]));
        String day = dateUnits[2];
        return (new StringBuilder(month)
                .append(" ")
                .append(day)
                .append(", ")
                .append(year)).toString();
    }

    private static String formatTime(String time) {
        String[] timeUnits = time.split(":");
        int hour = Integer.valueOf(timeUnits[0]);
        int minute = Integer.valueOf(timeUnits[1]);
        StringBuilder builder = new StringBuilder();
        if(hour > 12){
            hour -= 12;
            return (builder.append(hour).append(":").append(minute).append(" PM")).toString();
        }
        return (builder.append(hour).append(":").append(minute).append(" AM")).toString();
    }

    private static String getMonthString(int month) {
        switch (month){
            case 1: return "Jan";
            case 2: return "Feb";
            case 3: return "Mar";
            case 4: return "Apr";
            case 5: return "May";
            case 6: return "Jun";
            case 7: return "Jul";
            case 8: return "Aug";
            case 9: return "Sept";
            case 10: return "Oct";
            case 11: return "Nov";
            case 12: return "Dec";
            default: return "";
        }
    }
}

