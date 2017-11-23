package com.example.chala.inclass06_chalasani;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GameDetails extends AppCompatActivity implements DetailsAsyncTask.Data{
    String p,s;
    TextView t1,t2,t3,t4;
    ImageView iv;
    ProgressDialog pd;
    ArrayList<Details> d;
    final static String k1="Title";
    final static String k2="Sim";
    final static String k3="Name";
    final static String k4="Id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);

        if(getIntent().getExtras()!=null){
            p=(String) getIntent().getExtras().getSerializable(MainActivity.key);
            s=(String) getIntent().getExtras().getSerializable(MainActivity.key1);
        }
        t1=(TextView) findViewById(R.id.title);
        t2=(TextView) findViewById(R.id.over);
        t3=(TextView) findViewById(R.id.genre);
        t4=(TextView) findViewById(R.id.publisher);
        iv=(ImageView) findViewById(R.id.imageG);
        d=new ArrayList<Details>();
        String u="http://thegamesdb.net/api/GetGame.php?id="+s;
        pd=new ProgressDialog(this);
        pd.setCancelable(false);
        pd.show();
        new DetailsAsyncTask(GameDetails.this).execute(u);

        findViewById(R.id.similar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(GameDetails.this,SimDetails.class);
                i.putExtra(k1,d.get(0).getTitle());
                i.putExtra(k2,d.get(0).getSim());
                i.putExtra(k3,p);
                i.putExtra(k4,s);
                startActivity(i);
            }
        });

        findViewById(R.id.finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void over(ArrayList<Details> details) {
        pd.dismiss();
        d=details;
        t1.setText(d.get(0).getTitle());
        t2.setText(d.get(0).getOverview());
        t3.setText(d.get(0).getGenre());
        t4.setText(d.get(0).getPub());
        if(d.get(0).getImage()==null){
            Toast.makeText(this,"no image for this",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"loading image ",Toast.LENGTH_LONG).show();
            Picasso.with(this)
                    .load(d.get(0).getImage())
                    .into(iv);
        }
    }
}
