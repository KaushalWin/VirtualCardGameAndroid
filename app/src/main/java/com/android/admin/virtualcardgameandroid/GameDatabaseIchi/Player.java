package com.android.admin.virtualcardgameandroid.GameDatabaseIchi;

import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Created by Admin on 10/7/2017.
 */

public class Player  {

    private Connection con;
    private String User;
    private int Pid;
    public void setPlayerPositionInGame(int i)
    {
        Pid=i;
    }

    public int getPid() {
        return Pid;
    }

    private Player() {
        User=null;
        Pid=0;
        ConnectionCreator cc=new ConnectionCreator();
        cc.start();
        try {
            cc.join();
        } catch (InterruptedException e) {
            Log.d("a",e.toString());
        }
        con=cc.getCon();

    }

    private static Player obj;
    public static Player CreatOrGetInstance()
    {
        if(obj==null)
       return obj=new Player();
        else return obj;
    }

    public boolean createUser(String Uname,String Pass)
    {
        CreateUserClass cuc=new CreateUserClass(con,Uname,Pass);
        cuc.start();
        try {
            cuc.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(cuc.isCreated())
        {
            User=Uname;
            return true;
        }
        return false;
    }
    public void testDB() {

        try {


            //Toast.makeText(this, "" + "Database connection success", Toast.LENGTH_SHORT).show();

            /* System.out.println("Database connection success"); */


            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from CheckUpdate");
//            Toast.makeText(this, "" + rs.getInt(1), Toast.LENGTH_SHORT).show();

            while (rs.next())
                Log.d("DataBaseConnection", rs.getInt("PlayerId") + " 3 " + rs.getInt("CheckUpdate"));
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("DataBaseConnection", e.toString());
        }
    }
    public boolean ValidateUser(String uname,String pass)
    {
        ValideUser a=new ValideUser(con,uname,pass);
        a.start();
        try {
            a.join();
        } catch (InterruptedException e) {
            Log.d("DataBaseConnection",e.toString());
        }
        if(a.getValid()) {
            User=a.getUser();
            return true;
        }
        else {
            User=null;
            return false;
        }

    }
    static private class ConnectionCreator extends Thread
    {
        private static final String url = "jdbc:mysql://104.199.163.107:3306/java";
        private static final String user = "norm";
        private static final String pass = "KAMEHAMEHA";
        Connection con;


        @Override
        public void run() {
            con=null;
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Log.d("DataBaseConnection", "DriverLoaded");
                con= DriverManager.getConnection(url, user, pass);
                Log.d("DataBaseConnection", "Connection succeeded");
            } catch (Exception e) {
                Log.d("DataBaseConnection", e.toString());
            }
        }

        public Connection getCon() {
            return con;
        }
    }
    static private class ValideUser extends Thread
    {
        String uname;
        String pass;
        Connection con;
        Boolean valid;
        String User;
        public ValideUser(Connection con,String uname, String pass) {
            this.uname = uname;
            this.pass = pass;
            this.con = con;
            User="";
        }

        @Override
        public void run() {
            valid=CheckUser();
        }
        public boolean CheckUser()
        {
            try {
                Statement pr=con.createStatement();


                ResultSet ar=pr.executeQuery("select * from User where Uname='"+uname+"' and Pass='"+pass+"';");
               // Log.d("a1","select * from User where Uname='"+uname+"' and Pass='"+pass+"';"+":::statemnet");
                //Log.d("a1",(ar==null)+":::ar");

                //Log.d("a1",(ar==null)+":::ar");
                //Log.d("a1",ar.getString(1)+"");
                if(ar.next())
                {
                    User=ar.getString("Uname");
                    return true;
                }
                else
                {
                    //Log.d("a1","ProperFalse");
                    return false;
                }
            } catch (SQLException e) {
                Log.d("a1",e.toString()+"here");
            }
            return false;
        }

        public String getUser() {
            return User;
        }

        public Boolean getValid() {
            return valid;
        }
    }
    static private class CreateUserClass extends Thread
    {
        String uname;
        String pass;
        Connection con;
        boolean Created;
        String User;
        public CreateUserClass(Connection con,String uname, String pass) {
            this.uname = uname;
            this.pass = pass;
            this.con = con;

        }

        @Override
        public void run() {
                Created=false;
            try {
                Statement pr=con.createStatement();


                int i=pr.executeUpdate("insert into User values ('"+uname+"','"+pass+"');");
                // Log.d("a1","select * from User where Uname='"+uname+"' and Pass='"+pass+"';"+":::statemnet");
                //Log.d("a1",(ar==null)+":::ar");

                //Log.d("a1",(ar==null)+":::ar");
                //Log.d("a1",ar.getString(1)+"");
                if(i>0)
                {
                    Created=true;
                    return;
                }
                return;
            } catch (SQLException e) {
                Log.d("a1",e.toString());
            }
            return;
        }

        public boolean isCreated() {
            return Created;
        }
    }

    public Connection getCon() {
        return con;
    }

    public String getUser() {
        return User;
    }
}
