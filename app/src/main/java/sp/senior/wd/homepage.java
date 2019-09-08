package sp.senior.wd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class homepage extends AppCompatActivity {
    private String id;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        TextView text = findViewById(R.id.textView7);
        text.setText("Welcome, " + name + " :)");
        Log.e("id", id);

        Button btn_plan;
        btn_plan = (Button) findViewById(R.id.btn_plan);
        btn_plan.setOnClickListener(listener2);
    }
    Button.OnClickListener listener2;
    {
        listener2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homepage.this, Date_Budget.class);
                intent.putExtra("id", id);
                startActivity(intent);
     //           homepage.this.finish();

            }

            ;
        };
    }

    public void schedule (View view){
        Intent i = new Intent();
//        i.setClass(homepage.this, MySchedules.class);
        i.setClass(homepage.this, MyTrips.class);
        i.putExtra("id", id);
        startActivity(i);
    }

}
