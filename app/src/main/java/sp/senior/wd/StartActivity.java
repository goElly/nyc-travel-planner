package sp.senior.wd;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.multidex.MultiDex;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;

public class StartActivity extends AppCompatActivity {


    TextView subject, nickname, uid, tv, upload_date, timeFrom, timeTo;
    ListView discList;
    EditText title, description, email, location;
    Button btn_attr;

    private DatePicker datePicker;
    private TimePicker timePicker;

    // 声明数据
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minutes;
    private ImageView img;
    private Calendar calendar;


    DatabaseReference mRef;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this) ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        nickname = (TextView) findViewById(R.id.nickname_s);
        uid = (TextView) findViewById(R.id.uid_s);
        // discList = (ListView) findViewById(R.id.disclist);

        title = (EditText) findViewById(R.id.upload_title);
        description = (EditText) findViewById(R.id.description);
        email = (EditText) findViewById(R.id.email);
        location = (EditText) findViewById(R.id.location);


        upload_date = (TextView) findViewById(R.id.upload_date);
        timeFrom = (TextView) findViewById(R.id.timeFrom);
        timeTo = (TextView) findViewById(R.id.timeTo);


        datePicker = (DatePicker) findViewById(R.id.datePicker1);
        timePicker = (TimePicker) findViewById(R.id.timePicker1);


        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;// 注意这里获取到的月份比现实中少一
        day = calendar.get(Calendar.DAY_OF_MONTH);// 注意参数：获取的是当前月的具体哪一天
        hour = calendar.get(Calendar.HOUR_OF_DAY);// 注意参数：获取当天具体的hour
        minutes = calendar.get(Calendar.MINUTE);

        nickname.setText(getSharedPreferences("nameString", 0).getString("name", ""));
        uid.setText(getSharedPreferences("uidString", 0).getString("uid", ""));


        mRef = FirebaseDatabase.getInstance().getReference("forum/subject");
        img = (ImageView) findViewById(R.id.imageView7);
    }


    public void newMessage(View v) {
        String megText = title.getText().toString();

        if (!megText.equals("")) {

            DatabaseReference msgRef = mRef.push(); //  产生id
            HashMap msg = new HashMap();
            msg.put("subject", megText);
            msg.put("lastUpdateUserNickname", nickname.getText().toString());
            msg.put("des", description.getText().toString());
            msg.put("uid", uid.getText().toString());
            msg.put("date", upload_date.getText().toString());
            msg.put("timeF", timeFrom.getText().toString());
            msg.put("timeT", timeTo.getText().toString());
            msg.put("email", email.getText().toString());
            msg.put("location", location.getText().toString());
            msg.put("lastUpdate", ServerValue.TIMESTAMP);

            msgRef.setValue(msg);
            title.setText("");
        }

    }

    public void date(View v) {
        getDatePicker_Dialog().show();

    }

    public void time(View v) {
        getTimePicker_Dialog().show();
    }

    public void time2(View v) {
        getTimePicker_Dialog2().show();
    }


    private DatePickerDialog getDatePicker_Dialog() {
        DatePickerDialog dpd = new DatePickerDialog(StartActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        upload_date.setText(year + "/"
                                + (monthOfYear + 1) + "/" + dayOfMonth + "");
                    }
                }, year, month - 1, day);
        return dpd;
    }

    private TimePickerDialog getTimePicker_Dialog() {
        TimePickerDialog tpd = new TimePickerDialog(StartActivity.this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        timeFrom.setText("" + hourOfDay + ":" + minute + "");
                        timeFrom.setTextColor(Color.parseColor("#000000"));
                    }
                }, hour, minutes, true);
        return tpd;
    }

    private TimePickerDialog getTimePicker_Dialog2() {
        TimePickerDialog tpd = new TimePickerDialog(StartActivity.this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        timeTo.setText("" + hourOfDay + ":" + minute + "");
                        timeTo.setTextColor(Color.parseColor("#000000"));

                    }
                }, hour, minutes, true);
        return tpd;
    }

}


