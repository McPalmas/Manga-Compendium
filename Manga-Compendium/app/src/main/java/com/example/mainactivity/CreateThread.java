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

public class CreateThread extends Fragment {

    View view;
    View back;
    ImageView image;
    Uri selectedImage;
    TextView title, description;
    public Button save;

    public DbManager db = DbManager.getInstance();
    User user;
    Thread thread;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_create_thread, container, false);

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        user = db.getUserById(LogIn.sharedPref.getInt("user",-1));


        back = view.findViewById(R.id.backcreateDiscussion);
        save = view.findViewById(R.id.buttonCreateDiscussion);
        title = view.findViewById(R.id.titleCreateDiscussion);
        description = view.findViewById(R.id.descriptionCreateDiscussion);
        image = view.findViewById(R.id.imageChangeDiscussion);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new ForumFragment()).commit();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controll();
            }
        });

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title.setBackgroundResource(R.drawable.borderfield_grey);
            }
        });

        description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                description.setBackgroundResource(R.drawable.borderfield_grey);
            }
        });

        description.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    controll();
                }
                return false;
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                // pass the constant to compare it with the returned requestCode
                startActivityForResult(Intent.createChooser(i, "Select Picture"), 1);
            }
        });

        return view;
    }


    public void controll(){
        if(inputValid())
            if(checkFields()) {
                thread = new Thread("",title.getText().toString(),description.getText().toString(),LogIn.sharedPref.getInt("user",-1));
                Integer id = db.createThread(thread);
                ThreadFragment fragment = new ThreadFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                fragment.setArguments(bundle);
                Toast.makeText(getContext(), "Discussione creata" , Toast.LENGTH_LONG).show();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
            }
    }

    private Boolean inputValid(){
        boolean valid = true;
        if (title.getText().toString().equals("")) {
            title.setBackgroundResource(R.drawable.bordererror);
            title.setError("Errore! Inserisci un titolo!");
            valid = false;
        }

        if (description.getText().toString().equals("")) {
            description.setBackgroundResource(R.drawable.bordererror);
            description.setError("Errore! Inserisci una descrizione!");
            valid = false;
        }
        return valid;
    }

    private Boolean checkFields(){
        boolean valid = true;
        if(title.getText().toString().length() > 15 ) {
            title.setBackgroundResource(R.drawable.bordererror);
            title.setError("Errore! La descrizione deve contenere un massimo di 15 caratteri!");
            valid = false;
        }

        if(description.getText().toString().length() > 50 ) {
            description.setBackgroundResource(R.drawable.bordererror);
            description.setError("Errore! La descrizione deve contenere un massimo di 50 caratteri!");
            valid = false;
        }
        return valid;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == 1) {
                // Get the url of the image from data
                selectedImage = data.getData();
            }
        }
    }


}