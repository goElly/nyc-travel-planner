package sp.senior.wd;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import sp.senior.wd.Entity.Attraction;

public class Plan extends AppCompatActivity {

    EditText et1;
    TextView tv1, tv2, tv3, tv4, tv5, tv1a, tv2a, tv3a, tv4a, tv5a;


    ListView lv1;
    ArrayList<weather> arrlist;

    private View view1_1, view1_2, view1_3, view1_4, view1_5;
    private View view2_1, view2_2, view2_3, view2_4, view2_5;
    private View view3_1, view3_2, view3_3, view3_4, view3_5;
    private View view4_1, view4_2, view4_3, view4_4, view4_5;
    private View view5_1, view5_2, view5_3, view5_4, view5_5;


    private ViewPager viewPager1;  //对应的viewPager
    private ViewPager viewPager2;  //对应的viewPager
    private ViewPager viewPager3;  //对应的viewPager
    private ViewPager viewPager4;  //对应的viewPager
    private ViewPager viewPager5;  //对应的viewPager


    private List<View> viewList;//view数组
    private List<View> viewList2;//view数组
    private List<View> viewList3;//view数组
    private List<View> viewList4;//view数组
    private List<View> viewList5;//view数组

//    private static final String REMOTE_IP = "10.0.20.209";
//    private static final String REMOTE_IP = "216.37.100.185";
private static final String REMOTE_IP = "216.37.99.69";
    private static final String URL = "jdbc:mysql://" + REMOTE_IP + "/apptest";
    private static final String USER = "root";
    private static final String PASSWORD = "0213";
    private Connection conn;
//
//    public static Vector title = new Vector();
//    private Vector rating = new Vector();
//    private Vector price = new Vector();
//    private Vector hours = new Vector();
//    private Vector address = new Vector();

    private Attraction a1 = new Attraction();
    private Attraction a2 = new Attraction();
    private Attraction a3 = new Attraction();
    private Attraction a4 = new Attraction();
    private Attraction a5 = new Attraction();

    private String date;
    private String id;

    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        Intent i = getIntent();
        date = i.getStringExtra("date");
        id = i.getStringExtra("id");


        Button confirm = (Button) findViewById(R.id.confirm);


        lv1 = (ListView) findViewById(R.id.lv1);

        tv1 = findViewById(R.id.title1);
        tv2 = findViewById(R.id.title2);
        tv3 = findViewById(R.id.title3);
        tv4 = findViewById(R.id.title4);
        tv5 = findViewById(R.id.title5);

        tv1a = findViewById(R.id.address1);
        tv2a = findViewById(R.id.address2);
        tv3a = findViewById(R.id.address3);
        tv4a = findViewById(R.id.address4);
        tv5a = findViewById(R.id.address5);




        viewPager1 = (ViewPager) findViewById(R.id.activity_1);
        LayoutInflater inflater = getLayoutInflater();

        view1_1 = getLayoutInflater().inflate(R.layout.view1_1, null);
        final CheckBox check1_1 = (CheckBox) view1_1.findViewById(R.id.checkBox1_1);
        final TextView title1_1 = (TextView) view1_1.findViewById(R.id.title1_1);
        final TextView address1_1 = (TextView) view1_1.findViewById(R.id.address1_1);
        final TextView rating1_1 = view1_1.findViewById(R.id.rating1_1);
        final TextView price1_1 = view1_1.findViewById(R.id.price1_1);
        final TextView hours1_1 = view1_1.findViewById(R.id.duration1_1);
        final TextView tags1_1 = view1_1.findViewById(R.id.tag1_1);

        view1_2 = inflater.inflate(R.layout.view1_2, null);
        final CheckBox check1_2 = (CheckBox) view1_2.findViewById(R.id.checkBox1_2);
        final TextView title1_2 = (TextView) view1_2.findViewById(R.id.title1_2);
        final TextView address1_2 = (TextView) view1_2.findViewById(R.id.address1_2);
        final TextView rating1_2 = view1_2.findViewById(R.id.rating1_2);
        final TextView price1_2 = view1_2.findViewById(R.id.price1_2);
        final TextView hours1_2 = view1_2.findViewById(R.id.duration1_2);
        final TextView tags1_2 = view1_2.findViewById(R.id.tag1_2);

        view1_3 = inflater.inflate(R.layout.view1_3, null);
        final CheckBox check1_3 = (CheckBox) view1_3.findViewById(R.id.checkBox1_3);
        final TextView title1_3 = (TextView) view1_3.findViewById(R.id.title1_3);
        final TextView address1_3 = (TextView) view1_3.findViewById(R.id.address1_3);
        final TextView rating1_3 = view1_3.findViewById(R.id.rating1_3);
        final TextView price1_3 = view1_3.findViewById(R.id.price1_3);
        final TextView hours1_3 = view1_3.findViewById(R.id.duration1_3);
        final TextView tags1_3 = view1_3.findViewById(R.id.tag1_3);

        view1_4 = inflater.inflate(R.layout.view1_4, null);
        final CheckBox check1_4 = (CheckBox) view1_4.findViewById(R.id.checkBox1_4);
        final TextView title1_4 = (TextView) view1_4.findViewById(R.id.title1_4);
        final TextView address1_4 = (TextView) view1_4.findViewById(R.id.address1_4);
        final TextView rating1_4 = view1_4.findViewById(R.id.rating1_4);
        final TextView price1_4 = view1_4.findViewById(R.id.price1_4);
        final TextView hours1_4 = view1_4.findViewById(R.id.duration1_4);
        final TextView tags1_4 = view1_4.findViewById(R.id.tag1_4);

        view1_5 = inflater.inflate(R.layout.view1_5, null);
        final CheckBox check1_5 = (CheckBox) view1_5.findViewById(R.id.checkBox1_5);
        final TextView title1_5 = (TextView) view1_5.findViewById(R.id.title1_5);
        final TextView address1_5 = (TextView) view1_5.findViewById(R.id.address1_5);
        final TextView rating1_5 = view1_5.findViewById(R.id.rating1_5);
        final TextView price1_5 = view1_5.findViewById(R.id.price1_5);
        final TextView hours1_5 = view1_5.findViewById(R.id.duration1_5);
        final TextView tags1_5 = view1_5.findViewById(R.id.tag1_5);

        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList.add(view1_1);
        viewList.add(view1_2);
        viewList.add(view1_3);
        viewList.add(view1_4);
        viewList.add(view1_5);


