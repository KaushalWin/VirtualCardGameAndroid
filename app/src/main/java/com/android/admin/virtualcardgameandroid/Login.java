package com.android.admin.virtualcardgameandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.admin.virtualcardgameandroid.GameDatabaseIchi.Game;
import com.android.admin.virtualcardgameandroid.GameDatabaseIchi.Player;

import java.util.TreeSet;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Player p = Player.CreatOrGetInstance();
        Game g = Game.CreatOrGetInstance();
        TreeSet arr;
        Toast.makeText(this, ""+g.clearAll(), Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "" + p.createUser("Hemant", "ya"), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "" + p.ValidateUser("Khamar", "ya"), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "" + g.createGame("holo"), Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "" + p.ValidateUser("Kaushal", "ya"), Toast.LENGTH_SHORT).show();
        // Toast.makeText(this, ""+g.lookGame().length, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, ""+g.joinGame(g.lookGame()[0]), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, ""+p.getPid(), Toast.LENGTH_SHORT).show();
        arr=g.getDeck();
        Toast.makeText(this, "cards:"+arr, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, ""+g.removeFromDeck(0,3), Toast.LENGTH_SHORT).show();
        arr=g.getDeck();
        Toast.makeText(this, "cards:"+arr, Toast.LENGTH_SHORT).show();
//         Toast.makeText(this, ""+g.finish(), Toast.LENGTH_SHORT).show();

        // String str[]=g.lookGame();
        //for (int i=0;i<str.length;i++)Toast.makeText(this, str[i], Toast.LENGTH_SHORT).show();
    }
}
