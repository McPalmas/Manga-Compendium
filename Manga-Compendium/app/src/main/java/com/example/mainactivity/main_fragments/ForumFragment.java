package com.example.mainactivity.main_fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.mainactivity.CreateThread;
import com.example.mainactivity.CustomAdapterThreads;
import com.example.mainactivity.LogIn;
import com.example.mainactivity.Manga;
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

    EditText searchBar;
    CustomAdapterThreads adapter;

    DbManager db = DbManager.getInstance();

    ArrayList<Thread> threads = new ArrayList<>();
    ArrayList<Thread> filteredThreads ;

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

        searchBar = (EditText)  view.findViewById(R.id.searchThreads);
        create = (FloatingActionButton) view.findViewById(R.id.buttonNewDiscussion);


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new CreateThread()).commit();
            }
        });


        threads();
        filteredThreads = (ArrayList<Thread>) threads.clone();

        order();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listForums);
        adapter = new CustomAdapterThreads(filteredThreads, this.getContext(), getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);



        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text=editable.toString();
                filteredThreads = (ArrayList<Thread>) threads.clone();
                filteredThreads.removeIf(manga -> !manga.getTitle().toLowerCase().contains(text.toLowerCase()));
                adapter.replaceAll(filteredThreads);
            }
        });

        return view;
    }


    public void threads(){
       threads = db.getThreads();
    }

    private void order(){
        ArrayList<Integer> list = db.getUserThreadsByUserId(LogIn.sharedPref.getInt("user",-1));


        Integer id = 1;
        Collections.sort(filteredThreads, new Comparator<Thread>() {
            @Override
            public int compare(Thread t1, Thread t2) {
                if(list.contains(t1.getId()) && !list.contains(t2.getId()))
                    return -1;
                if (!list.contains(t1.getId()) && list.contains(t2.getId()))
                    return 1;
                if (t1.getTimeStamp().getTime() > t2.getTimeStamp().getTime())
                    return 1;
                else
                    return -1;
            }
        });
    }

}