        viewPager2 = (ViewPager) findViewById(R.id.activity_2);
        LayoutInflater inflater2 = getLayoutInflater();
        view2_1 = inflater2.inflate(R.layout.view2_1, null);
        final CheckBox check2_1 = (CheckBox) view2_1.findViewById(R.id.checkBox2_1);
        final TextView title2_1 = (TextView) view2_1.findViewById(R.id.title2_1);
        final TextView address2_1 = (TextView) view2_1.findViewById(R.id.address2_1);
        final TextView rating2_1 = view2_1.findViewById(R.id.rating2_1);
        final TextView price2_1 = view2_1.findViewById(R.id.price2_1);
        final TextView hours2_1 = view2_1.findViewById(R.id.duration2_1);
        final TextView tags2_1 = view2_1.findViewById(R.id.tag2_1);

        view2_2 = inflater2.inflate(R.layout.view2_2, null);
        view2_2 = inflater2.inflate(R.layout.view2_2, null);
        final CheckBox check2_2 = (CheckBox) view2_2.findViewById(R.id.checkBox2_2);
        final TextView title2_2 = (TextView) view2_2.findViewById(R.id.title2_2);
        final TextView address2_2 = (TextView) view2_2.findViewById(R.id.address2_2);
        final TextView rating2_2 = view2_2.findViewById(R.id.rating2_2);
        final TextView price2_2 = view2_2.findViewById(R.id.price2_2);
        final TextView hours2_2 = view2_2.findViewById(R.id.duration2_2);
        final TextView tags2_2 = view2_2.findViewById(R.id.tag2_2);

        view2_3 = inflater2.inflate(R.layout.view2_3, null);
        view2_3 = inflater2.inflate(R.layout.view2_3, null);
        final CheckBox check2_3 = (CheckBox) view2_3.findViewById(R.id.checkBox2_3);
        final TextView title2_3 = (TextView) view2_3.findViewById(R.id.title2_3);
        final TextView address2_3 = (TextView) view2_3.findViewById(R.id.address2_3);
        final TextView rating2_3 = view2_3.findViewById(R.id.rating2_3);
        final TextView price2_3 = view2_3.findViewById(R.id.price2_3);
        final TextView hours2_3 = view2_3.findViewById(R.id.duration2_3);
        final TextView tags2_3 = view2_3.findViewById(R.id.tag2_3);

        view2_4 = inflater2.inflate(R.layout.view2_4, null);
        view2_4 = inflater2.inflate(R.layout.view2_4, null);
        final CheckBox check2_4 = (CheckBox) view2_4.findViewById(R.id.checkBox2_4);
        final TextView title2_4 = (TextView) view2_4.findViewById(R.id.title2_4);
        final TextView address2_4 = (TextView) view2_4.findViewById(R.id.address2_4);
        final TextView rating2_4 = view2_4.findViewById(R.id.rating2_4);
        final TextView price2_4 = view2_4.findViewById(R.id.price2_4);
        final TextView hours2_4 = view2_4.findViewById(R.id.duration2_4);
        final TextView tags2_4 = view2_4.findViewById(R.id.tag2_4);

        view2_5 = inflater2.inflate(R.layout.view2_5, null);
        view2_5 = inflater2.inflate(R.layout.view2_5, null);
        final CheckBox check2_5 = (CheckBox) view2_5.findViewById(R.id.checkBox2_5);
        final TextView title2_5 = (TextView) view2_5.findViewById(R.id.title2_5);
        final TextView address2_5 = (TextView) view2_5.findViewById(R.id.address2_5);
        final TextView rating2_5 = view2_5.findViewById(R.id.rating2_5);
        final TextView price2_5 = view2_5.findViewById(R.id.price2_5);
        final TextView hours2_5 = view2_5.findViewById(R.id.duration2_5);
        final TextView tags2_5 = view2_5.findViewById(R.id.tag2_5);

        viewList2 = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList2.add(view2_1);
        viewList2.add(view2_2);
        viewList2.add(view2_3);
        viewList2.add(view2_4);
        viewList2.add(view2_5);


        viewPager3 = (ViewPager) findViewById(R.id.activity_3);
        LayoutInflater inflater3 = getLayoutInflater();
        view3_1 = inflater3.inflate(R.layout.view3_1, null);
        final CheckBox check3_1 = (CheckBox) view3_1.findViewById(R.id.checkBox3_1);
        final TextView title3_1 = (TextView) view3_1.findViewById(R.id.title3_1);
        final TextView address3_1 = (TextView) view3_1.findViewById(R.id.address3_1);
        final TextView rating3_1 = view3_1.findViewById(R.id.rating3_1);
        final TextView price3_1 = view3_1.findViewById(R.id.price3_1);
        final TextView hours3_1 = view3_1.findViewById(R.id.duration3_1);
        final TextView tags3_1 = view3_1.findViewById(R.id.tag3_1);

        view3_2 = inflater3.inflate(R.layout.view3_2, null);
        final CheckBox check3_2 = (CheckBox) view3_2.findViewById(R.id.checkBox3_2);
        final TextView title3_2 = (TextView) view3_2.findViewById(R.id.title3_2);
        final TextView address3_2 = (TextView) view3_2.findViewById(R.id.address3_2);
        final TextView rating3_2 = view3_2.findViewById(R.id.rating3_2);
        final TextView price3_2 = view3_2.findViewById(R.id.price3_2);
        final TextView hours3_2 = view3_2.findViewById(R.id.duration3_2);
        final TextView tags3_2 = view3_2.findViewById(R.id.tag3_2);

        view3_3 = inflater3.inflate(R.layout.view3_3, null);
        final CheckBox check3_3 = (CheckBox) view3_3.findViewById(R.id.checkBox3_3);
        final TextView title3_3 = (TextView) view3_3.findViewById(R.id.title3_3);
        final TextView address3_3 = (TextView) view3_3.findViewById(R.id.address3_3);
        final TextView rating3_3 = view3_3.findViewById(R.id.rating3_3);
        final TextView price3_3 = view3_3.findViewById(R.id.price3_3);
        final TextView hours3_3 = view3_3.findViewById(R.id.duration3_3);
        final TextView tags3_3 = view3_3.findViewById(R.id.tag3_3);

        view3_4 = inflater3.inflate(R.layout.view3_4, null);
        final CheckBox check3_4 = (CheckBox) view3_4.findViewById(R.id.checkBox3_4);
        final TextView title3_4 = (TextView) view3_4.findViewById(R.id.title3_4);
        final TextView address3_4 = (TextView) view3_4.findViewById(R.id.address3_4);
        final TextView rating3_4 = view3_4.findViewById(R.id.rating3_4);
        final TextView price3_4 = view3_4.findViewById(R.id.price3_4);
        final TextView hours3_4 = view3_4.findViewById(R.id.duration3_4);
        final TextView tags3_4 = view3_4.findViewById(R.id.tag3_4);

