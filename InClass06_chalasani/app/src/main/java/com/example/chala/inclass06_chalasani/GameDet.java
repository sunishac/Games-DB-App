package com.example.chala.inclass06_chalasani;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GameDet extends AppCompatActivity implements DetailsAsyncTask.Data{
    String d;
    ProgressDialog pd;
    ArrayList<Details> s;
    TextView w1,w2,w3,w4;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_det);
        w1=(TextView) findViewById(R.id.titleL);
        w3=(TextView) findViewById(R.id.genre_l);
        w4=(TextView) findViewById(R.id.pub_l);
        w2=(TextView) findViewById(R.id.textw);
        iv=(ImageView) findViewById(R.id.imageL);

        if(getIntent().getExtras()!=null){
            d=(String) getIntent().getExtras().getSerializable(SimDetails.k);
        }
        String u="http://thegamesdb.net/api/GetGame.php?id="+d;
        pd=new ProgressDialog(this);
        pd.setCancelable(false);
        pd.show();
        new DetailsAsyncTask(GameDet.this).execute(u);

        findViewById(R.id.finishL).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void over(ArrayList<Details> details) {
        s=details;
        pd.dismiss();
        w2=new TextView(this);
        w1.setText(s.get(0).getTitle());
        w2.setText(s.get(0).getOverview());
        w3.setText(s.get(0).getGenre());
        w4.setText(s.get(0).getPub());
        if(s.get(0).getImage()==null){
            Toast.makeText(this,"no image for this",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"loading image ",Toast.LENGTH_LONG).show();
            Picasso.with(this)
                    .load(s.get(0).getImage())
                    .into(iv);
        }
    }
}
