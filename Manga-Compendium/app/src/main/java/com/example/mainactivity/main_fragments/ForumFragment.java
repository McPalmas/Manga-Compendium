package com.example.mainactivity.main_fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mainactivity.CreateDiscussion;
import com.example.mainactivity.CustomAdapterMangasLibrary;
import com.example.mainactivity.CustomAdapterThreads;
import com.example.mainactivity.LogIn;
import com.example.mainactivity.MangaState;
import com.example.mainactivity.R;
import com.example.mainactivity.Thread;
import com.example.mainactivity.db.DbManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ForumFragment extends Fragment {

    View view;
    FloatingActionButton create;

    CustomAdapterThreads adapter;

    DbManager db = DbManager.getInstance();

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

        ArrayList<Thread> threads = threads();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listForums);
        adapter = new CustomAdapterThreads(threads, this.getContext(), getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);


        return view;
    }


    public ArrayList<Thread> threads(){
       return   db.getThreads();

    }

}