        view3_5 = inflater3.inflate(R.layout.view3_5, null);
        final CheckBox check3_5 = (CheckBox) view3_5.findViewById(R.id.checkBox3_5);
        final TextView title3_5 = (TextView) view3_5.findViewById(R.id.title3_5);
        final TextView address3_5 = (TextView) view3_5.findViewById(R.id.address3_5);
        final TextView rating3_5 = view3_5.findViewById(R.id.rating3_5);
        final TextView price3_5 = view3_5.findViewById(R.id.price3_5);
        final TextView hours3_5 = view3_5.findViewById(R.id.duration3_5);
        final TextView tags3_5 = view3_5.findViewById(R.id.tag3_5);

        viewList3 = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList3.add(view3_1);
        viewList3.add(view3_2);
        viewList3.add(view3_3);
        viewList3.add(view3_4);
        viewList3.add(view3_5);


        viewPager4 = (ViewPager) findViewById(R.id.activity_4);
        LayoutInflater inflater4 = getLayoutInflater();
        view4_1 = inflater4.inflate(R.layout.view4_1, null);
        final CheckBox check4_1 = (CheckBox) view4_1.findViewById(R.id.checkBox4_1);
        final TextView title4_1 = (TextView) view4_1.findViewById(R.id.title4_1);
        final TextView address4_1 = (TextView) view4_1.findViewById(R.id.address4_1);
        final TextView rating4_1 = view4_1.findViewById(R.id.rating4_1);
        final TextView price4_1 = view4_1.findViewById(R.id.price4_1);
        final TextView hours4_1 = view4_1.findViewById(R.id.duration4_1);
        final TextView tags4_1 = view4_1.findViewById(R.id.tag4_1);

        view4_2 = inflater4.inflate(R.layout.view4_2, null);
        final CheckBox check4_2 = (CheckBox) view4_2.findViewById(R.id.checkBox4_2);
        final TextView title4_2 = (TextView) view4_2.findViewById(R.id.title4_2);
        final TextView address4_2 = (TextView) view4_2.findViewById(R.id.address4_2);
        final TextView rating4_2 = view4_2.findViewById(R.id.rating4_2);
        final TextView price4_2 = view4_2.findViewById(R.id.price4_2);
        final TextView hours4_2 = view4_2.findViewById(R.id.duration4_2);
        final TextView tags4_2 = view4_2.findViewById(R.id.tag4_2);

        view4_3 = inflater4.inflate(R.layout.view4_3, null);
        final CheckBox check4_3 = (CheckBox) view4_3.findViewById(R.id.checkBox4_3);
        final TextView title4_3 = (TextView) view4_3.findViewById(R.id.title4_3);
        final TextView address4_3 = (TextView) view4_3.findViewById(R.id.address4_3);
        final TextView rating4_3 = view4_3.findViewById(R.id.rating4_3);
        final TextView price4_3 = view4_3.findViewById(R.id.price4_3);
        final TextView hours4_3 = view4_3.findViewById(R.id.duration4_3);
        final TextView tags4_3 = view4_3.findViewById(R.id.tag4_3);


        view4_4 = inflater4.inflate(R.layout.view4_4, null);
        final CheckBox check4_4 = (CheckBox) view4_4.findViewById(R.id.checkBox4_4);
        final TextView title4_4 = (TextView) view4_4.findViewById(R.id.title4_4);
        final TextView address4_4 = (TextView) view4_4.findViewById(R.id.address4_4);
        final TextView rating4_4 = view4_4.findViewById(R.id.rating4_4);
        final TextView price4_4 = view4_4.findViewById(R.id.price4_4);
        final TextView hours4_4 = view4_4.findViewById(R.id.duration4_4);
        final TextView tags4_4 = view4_4.findViewById(R.id.tag4_4);

        view4_5 = inflater4.inflate(R.layout.view4_5, null);
        final CheckBox check4_5 = (CheckBox) view4_5.findViewById(R.id.checkBox4_5);
        final TextView title4_5 = (TextView) view4_5.findViewById(R.id.title4_5);
        final TextView address4_5 = (TextView) view4_5.findViewById(R.id.address4_5);
        final TextView rating4_5 = view4_5.findViewById(R.id.rating4_5);
        final TextView price4_5 = view4_5.findViewById(R.id.price4_5);
        final TextView hours4_5 = view4_5.findViewById(R.id.duration4_5);
        final TextView tags4_5 = view4_5.findViewById(R.id.tag4_5);

        viewList4 = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList4.add(view4_1);
        viewList4.add(view4_2);
        viewList4.add(view4_3);
        viewList4.add(view4_4);
        viewList4.add(view4_5);


        viewPager5 = (ViewPager) findViewById(R.id.activity_5);
        LayoutInflater inflater5 = getLayoutInflater();
        view5_1 = inflater4.inflate(R.layout.view5_1, null);
        final CheckBox check5_1 = (CheckBox) view5_1.findViewById(R.id.checkBox5_1);
        final TextView title5_1 = (TextView) view5_1.findViewById(R.id.title5_1);
        final TextView address5_1 = (TextView) view5_1.findViewById(R.id.address5_1);
        final TextView rating5_1 = view5_1.findViewById(R.id.rating5_1);
        final TextView price5_1 = view5_1.findViewById(R.id.price5_1);
        final TextView hours5_1 = view5_1.findViewById(R.id.duration5_1);
        final TextView tags5_1 = view5_1.findViewById(R.id.tag5_1);

        view5_2 = inflater4.inflate(R.layout.view5_2, null);
        final CheckBox check5_2 = (CheckBox) view5_2.findViewById(R.id.checkBox5_2);
        final TextView title5_2 = (TextView) view5_2.findViewById(R.id.title5_2);
        final TextView address5_2 = (TextView) view5_2.findViewById(R.id.address5_2);
        final TextView rating5_2 = view5_2.findViewById(R.id.rating5_2);
        final TextView price5_2 = view5_2.findViewById(R.id.price5_2);
        final TextView hours5_2 = view5_2.findViewById(R.id.duration5_2);
        final TextView tags5_2 = view5_2.findViewById(R.id.tag5_2);

        view5_3 = inflater4.inflate(R.layout.view5_3, null);
        final CheckBox check5_3 = (CheckBox) view5_3.findViewById(R.id.checkBox5_3);
        final TextView title5_3 = (TextView) view5_3.findViewById(R.id.title5_3);
        final TextView address5_3 = (TextView) view5_3.findViewById(R.id.address5_3);
        final TextView rating5_3 = view5_3.findViewById(R.id.rating5_3);
        final TextView price5_3 = view5_3.findViewById(R.id.price5_3);
        final TextView hours5_3 = view5_3.findViewById(R.id.duration5_3);
        final TextView tags5_3 = view5_3.findViewById(R.id.tag5_3);

