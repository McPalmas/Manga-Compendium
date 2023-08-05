package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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

import java.util.ArrayList;

public class ThreadFragment extends Fragment {

    View view;
    Integer idThread;
    TextView title;
    DbManager db = DbManager.getInstance();
    Thread thread;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_thread, container, false);

        savedInstanceState = this.getArguments();
        if (savedInstanceState != null) {
            idThread = savedInstanceState.getInt("id", -1);
        }

        title = (TextView) view.findViewById(R.id.titleThread);

        thread= db.getThreadById(idThread);

        title.setText(thread.getTitle());

        return view;
    }





}
