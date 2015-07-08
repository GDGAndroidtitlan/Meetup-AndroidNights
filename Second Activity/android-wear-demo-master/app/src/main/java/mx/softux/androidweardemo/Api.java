package mx.softux.androidweardemo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by juan on 7/7/15.
 */
public class Api {
    private static AndroidWearDemoService service;

    public static AndroidWearDemoService getService() {
        if(service == null) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .create();

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://softux.ddns.net:1337")
                    .setConverter(new GsonConverter(gson))
                    .build();

            service = restAdapter.create(AndroidWearDemoService.class);
        }

        return service;
    }
}
