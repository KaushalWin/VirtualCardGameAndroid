package com.android.admin.virtualcardgameandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

public class SwipeActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{

    GestureDetector mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);

        mDetector = new GestureDetector(this,this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        //System.out.println("down");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        //System.out.println("showpress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        //System.out.println("singletapup");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        System.out.println("scroll");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        //System.out.println("longpress");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float v, float v1) {
        System.out.println("Fling : " +e1.getX() +"  :  "+ e2.getX());

        if(e1.getY() - e2.getY() > 50){

            Toast.makeText(this , " Swipe Up " , Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,JoinActivity.class));
            return true;
        }

        if(e2.getY() - e1.getY() > 50){

            Toast.makeText(this , " Swipe Down " , Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,CreateGameActivity.class));
            return true;
        }
        else {

            return true ;
        }

        //Toast.makeText(this,"fling",Toast.LENGTH_SHORT).show();
        //return true;
    }


}
