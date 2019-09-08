package sp.senior.wd;


import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class MyTrips extends AppCompatActivity {
//    private static final String REMOTE_IP = "216.37.100.185";
//    private static final String REMOTE_IP = "10.0.20.209";
    private static final String REMOTE_IP = "216.37.99.69";
    private static final String URL = "jdbc:mysql://" + REMOTE_IP + "/apptest";
    private static final String USER = "root";
    private static final String PASSWORD = "0213";

//    private static final String REMOTE_IP = "216.37.98.200";
//    private static final String URL = "jdbc:mysql://" + REMOTE_IP + "/test";
//    private static final String USER = "root";
//    private static final String PASSWORD = "123456";

    private Connection conn;

    private String id;
    private ListView lv;
    private Vector date = new Vector();
    private String trip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trips);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        lv= (ListView) findViewById(R.id.listview);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()

            {

                // 第position项被单击时激发该方法

                @Override

                public void onItemClick(AdapterView<?> parent, View view, int position, long id2)
                {
                    trip = date.get(position).toString();
                    //Log.e("date",trip);
                    Intent i = new Intent ();
                    i.setClass(MyTrips.this, SingleTrip.class);
                    i.putExtra("date", trip);
                    i.putExtra("id", id);
                    startActivity(i);
                }

            });




        final Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        MyTrips.this, android.R.layout.simple_list_item_1, date);
                lv.setAdapter(adapter);
            }
        }
    };

        new Thread(new Runnable() {
            @Override
            public void run() {
                conn = Util.openConnection(URL, USER, PASSWORD);
                try {
                    String sql = "select date from apptest.trips where trips.userid = '"+id+"'";
                    Statement s = (Statement) conn.createStatement();
                    ResultSet rs = s.executeQuery(sql);
                    while (rs.next()) {
                        date.add(rs.getString("date"));
                    }
                    Message msg = Message.obtain();
                    msg.what = 1;
                    handler.sendMessage(msg);
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
