package com.example.mainactivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class HelpDeleteManga extends Fragment {

    View view, back;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_help_delete_manga, container, false);

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        back = view.findViewById(R.id.backHelpDeleteManga);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HelpFragment.active = true;
                getActivity().onBackPressed();
            }
        });

        return view;
    }
}