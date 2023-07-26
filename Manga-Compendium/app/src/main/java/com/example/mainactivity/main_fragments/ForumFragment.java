package com.example.mainactivity.main_fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mainactivity.CreateDiscussion;
import com.example.mainactivity.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ForumFragment extends Fragment {

    View view;
    FloatingActionButton create;

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("FORUM");
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_forum, container, false);

        create = (FloatingActionButton) view.findViewById(R.id.buttonNewDiscussion);


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new CreateDiscussion()).commit();

            }
        });

        return view;
    }
}