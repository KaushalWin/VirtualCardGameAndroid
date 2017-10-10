package com.android.admin.virtualcardgameandroid.GameDatabaseIchi;

import android.gesture.GestureUtils;
import android.support.v4.content.res.TypedArrayUtils;
import android.text.BoringLayout;
import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

/**
 * Created by Admin on 10/8/2017.
 */

public class Game {
    String gname;
    private Game() {
        gname=null;
    }
    private static Game obj;
    public static Game CreatOrGetInstance()
    {
        if(obj==null)
            return obj=new Game();
        else return obj;
    }//Create look finish game
    public boolean createGame(String name)
    {
        if(gname!=null) return false;
        CreateClass cc=new CreateClass(name);
        cc.start();
        try {
            cc.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(cc.isGameOnline())
        {
            gname=name;
            Player.CreatOrGetInstance().setPlayerPositionInGame(1);
            return true;
        }
        return false;
    }
    public String[] lookGame()
    {
        LookClass lc=new LookClass();
        lc.start();
        try {
            lc.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return lc.getStr();

    }
    public boolean joinGame(String name)
    {
        JoinGameClass jgc = new JoinGameClass(name);
        jgc.start();
        try {
            jgc.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(jgc.isJoined())
        {
            gname=name;
            Player.CreatOrGetInstance().setPlayerPositionInGame(jgc.getPid());
            return true;
        }
        return false;
    }
    public boolean finish()
    {
        RemoveGameClass rgc=new RemoveGameClass();
        rgc.start();
        try {
            rgc.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return rgc.isRemoved();
    }
    public boolean clearAll()
    {
        RemoveAllClass rac=new RemoveAllClass();
        rac.start();
        try {
            rac.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return rac.areRemoved;
    }

    public String getGname() {
        return gname;
    }
    public TreeSet getDeck()
    {
        if(gname==null) return null;
        GetDeckPotClass gdc=new GetDeckPotClass("Deck");
        gdc.start();
        try {
            gdc.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return  gdc.getDeck();
    }
    public TreeSet getPot()
    {
        if(gname==null) return null;
        GetDeckPotClass gpc=new GetDeckPotClass("Deck");
        gpc.start();
        try {
            gpc.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return  gpc.getDeck();
    }
    public TreeSet getPlayerCards()
    {
        Player p=Player.CreatOrGetInstance();
        if(p.getPid()>0)
        {
            if(gname==null) return null;
            return  getSpecificPlayerCard(p.getPid());

        }
        return null;
    }
    private TreeSet getSpecificPlayerCard(int i)
    {
        if(!(i>0 && i<=6)) return null;
        GetDeckPotClass gpc=new GetDeckPotClass("Player"+i);
        gpc.start();
        try {
            gpc.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  gpc.getDeck();
    }
    public boolean removeFromDeck(int playerno,int cardno)
    {
        TreeSet Deck=null;
        if(playerno==0)
        {
            Deck=getDeck();
        }
        else if(playerno==-1)
        {
            Deck=getPot();
        }
        else if(playerno>0 && playerno<=6)
        {
            Deck=getSpecificPlayerCard(playerno);
        }
        if(Deck==null)return false;

        //ArrayList al=new ArrayList()
        boolean a=Deck.remove(cardno);
        String kk=Deck.toString();
        kk=kk.substring(1,kk.length()-1).replaceAll("\\s+","");;
        if(a) {

            setDeckPotClass sdpc;
            if(playerno==0)
            {
                sdpc=new setDeckPotClass("Deck",kk);
            }
            else if(playerno==-1)
            {
                sdpc=new setDeckPotClass("Pot",kk);
            }
            else if(playerno>0 && playerno<=6)
            {
                sdpc=new setDeckPotClass("Player"+playerno,kk);
            }
            else{
                return false;
            }
            sdpc.start();
            try {
                sdpc.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a=sdpc.isUpdated();
        }
        return a;
    }
    public boolean insertInDeck(int playerno,int cardno)
    {
        TreeSet Deck=null;
        if(playerno==0)
        {
            Deck=getDeck();
        }
        else if(playerno==-1)
        {
            Deck=getPot();
        }
        else if(playerno>0 && playerno<=6)
        {
            Deck=getSpecificPlayerCard(playerno);
        }
        if(Deck==null)return false;

        //ArrayList al=new ArrayList()
        boolean a=Deck.add(cardno);
        String kk=Deck.toString();
        kk=kk.substring(1,kk.length()-1).replaceAll("\\s+","");;
        if(a) {

            setDeckPotClass sdpc;
            if(playerno==0)
            {
                sdpc=new setDeckPotClass("Deck",kk);
            }
            else if(playerno==-1)
            {
                sdpc=new setDeckPotClass("Pot",kk);
            }
            else if(playerno>0 && playerno<=6)
            {
                sdpc=new setDeckPotClass("Player"+playerno,kk);
            }
            else{
                return false;
            }
            sdpc.start();
            try {
                sdpc.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a=sdpc.isUpdated();
        }
        return a;
    }

    private static class CreateClass extends Thread{
        boolean isGameOnline;
        Player p;
        Connection con;
        Statement s;
        String gg;

        public CreateClass(String gg) {
            this.gg = gg;
        }

        @Override
        public void run() {
            isGameOnline=false;
            p=Player.CreatOrGetInstance();
            con=p.getCon();

            try {
                s=con.createStatement();
                String user=p.getUser();

                //Log.d("a1",user+"::"+gg);
                if(user==null) return;
                if(gg.length()<3)return;
                int i=s.executeUpdate("insert into Room(Name,Player1) values ('"+gg+"','"+user+"');");
                //Log.d("a1","here");
                if(i>0){
                    String str="";
                    for(int k=1;k<52;k++)
                    {
                        str+=k+",";
                    }
                    str+="52";
                    i=s.executeUpdate("insert into Card(Name,Deck) values ('"+gg+"','"+str+"');");
                    if(i>0)
                    {
                        isGameOnline=true;
                    }
                }
            } catch (SQLException e) {
                Log.d("a1",e.toString());
            }

        }

        public boolean isGameOnline() {
            return isGameOnline;
        }
    }
    private static class LookClass extends Thread{
        String str[];
        Player p;
        Connection con;
        Statement s;
        ResultSet rs;
        @Override
        public void run() {
            str=null;
            p=Player.CreatOrGetInstance();
            con=p.getCon();
            try {
                s=con.createStatement();
                rs=s.executeQuery("Select Name from Room;");
                if(!rs.next())
                {
                    str=null;
                    return;
                }
                rs.last();
                str=new String[rs.getRow()];
                rs.beforeFirst();
                int i=0;
                while(rs.next())
                {
                    str[i]=rs.getString("Name");
                    i++;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }




        }

        public String[] getStr() {
            return str;
        }
    }
    private static class RemoveAllClass extends Thread{
        boolean areRemoved;
        Player p;
        Connection con;
        Statement s;
        @Override
        public void run() {
            areRemoved=false;
            p=Player.CreatOrGetInstance();
            con=p.getCon();

            try {
                s=con.createStatement();
                int i=s.executeUpdate("delete from Room");
                int j=s.executeUpdate("delete from Card");
                if(i>0 && j>0)
                {
                    areRemoved=true;
                }
            } catch (SQLException e) {
                Log.d("a1",e.toString());
            }
        }

        public boolean AreRemoved() {
            return areRemoved;
        }
    }
    private static class RemoveGameClass extends Thread{
        boolean isRemoved;
        Player p;
        Connection con;
        Statement s;
        @Override
        public void run() {
            isRemoved=false;
            p=Player.CreatOrGetInstance();
            con=p.getCon();

            try {
                s=con.createStatement();
                String g=Game.CreatOrGetInstance().getGname();
                int i=s.executeUpdate("delete from Room where Name='"+g+"';");
                int j=s.executeUpdate("delete from Card where Name='"+g+"';");
                if(i>0 && j>0)
                {
                    isRemoved=true;
                }
            } catch (SQLException e) {
                Log.d("a1",e.toString());
            }
        }

        public boolean isRemoved() {
            return isRemoved;
        }
    }
    private static class JoinGameClass extends Thread
    {
        Player p;
        Connection con;
        Statement s;
        ResultSet rs;
        boolean isJoined;
        String name;
        int pid;
        public JoinGameClass(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            isJoined=false;
            p=Player.CreatOrGetInstance();
            String plr=p.getUser();
            if(name==null || plr==null) return;
            con=p.getCon();
            try {
                s=con.createStatement();
                rs=s.executeQuery("select * from Room where Name='"+name+"';");
                //Log.d("a1","rs null?");
                if(!rs.next()) return;
                //Log.d("a1","rs created"+rs.getString(3));
                for(int i=1;i<7;i++)
                {

                    String str=rs.getString("Player"+(i));


                    if(str==null)
                    {
                        rs.close();
                        int k=s.executeUpdate("update Room set Player"+(i)+"='"+plr+"' where Name='"+name+"';");
                        if(k>0)
                        {
                            isJoined=true;
                            pid=i;
                            return;
                        }
                    }
                    if(str.equals(plr)) return;
                }
            } catch (SQLException e) {
                Log.d("a1",e.toString());

            }

        }

        public boolean isJoined() {
            return isJoined;
        }

        public int getPid() {
            return pid;
        }
    }

    private static class GetDeckPotClass extends Thread{
        TreeSet deck;
        Player p;
        Connection con;
        Statement s;
        ResultSet rs;
        String type;
        public GetDeckPotClass(String type) {
            this.type=type;
        }

        @Override
        public void run() {
            deck=null;
            p=Player.CreatOrGetInstance();
            con=p.getCon();
            String name=Game.CreatOrGetInstance().getGname();
            try {
                s=con.createStatement();
                rs=s.executeQuery("Select "+type+" from Card where Name='"+name+"';");
                //Log.d("Debra1","RS NULL?");
                if(!rs.next())
                {
                    return;
                }
                //Log.d("Debra1","RS present"+"Player"+p.getPid());
                String str=rs.getString(type);
                if(str==null)return;
                Log.d("Debra1",type+"::"+str);
                String strarr[]=str.split(",");
                deck=new TreeSet();

                for(int i=0;i<strarr.length;i++)
                {
                    deck.add(Integer.parseInt(strarr[i]));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }




        }

        public TreeSet getDeck() {
            return deck;
        }
    }
    private static class setDeckPotClass extends Thread{
        Player p;
        Connection con;
        Statement s;
        String type;
        String Data;
        boolean updated;

        public setDeckPotClass(String type, String data) {
            this.type = type;
            Data = data;
        }

        public boolean isUpdated() {
            return updated;
        }

        @Override
        public void run() {
            updated=false;
            p=Player.CreatOrGetInstance();
            con=p.getCon();
            String name=Game.CreatOrGetInstance().getGname();
            try {
                s=con.createStatement();
                int k=s.executeUpdate("update Card set "+type+"="+"'"+Data+"' where Name='"+name+"';");
                //Log.d("Debra1","RS NULL?");
                if(k==1)
                {
                    updated=true;
                    return;
                }

                //Log.d("Debra1","RS present"+"Player"+p.getPid());

            } catch (SQLException e) {
                Log.d("Debra1",e.toString());
            }




        }

    }
}
