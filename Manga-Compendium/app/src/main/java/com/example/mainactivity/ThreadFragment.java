package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.net.Uri;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mainactivity.db.DbManager;
import com.example.mainactivity.main_fragments.ForumFragment;

import java.util.ArrayList;

public class ThreadFragment extends Fragment {

    View view, back;
    ImageView image;
    Integer idThread;
    TextView pageTitle,title, description, creator;
    DbManager db = DbManager.getInstance();
    Thread thread;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_thread, container, false);

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        savedInstanceState = this.getArguments();
        if (savedInstanceState != null) {
            idThread = savedInstanceState.getInt("id", -1);
        }

        back = (View) view.findViewById(R.id.backThread);
        image = (ImageView) view.findViewById(R.id.imageViewThread);
        pageTitle = (TextView) view.findViewById(R.id.threadPageTitle);
        title = (TextView) view.findViewById(R.id.titleThread);
        description = (TextView) view.findViewById(R.id.descriptionThread);
        creator = (TextView) view.findViewById(R.id.creatorThread);

        thread= db.getThreadById(idThread);

        pageTitle.setText(thread.getTitle());
        title.setText(thread.getTitle());
        description.setText(thread.getDescription());
        User user = db.getUserById(thread.getId_creator());
        creator.setText("Creatore: "+user.getUsername());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AppCompatActivity)getActivity()).getSupportActionBar().show();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new ForumFragment()).commit();            }
        });


        /*RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listForums);
        adapter = new CustomAdapterThreads(threads, this.getContext(), getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);*/

        return view;
    }





}
