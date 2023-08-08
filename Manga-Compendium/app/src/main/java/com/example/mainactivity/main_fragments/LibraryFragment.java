package com.example.mainactivity.main_fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.mainactivity.CustomAdapterMangasLibrary;
import com.example.mainactivity.LogIn;
import com.example.mainactivity.MangaState;
import com.example.mainactivity.R;
import com.example.mainactivity.db.DbManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LibraryFragment extends Fragment {

    View view;
    String[] orderBy = {"Nome", "Stato", "Aggiunta"};

    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;

    RecyclerView list;

    ArrayList<MangaState> mangas = new ArrayList<>();

    CustomAdapterMangasLibrary adapter;
    static int state = 0; // 0 aggiunta 1 nome 2 stato

    DbManager db = DbManager.getInstance();

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("LIBRERIA");
        libraryMangas();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
        autoCompleteTextView = view.findViewById(R.id.autoCompleteTextView);
        adapterItems = new ArrayAdapter<String>(getContext(), R.layout.spinner_dropdown_style, orderBy);
        autoCompleteTextView.setAdapter(adapterItems);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_library, container, false);

        list = (RecyclerView) view.findViewById(R.id.listMangasLibrary);

        autoCompleteTextView = view.findViewById(R.id.autoCompleteTextView);
        adapterItems = new ArrayAdapter<String>(getContext(), R.layout.spinner_dropdown_style, orderBy);
        autoCompleteTextView.setAdapter(adapterItems);

        libraryMangas();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listMangasLibrary);
        adapter = new CustomAdapterMangasLibrary(mangas, this.getContext(), getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);


        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                if(i == 0){
                    state = 1;
                    mangas.sort(Comparator.comparing(MangaState::getTitle));
                    adapter.replaceAll(mangas);
                }else if(i == 1) {
                    state = 2;
                    mangas.sort(Comparator.comparing(MangaState::getState));
                    Collections.reverse(mangas);
                    adapter.replaceAll(mangas);
                }else{
                    state = 0;
                    libraryMangas();
                    adapter.replaceAll(mangas);
                }
            }
        });

        return view;
    }


    // restituisce tutti i manga legati ad un certo utente, li trova attaverso il loro id
    public void libraryMangas(){
        ArrayList<MangaState> list = db.getMangasStatesByUserId(LogIn.sharedPref.getInt("user",-1));
        mangas = list;
        if(state == 1)
            mangas.sort(Comparator.comparing(MangaState::getTitle));
        if(state == 2) {
            mangas.sort(Comparator.comparing(MangaState::getState));
            Collections.reverse(mangas);
        }
    }

}