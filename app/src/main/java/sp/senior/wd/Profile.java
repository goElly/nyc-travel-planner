package sp.senior.wd;


import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;

public class Profile extends AppCompatActivity {

    private TextView tag1,tag2,tag3,tag4,tag5,tag6,tag7,tag8,tag9,tag10,tag11,tag12,tag13,tag14,tag15,tag16,tag17;
    private TextView tv_13,tv_14;
    private DatabaseReference mRef, mSubRef;
    private DatabaseReference mDRef;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this) ;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mRef = FirebaseDatabase.getInstance().getReference("user/uid/id");

        tag1=(TextView)findViewById(R.id.tag1);
        tag2=(TextView)findViewById(R.id.tag2);
        tag3=(TextView)findViewById(R.id.tag3);
        tag4=(TextView)findViewById(R.id.tag4);
        tag5=(TextView)findViewById(R.id.tag5);
        tag6=(TextView)findViewById(R.id.tag6);
        tag7=(TextView)findViewById(R.id.tag7);
        tag8=(TextView)findViewById(R.id.tag8);
        tag9=(TextView)findViewById(R.id.tag9);
        tag10=(TextView)findViewById(R.id.tag10);
        tag11=(TextView)findViewById(R.id.tag11);
        tag12=(TextView)findViewById(R.id.tag12);
        tag13=(TextView)findViewById(R.id.tag13);
        tag14=(TextView)findViewById(R.id.tag17);
        tag15=(TextView)findViewById(R.id.tag15);
        tag16=(TextView)findViewById(R.id.tag16);
        tag17=(TextView)findViewById(R.id.tag17);
        tv_13=(TextView)findViewById(R.id.tv_13);
        tv_14=(TextView)findViewById(R.id.tv_14);


        tv_13.setText(getSharedPreferences("nameString", 0).getString("name", ""));
        tv_14.setText(getSharedPreferences("uidString", 0).getString("uid", ""));

    }

    public void setTag1 (View v){

        String t1= tag1.getText().toString();
        mDRef.setValue(t1);

    }
}
