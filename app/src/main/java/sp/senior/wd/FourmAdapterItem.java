package sp.senior.wd;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FourmAdapterItem {
    public String subject, des;
    public Long lastUpdateDate;
    public String lastUpdateUserNickname;
    public String key;
    public String date;
    public String location;
    public String time;
    public String uid;



    FourmAdapterItem(String subject,
                     Long lastUpdateDate,
                     String lastUpdateUserNickname,
                     String key,
                     String des,
                     String date,
                     String time,
                     String location,
                     String uid) {
        this.subject = subject;
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdateUserNickname = lastUpdateUserNickname;
        this.key = key;
        this.des = des;
        this.date = date;
        this.location = location;
        this.time = time;
        this.uid = uid;



    }
}
