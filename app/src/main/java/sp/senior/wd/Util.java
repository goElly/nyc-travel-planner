package sp.senior.wd;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {

    public static Connection openConnection(String url, String user,
                                            String password) {
        Connection conn = null;
        try {
            final String DRIVER_NAME = "com.mysql.jdbc.Driver";
            Class.forName(DRIVER_NAME);
            conn = DriverManager.getConnection(url + "?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&&failOverReadOnly=false&useSSL=false&serverTimezone=GMT&allowPublicKeyRetrieval=true", user, password);
            System.out.print("success");
        } catch (ClassNotFoundException e) {
            conn = null;
            e.printStackTrace();
        } catch (SQLException e) {
            conn = null;
            e.printStackTrace();
        }

        return conn;
    }

    public static void query(Connection conn, String sql) {

        if (conn == null) {
            return;
        }

        Statement statement = null;
        ResultSet result = null;

        try {
            statement = conn.createStatement();
            result = statement.executeQuery(sql);
            while (result.next()) {
                System.out.print(result.getString("name"));
//            if (result != null && result.first()) {
//                int idColumnIndex = result.findColumn("id");
//                int nameColumnIndex = result.findColumn("username");
//                System.out.println("id\t\t" + "name");
//                while (!result.isAfterLast()) {
//                    System.out.print(result.getString(idColumnIndex) + "\t\t");
//                    System.out.println(result.getString(nameColumnIndex));
//                    result.next();
//                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                    result = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }

            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
    }

    public static boolean execSQL(Connection conn, String sql) {
        boolean execResult = false;
        if (conn == null) {
            return execResult;
        }

        Statement statement = null;

        try {
            statement = conn.createStatement();
            if (statement != null) {
                execResult = statement.execute(sql);
            }
        } catch (SQLException e) {
            execResult = false;
        }

        return execResult;
    }
}
