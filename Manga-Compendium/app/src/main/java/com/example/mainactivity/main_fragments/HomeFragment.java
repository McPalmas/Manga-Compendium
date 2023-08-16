package com.example.mainactivity.main_fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.mainactivity.CustomAdapterMangasHome;
import com.example.mainactivity.Manga;
import com.example.mainactivity.R;
import com.example.mainactivity.db.DbManager;

import java.util.ArrayList;
import java.util.Comparator;

public class HomeFragment extends Fragment {


    View view;
    ScrollView scrollView;
    RecyclerView list;
    ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9 , img10;
    TextView text1, text2, text3, text4, text5, text6, text7, text8, text9 , text10;
    DbManager db = DbManager.getInstance();

    ArrayList<Manga> mangas ;
    FragmentActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle z) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        MainActivity.bottomNavigationView.setVisibility(View.VISIBLE);

        ((AppCompatActivity)getActivity()).getSupportActionBar().show();


        activity = getActivity();

        scrollView = (ScrollView) view.findViewById(R.id.homeScrollView);
        list = (RecyclerView) view.findViewById(R.id.listMangasHome);

        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {

            @Override
            public void onScrollChanged() {
                //list.scrollTo(scrollView.scrollTo(0, scrollView.getScrollY()));
            }
        });


        mangas = db.getAllMangas();
        mangas.sort(Comparator.comparing(Manga::getYear));

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listMangasHome);
        CustomAdapterMangasHome adapter = new CustomAdapterMangasHome(mangas,this.getContext(), getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(),2,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // Disallow the touch request for parent scroll on touch of child view
                view.getParent().requestDisallowInterceptTouchEvent(false);
                return true;
            }
        });


        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("MANGA COMPENDIUM");
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.top_menu, menu);
     }


}