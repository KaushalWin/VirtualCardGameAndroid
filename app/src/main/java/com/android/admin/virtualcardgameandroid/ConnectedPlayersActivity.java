package com.android.admin.virtualcardgameandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConnectedPlayersActivity extends AppCompatActivity implements View.OnClickListener{

    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connected_players);
        b1 = (Button) findViewById(R.id.button5);
        b1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this,PlayActivity.class));
    }
}
