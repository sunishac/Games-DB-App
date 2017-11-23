package com.example.chala.inclass06_chalasani;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.widget.Toast.*;
import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by chala on 2/20/2017.
 */

public class GamesAdapter extends ArrayAdapter<Games> implements DetailsAsyncTask.Data{
    List<Games> mdata;
    Context mcontext;
    int mResouce;
    ArrayList<Details> ds=new ArrayList<Details>();
    ImageView iv;

    public GamesAdapter(Context context, int resource, List<Games> objects) {
        super(context, resource, objects);
        this.mcontext=context;
        this.mResouce=resource;
        this.mdata=objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResouce,parent,false);
        }

        if(position<10) {
            Games game = mdata.get(position);
            TextView tv = (TextView) convertView.findViewById(R.id.textView);
            iv = (ImageView) convertView.findViewById(R.id.imageView);
            Log.d("demo", "" + game.getDate());
            String dob = game.getDate();
            int yea = 0;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                Date d = sdf.parse(dob);
                Calendar cal = Calendar.getInstance();
                cal.setTime(d);
                yea = (cal.get(Calendar.YEAR));

            } catch (Exception e) {
                e.printStackTrace();
            }
            tv.setText(game.getTitle() + ". Released in " + yea + ". Platform: " + game.getPlatform() + ".");
            String u="http://thegamesdb.net/api/GetGame.php?id="+game.getId();
            new DetailsAsyncTask(GamesAdapter.this).execute(u);
        }

        return convertView;
    }

    @Override
    public void over(ArrayList<Details> details) {
        ds=details;
        Picasso.with(mcontext).load(ds.get(0).getImage()).into(iv);
    }
}
