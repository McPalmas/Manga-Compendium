package com.example.mainactivity;

import static android.app.Activity.RESULT_OK;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mainactivity.db.DbManager;
import com.example.mainactivity.main_fragments.ForumFragment;
import com.example.mainactivity.main_fragments.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CreateMessage extends Fragment {

    View view, back;
    TextView message;
    Button post;

    public DbManager db = DbManager.getInstance();

    Integer idThread;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_create_message, container, false);

        MainActivity.bottomNavigationView.setVisibility(View.GONE);

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        savedInstanceState = this.getArguments();
        if (savedInstanceState != null) {
            idThread = savedInstanceState.getInt("id", -1);
        }

        back = (View) view.findViewById(R.id.backCreateMessage);
        message = (TextView) view.findViewById(R.id.newMessageText);
        post =  (Button) view.findViewById(R.id.postMessage);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new ForumFragment()).commit();
                getActivity().onBackPressed();
            }
        });

        message.requestFocus();

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setBackgroundResource(R.drawable.borderlist_manga);
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controll();
            }
        });

        return view;
    }


    public void controll(){
        if(inputValid())
            if(checkFields()) {
                Date currentTime = Calendar.getInstance().getTime();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss", Locale.getDefault());
                String date = formatter.format(currentTime);
                Message msg = new Message(message.getText().toString(),LogIn.sharedPref.getInt("user",-1),idThread,date);
                db.createMessage(msg);
                ThreadFragment fragment = new ThreadFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("id", idThread);
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container,fragment).addToBackStack(null).commit();
            }
    }

    private Boolean inputValid(){
        if (message.getText().toString().equals("")) {
            message.setBackgroundResource(R.drawable.borderlist_red);
            message.setError("Errore! Inserisci un messaggio");
            return  false;
        }
        return true;
    }

    private Boolean checkFields(){
        if(message.getText().toString().length() > 300 ) {
            message.setBackgroundResource(R.drawable.borderlist_red);
            message.setError("Errore! Il messaggio deve contenere un massimo di 300 caratteri!");
            return false;
        }
        return  true;
    }




}