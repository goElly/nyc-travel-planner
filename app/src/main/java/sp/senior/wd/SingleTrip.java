package sp.senior.wd;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class SingleTrip extends AppCompatActivity {
//    private static final String REMOTE_IP = "216.37.100.185";
//    private static final String REMOTE_IP = "10.0.20.209";
private static final String REMOTE_IP = "216.37.99.69";
    private static final String URL = "jdbc:mysql://" + REMOTE_IP + "/apptest";
    private static final String USER = "root";
    private static final String PASSWORD = "0213";
    private Connection conn;

    private String date;
    private String id;

    private Vector places = new Vector();
    private Vector singleplaces = new Vector();
    TextView title;
    ListView placeslist;

    private Vector place1 = new Vector();
    private Vector place2 = new Vector();
    private Vector place3 = new Vector();
    private Vector place4 = new Vector();
    private Vector place5 = new Vector();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_trip);
        Intent i = getIntent();
        date = i.getStringExtra("date");
        id = i.getStringExtra("id");
        Log.e("date",date);
        Log.e("id",id);
        title = findViewById(R.id.title_date);
        title.setText(date);
        placeslist = findViewById(R.id.placeslist);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.map);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(SingleTrip.this, MapsActivity2.class);
                i.putExtra("place1", transDouble(place1));
                i.putExtra("place2", transDouble(place2));
                i.putExtra("place3", transDouble(place3));
                i.putExtra("place4", transDouble(place4));
                i.putExtra("place5", transDouble(place5));
                i.putExtra("place_name1", singleplaces.get(0).toString());
                i.putExtra("place_name2", singleplaces.get(1).toString());
                i.putExtra("place_name3", singleplaces.get(2).toString());
                i.putExtra("place_name4", singleplaces.get(3).toString());
                i.putExtra("place_name5", singleplaces.get(4).toString());
                startActivity(i);
            }
        });

        final Handler handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                            SingleTrip.this, android.R.layout.simple_list_item_1, places);
                    placeslist.setAdapter(adapter);
                }
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                conn = Util.openConnection(URL, USER, PASSWORD);
                try {
                    String sql = "select * from apptest.trips where trips.userid = '"+id+"' and trips.date = '"+date+"' ";
                    Statement s = (Statement) conn.createStatement();
                    ResultSet rs = s.executeQuery(sql);
                    while (rs.next()) {
                        places.add("8:00 AM      " + rs.getString("place1"));
                        places.add("10:30 AM    " + rs.getString("place2"));
                        places.add("1:00 PM      " + rs.getString("place3"));
                        places.add("3:30 PM      " + rs.getString("place4"));
                        places.add("6:00 PM      " + rs.getString("place5"));

                        singleplaces.add(rs.getString("place1"));
                        singleplaces.add(rs.getString("place2"));
                        singleplaces.add(rs.getString("place3"));
                        singleplaces.add(rs.getString("place4"));
                        singleplaces.add(rs.getString("place5"));
                    }

                        String sql1 = "select latitude,longitude from apptest.attractions where attractions.name = '"+singleplaces.get(0).toString()+"'";
                        String sql2 = "select latitude,longitude from apptest.attractions where attractions.name = '"+singleplaces.get(1).toString()+"'";
                        String sql3 = "select latitude,longitude from apptest.attractions where attractions.name = '"+singleplaces.get(2).toString()+"'";
                        String sql4 = "select latitude,longitude from apptest.attractions where attractions.name = '"+singleplaces.get(3).toString()+"'";
                        String sql5 = "select latitude,longitude from apptest.attractions where attractions.name = '"+singleplaces.get(4).toString()+"'";

                        ResultSet rs1 = s.executeQuery(sql1);
                        while (rs1.next()) {
                            place1.add(rs1.getString("latitude"));
                            place1.add(rs1.getString("longitude"));
                        }

                        ResultSet rs2 = s.executeQuery(sql2);
                        while (rs2.next()) {
                            place2.add(rs2.getString("latitude"));
                            place2.add(rs2.getString("longitude"));
                        }

                        ResultSet rs3 = s.executeQuery(sql3);
                        while (rs3.next()) {
                            place3.add(rs3.getString("latitude"));
                            place3.add(rs3.getString("longitude"));
                        }

                        ResultSet rs4 = s.executeQuery(sql4);
                        while (rs4.next()) {
                            place4.add(rs4.getString("latitude"));
                            place4.add(rs4.getString("longitude"));
                        }

                        ResultSet rs5 = s.executeQuery(sql5);
                        while (rs5.next()) {
                            place5.add(rs5.getString("latitude"));
                            place5.add(rs5.getString("longitude"));
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

    private double[] transDouble(Vector place) {
        double lat = Double.parseDouble(place.get(0).toString());
        double lng = Double.parseDouble(place.get(1).toString());
        return new double[] {lat,lng};
    }
}
