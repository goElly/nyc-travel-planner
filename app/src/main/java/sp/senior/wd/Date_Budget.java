package sp.senior.wd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import sp.senior.wd.Entity.CalendarDay;

public class Date_Budget extends AppCompatActivity {

    private TextView mTextMessage;
    private SeekBar seekBar;
    private TextView textView;
    private Calendar calendar = Calendar.getInstance();
    private CalendarDay currentDate = new CalendarDay();
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date__budget);

        Intent i = getIntent();
        id = i.getStringExtra("id");

        seekBar = (SeekBar) findViewById(R.id.seekBar);

        textView = (TextView) findViewById(R.id.text_budget);

        SeekBarListener listener = new SeekBarListener();
        seekBar.setOnSeekBarChangeListener(listener);


        Button button4;
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(listener3);

        CalendarView calendar = findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            public void onSelectedDayChange( CalendarView view, int year, int month, int dayOfMonth) {
                //显示用户选择的日期
                currentDate.setDay(dayOfMonth);
                currentDate.setMonth(month+1);
                currentDate.setYear(year);
                Toast.makeText(Date_Budget.this, (month+1) + "/" + dayOfMonth + "/" + year,Toast.LENGTH_SHORT).show();
            }
        });


    }
    class SeekBarListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            textView.setText(" " + "$ " + progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }

    Button.OnClickListener listener3;

    {
        listener3 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Date_Budget.this, Plan.class);
                String budget=textView.getText().toString().trim();
                String date = currentDate.getYear() + "/" + currentDate.getMonth() + "/" +currentDate.getDay();
                intent.putExtra("data",budget);
                intent.putExtra("date", date);
                intent.putExtra("id", id);
                startActivity(intent);
                Date_Budget.this.finish();

            }

            ;
        };
    }
}
