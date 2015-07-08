package mx.softux.androidweardemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by juan on 7/7/15.
 */
public class ConversationFragment extends ListFragment implements LoaderManager.LoaderCallbacks<List<ConversationMessage>> {
    ConversationAdapter conversationAdapter;

    public static ConversationFragment newInstance(CharSequence messageText) {
        ConversationFragment fragment = new ConversationFragment();
        if (messageText != null) {
            Bundle arguments = new Bundle();
            arguments.putCharSequence(AndroidWearDemoGcmListenerService.EXTRA_VOICE_REPLY, messageText);
            fragment.setArguments(arguments);
        }
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        conversationAdapter = new ConversationAdapter(getActivity());
        setListAdapter(conversationAdapter);

        getLoaderManager().initLoader(0, null, this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conversation, null);

        final EditText senderEditText = (EditText) view.findViewById(R.id.sender_edit_text);
        final EditText messageEditText = (EditText) view.findViewById(R.id.message_edit_text);

        Button sendButton = (Button) view.findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AndroidWearDemoService service = Api.getService();
                service.postMessage(new ConversationMessage(senderEditText.getText().toString(), messageEditText.getText().toString()), new Callback<ConversationMessage>() {
                    @Override
                    public void success(ConversationMessage message, Response response) {
                        messageEditText.setText("");
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        if(savedInstanceState == null && getArguments() != null) {
            messageEditText.setText(getArguments().getCharSequence(AndroidWearDemoGcmListenerService.EXTRA_VOICE_REPLY));
            sendButton.callOnClick();
        }

        return view;
    }

    @Override
    public Loader<List<ConversationMessage>> onCreateLoader(int id, Bundle args) {
        return new ConversationLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<ConversationMessage>> loader, List<ConversationMessage> messages) {
        conversationAdapter.addMessages(messages);
    }

    @Override
    public void onLoaderReset(Loader<List<ConversationMessage>> loader) {
        conversationAdapter.removeMessages();
    }
}
