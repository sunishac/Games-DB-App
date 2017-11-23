package com.example.chala.inclass06_chalasani;

import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by chala on 2/20/2017.
 */

public class GamesListAsyncTask extends AsyncTask<String, Object, ArrayList<Games>> {
    IData activity;

    public GamesListAsyncTask(IData activity) {
        this.activity = activity;
    }

    @Override
    protected ArrayList<Games> doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statusCode = con.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                InputStream in = con.getInputStream();
                Log.d("demo", in.toString());
                return GamesListPull.gamesPullParser.parseGames(in);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Games> strings) {
        super.onPostExecute(strings);
        if(strings!= null){
            activity.List(strings);
        }
        /*else{
            activity.List(null);
        }*/
    }

    public interface IData{
        public void List(ArrayList<Games> games);
    }
}
