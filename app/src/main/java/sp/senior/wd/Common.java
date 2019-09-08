package sp.senior.wd;

import sp.senior.wd.Remote.IGoogleAPIService;
import sp.senior.wd.Remote.RetrofitClient;

public class Common {

    private static final String GOOGLE_API_URL ="https://maps.googleapis.com/";

    public static IGoogleAPIService getGoogleAPIService (){
        return RetrofitClient.getClient(GOOGLE_API_URL).create(IGoogleAPIService.class);
    }
}
