package com.example.mainactivity.main_fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
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

        activity = getActivity();

        scrollView = (ScrollView) view.findViewById(R.id.homeScrollView);
        list = (RecyclerView) view.findViewById(R.id.listMangasHome);
        /*final Bundle bundle=new Bundle();
        view = inflater.inflate(R.layout.fragment_home, container, false);

        img1 = (ImageView) view.findViewById(R.id.imageView1);
        img2 = (ImageView) view.findViewById(R.id.imageView2);
        img3 = (ImageView) view.findViewById(R.id.imageView3);
        img4 = (ImageView) view.findViewById(R.id.imageView4);
        img5 = (ImageView) view.findViewById(R.id.imageView5);
        img6 = (ImageView) view.findViewById(R.id.imageView6);
        img7 = (ImageView) view.findViewById(R.id.imageView7);
        img8 = (ImageView) view.findViewById(R.id.imageView8);
        img9 = (ImageView) view.findViewById(R.id.imageView9);
        img10 = (ImageView) view.findViewById(R.id.imageView10);

        text1 = (TextView) view.findViewById(R.id.titleManga1);
        text2 = (TextView) view.findViewById(R.id.titleManga2);
        text3 = (TextView) view.findViewById(R.id.titleManga3);
        text4 = (TextView) view.findViewById(R.id.titleManga4);
        text5 = (TextView) view.findViewById(R.id.titleManga5);
        text6 = (TextView) view.findViewById(R.id.titleManga6);
        text7 = (TextView) view.findViewById(R.id.titleManga7);
        text8 = (TextView) view.findViewById(R.id.titleManga8);
        text9 = (TextView) view.findViewById(R.id.titleManga9);
        text10 = (TextView) view.findViewById(R.id.titleManga10);


        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer id = db.findMangaByTitle(text1.getText().toString());
                MangaFragment fragment = new MangaFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("id2", id);
                fragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,new MangaFragment()).commit();
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer id = db.findMangaByTitle(text2.getText().toString());
                MangaFragment fragment = new MangaFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new MangaFragment()).commit();
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer id = db.findMangaByTitle(text3.getText().toString());
                MangaFragment fragment = new MangaFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new MangaFragment()).commit();
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer id = db.findMangaByTitle(text4.getText().toString());
                MangaFragment fragment = new MangaFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new MangaFragment()).commit();
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer id = db.findMangaByTitle(text5.getText().toString());
                MangaFragment fragment = new MangaFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new MangaFragment()).commit();
            }
        });
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer id = db.findMangaByTitle(text6.getText().toString());
                MangaFragment fragment = new MangaFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new MangaFragment()).commit();
            }
        });
        img7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer id = db.findMangaByTitle(text7.getText().toString());
                MangaFragment fragment = new MangaFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new MangaFragment()).commit();
            }
        });
        img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer id = db.findMangaByTitle(text8.getText().toString());
                MangaFragment fragment = new MangaFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new MangaFragment()).commit();
            }
        });
        img9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer id = db.findMangaByTitle(text9.getText().toString());
                MangaFragment fragment = new MangaFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new MangaFragment()).commit();
            }
        });
        img10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer id = db.findMangaByTitle(text10.getText().toString());
                MangaFragment fragment = new MangaFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new MangaFragment()).commit();
            }
        });*/

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


        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("MANGA COMPENDIUM");
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.top_menu, menu);
     }


}