package com.example.chala.inclass06_chalasani;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GamesListAsyncTask.IData {
    ProgressDialog pd;
    EditText et;
    ArrayList<Games> gms;
    GamesAdapter adapter;
    ListView listView;
    String n,g;
    public static final String key = "name";
    public static final String key1 = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gms=new ArrayList<Games>();
        et=(EditText) findViewById(R.id.search_edit);
        listView=(ListView) findViewById(R.id.lv);

        findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et.getText().length()!=0) {
                    pd = new ProgressDialog(MainActivity.this);
                    pd.setCancelable(false);
                    pd.show();
                    String u = "http://thegamesdb.net/api/GetGamesList.php?name="+et.getText().toString();
                    new GamesListAsyncTask(MainActivity.this).execute(u);
                }
                else{
                    Toast.makeText(MainActivity.this,"No keyword entered", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    @Override
    public void List(ArrayList<Games> games) {
        gms = games;
        pd.dismiss();
        if (gms.size() > 0) {
            adapter = new GamesAdapter(MainActivity.this, R.layout.row_item_list, gms);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    g = et.getText().toString();
                    n = gms.get(position).getId();
                    Intent i = new Intent(MainActivity.this, GameDetails.class);
                    i.putExtra(key, g);
                    i.putExtra(key1, n);
                    startActivity(i);
                }
            });
        }
        else{
            Toast.makeText(MainActivity.this,"No such games ", Toast.LENGTH_LONG).show();
        }
    }
}
