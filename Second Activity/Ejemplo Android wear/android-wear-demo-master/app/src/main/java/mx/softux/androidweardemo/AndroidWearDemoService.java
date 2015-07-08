package mx.softux.androidweardemo;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by juan on 7/7/15.
 */
public interface AndroidWearDemoService {
    @GET("/message")
    ConversationMessageList getMessages();

    @POST("/message")
    void postMessage(@Body ConversationMessage message, Callback<ConversationMessage> conversationMessageCallback);

    @GET("/device/:id")
    Device getDevice(@Path("id") Integer id);

    @POST("/device")
    Device register(@Body Device device);
}
