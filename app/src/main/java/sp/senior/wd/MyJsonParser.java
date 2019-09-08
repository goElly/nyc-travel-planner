package sp.senior.wd;

import sp.senior.wd.weather;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyJsonParser {

    public static ArrayList<weather> getWeather(String str) throws Exception {
        ArrayList<weather> arrlist = new ArrayList<weather>();
        //获取json对象
        JSONObject json = new JSONObject(str);
        JSONArray results = json.getJSONArray("results");
        //获取城市
        JSONObject location = (JSONObject) results.get(0);

        String city = location.getJSONObject("location").getString("name");
        //获取daily数组
        JSONArray daily = location.getJSONArray("daily");
        for(int i=0;i<daily.length();i++) {
            //准备一个weather对象
            weather weather = new weather();
            //添加城市
            weather.setCity(city);
            JSONObject day = (JSONObject) daily.get(i);
            //获取日期
            String date = day.getString("date");
            weather.setDate(date);
            //获取text_day（白天天描述）
            String text_day = day.getString("text_day");
            weather.setText_day(text_day);
            //获取text_night（夜晚天描述）
            String code_day = day.getString("code_night");
            weather.setcode_day(code_day);
            //获取最高温度
            String high = day.getString("high");
            weather.setHigh(high);
            //获取最低温度
            String low = day.getString("low");
            weather.setLow(low);

            arrlist.add(weather);
        }
        return arrlist;
    }}
