package com.android.admin.virtualcardgameandroid;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.ImageView;

import static android.view.WindowManager.*;

public class splashActivity extends AppCompatActivity {

    Handler h = new Handler();
    ImageView iv;
    Animation an;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //iv = (ImageView) findViewById(R.id.imageView2);
        //an = AnimationUtils.loadAnimation(this,R.anim.splash);
        //iv.setAnimation(an);

        final Intent i = new Intent(this,SignInActivity.class);

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(i);
                finish();
            }
        }, 2000);

    }
}
