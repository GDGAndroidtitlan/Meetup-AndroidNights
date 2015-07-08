package com.androititlan.gdg.androiddesignlibrary.ui.fragment;

import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.androititlan.gdg.androiddesignlibrary.R;
import com.androititlan.gdg.androiddesignlibrary.model.Chat;
import com.androititlan.gdg.androiddesignlibrary.ui.adapter.ChatAdapter;
import com.androititlan.gdg.androiddesignlibrary.util.ConfigPreferences;
import com.androititlan.gdg.androiddesignlibrary.util.Configure;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jhordan on 06/07/15.
 */
public class ChatFragment extends FragmentBase {


    public static ChatFragment getInstance() {
        return new ChatFragment();
    }


    private Firebase mFirebaseRef;
    private List<Chat> chatList;
    private ChatAdapter chatAdapter;
    private String id_device;


    @Bind(R.id.edit_txt_message)
    EditText editTextMsg;
    @Bind(R.id.recycler_view_chats)
    RecyclerView recyclerViewChat;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Firebase.setAndroidContext(getActivity());
        mFirebaseRef = new Firebase(Configure.FIREBASE_URL).child(Configure.FIREBASE_CHILD);
        chatList = new ArrayList<>();
        id_device = Settings.Secure.getString(getActivity().getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chat, container, false);
        ButterKnife.bind(this, rootView);
        settingsToolbar(rootView,"Chat Droid");
        chatAdapter = new ChatAdapter(chatList, id_device);
        setUpRecyclerView(recyclerViewChat, chatAdapter);
        getChatMessages();

        return rootView;

    }


    private void setUpRecyclerView(RecyclerView recyclerView, ChatAdapter chatAdapter) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(chatAdapter);
    }

    private void getChatMessages() {
        //Firebase - Recebe mensagem
        mFirebaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot != null && dataSnapshot.getValue() != null) {

                    //Firebase - Converte a resposta em um objeto do tipo Chat
                    Chat model = dataSnapshot.getValue(Chat.class);
                    chatList.add(model);
                    recyclerViewChat.scrollToPosition(chatList.size() - 1);
                    chatAdapter.notifyItemInserted(chatList.size() - 1);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    @OnClick(R.id.btn_sent)
    void send() {

        String message = editTextMsg.getText().toString();
        if (!message.isEmpty()) {
            mFirebaseRef.push().setValue(new Chat(message, id_device, ConfigPreferences.getUrl(getActivity())));
        }
        editTextMsg.setText("");

    }


    @Override
    protected void settingsToolbar(View rootView, String title) {
        super.settingsToolbar(rootView,title);
    }
}
