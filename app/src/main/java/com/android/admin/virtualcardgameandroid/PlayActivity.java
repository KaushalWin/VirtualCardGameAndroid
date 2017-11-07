package com.android.admin.virtualcardgameandroid;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Icon;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Space;

import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton ib[];
    ArrayList<Integer> pc;
    RelativeLayout rl;
    ImageView iv1,iv2;
    String players[] = {"p1","p2","p3","p4"};
    Button b[] = new Button[5];
    Space sp ;
    int last_card;

    int cards[] ={R.drawable.a1_0,R.drawable.a1_1,R.drawable.a1_2,R.drawable.a1_3,R.drawable.a1_4,R.drawable.a1_5,R.drawable.a1_6,R.drawable.a1_7,R.drawable.a1_8,R.drawable.a1_9,R.drawable.a1_10,R.drawable.a1_11,R.drawable.a1_12,
            R.drawable.a2_0,R.drawable.a2_1,R.drawable.a2_2,R.drawable.a2_3,R.drawable.a2_4,R.drawable.a2_5,R.drawable.a2_6,R.drawable.a2_7,R.drawable.a2_8,R.drawable.a2_9,R.drawable.a2_10,R.drawable.a2_11,R.drawable.a2_12,
            R.drawable.a3_0,R.drawable.a3_1,R.drawable.a3_2,R.drawable.a3_3,R.drawable.a3_4,R.drawable.a3_5,R.drawable.a3_6,R.drawable.a3_7,R.drawable.a3_8,R.drawable.a3_9,R.drawable.a3_10,R.drawable.a3_11,R.drawable.a3_12,
            R.drawable.a4_0,R.drawable.a4_1,R.drawable.a4_2,R.drawable.a4_3,R.drawable.a4_4,R.drawable.a4_5,R.drawable.a4_6,R.drawable.a4_7,R.drawable.a4_8,R.drawable.a4_9,R.drawable.a4_10,R.drawable.a4_11,R.drawable.a4_12
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        setIds();
        generateButtons(pc);
        rotate(pc.size(),9000);
    }

    void setIds()
    {

        rl = (RelativeLayout) findViewById(R.id.rl);
        iv1 = (ImageView) findViewById(R.id.imageView2);
        iv2 = (ImageView) findViewById(R.id.imageView);
        b[0] = (Button) findViewById(R.id.button6);
        b[1] = (Button) findViewById(R.id.button7);
        b[2] = (Button) findViewById(R.id.button8);
        b[3] = (Button) findViewById(R.id.button9);
        b[4] = (Button) findViewById(R.id.button10);
        //b[1].setEnabled(false);

        sp = (Space) findViewById(R.id.sp2);

        for(int i = 0;i<b.length;i++)
        {
            b[i].setOnClickListener(this);
        }

        pc = new ArrayList<>();
        pc.add(new Integer(1));pc.add(new Integer(5));
        pc.add(new Integer(31));pc.add(new Integer(21));
        pc.add(new Integer(38));pc.add(new Integer(44));
    }

    void generateButtons(ArrayList<Integer> c)
    {
        int n = c.size(), degree = (330) - (n*10);
        ib = new ImageButton[n];
        int k=0;
        int x = 20,y=0,width = 330;
        if(n>3)
        {
            width -= (n-3)*25;
        }
        sp.setLayoutParams(new RelativeLayout.LayoutParams(width,150));


        for(int i = 0;i<n;i++) {
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
            lp.addRule(RelativeLayout.BELOW,R.id.sp1);
            lp.addRule(RelativeLayout.RIGHT_OF,R.id.sp2);
            lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
            //lp.addRule(RelativeLayout.,R.id.sp2);

            ib[i] = new ImageButton(this);
            ib[i].setImageResource(cards[c.get(i)]);
            ib[i].setLayoutParams(lp);
            ib[i].setOnClickListener(this);
            ib[i].setBackgroundColor(Color.TRANSPARENT);
            ib[i].setTag(100+i);
            ib[i].setId(i);
            //System.out.println(ib[i].getId());
            ib[i].setX(x);
            x += 60;
            ib[i].setY(y);
            rl.addView(ib[i]);
        }


    }

    void rotate(int n,int duration)
    {
        int x;
        duration = 1000;
        for(int i = 0;i<n;i++)
        {
            x = (int)ib[i].getX()-20;
            RotateAnimation rotate = new RotateAnimation(0, (360), Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(duration);
            rotate.setInterpolator(new LinearInterpolator());
            rotate.setFillAfter(true);
//            TranslateAnimation animation = new TranslateAnimation(-1000, ib[i].getX(), 0, 0); //(float From X,To X, From Y, To Y)
//            animation.setDuration(duration);
//            animation.setInterpolator(new LinearInterpolator());
//            animation.setFillAfter(true);
            duration += 100;

            ib[i].setAnimation(rotate);

        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();


        if(id == b[0].getId())
        {
            for(int i=0;i<pc.size();i++)
            {
                rl.removeView(ib[i]);
            }
            //pc.add();
            ib = null;
            generateButtons(pc);
            rotate(pc.size(),0);
        }
        else if(id == b[1].getId())
        {
            for(int i=0;i<pc.size();i++)
            {
                rl.removeView(ib[i]);
            }
            pc.remove(new Integer(last_card));
            iv2.setImageDrawable(iv1.getDrawable());
            iv1.setImageDrawable(getResources().getDrawable(R.drawable.backc));
            ib= null;
            generateButtons(pc);
            rotate(pc.size(),0);
        }
        else if(id == b[2].getId())
        {
            //pass
            pc.remove(last_card);
        }
        else if(id == b[3].getId())
        {
            generateDialogue();
        }
        else if(id == b[4].getId())
        {
            for(int i=0;i<pc.size();i++)
            {
                rl.removeView(ib[i]);
            }
            //pc.add();
            ib = null;
            generateButtons(pc);
            rotate(pc.size(),0);
            iv1.setImageDrawable(getResources().getDrawable(cards[23]));
        }



        if(id >= 0 && id<52)
        {
            last_card = pc.get(id);
            iv1.setImageDrawable(getResources().getDrawable(cards[pc.get(id)]));
        }

    }

    void generateDialogue()
    {
        final Dialog dialog = new Dialog(this);

        //tell the Dialog to use the dialog.xml as it's layout description
        dialog.setContentView(R.layout.player_dialogue);
        dialog.setTitle("Android Custom Dialog Box");

        ListView lv = (ListView) dialog.findViewById(R.id.lv1);
        ArrayAdapter ad = new ArrayAdapter(this,android.R.layout.simple_list_item_1,players);
        lv.setAdapter(ad);

        dialog.show();
    }
}
