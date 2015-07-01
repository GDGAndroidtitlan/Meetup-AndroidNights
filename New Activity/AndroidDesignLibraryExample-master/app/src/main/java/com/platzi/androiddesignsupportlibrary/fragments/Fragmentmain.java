package com.platzi.androiddesignsupportlibrary.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.platzi.androiddesignsupportlibrary.R;

/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class Fragmentmain extends android.app.Fragment {


    public Fragmentmain() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_fragmentone, container, false);
    }


}