        view5_4 = inflater4.inflate(R.layout.view5_4, null);
        final CheckBox check5_4 = (CheckBox) view5_4.findViewById(R.id.checkBox5_4);
        final TextView title5_4 = (TextView) view5_4.findViewById(R.id.title5_4);
        final TextView address5_4 = (TextView) view5_4.findViewById(R.id.address5_4);
        final TextView rating5_4 = view5_4.findViewById(R.id.rating5_4);
        final TextView price5_4 = view5_4.findViewById(R.id.price5_4);
        final TextView hours5_4 = view5_4.findViewById(R.id.duration5_4);
        final TextView tags5_4 = view5_4.findViewById(R.id.tag5_4);

        view5_5 = inflater4.inflate(R.layout.view5_5, null);
        final CheckBox check5_5 = (CheckBox) view5_5.findViewById(R.id.checkBox5_5);
        final TextView title5_5 = (TextView) view5_5.findViewById(R.id.title5_5);
        final TextView address5_5 = (TextView) view5_5.findViewById(R.id.address5_5);
        final TextView rating5_5 = view5_5.findViewById(R.id.rating5_5);
        final TextView price5_5 = view5_5.findViewById(R.id.price5_5);
        final TextView hours5_5 = view5_5.findViewById(R.id.duration5_5);
        final TextView tags5_5 = view5_5.findViewById(R.id.tag5_5);

        viewList5 = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList5.add(view5_1);
        viewList5.add(view5_2);
        viewList5.add(view5_3);
        viewList5.add(view5_4);
        viewList5.add(view5_5);

        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                super.handleMessage(msg);
                if(msg.what == 1 && a1.getTitle().size() > 4){
                    title1_1.setText(a1.getTitle().get(0).toString());
                    address1_1.setText(a1.getAddress().get(0).toString());
                    rating1_1.setText("Rating: " + a1.getRating().get(0).toString());
                    price1_1.setText("Price: " + a1.getPrice().get(0).toString());
                    hours1_1.setText(a1.getHours().get(0).toString() + " hours");
                    tags1_1.setText(a1.getTags().get(0).toString());

                    title1_2.setText(a1.getTitle().get(1).toString());
                    address1_2.setText(a1.getAddress().get(1).toString());
                    rating1_2.setText("Rating: " + a1.getRating().get(1).toString());
                    price1_2.setText("Price: " + a1.getPrice().get(1).toString());
                    hours1_2.setText(a1.getHours().get(1).toString() + " hours");
                    tags1_2.setText(a1.getTags().get(1).toString());

                    title1_3.setText(a1.getTitle().get(2).toString());
                    address1_3.setText(a1.getAddress().get(2).toString());
                    rating1_3.setText("Rating: " + a1.getRating().get(2).toString());
                    price1_3.setText("Price: " + a1.getPrice().get(2).toString());
                    hours1_3.setText(a1.getHours().get(2).toString() + " hours");
                    tags1_3.setText(a1.getTags().get(2).toString());

                    title1_4.setText(a1.getTitle().get(3).toString());
                    address1_4.setText(a1.getAddress().get(3).toString());
                    rating1_4.setText("Rating: " + a1.getRating().get(3).toString());
                    price1_4.setText("Price: " + a1.getPrice().get(3).toString());
                    hours1_4.setText(a1.getHours().get(3).toString() + " hours");
                    tags1_4.setText(a1.getTags().get(3).toString());

                    title1_5.setText(a1.getTitle().get(4).toString());
                    address1_5.setText(a1.getAddress().get(4).toString());
                    rating1_5.setText("Rating: " + a1.getRating().get(4).toString());
                    price1_5.setText("Price: " + a1.getPrice().get(4).toString());
                    hours1_5.setText(a1.getHours().get(4).toString() + " hours");
                    tags1_5.setText(a1.getTags().get(4).toString());
                }

                if(msg.what == 1 && a2.getTitle().size() > 4){
                    title2_1.setText(a2.getTitle().get(0).toString());
                    address2_1.setText(a2.getAddress().get(0).toString());
                    rating2_1.setText("Rating: " + a2.getRating().get(0).toString());
                    price2_1.setText("Price: " + a2.getPrice().get(0).toString());
                    hours2_1.setText(a2.getHours().get(0).toString() + " hours");
                    tags2_1.setText(a2.getTags().get(0).toString());

                    title2_2.setText(a2.getTitle().get(1).toString());
                    address2_2.setText(a2.getAddress().get(1).toString());
                    rating2_2.setText("Rating: " + a2.getRating().get(1).toString());
                    price2_2.setText("Price: " + a2.getPrice().get(1).toString());
                    hours2_2.setText(a2.getHours().get(1).toString() + " hours");
                    tags2_2.setText(a2.getTags().get(1).toString());

                    title2_3.setText(a2.getTitle().get(2).toString());
                    address2_3.setText(a2.getAddress().get(2).toString());
                    rating2_3.setText("Rating: " + a2.getRating().get(2).toString());
                    price2_3.setText("Price: " + a2.getPrice().get(2).toString());
                    hours2_3.setText(a2.getHours().get(2).toString() + " hours");
                    tags2_3.setText(a2.getTags().get(2).toString());

                    title2_4.setText(a2.getTitle().get(3).toString());
                    address2_4.setText(a2.getAddress().get(3).toString());
                    rating2_4.setText("Rating: " + a2.getRating().get(3).toString());
                    price2_4.setText("Price: " + a2.getPrice().get(3).toString());
                    hours2_4.setText(a2.getHours().get(3).toString() + " hours");
                    tags2_4.setText(a2.getTags().get(3).toString());

                    title2_5.setText(a2.getTitle().get(4).toString());
                    address2_5.setText(a2.getAddress().get(4).toString());
                    rating2_5.setText("Rating: " + a2.getRating().get(4).toString());
                    price2_5.setText("Price: " + a2.getPrice().get(4).toString());
                    hours2_5.setText(a2.getHours().get(4).toString() + " hours");
                    tags2_5.setText(a2.getTags().get(4).toString());
                }

