package com.androititlan.gdg.androiddesignlibrary.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androititlan.gdg.androiddesignlibrary.R;
import com.androititlan.gdg.androiddesignlibrary.util.Configure;
import com.bumptech.glide.Glide;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Jhordan on 06/07/15.
 */
public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder>{


    List<Tweet> tweetList;
    public TweetAdapter(){
        tweetList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tweet,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Tweet tweet = getListItems().get(position);
        holder.txtUser.setText(tweet.user.name);
        holder.txtTweet.setText(tweet.text);
        holder.txtExtra.setText("Favorito" + " " +Integer.toString(tweet.favoriteCount) + " " + " retweets " +Integer.toString(tweet.retweetCount) );
        Glide.with(holder.circleImageViewUser.getContext()).load(tweet.user.profileImageUrl)
                .fitCenter().into(holder.circleImageViewUser);
    }

    // pequeño hack para obtener el tamaño de la foto original de perfil
    private String getUrlPhotoProfile(String cadena){
        return cadena.substring(0,cadena.indexOf(Configure.SUFIXE)) + Configure.JPEG_EXTENSION;
    }


    @Override
    public int getItemCount() {
        return tweetList.size();
    }

    public void setListItems(List<Tweet> listItems){
        if(listItems != null){
            clear();
            this.tweetList.addAll(listItems);
            notifyDataSetChanged();
        }
    }

    public List<Tweet> getListItems(){
        return this.tweetList;
    }

    public void clear(){
        if(this.tweetList != null){
            this.tweetList.clear();
            notifyDataSetChanged();
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.txt_user) TextView txtUser;
        @Bind(R.id.txt_tweet) TextView txtTweet;
        @Bind(R.id.txt_extra) TextView txtExtra;
        @Bind(R.id.circule_view_photo) CircleImageView circleImageViewUser;

        public ViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);

        }


    }

}
