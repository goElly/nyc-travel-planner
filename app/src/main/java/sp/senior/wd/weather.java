package sp.senior.wd;

public class weather {
    private String city; //城市
    private String date; //日期
    private String text_day; //白天天气描述
    private String code_day; //夜晚天气描述
    private String high; // 最高温度
    private String low; //最低温度
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getText_day() {
        return text_day;
    }
    public void setText_day(String text_day) {
        this.text_day = text_day;
    }
    public String getcode_day() {
        return code_day;
    }
    public void setcode_day(String text_night) {
        this.code_day = code_day;
    }
    public String getHigh() {
        return high;
    }
    public void setHigh(String high) {
        this.high = high;
    }
    public String getLow() {
        return low;
    }
    public void setLow(String low) {
        this.low = low;
    }

    @Override
    public String toString() {
        return "Weather [city=" + city + ", date=" + date + ", text_day="
                + text_day + ", code_day=" + code_day + ", high=" + high
                + ", low=" + low + "]";
    }



}
