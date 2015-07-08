package com.androititlan.gdg.androiddesignlibrary.api;

/**
 * Created by Jhordan on 01/07/15.
 */

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;

public class MyTwitterApiClient extends TwitterApiClient {

    public MyTwitterApiClient(TwitterSession session){
        super(session);
    }

    public TwitterService getTwitterService(){
        return getService(TwitterService.class);
    }
}
