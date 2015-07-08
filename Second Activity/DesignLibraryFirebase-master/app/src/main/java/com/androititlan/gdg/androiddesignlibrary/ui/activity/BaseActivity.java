package com.androititlan.gdg.androiddesignlibrary.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.androititlan.gdg.androiddesignlibrary.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jhordan on 05/07/15.
 */
public abstract class BaseActivity extends AppCompatActivity {


    @Bind(R.id.toolbar)Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        ButterKnife.bind(this);

        if (toolbar != null){
            setSupportActionBar(toolbar);
            ActionBar toolbarActionBar = getSupportActionBar();
            toolbarActionBar.setHomeButtonEnabled(getStatusEnabledBackButton());
            toolbarActionBar.setDisplayHomeAsUpEnabled(getStatusEnabledBackButton());
        }
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    protected  abstract int getLayoutResource();
    protected  abstract boolean getStatusEnabledBackButton();

}
