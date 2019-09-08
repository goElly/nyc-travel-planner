package sp.senior.wd;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.multidex.MultiDex;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import sp.senior.wd.Activities;
import sp.senior.wd.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Explore extends AppCompatActivity {
    ListView fourmlsit;

    private View view_attraction, view_local;
    private ViewPager activityPager;  //对应的viewPager
    private List<View> viewList;//view数组
    private int query;

    TextView et1, et2, et3, et4, et5, ea1, ea2, ea3, ea4, ea5, midV,midA,cho;
    private String T1, T2, T3, T4, T5;
    private String eT1, eT2, eT3, eT4, eT5;

    private String A1, A2, A3, A4, A5,ch,dt,T,A;
    private String eA1, eA2, eA3, eA4, eA5;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this) ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourm_list_view);
        updateList();

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
        String dt = intent.getStringExtra("Date");

        fourmlsit = (ListView) findViewById(R.id.fourmList);
        updateList();
        int query = 0;

        et1 = (TextView) findViewById(R.id.et1);
        et2 = (TextView) findViewById(R.id.et2);
        et3 = (TextView) findViewById(R.id.et3);
        et4 = (TextView) findViewById(R.id.et4);
        et5 = (TextView) findViewById(R.id.et5);

        ea1 = (TextView) findViewById(R.id.ea1);
        ea2 = (TextView) findViewById(R.id.ea2);
        ea3 = (TextView) findViewById(R.id.ea3);
        ea4 = (TextView) findViewById(R.id.ea4);
        ea5 = (TextView) findViewById(R.id.ea5);

        midV = (TextView) findViewById(R.id.midV);
        midA = (TextView) findViewById(R.id.midA);


        cho = (TextView) findViewById(R.id.choice);

        String ch = cho.getText().toString();



    }


    private void updateList() {
        final ArrayList<FourmAdapterItem> listForumDataAdpter = new ArrayList<FourmAdapterItem>();
        FirebaseDatabase.getInstance().getReference("forum/subject")
                .orderByChild("attr")
                // .equalTo(query)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {

                            String subject = childDataSnapshot.child("subject").getValue().toString();
                            String nickname = childDataSnapshot.child("lastUpdateUserNickname").getValue().toString();
                            String lastUpdate = childDataSnapshot.child("lastUpdate").getValue().toString();
                            String key = childDataSnapshot.getKey();
                            String des = childDataSnapshot.child("des").getValue().toString();
                            String date = childDataSnapshot.child("date").getValue().toString();
                            String time = childDataSnapshot.child("timeF").getValue().toString();
                            String location = childDataSnapshot.child("location").getValue().toString();
                            String uid = childDataSnapshot.child("uid").getValue().toString();


                            FourmAdapterItem item = new FourmAdapterItem(subject, Long.parseLong(lastUpdate), nickname, key, des, date, time, location, uid);
                            listForumDataAdpter.add(item);
                        }
                        fourmlsit.setAdapter(new MyCustomAdapter(listForumDataAdpter));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }


    private class MyCustomAdapter extends BaseAdapter {

        public ArrayList<FourmAdapterItem> listForumDataAdpter;

        public MyCustomAdapter(ArrayList<FourmAdapterItem> listForumDataAdpter) {
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
            View myView = layoutInflater.inflate(R.layout.fourm_listitem, null);
            final FourmAdapterItem item = listForumDataAdpter.get(i);

            TextView subject = (TextView) myView.findViewById(R.id.subject);
            subject.setText(item.subject);

            TextView lastUpdateUserNickname = (TextView) myView.findViewById(R.id.lastUpdateUserNickname);
            lastUpdateUserNickname.setText(item.lastUpdateUserNickname);

            TextView des = (TextView) myView.findViewById(R.id.description);
            des.setText(item.des);

            TextView date = (TextView) myView.findViewById(R.id.date_tv);
            date.setText(item.date);


            TextView time = (TextView) myView.findViewById(R.id.time_tv);
            time.setText(item.time);

            TextView location = (TextView) myView.findViewById(R.id.location_tv);
            location.setText(item.location);

            TextView uid = (TextView) myView.findViewById(R.id.uid_tv);
            uid.setText(item.uid);

            CheckBox checkBox = (CheckBox) myView.findViewById(R.id.checkBox);

            //TextView lastUpdate = (TextView)myView.findViewById(R.id.lastUpdate);
            // Date date = new Date(item.lastUpdateDate);
            //SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd HH:mm");
            //String dateString = dateFormat.format(date);
            //lastUpdate.setText(dateString);

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        showSingDialog();
                        midV.setText(item.subject);
                        midA.setText(item.location);

                    } else {

                    }
                }
            });

            myView.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              Intent intent = new Intent();
                                              intent.setClass(Explore.this, DiscussActivity.class);
                                              intent.putExtra("discKey", item.key);
                                              intent.putExtra("subject", item.subject);
                                              intent.putExtra("des", item.des);
                                              startActivity(intent);

                                          }
                                      }

            );


            return myView;
        }




        String T = midV.getText().toString();
        String A = midA.getText().toString();

        int choice;

        private void showSingDialog() {
            final String[] items = {"8:00AM - 10:30AM", "10:30AM - 1:00PM", "1:00PM - 3:30PM", "3:30PM - 6:00PM", "6:00PM - 8:30PM"};
            AlertDialog.Builder singleChoiceDialog = new AlertDialog.Builder(Explore.this);
            singleChoiceDialog.setTitle("Please choose a time slot");
            singleChoiceDialog.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    choice = which;
                }
            });
            singleChoiceDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String T = midV.getText().toString();
                    String A = midA.getText().toString();
                    switch (choice) {
                        case 0:
                            if (!T1.isEmpty()) {
                                showSingDialog2();
                            } else {
                                et1.setText(T);
                                ea1.setText(A);
                            }
                            break;
                        case 1:
                            if (!T2.isEmpty()) {
                                showSingDialog2();
                            } else {
                                eT2=T;
                                eA2=A;
                            }
                            break;
                        case 2:
                            if (!T3.isEmpty()) {
                                showSingDialog2();
                            } else {
                                eT3=T;
                                eA3=A;
                            }
                            break;
                        case 3:
                            if (!T4.isEmpty()) {
                                showSingDialog2();
                            } else {
                                eT4=T;
                                eA4=A;
                            }
                            break;
                        case 4:
                            if (!T5.isEmpty()) {
                                showSingDialog2();
                            } else {
                                eT5=T;
                                eA5=A;
                            }
                            break;
                    }
                }
            });
            singleChoiceDialog.show();
        }

        private void showSingDialog2() {
            final AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(Explore.this);
            alterDiaglog.setMessage("You already have an arrangement for this time slot. Do you want to replace it?");//提示消息
            alterDiaglog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (choice) {
                        case 0:
                            et1.setText(T);
                            ea1.setText(A);
                            break;
                        case 2:
                            eT2=T;
                            eA2=A;
                            break;
                        case 3:
                            eT3=T;
                            eA3=A;
                            break;
                        case 4:
                            eT4=T;
                            eA4=A;
                            break;
                        case 5:
                            eT5=T;
                            eA5=A;
                            break;

                    }
                }
            });
            //消极的选择
            alterDiaglog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    showSingDialog();
                }
            });
            alterDiaglog.show();
        }
    }

    public void Confirm2(View v) {

        String eT1=et1.getText().toString();
        String eT2=et2.getText().toString();
        String eT3=et3.getText().toString();
        String eT4=et4.getText().toString();
        String eT5=et5.getText().toString();


        Intent intent2 = new Intent();

        intent2.putExtra("eT1", eT1);
        intent2.putExtra("eA1", eA1);
        intent2.putExtra("eT2", eT2);
        intent2.putExtra("eA2", eA2);
        intent2.putExtra("eT3", eT3);
        intent2.putExtra("eA3", eA3);
        intent2.putExtra("eT4", eT4);
        intent2.putExtra("eA4", eA4);
        intent2.putExtra("eT5", eT5);
        intent2.putExtra("eA5", eA5);

        intent2.putExtra("T1", T1);
        intent2.putExtra("A1", A1);
        intent2.putExtra("T2", T2);
        intent2.putExtra("A2", A2);
        intent2.putExtra("T3", T3);
        intent2.putExtra("A3", A3);
        intent2.putExtra("T4", T4);
        intent2.putExtra("A4", A4);
        intent2.putExtra("T5", T5);
        intent2.putExtra("A5", A5);
        intent2.putExtra("date", dt);

        intent2.setClass(Explore.this, Confirm.class);//从哪里跳到哪里
        Explore.this.startActivity(intent2);
    }

}

