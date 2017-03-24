package com.dev4u.ntc.webservice.webservice;

/**
 * IDE: Android Studio
 * Created by Nguyen Trong Cong  - 2DEV4U.COM
 * Name packge: com.dev4u.ntc.webservice
 * Name project: WebService
 * Date: 3/24/2017
 * Time: 17:54
 */

public class ApiUtils {
    public static final String BASE_URL = "http://10.0.2.2";

    private ApiUtils() {
    }

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
