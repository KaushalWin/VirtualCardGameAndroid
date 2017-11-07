package com.android.admin.virtualcardgameandroid;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class JoinActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView lv;
    String games[] = {"Game1","Game2","Game3","Game4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_up,android.R.anim.fade_out);
        setContentView(R.layout.activity_join);
        lv = (ListView) findViewById(R.id.list_join);
        lv.setOnItemClickListener(this);
        ArrayAdapter<String> ad = new ArrayAdapter<String>(this,0,games){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                convertView = getLayoutInflater().inflate(R.layout.adapter_template1,null);
                TextView tv = (TextView) convertView.findViewById(R.id.tv_temp1);
                tv.setText(games[position]);
                return convertView;
            }
        };
        lv.setAdapter(ad);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        startActivity(new Intent(this,ConnectedPlayersActivity.class));
    }
}
