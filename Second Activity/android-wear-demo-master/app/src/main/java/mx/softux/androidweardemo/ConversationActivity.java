package mx.softux.androidweardemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by juan on 7/7/15.
 */
public class ConversationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            CharSequence messageText = getMessageText(getIntent());
            ConversationFragment conversationFragment = ConversationFragment.newInstance(messageText);
            getSupportFragmentManager().beginTransaction().add(R.id.conversation_fragment, conversationFragment).commit();

            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }
    }

    private CharSequence getMessageText(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            return remoteInput.getCharSequence(AndroidWearDemoGcmListenerService.EXTRA_VOICE_REPLY);
        }

        return null;
    }
}