                if(msg.what == 1 && a3.getTitle().size() > 4){
                    title3_1.setText(a3.getTitle().get(0).toString());
                    address3_1.setText(a3.getAddress().get(0).toString());
                    rating3_1.setText("Rating: " + a3.getRating().get(0).toString());
                    price3_1.setText("Price: " + a3.getPrice().get(0).toString());
                    hours3_1.setText(a3.getHours().get(0).toString() + " hours");
                    tags3_1.setText(a3.getTags().get(0).toString());

                    title3_2.setText(a3.getTitle().get(1).toString());
                    address3_2.setText(a3.getAddress().get(1).toString());
                    rating3_2.setText("Rating: " + a3.getRating().get(1).toString());
                    price3_2.setText("Price: " + a3.getPrice().get(1).toString());
                    hours3_2.setText(a3.getHours().get(1).toString() + " hours");
                    tags3_2.setText(a3.getTags().get(1).toString());

                    title3_3.setText(a3.getTitle().get(2).toString());
                    address3_3.setText(a3.getAddress().get(2).toString());
                    rating3_3.setText("Rating: " + a3.getRating().get(2).toString());
                    price3_3.setText("Price: " + a3.getPrice().get(2).toString());
                    hours3_3.setText(a3.getHours().get(2).toString() + " hours");
                    tags3_3.setText(a3.getTags().get(2).toString());

                    title3_4.setText(a3.getTitle().get(3).toString());
                    address3_4.setText(a3.getAddress().get(3).toString());
                    rating3_4.setText("Rating: " + a3.getRating().get(3).toString());
                    price3_4.setText("Price: " + a3.getPrice().get(3).toString());
                    hours3_4.setText(a3.getHours().get(3).toString() + " hours");
                    tags3_4.setText(a3.getTags().get(3).toString());

                    title3_5.setText(a3.getTitle().get(4).toString());
                    address3_5.setText(a3.getAddress().get(4).toString());
                    rating3_5.setText("Rating: " + a3.getRating().get(4).toString());
                    price3_5.setText("Price: " + a3.getPrice().get(4).toString());
                    hours3_5.setText(a3.getHours().get(4).toString() + " hours");
                    tags3_5.setText(a3.getTags().get(4).toString());
                }

                if(msg.what == 1 && a4.getTitle().size() > 4){
                    title4_1.setText(a4.getTitle().get(0).toString());
                    address4_1.setText(a4.getAddress().get(0).toString());
                    rating4_1.setText("Rating: " + a4.getRating().get(0).toString());
                    price4_1.setText("Price: " + a4.getPrice().get(0).toString());
                    hours4_1.setText(a4.getHours().get(0).toString() + " hours");
                    tags4_1.setText(a4.getTags().get(0).toString());

                    title4_2.setText(a4.getTitle().get(1).toString());
                    address4_2.setText(a4.getAddress().get(1).toString());
                    rating4_2.setText("Rating: " + a4.getRating().get(1).toString());
                    price4_2.setText("Price: " + a4.getPrice().get(1).toString());
                    hours4_2.setText(a4.getHours().get(1).toString() + " hours");
                    tags4_2.setText(a4.getTags().get(1).toString());

                    title4_3.setText(a4.getTitle().get(2).toString());
                    address4_3.setText(a4.getAddress().get(2).toString());
                    rating4_3.setText("Rating: " + a4.getRating().get(2).toString());
                    price4_3.setText("Price: " + a4.getPrice().get(2).toString());
                    hours4_3.setText(a4.getHours().get(2).toString() + " hours");
                    tags4_3.setText(a4.getTags().get(2).toString());

                    title4_4.setText(a4.getTitle().get(3).toString());
                    address4_4.setText(a4.getAddress().get(3).toString());
                    rating4_4.setText("Rating: " + a4.getRating().get(3).toString());
                    price4_4.setText("Price: " + a4.getPrice().get(3).toString());
                    hours4_4.setText(a4.getHours().get(3).toString() + " hours");
                    tags4_4.setText(a4.getTags().get(3).toString());

                    title4_5.setText(a4.getTitle().get(4).toString());
                    address4_5.setText(a4.getAddress().get(4).toString());
                    rating4_5.setText("Rating: " + a4.getRating().get(4).toString());
                    price4_5.setText("Price: " + a4.getPrice().get(4).toString());
                    hours4_5.setText(a4.getHours().get(4).toString() + " hours");
                    tags4_5.setText(a4.getTags().get(4).toString());
                }

