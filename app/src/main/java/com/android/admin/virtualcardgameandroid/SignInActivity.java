package com.android.admin.virtualcardgameandroid;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    Button b1;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        tv = (TextView) findViewById(R.id.textView2);
        b1 = (Button)findViewById(R.id.button);

        b1.setOnClickListener(this);
        tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == b1.getId())
        {
            startActivity(new Intent(this,SwipeActivity.class));
            finish();
        }
        else
        {
            startActivity(new Intent(this,RegisterActivity.class));
        }
    }
}
