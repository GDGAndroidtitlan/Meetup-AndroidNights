package com.androititlan.gdg.androiddesignlibrary.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androititlan.gdg.androiddesignlibrary.R;
import com.androititlan.gdg.androiddesignlibrary.ui.adapter.TweetAdapter;
import com.androititlan.gdg.androiddesignlibrary.util.ConfigPreferences;
import com.androititlan.gdg.androiddesignlibrary.util.Configure;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jhordan on 06/07/15.
 */
public class TweetFragment extends FragmentBase {


    public static TweetFragment getInstance() {
        return new TweetFragment();
    }

    @Bind(R.id.recyclerview_tweets) RecyclerView recyclerViewTweets;
    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;
    TweetAdapter tweetAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tweetAdapter = new TweetAdapter();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tweet, container, false);
        settingsToolbar(rootView,"");
        ButterKnife.bind(this, rootView);
        setUpRecyclerView(recyclerViewTweets);
        collapsingToolbarLayout.setTitle("Lista de Tweets");
        return rootView;

    }


    @Override
    public void onResume() {
        super.onResume();
        getTweets(ConfigPreferences.getPreferenceId(getActivity()));
    }

    /**
     *
     * @param recyclerView pasamos el recyclerview para asignarle su configuración
     */

    private void setUpRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(tweetAdapter);

    }


    /**
     *  hacemos la peticion de lista de tweets
     */

    private void getTweets(Long id) {

        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        StatusesService statusesService = twitterApiClient.getStatusesService();
        statusesService.userTimeline(id, null, null, null, null, null, null, null, null, new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result) {

                 updateUI(result.data);

            }


            public void failure(TwitterException exception) {
                //Do something on failure
                exception.printStackTrace();
            }
        });

    }

    /**
     *
     * @param listTweets le pasamos nuestra lista de tweets que obtenemos de la posicion y actualizamos la vista
     */
    private void updateUI(final List<Tweet> listTweets){

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tweetAdapter.setListItems(listTweets);
            }
        });

    }


    @Override
    protected void settingsToolbar(View rootView,String title) {
        super.settingsToolbar(rootView, title);
    }
}
