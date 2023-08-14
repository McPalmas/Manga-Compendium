package com.example.mainactivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AboutUs extends Fragment {

    View view, back;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_about_us, container, false);

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        back = view.findViewById(R.id.backAboutUs);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AppCompatActivity)getActivity()).getSupportActionBar().show();
                getActivity().onBackPressed();
            }
        });

        return view;
    }
}