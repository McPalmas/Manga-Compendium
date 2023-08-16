package com.example.mainactivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mainactivity.main_fragments.MainActivity;


public class AboutUs extends Fragment {

    View view, back;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_about_us, container, false);

        MainActivity.bottomNavigationView.setVisibility(View.GONE);

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        back = view.findViewById(R.id.backAboutUs);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AppCompatActivity)getActivity()).getSupportActionBar().show();
                MainActivity.bottomNavigationView.setVisibility(View.VISIBLE);
                getActivity().onBackPressed();
            }
        });

        return view;
    }
}