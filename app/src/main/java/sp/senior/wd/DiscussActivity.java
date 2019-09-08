package sp.senior.wd;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class DiscussActivity extends AppCompatActivity {


    TextView subject, nickname_curr, des_tv;
    ListView discList;
    EditText message;
    DatabaseReference mRef, mSubRef;
    private String key;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this) ;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discuss);
        updateList();


        subject = (TextView) findViewById(R.id.subject);
        nickname_curr = (TextView) findViewById(R.id.nickname_d);
        des_tv = (TextView) findViewById(R.id.des_tv);

        discList = (ListView) findViewById(R.id.disclist);
        updateList();

        int query = 0;


        message = (EditText) findViewById(R.id.description);

        //subject
        Bundle bundle = getIntent().getExtras();
        subject.setText(bundle.getString("subject", ""));
        des_tv.setText(bundle.getString("des", ""));


        //nickname
        nickname_curr.setText(getSharedPreferences("nameString", 0).getString("name", ""));


        String key = bundle.getString("discKey", "");
        mRef = FirebaseDatabase.getInstance().getReference("forum/disc").child(key);
        mSubRef = FirebaseDatabase.getInstance().getReference("forum/subject").child(key);

    }


    public void newMessage(View v) {
        String megText = message.getText().toString();

        if (!megText.equals("")) {


            DatabaseReference msgRef = mRef.push(); //  产生id
            HashMap msg = new HashMap();
            msg.put("content", megText);
            msg.put("nickname", nickname_curr.getText().toString());
            msg.put("timestamp", ServerValue.TIMESTAMP);
            msgRef.setValue(msg);
            message.setText("");
            mSubRef.child("lastUpdate").setValue(ServerValue.TIMESTAMP);
            mSubRef.child("lastUpdateUserNickname").setValue(nickname_curr.getText().toString());
            updateList();

        }
    }

    private void updateList() {
        final ArrayList<DiscContent> listForumDataAdpter = new ArrayList<DiscContent>();
        FirebaseDatabase.getInstance().getReference("forum/disc/1")
                // .equalTo(query)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                            String nickname = childDataSnapshot.child("nickname").getValue().toString();
                            String date = childDataSnapshot.child("timestamp").getValue().toString();
                            String content = childDataSnapshot.child("content").getValue().toString();
                            String messageKey = childDataSnapshot.getKey();
                            // String uid = childDataSnapshot.child("uid").getValue().toString();


                            DiscContent item = new DiscContent(content, nickname, Long.parseLong(date), messageKey);
                            listForumDataAdpter.add(item);
                        }
                        discList.setAdapter(new DiscussActivity.MyCustomAdapter(listForumDataAdpter));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }


    private class MyCustomAdapter extends BaseAdapter {

        public ArrayList<DiscContent> listForumDataAdpter;

        public MyCustomAdapter(ArrayList<DiscContent> listForumDataAdpter) {
            this.listForumDataAdpter = listForumDataAdpter;
        }

        @Override
        public int getCount() {
            return listForumDataAdpter.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View myView = layoutInflater.inflate(R.layout.disc_content, null);
            final DiscContent item = listForumDataAdpter.get(i);


            TextView lastUpdateUserNickname = (TextView) myView.findViewById(R.id.textView5);
            lastUpdateUserNickname.setText(item.nickname);

            TextView des = (TextView) myView.findViewById(R.id.disccontent);
            des.setText(item.content);


            TextView lastUpdate = (TextView) myView.findViewById(R.id.disctime);
            Date date = new Date(item.date);
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd HH:mm");
            String dateString = dateFormat.format(date);
            lastUpdate.setText(dateString);


            return myView;
        }
    }
}