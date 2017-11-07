package com.android.admin.virtualcardgameandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateGameActivity extends AppCompatActivity implements View.OnClickListener{

    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_down,android.R.anim.fade_out);
        setContentView(R.layout.activity_create_game);
        b1 = (Button) findViewById(R.id.button2);
        b1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this,ConnectedPlayersActivity.class));
    }
}
