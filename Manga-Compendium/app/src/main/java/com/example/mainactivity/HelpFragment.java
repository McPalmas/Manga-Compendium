package com.example.mainactivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mainactivity.main_fragments.MainActivity;

public class HelpFragment extends Fragment {

   View view,back;
   TextView menuText,addManga, forumText, deleteText, changeImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_help, container, false);

        MainActivity.bottomNavigationView.setVisibility(View.GONE);

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        back = view.findViewById(R.id.backHelp);
        menuText = (TextView) view.findViewById(R.id.navigateTitle);
        addManga = (TextView) view.findViewById(R.id.helpAddManga);
        forumText = (TextView) view.findViewById(R.id.helpForum);
        deleteText = (TextView) view.findViewById(R.id.helpDeleteManga);
        changeImage = (TextView) view.findViewById(R.id.helpChangeImage);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AppCompatActivity)getActivity()).getSupportActionBar().show();
                MainActivity.bottomNavigationView.setVisibility(View.VISIBLE);
                getActivity().onBackPressed();
            }
        });

        menuText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container,new HelpBottomMenu()).addToBackStack(null).commit();
            }
        });

        addManga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container,new HelpAddManga()).addToBackStack(null).commit();
            }
        });

        forumText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container,new HelpForum()).addToBackStack(null).commit();

            }
        });

        deleteText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container,new HelpDeleteManga()).addToBackStack(null).commit();

            }
        });

        changeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container,new HelpChangeImage()).addToBackStack(null).commit();

            }
        });

        return view;
    }
}