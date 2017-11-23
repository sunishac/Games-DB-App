package com.example.chala.inclass06_chalasani;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SimDetails extends AppCompatActivity implements GamesListAsyncTask.IData {
    ListView lv;
    TextView tv;
    String a,id,t,d;
    ArrayList<Games> c=new ArrayList<Games>();
    ArrayList<String> sm=new ArrayList<String>();
    final static String k="Id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sim_details);
        lv=(ListView) findViewById(R.id.lv_s);
        tv=(TextView) findViewById(R.id.titleS);
        sm=null;
        if(getIntent().getExtras()!=null){
            a=(String) getIntent().getExtras().getSerializable(GameDetails.k3);
            id=(String) getIntent().getExtras().getSerializable(GameDetails.k4);
            t=(String) getIntent().getExtras().getSerializable(GameDetails.k1);
            sm=(ArrayList<String>) getIntent().getExtras().getSerializable(GameDetails.k2);
        }

        tv.setText(t);
        String d="http://thegamesdb.net/api/GetGamesList.php?name="+a;
        Log.d("demo","sim games: "+sm);
        if(sm==null ) {
            Toast.makeText(this,"No similar games",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Loading similar games ",Toast.LENGTH_LONG).show();
            new GamesListAsyncTask(SimDetails.this).execute(d);
        }

        findViewById(R.id.finishS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void List(ArrayList<Games> games) {
        c=games;
        Log.d("demo","c is"+ c);
        for (int i = 0; i < c.size(); i++) {
            for (int j = 0; j < sm.size(); j++) {
                if (c.get(i).getId().equals(sm.get(j))) {
                    Log.d("demo", "id in list " + c.get(i).getId() + " , sim Id is " + sm.get(j));
                    ArrayList<String> dls=new ArrayList<String>();
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,dls);
                    String dob=c.get(i).getDate();
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
                    dls.add(c.get(i).getTitle() + ", Released in " + yea + ", Platform: " + c.get(i).getPlatform() + ".");
                    lv.setAdapter(adapter);
                    adapter.setNotifyOnChange(true);
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            d=c.get(position).getId();
                            Intent i=new Intent(SimDetails.this,GameDet.class);
                            i.putExtra(k,c.get(position).getId());
                            startActivity(i);
                        }
                    });
                }
            }
        }
    }
}