                if(msg.what == 1 && a5.getTitle().size() > 4){
                    title5_1.setText(a5.getTitle().get(0).toString());
                    address5_1.setText(a5.getAddress().get(0).toString());
                    rating5_1.setText("Rating: " + a5.getRating().get(0).toString());
                    price5_1.setText("Price: " + a5.getPrice().get(0).toString());
                    hours5_1.setText(a5.getHours().get(0).toString() + " hours");
                    tags5_1.setText(a5.getTags().get(0).toString());

                    title5_2.setText(a5.getTitle().get(1).toString());
                    address5_2.setText(a5.getAddress().get(1).toString());
                    rating5_2.setText("Rating: " + a5.getRating().get(1).toString());
                    price5_2.setText("Price: " + a5.getPrice().get(1).toString());
                    hours5_2.setText(a5.getHours().get(1).toString() + " hours");
                    tags5_2.setText(a5.getTags().get(1).toString());

                    title5_3.setText(a5.getTitle().get(2).toString());
                    address5_3.setText(a5.getAddress().get(2).toString());
                    rating5_3.setText("Rating: " + a5.getRating().get(2).toString());
                    price5_3.setText("Price: " + a5.getPrice().get(2).toString());
                    hours5_3.setText(a5.getHours().get(2).toString() + " hours");
                    tags5_3.setText(a5.getTags().get(2).toString());

                    title5_4.setText(a5.getTitle().get(3).toString());
                    address5_4.setText(a5.getAddress().get(3).toString());
                    rating5_4.setText("Rating: " + a5.getRating().get(3).toString());
                    price5_4.setText("Price: " + a5.getPrice().get(3).toString());
                    hours5_4.setText(a5.getHours().get(3).toString() + " hours");
                    tags5_4.setText(a5.getTags().get(3).toString());

                    title5_5.setText(a5.getTitle().get(4).toString());
                    address5_5.setText(a5.getAddress().get(4).toString());
                    rating5_5.setText("Rating: " + a5.getRating().get(4).toString());
                    price5_5.setText("Price: " + a5.getPrice().get(4).toString());
                    hours5_5.setText(a5.getHours().get(4).toString() + " hours");
                    tags5_5.setText(a5.getTags().get(4).toString());
                }
            }
        };
        final String[] tag_title = {"Museum", "Park", "Shopping",	"View",	"Cathedral", "Culture",
                "Garden",	"Zoo",	"Theatre",	"Bridge",	"Indoor",	"Show",
                "Workshop",	"Food",	"Sports",	"Landmark",	"History"};
        new Thread(new Runnable() {
            @Override
            public void run() {
                conn = Util.openConnection(URL, USER, PASSWORD);
                try {
                    String sql1 = "select * from apptest.attractions where attractions.group like '%1%'; ";
                    String sql2 = "select * from apptest.attractions where attractions.group like '%2%'; ";
                    String sql3 = "select * from apptest.attractions where attractions.group like '%3%'; ";
                    String sql4 = "select * from apptest.attractions where attractions.group like '%4%'; ";
                    String sql5 = "select * from apptest.attractions where attractions.group like '%5%'; ";
                    Statement s = conn.createStatement();
                    ResultSet rs = s.executeQuery(sql1);
                    Vector title = new Vector();
                    Vector rating = new Vector();
                    Vector price = new Vector();
                    Vector hours = new Vector();
                    Vector address = new Vector();
                    Vector tags = new Vector();
                    while (rs.next()) {
                        title.add(rs.getString("name"));
                        rating.add(rs.getString("ratings"));
                        price.add(rs.getString("price"));
                        hours.add(rs.getString("visiting hours"));
                        address.add(rs.getString("address"));
                        Vector tag = new Vector();
                        for (int i = 12; i < 29; i ++){
                            if (rs.getInt(i) == 1)
                                tag.add(tag_title[i-12]);
                        }
                        tags.add(tag.toString());
                    }
                    a1.setAddress(address);
                    a1.setHours(hours);
                    a1.setPrice(price);
                    a1.setTitle(title);
                    a1.setRating(rating);
                    a1.setTags(tags);

                    ResultSet rs2 = s.executeQuery(sql2);
                    Vector title2 = new Vector();
                    Vector rating2 = new Vector();
                    Vector price2 = new Vector();
                    Vector hours2 = new Vector();
                    Vector address2 = new Vector();
                    Vector tags2 = new Vector();
                    while (rs2.next()) {
                        title2.add(rs2.getString("name"));
                        rating2.add(rs2.getString("ratings"));
                        price2.add(rs2.getString("price"));
                        hours2.add(rs2.getString("visiting hours"));
                        address2.add(rs2.getString("address"));
                        Vector tag = new Vector();
                        for (int i = 12; i < 29; i ++){
                            if (rs2.getInt(i) == 1)
                                tag.add(tag_title[i-12]);
                        }
                        tags2.add(tag.toString());
                    }
                    a2.setAddress(address2);
                    a2.setHours(hours2);
                    a2.setPrice(price2);
                    a2.setTitle(title2);
                    a2.setRating(rating2);
                    a2.setTags(tags2);

                    ResultSet rs3 = s.executeQuery(sql3);
                    Vector title3 = new Vector();
                    Vector rating3 = new Vector();
                    Vector price3 = new Vector();
                    Vector hours3 = new Vector();
                    Vector address3 = new Vector();
                    Vector tags3 = new Vector();
                    while (rs3.next()) {
                        title3.add(rs3.getString("name"));
                        rating3.add(rs3.getString("ratings"));
                        price3.add(rs3.getString("price"));
                        hours3.add(rs3.getString("visiting hours"));
                        address3.add(rs3.getString("address"));
                        Vector tag = new Vector();
                        for (int i = 12; i < 29; i ++){
                            if (rs3.getInt(i) == 1)
                                tag.add(tag_title[i-12]);
                        }
                        tags3.add(tag.toString());
                    }
                    a3.setAddress(address3);
                    a3.setHours(hours3);
                    a3.setPrice(price3);
                    a3.setTitle(title3);
                    a3.setRating(rating3);
                    a3.setTags(tags3);

                    ResultSet rs4 = s.executeQuery(sql4);
                    Vector title4 = new Vector();
                    Vector rating4 = new Vector();
                    Vector price4 = new Vector();
                    Vector hours4 = new Vector();
                    Vector address4 = new Vector();
                    Vector tags4 = new Vector();
                    while (rs4.next()) {
                        title4.add(rs4.getString("name"));
                        rating4.add(rs4.getString("ratings"));
                        price4.add(rs4.getString("price"));
                        hours4.add(rs4.getString("visiting hours"));
                        address4.add(rs4.getString("address"));
                        Vector tag = new Vector();
                        for (int i = 12; i < 29; i ++){
                            if (rs4.getInt(i) == 1)
                                tag.add(tag_title[i-12]);
                        }
                        tags4.add(tag.toString());
                    }
                    a4.setAddress(address4);
                    a4.setHours(hours4);
                    a4.setPrice(price4);
                    a4.setTitle(title4);
                    a4.setRating(rating4);
                    a4.setTags(tags4);

                    ResultSet rs5 = s.executeQuery(sql5);
                    Vector title5 = new Vector();
                    Vector rating5 = new Vector();
                    Vector price5 = new Vector();
                    Vector hours5 = new Vector();
                    Vector address5 = new Vector();
                    Vector tags5 = new Vector();
                    while (rs5.next()) {
                        title5.add(rs5.getString("name"));
                        rating5.add(rs5.getString("ratings"));
                        price5.add(rs5.getString("price"));
                        hours5.add(rs5.getString("visiting hours"));
                        address5.add(rs5.getString("address"));
                        Vector tag = new Vector();
                        for (int i = 12; i < 29; i ++){
                            if (rs5.getInt(i) == 1)
                                tag.add(tag_title[i-12]);
                        }
                        tags5.add(tag.toString());
                    }
                    a5.setAddress(address5);
                    a5.setHours(hours5);
                    a5.setPrice(price5);
                    a5.setTitle(title5);
                    a5.setRating(rating5);
                    a5.setTags(tags5);

                    Message msg = Message.obtain();
                    msg.what = 1;
                    handler.sendMessage(msg);

                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }).start();


        PagerAdapter pagerAdapter = new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return viewList.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                // TODO Auto-generated method stub
                container.removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                container.addView(viewList.get(position));


                return viewList.get(position);
            }


        };


        PagerAdapter pagerAdapter2 = new PagerAdapter() {


            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return viewList2.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                // TODO Auto-generated method stub
                container.removeView(viewList2.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                container.addView(viewList2.get(position));


                return viewList2.get(position);
            }
        };

        PagerAdapter pagerAdapter3 = new PagerAdapter() {
            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return viewList3.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                // TODO Auto-generated method stub
                container.removeView(viewList3.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                container.addView(viewList3.get(position));


                return viewList3.get(position);
            }
        };

        PagerAdapter pagerAdapter4 = new PagerAdapter() {
            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return viewList4.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                // TODO Auto-generated method stub
                container.removeView(viewList4.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                container.addView(viewList4.get(position));


                return viewList4.get(position);
            }
        };

        PagerAdapter pagerAdapter5 = new PagerAdapter() {
            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return viewList5.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                // TODO Auto-generated method stub
                container.removeView(viewList5.get(position));
            }


            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                container.addView(viewList5.get(position));


                return viewList5.get(position);
            }


        };

