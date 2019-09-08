package sp.senior.wd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Confirm extends AppCompatActivity {
    String id;
    String date;

    //    private static final String REMOTE_IP = "10.0.20.209";
//    private static final String REMOTE_IP = "216.37.100.185";
    private static final String REMOTE_IP = "216.37.98.216";
    private static final String URL = "jdbc:mysql://" + REMOTE_IP + "/apptest";
    private static final String USER = "root";
    private static final String PASSWORD = "0213";
    private Connection conn;

    String T1;
    String T2;
    String T3;
    String T4;
    String T5;
    String A1;
    String A2;
    String A3;
    String A4;
    String A5;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        Intent intent = getIntent();
        T1 = intent.getStringExtra("T1");
        A1 = intent.getStringExtra("A1");
        T2 = intent.getStringExtra("T2");
        A2 = intent.getStringExtra("A2");
        T3 = intent.getStringExtra("T3");
        A3 = intent.getStringExtra("A3");
        T4 = intent.getStringExtra("T4");
        A4 = intent.getStringExtra("A4");
        T5 = intent.getStringExtra("T5");
        A5 = intent.getStringExtra("A5");
        id = intent.getStringExtra("id");
        date = intent.getStringExtra("date");

        TextView tv1 = (TextView) findViewById(R.id.tv1);
        tv1.setText(T1);
        TextView tv1a = (TextView) findViewById(R.id.tv1a);
        tv1a.setText(A1);

        TextView tv2 = (TextView) findViewById(R.id.tv2);
        tv2.setText(T2);
        TextView tv2a = (TextView) findViewById(R.id.tv2a);
        tv2a.setText(A2);

        TextView tv3 = (TextView) findViewById(R.id.tv3);
        tv3.setText(T3);
        TextView tv3a = (TextView) findViewById(R.id.tv3a);
        tv3a.setText(A3);

        TextView tv4 = (TextView) findViewById(R.id.tv4);
        tv4.setText(T4);
        TextView tv4a = (TextView) findViewById(R.id.tv4a);
        tv4a.setText(A4);

        TextView tv5 = (TextView) findViewById(R.id.tv5);
        tv5.setText(T5);
        TextView tv5a = (TextView) findViewById(R.id.tv5a);
        tv5a.setText(A5);

        TextView time1 = (TextView) findViewById(R.id.textView1);
        TextView time2 = (TextView) findViewById(R.id.textView2);
        TextView time3 = (TextView) findViewById(R.id.textView3);
        TextView time4 = (TextView) findViewById(R.id.textView4);
        TextView time5 = (TextView) findViewById(R.id.textView5);

        new Thread(new Runnable() {
            @Override
            public void run() {
                conn = Util.openConnection(URL, USER, PASSWORD);
                String sql = "insert into apptest.trips values ('"+id+"', '"+date+"', '"+T1+"', '"+T2+"', " +
                        "'"+T3+"', '"+T4+"', '"+T5+"');";
                try {
                    Statement statement = conn.createStatement();
                    statement.execute(sql);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }).start();
// 若未选择事件，隐藏对应栏
        if(T1.isEmpty()){
            time1.setHeight(0);
            tv1.setHeight(0);
            tv1a.setHeight(0);

        }

        if(T2.isEmpty()){
            time2.setHeight(0);
            tv2.setHeight(0);
            tv2a.setHeight(0);

        }

        if(T3.isEmpty()){
            time3.setHeight(0);
            tv3.setHeight(0);
            tv3a.setHeight(0);

        }


        if(T4.isEmpty()){
            time4.setHeight(0);
            tv4.setHeight(0);
            tv4a.setHeight(0);

        }

        if(T5.isEmpty()){
            time5.setHeight(0);
            tv5.setHeight(0);
            tv5a.setHeight(0);

        }
    }
}
