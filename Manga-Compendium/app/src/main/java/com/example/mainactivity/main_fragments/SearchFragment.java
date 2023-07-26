package com.example.mainactivity.main_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.mainactivity.CustomAdapterMangasSearch;
import com.example.mainactivity.Manga;
import com.example.mainactivity.R;
import com.example.mainactivity.db.DbManager;

import java.util.ArrayList;


public class SearchFragment extends Fragment {

    View view;
    RecyclerView list;

    public DbManager db = DbManager.getInstance();

    public EditText searchBar;

    ArrayList<Manga> mangas ;

    ArrayList<Manga> filteredMangas ;

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("CERCA");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);

        searchBar = (EditText) view.findViewById(R.id.searchMangas);
        list = (RecyclerView) view.findViewById(R.id.listMangas);

        mangas = db.getAllMangas();
        filteredMangas = (ArrayList<Manga>) mangas.clone();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listMangas);
        CustomAdapterMangasSearch adapter = new CustomAdapterMangasSearch(filteredMangas,this.getContext(), getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(),2,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.addItemDecoration(new DividerItemDecoration(list.getContext(),DividerItemDecoration.VERTICAL));
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
                filteredMangas = (ArrayList<Manga>) mangas.clone();
                filteredMangas.removeIf(manga -> !manga.getTitle().toLowerCase().contains(text.toLowerCase()));
                //Collections.sort(filteredMangas, String.CASE_INSENSITIVE_ORDER);
                adapter.replaceAll(filteredMangas);
            }
        });


        return view;
    }
}