// pager切换效果
        viewPager1.setOffscreenPageLimit(3);
        viewPager1.setAdapter(pagerAdapter);
        viewPager1.setPageTransformer(true, new GalleryTransformer());
        viewPager1.setPageMargin(10);

        viewPager2.setOffscreenPageLimit(3);
        viewPager2.setAdapter(pagerAdapter2);
        viewPager2.setPageTransformer(true, new GalleryTransformer());
        viewPager2.setPageMargin(10);

        viewPager3.setOffscreenPageLimit(3);
        viewPager3.setAdapter(pagerAdapter3);
        viewPager3.setPageTransformer(true, new GalleryTransformer());
        viewPager3.setPageMargin(10);

        viewPager4.setOffscreenPageLimit(3);
        viewPager4.setAdapter(pagerAdapter4);
        viewPager4.setPageTransformer(true, new GalleryTransformer());
        viewPager4.setPageMargin(10);

        viewPager5.setOffscreenPageLimit(3);
        viewPager5.setAdapter(pagerAdapter5);
        viewPager5.setPageTransformer(true, new GalleryTransformer());
        viewPager5.setPageMargin(10);

//获取天气
        new Thread() {
            @Override
            public void run() {
                try {
                    //String city = et1.getText().toString().trim();
                    String city = "DR5REGY9B42H";


                    String path = "https://api.seniverse.com/v3/weather/daily.json?key=SThTZxHldvHWebZH8&location=" + city +
                            "&language=en&unit=c&start=0&days=1";
                    URL url = new URL(path);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(5000);
                    conn.setRequestMethod("GET");
                    if (conn.getResponseCode() == 200) {
                        //请求成功
                        InputStream in = conn.getInputStream();
                        String str = "";
                        int len = 0;
                        byte[] buffer = new byte[1024];
                        while ((len = in.read(buffer)) > 0) {

                            str += new String(buffer, 0, len);
                        }
                        //解析json
                        arrlist = MyJsonParser.getWeather(str);
                        runOnUiThread(
                                new Runnable() {
                                    public void run() {
                                        tv1.setText(arrlist.get(0).getCity());
                                        lv1.setAdapter(new MyAdapter());

                                    }
                                }
                        );


                    } else {
                        showContent("请求失败");
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    showContent("服务器繁忙。。。");
                }
            }
        }.start();

// 跳转到confirm界面
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String T1 = tv1.getText().toString();
                String A1 = tv1a.getText().toString();

                String T2 = tv2.getText().toString();
                String A2 = tv2a.getText().toString();

                String T3 = tv3.getText().toString();
                String A3 = tv3a.getText().toString();

                String T4 = tv4.getText().toString();
                String A4 = tv4a.getText().toString();

 //               String T5 = tv5.getText().toString();
                String T5 = "Central Park";
                String A5 = tv5a.getText().toString();

//                //conn2 = Util.openConnection(URL, USER, PASSWORD);
//                String sql = "inset into apptest.trips values ('"+id+"', '"+date+"', '"+T1+"', '"+T2+"', " +
//                        "'"+T3+"', '"+T4+"', '"+T5+"');";
//                try {
//                    Statement statement = conn.createStatement();
//                    statement.execute(sql);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }

                Intent intent = new Intent();
                intent.putExtra("T1", T1);
                intent.putExtra("A1", A1);
                intent.putExtra("T2", T2);
                intent.putExtra("A2", A2);
                intent.putExtra("T3", T3);
                intent.putExtra("A3", A3);
                intent.putExtra("T4", T4);
                intent.putExtra("A4", A4);
                intent.putExtra("T5", T5);
                intent.putExtra("A5", A5);
                intent.putExtra("id", id);
                intent.putExtra("date", date);


                intent.setClass(Plan.this, Confirm.class);
                Plan.this.startActivity(intent);
            }
        });

