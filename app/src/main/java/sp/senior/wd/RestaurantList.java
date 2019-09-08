package sp.senior.wd;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;

public class RestaurantList extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        Thread thread=new Thread(new Runnable() {

            @Override

            public void run() {

                String html = null;
                try {

                    html = HtmlService.getHtml("https://maps.googleapis.com/maps/api/place/textsearch/json?query=restaurants+in+Sydney&key=AIzaSyARQYUYAC6YbyzHT6JV5Un1vrEoU2nIoh8");

                    Log.i("jsonData", html);


                } catch (Exception e) {

                    e.printStackTrace();

                }

                TextView tv1 = findViewById(R.id.textView2);
                tv1.setText(html);

                try {
                    com.example.myapplication.JsonR json = new com.example.myapplication.JsonR(html);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }



        });

        thread.start();
    }


}
