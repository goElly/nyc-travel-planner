package sp.senior.wd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class SQLTestActivity extends Activity {

    private static final String REMOTE_IP = "10.0.20.209";
    private static final String URL = "jdbc:mysql://" + REMOTE_IP + "/apptest";
    private static final String USER = "root";
    private static final String PASSWORD = "0213";
    private Connection conn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqltest);
    }

//    private Handler Handler = new Handler() {
//        public void handleMessage(android.os.Message msg) {
//            if (msg.what == 0 ) {
////                Toast.makeText(ViewTest.this, "提交成功", Toast.LENGTH_SHORT).show();
////                InitEye();
//            }
//        }
//    };

    public void onConn(View view) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Connection cn=null;
//                try {
//
//                    Class.forName("com.mysql.jdbc.Driver");
//                    cn = DriverManager.getConnection("jdbc:mysql://10.0.20.209:3306/apptest?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&useSSL=false&serverTimezone=GMT&allowPublicKeyRetrieval=true", "root", "0213");
//
//                    String sql= "select * from user";
//                    Statement st=(Statement)cn.createStatement();
//                    ResultSet rs=st.executeQuery(sql);
//                    while(rs.next()){
//                        String data=rs.getString("name");
//                        Log.i("RegisterActivity",data);
//                    }
//
//                } catch ( SQLException e) {
//
//                    e.printStackTrace();
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                conn = Util.openConnection(URL, USER, PASSWORD);
            try {
                String sql = "select * from user";
                Statement s = (Statement) conn.createStatement();
                ResultSet rs = s.executeQuery(sql);
                while (rs.next()) {
                    String data = rs.getString("name");
                    Log.i("RegisterActivity", data);
                }
            }catch(SQLException e){
                        e.printStackTrace();
             }
            }
        }).start();
    }

    public void onInsert(View view) {
        String sql = "insert into user values(0, 'han')";
        Util.execSQL(conn, sql);
    }

    public void onDelete(View view) {
        String sql = "delete from user where name='mark'";
        Util.execSQL(conn, sql);
    }

    public void onUpdate(View view) {
        String sql = "update user set name='lilei' where name='hanmeimei'";
        Util.execSQL(conn, sql);
    }

    public void onQuery(View view) {
        System.out.println("All users info:");
        Util.execSQL(conn, "select * from student");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                conn = null;
            } finally {
                conn = null;
            }
        }
    }
}
