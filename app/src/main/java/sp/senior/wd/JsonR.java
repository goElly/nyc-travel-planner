package sp.senior.wd;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonR {

    public JsonR (String str) throws JSONException {
        JSONObject json = new JSONObject(str);
        JSONArray jsonArray = json.getJSONArray("results");

    }

}
