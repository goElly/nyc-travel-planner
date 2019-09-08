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

public class DiscContent {
    public String content;
    public String nickname;
    public Long date;
    public String messageKey;

    DiscContent(String content, String nickname, Long date, String messageKey){
        this.content = content;
        this.nickname = nickname;
        this.date = date;
        this.messageKey = messageKey;
    }


}
