package com.androititlan.gdg.androiddesignlibrary.api;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.models.User;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Jhordan on 01/07/15.
 */
public interface TwitterService {
    @GET("/1.1/users/show.json")
    void show(@Query("user_id") Long userId,
              @Query("screen_name") String screenName,
              @Query("include_entities") Boolean includeEntities,
              Callback<User> cb);

    //creamos un servicio extra para obtener el perfil de usuario

}
