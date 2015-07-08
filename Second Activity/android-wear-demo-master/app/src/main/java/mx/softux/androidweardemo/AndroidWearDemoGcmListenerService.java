package mx.softux.androidweardemo;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by juan on 7/7/15.
 */
public class AndroidWearDemoGcmListenerService extends GcmListenerService {
    private static final String TAG = AndroidWearDemoGcmListenerService.class.getSimpleName();
    public static final String EXTRA_VOICE_REPLY = "extra_voice_reply";

    @Override
    public void onMessageReceived(String from, Bundle data) {
        String sender = data.getString("sender");
        String message = data.getString("message");
        Log.d(TAG, "From: " + from);
        Log.d(TAG, "Message: " + message);

        EventBus.getInstance().post(new ConversationMessage(sender, message));

        sendNotification(message);
    }

    private void sendNotification(String message) {
        int notificationId = 001;

        Intent viewIntent = new Intent(this, ConversationActivity.class);
        PendingIntent viewPendingIntent = PendingIntent.getActivity(this, 0, viewIntent, 0);

        NotificationCompat.BigTextStyle secondPageStyle = new NotificationCompat.BigTextStyle();
        secondPageStyle.setBigContentTitle(getString(R.string.notification_full_conversation_page_title)).bigText(Api.getService().getMessages().toString());

        Notification secondPageNotification = new NotificationCompat.Builder(this)
                .setStyle(secondPageStyle)
                .build();

        String replyLabel = getResources().getString(R.string.notification_reply_title);
        String[] replyChoices = getResources().getStringArray(R.array.notification_reply_choices);

        RemoteInput remoteInput = new RemoteInput.Builder(EXTRA_VOICE_REPLY)
                .setLabel(replyLabel)
                .setChoices(replyChoices)
                .build();

        NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.drawable.ic_replay_black_24dp, getString(R.string.notification_reply_title), viewPendingIntent)
                .addRemoteInput(remoteInput)
                .build();

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_chat_black_24dp)
                .setContentTitle("New message")
                .setContentText(message)
                .setContentIntent(viewPendingIntent)
                .extend(new WearableExtender()
                                .addPage(secondPageNotification)
                                .addAction(action)
                );

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);


        notificationManager.notify(notificationId, notificationBuilder.build());
    }
}
