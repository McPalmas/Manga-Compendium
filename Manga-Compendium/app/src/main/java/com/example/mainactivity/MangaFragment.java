package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

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
import com.example.mainactivity.main_fragments.LibraryFragment;
import com.example.mainactivity.main_fragments.MainActivity;

import java.util.ArrayList;

public class MangaFragment extends Fragment implements
        AdapterView.OnItemSelectedListener {

    View view;
    TextView pageTitle,title, year, author, publisher, magazine, genre, volumes, chapters, plot, addManga;

    ImageView img;
    Spinner spinner; String[] stati = {"Non Letto", "In Corso", "Letto"};
    View back;
    DbManager db = DbManager.getInstance();

    Integer idManga;

    Manga manga;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_manga, container, false);

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        MainActivity.bottomNavigationView.setVisibility(View.GONE);

        savedInstanceState = this.getArguments();
        if (savedInstanceState != null) {
            idManga = savedInstanceState.getInt("id", -1);
        }

        manga = db.getMangaById(idManga);

        back = view.findViewById(R.id.backManga);

        pageTitle = view.findViewById(R.id.mangaPageTitle);
        pageTitle.setText(manga.getTitle());

        img = view.findViewById(R.id.imageViewManga);
        img.setImageURI(Uri.parse(manga.getImg()));

        title = view.findViewById(R.id.titleManga);
        title.setText(manga.getTitle());

        addManga = view.findViewById(R.id.buttonAddManga);

        spinner = (Spinner) view.findViewById(R.id.stateSpinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(getContext(),R.layout.spinner_style,stati);
        aa.setDropDownViewResource(R.layout.spinner_dropdown_style);
        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(aa);


        author = view.findViewById(R.id.author);
        author.setText(manga.getAuthor());

        year = view.findViewById(R.id.year);
        year.setText(manga.getYear().toString());

        publisher = view.findViewById(R.id.publisher);
        publisher.setText(manga.getPublisher());


        magazine = view.findViewById(R.id.magazine);
        magazine.setText(manga.getMagazine());


        genre = view.findViewById(R.id.genre);
        genre.setText(manga.getGenre());


        volumes = view.findViewById(R.id.volumes);
        volumes.setText(manga.getVolumes().toString());

        chapters = view.findViewById(R.id.chapters);
        chapters.setText(manga.getChapters().toString());


        plot = view.findViewById(R.id.plot);
        plot.setText(manga.getPlot());


        if(mangaIsAlreadyAdded()){
            addManga.setText("Rimuovi");
            addManga.setTextColor(getResources().getColor(R.color.primary));
            addManga.setBackgroundResource(R.drawable.bordererror);
            addManga.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getActivity().getDrawable(R.drawable.star_grey), null);
            spinner.setEnabled(true);
            setDefaultSpinnerSelection();
        }else{
            addManga.setText(R.string.aggiungi_manga);
            addManga.setTextColor(getResources().getColor(R.color.darkerGreen));
            addManga.setBackgroundResource(R.drawable.borderfield_green);
            addManga.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getActivity().getDrawable(R.drawable.star_empty), null);
            spinner.setEnabled(false);
        }



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AppCompatActivity)getActivity()).getSupportActionBar().show();
                MainActivity.bottomNavigationView.setVisibility(View.VISIBLE);
                if(LibraryFragment.interrupt) {
                    LibraryFragment.interrupt = false;
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new LibraryFragment()).commit();
                }else
                    getActivity().onBackPressed();
            }
        });

        addManga.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View view) {
                if(addManga.getText().equals("Aggiungi")) {
                    addManga.setText("Rimuovi");
                    addManga.setBackgroundResource(R.drawable.bordererror);
                    addManga.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getActivity().getDrawable(R.drawable.star_grey), null);
                    addManga.setTextColor(getResources().getColor(R.color.primary));
                    db.saveUserManga(LogIn.sharedPref.getInt("user",-1),idManga);
                    spinner.setEnabled(true);
                }else{
                    addManga.setText(R.string.aggiungi_manga);
                    addManga.setBackgroundResource(R.drawable.borderfield_green);
                    addManga.setTextColor(getResources().getColor(R.color.darkerGreen));
                    addManga.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getActivity().getDrawable(R.drawable.star_empty), null);
                    deleteUserManga();
                    spinner.setEnabled(false);
                }
            }
        });
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View arg1, int position, long id) {
        if(mangaIsAlreadyAdded())
            setState(position);
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public boolean mangaIsAlreadyAdded(){
        ArrayList<Integer> list = db.getMangasIdByUserId(LogIn.sharedPref.getInt("user",-1));

        if(list == null)
            return false;

        for (int i = 0; i < list.size() ; i++) {
            if(list.get(i).equals(idManga))
                return true;
        }
        return false;
    }

    public void setDefaultSpinnerSelection(){
        ArrayList<MangaState> list = db.getMangasStatesByUserId(LogIn.sharedPref.getInt("user",-1));

        for (int i = 0; i < list.size() ; i++) {
            if(list.get(i).getTitle().equals(manga.getTitle()))
                switch (list.get(i).getState()){
                    case 0 : spinner.setSelection(0);
                        break;
                    case 1 : spinner.setSelection(1);
                        break;
                    case 2 : spinner.setSelection(2);
                        break;
                }
        }
    }

    public void setState(Integer stato){
        db.updateState(LogIn.sharedPref.getInt("user",-1),manga.getTitle(),stato);
    }

    public void deleteUserManga(){
        db.deleteUserManga(LogIn.sharedPref.getInt("user",-1),manga.getTitle());
    }

}