// 选择事件
        check1_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String T1 = title1_1.getText().toString();
                    String A1 = address1_1.getText().toString();
                    tv1.setText(T1);
                    tv1a.setText(A1);
                    check1_2.setChecked(false);
                    check1_3.setChecked(false);
                    check1_4.setChecked(false);
                    check1_5.setChecked(false);

                } else {

                }
            }
        });


        check1_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String T1 = title1_2.getText().toString();
                    String A1 = address1_2.getText().toString();
                    tv1.setText(T1);
                    tv1a.setText(A1);
                    check1_1.setChecked(false);
                    check1_3.setChecked(false);
                    check1_4.setChecked(false);
                    check1_5.setChecked(false);
                } else {

                }
            }
        });

        check1_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String T1 = title1_3.getText().toString();
                    String A1 = address1_3.getText().toString();
                    tv1.setText(T1);
                    tv1a.setText(A1);
                    check1_1.setChecked(false);
                    check1_2.setChecked(false);
                    check1_4.setChecked(false);
                    check1_5.setChecked(false);
                } else {
                }
            }
        });

        check1_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String T1 = title1_4.getText().toString();
                    String A1 = address1_4.getText().toString();
                    tv1.setText(T1);
                    tv1a.setText(A1);
                    check1_1.setChecked(false);
                    check1_2.setChecked(false);
                    check1_3.setChecked(false);
                    check1_5.setChecked(false);
                } else {
                }
            }
        });

        check1_5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String T1 = title1_5.getText().toString();
                    String A1 = address1_5.getText().toString();
                    tv1.setText(T1);
                    tv1a.setText(A1);
                    check1_1.setChecked(false);
                    check1_2.setChecked(false);
                    check1_3.setChecked(false);
                    check1_4.setChecked(false);

                } else {
                }
            }
        });

        check2_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String T2 = title2_1.getText().toString();
                    String A2 = address2_1.getText().toString();
                    tv2.setText(T2);
                    tv2a.setText(A2);
                    check2_2.setChecked(false);
                    check2_3.setChecked(false);
                    check2_4.setChecked(false);
                    check2_5.setChecked(false);
                } else {
                }
            }
        });

        check2_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String T2 = title2_2.getText().toString();
                    String A2 = address2_2.getText().toString();
                    tv2.setText(T2);
                    tv2a.setText(A2);
                    check2_1.setChecked(false);
                    check2_3.setChecked(false);
                    check2_4.setChecked(false);
                    check2_5.setChecked(false);
                } else {
                }
            }
        });

        check2_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String T2 = title2_3.getText().toString();
                    String A2 = address2_3.getText().toString();
                    tv2.setText(T2);
                    tv2a.setText(A2);
                    check2_1.setChecked(false);
                    check2_4.setChecked(false);
                    check2_2.setChecked(false);
                    check2_5.setChecked(false);
                } else {
                }
            }
        });

        check2_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String T2 = title2_4.getText().toString();
                    String A2 = address2_4.getText().toString();
                    tv2.setText(T2);
                    tv2a.setText(A2);
                    check2_1.setChecked(false);
                    check2_2.setChecked(false);
                    check2_3.setChecked(false);
                    check2_5.setChecked(false);
                } else {
                }
            }
        });

        check2_5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String T2 = title2_5.getText().toString();
                    String A2 = address2_5.getText().toString();
                    tv2.setText(T2);
                    tv2a.setText(A2);
                    check2_1.setChecked(false);
                    check2_2.setChecked(false);
                    check2_3.setChecked(false);
                    check2_4.setChecked(false);
                } else {
                }
            }
        });

        check3_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String T3 = title3_1.getText().toString();
                    String A3 = address3_1.getText().toString();
                    tv3.setText(T3);
                    tv3a.setText(A3);
                    check3_2.setChecked(false);
                    check3_3.setChecked(false);
                    check3_4.setChecked(false);
                    check3_5.setChecked(false);
                } else {
                }
            }
        });


        check3_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String T3 = title3_2.getText().toString();
                    String A3 = address3_2.getText().toString();
                    tv3.setText(T3);
                    tv3a.setText(A3);
                    check3_1.setChecked(false);
                    check3_3.setChecked(false);
                    check3_4.setChecked(false);
                    check3_5.setChecked(false);
                } else {
                }
            }
        });

        check3_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String T3 = title3_3.getText().toString();
                    String A3 = address3_3.getText().toString();
                    tv3.setText(T3);
                    tv3a.setText(A3);
                    check3_1.setChecked(false);
                    check3_2.setChecked(false);
                    check3_4.setChecked(false);
                    check3_5.setChecked(false);
                } else {
                }
            }
        });

        check3_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String T3 = title3_4.getText().toString();
                    String A3 = address3_4.getText().toString();
                    tv3.setText(T3);
                    tv3a.setText(A3);
                    check3_1.setChecked(false);
                    check3_2.setChecked(false);
                    check3_3.setChecked(false);
                    check3_5.setChecked(false);
                } else {
                }
            }
        });

        check3_5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String T3 = title3_5.getText().toString();
                    String A3 = address3_5.getText().toString();
                    tv3.setText(T3);
                    tv3a.setText(A3);
                    check3_1.setChecked(false);
                    check3_2.setChecked(false);
                    check3_3.setChecked(false);
                    check3_4.setChecked(false);
                } else {
                }
            }
        });

        check4_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String T4 = title4_1.getText().toString();
                    String A4 = address4_1.getText().toString();
                    tv4.setText(T4);
                    tv4a.setText(A4);
                    check4_5.setChecked(false);
                    check4_2.setChecked(false);
                    check4_3.setChecked(false);
                    check4_4.setChecked(false);
                } else {
                }
            }
        });
        check4_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String T4 = title4_2.getText().toString();
                    String A4 = address4_2.getText().toString();
                    tv4.setText(T4);
                    tv4a.setText(A4);
                    check4_1.setChecked(false);
                    check4_3.setChecked(false);
                    check4_4.setChecked(false);
                    check4_5.setChecked(false);
                } else {
                }
            }
        });
        check4_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String T4 = title4_3.getText().toString();
                    String A4 = address4_3.getText().toString();
                    tv4.setText(T4);
                    tv4a.setText(A4);
                    check4_1.setChecked(false);
                    check4_2.setChecked(false);
                    check4_4.setChecked(false);
                    check4_5.setChecked(false);
                } else {
                }
            }
        });
        check4_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String T4 = title4_4.getText().toString();
                    String A4 = address4_4.getText().toString();
                    tv4.setText(T4);
                    tv4a.setText(A4);
                    check4_1.setChecked(false);
                    check4_2.setChecked(false);
                    check4_3.setChecked(false);
                    check4_5.setChecked(false);
                } else {
                }
            }
        });
        check4_5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String T4 = title4_5.getText().toString();
                    String A4 = address4_5.getText().toString();
                    tv4.setText(T4);
                    tv4a.setText(A4);
                    check4_1.setChecked(false);
                    check4_2.setChecked(false);
                    check4_3.setChecked(false);
                    check4_4.setChecked(false);
                } else {
                }
            }
        });

        check5_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String T5 = title5_1.getText().toString();
                    String A5 = address5_1.getText().toString();
                    tv5.setText(T5);
                    tv5a.setText(A5);
                    check5_5.setChecked(false);
                    check5_2.setChecked(false);
                    check5_3.setChecked(false);
                    check5_4.setChecked(false);
                } else {
                }
            }
        });

        check5_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String T5 = title5_2.getText().toString();
                    String A5 = address5_2.getText().toString();
                    tv5.setText(T5);
                    tv5a.setText(A5);
                    check5_1.setChecked(false);
                    check5_3.setChecked(false);
                    check5_4.setChecked(false);
                    check5_5.setChecked(false);
                } else {
                }
            }
        });

        check5_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String T5 = title5_3.getText().toString();
                    String A5 = address5_3.getText().toString();
                    tv5.setText(T5);
                    tv5a.setText(A5);
                    check5_1.setChecked(false);
                    check5_2.setChecked(false);
                    check5_4.setChecked(false);
                    check5_5.setChecked(false);
                } else {
                }
            }
        });

        check5_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String T5 = title5_4.getText().toString();
                    String A5 = address5_4.getText().toString();
                    tv5.setText(T5);
                    tv5a.setText(A5);
                    check5_1.setChecked(false);
                    check5_2.setChecked(false);
                    check5_3.setChecked(false);
                    check5_5.setChecked(false);
                } else {
                }
            }
        });

        check5_5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String T5 = title5_5.getText().toString();
                    String A5 = address5_5.getText().toString();
                    tv5.setText(T5);
                    tv5a.setText(A5);
                    check5_1.setChecked(false);
                    check5_2.setChecked(false);
                    check5_3.setChecked(false);
                    check5_4.setChecked(false);
                } else {
                }
            }
        });
        

    }


    public void showContent(final String str) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
            }
        });
    }


    class MyAdapter extends BaseAdapter implements ListAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return arrlist.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View vertview, ViewGroup parent) {
            // TODO Auto-generated method stub
            View view;
            if (vertview == null) {
                //使用打气筒找到item
                view = View.inflate(getApplicationContext(), R.layout.content_weather, null);

            } else {
                view = vertview;
            }
            // 找到相关的控件
            // TextView date = (TextView) view.findViewById(R.id.date);
            TextView text_day = (TextView) view.findViewById(R.id.text_day);
            //TextView text_night = (TextView) view.findViewById(R.id.text_night);
            TextView low = (TextView) view.findViewById(R.id.low);
            TextView high = (TextView) view.findViewById(R.id.high);

            //设置相关的值
            weather wea = arrlist.get(position);
            // date.setText(wea.getDate());
            text_day.setText(" " + wea.getText_day());
            //text_night.setText("晚上："+wea.getcode_day());
            low.setText("" + wea.getLow());
            high.setText("-" + wea.getHigh() + "°C");

            return view;
        }


    }



}

