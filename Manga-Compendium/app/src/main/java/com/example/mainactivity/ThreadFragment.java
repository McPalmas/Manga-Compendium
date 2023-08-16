package com.example.mainactivity;

import static android.app.Activity.RESULT_OK;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;

import android.os.Bundle;
import android.provider.MediaStore;
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
import com.example.mainactivity.main_fragments.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ThreadFragment extends Fragment {

    View view, back;
    ImageView image;
    Integer idThread;
    TextView pageTitle,title, description, creator, button;

    Uri selectedImage;

    FloatingActionButton newMsg;

    DbManager db = DbManager.getInstance();
    Thread thread;// il thread da cui carico tutte le info

    ArrayList<Message> messages;

    CustomAdapterMessages adapter;

    RecyclerView recyclerView;

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_thread, container, false);

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        MainActivity.bottomNavigationView.setVisibility(View.GONE);

        savedInstanceState = this.getArguments();
        if (savedInstanceState != null) {
            idThread = savedInstanceState.getInt("id", -1);
        }

        back = (View) view.findViewById(R.id.backThread);
        image = (ImageView) view.findViewById(R.id.imageViewThread);
        pageTitle = (TextView) view.findViewById(R.id.threadPageTitle);
        title = (TextView) view.findViewById(R.id.titleThread);
        button = (TextView) view.findViewById(R.id.buttonAddThread);
        description = (TextView) view.findViewById(R.id.descriptionThread);
        creator = (TextView) view.findViewById(R.id.creatorThread);
        newMsg = (FloatingActionButton) view.findViewById(R.id.buttonNewMessage);

        thread= db.getThreadById(idThread);

        pageTitle.setText(thread.getTitle());
        title.setText(thread.getTitle());
        description.setText(thread.getDescription());
        User user = db.getUserById(thread.getId_creator());
        creator.setText("Creatore: "+user.getUsername());

        if(db.findUserThread(LogIn.sharedPref.getInt("user",-1),idThread)) {
            button.setText("Abbandona");
            button.setTextColor(getResources().getColor(R.color.primary));
            newMsg.setVisibility(View.VISIBLE);
        }else {
            button.setText("Partecipa");
            button.setTextColor(getResources().getColor(R.color.darkerGreen));
            newMsg.setVisibility(View.GONE);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new ForumFragment()).commit();            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(button.getText().equals("Partecipa")) {
                    db.saveUserThread(LogIn.sharedPref.getInt("user",-1),idThread);
                    button.setText("Abbandona");
                    button.setTextColor(getResources().getColor(R.color.primary));
                    newMsg.setVisibility(View.VISIBLE);
                }else {
                    db.deleteUserThread(LogIn.sharedPref.getInt("user",-1),idThread);
                    button.setText("Partecipa");
                    button.setTextColor(getResources().getColor(R.color.darkerGreen));
                    newMsg.setVisibility(View.GONE);
                }
            }
        });

        if(!thread.getImage().equals(""))
            image.setImageBitmap(FileHelper.loadBitmap(thread.getImage()));


        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LogIn.sharedPref.getInt("user",-1) == thread.getId_creator()) {
                    Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, 3);
                }
            }
        });

        newMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateMessage fragment = new CreateMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("id", idThread);
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container,fragment).addToBackStack(null).commit();
            }
        });

        messages = getThreadMessages();
        Collections.reverse(messages);

        recyclerView = (RecyclerView) view.findViewById(R.id.listThreads);
        adapter = new CustomAdapterMessages(messages, this.getContext(), getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }


    public ArrayList<Message> getThreadMessages(){return db.getThreadMessages(idThread);}


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            selectedImage = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                image.setImageBitmap(bitmap);
                FileHelper.saveThreadImgToInternalStorage(bitmap,db,getActivity(),idThread);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }


}
