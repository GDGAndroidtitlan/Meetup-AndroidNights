package com.androititlan.gdg.androiddesignlibrary.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androititlan.gdg.androiddesignlibrary.R;
import com.androititlan.gdg.androiddesignlibrary.model.Chat;
import com.androititlan.gdg.androiddesignlibrary.util.Configure;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Jhordan on 05/07/15.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private List<Chat> chatList;
    private String mId;


    public ChatAdapter(List<Chat> chatList, String id) {
        this.chatList = chatList;
        mId = id;
    }

    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 1)
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_sent, parent, false);
        else
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_received, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        if (chatList.get(position).getId().equals(mId))
            return 1;

        return 2;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Chat chat = chatList.get(position);
        holder.txtMessage.setText(chat.getMessage());
        Glide.with(holder.circleImageView.getContext()).load(chat.getUrlImg()).fitCenter().into(holder.circleImageView);
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.txt_msg) TextView txtMessage;
        @Bind(R.id.circule_view_photo)CircleImageView circleImageView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
