package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mainactivity.db.DbManager;
import com.example.mainactivity.main_fragments.MainActivity;
import com.example.mainactivity.main_fragments.ProfileFragment;

public class ChangeEmail extends Fragment {

    View view;

    public EditText oldEmail, newEmail;
    public Button save;

    public DbManager db = DbManager.getInstance();

    User user;

    View back;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_change_email, container, false);

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        MainActivity.bottomNavigationView.setVisibility(View.GONE);

        user = db.getUserById(LogIn.sharedPref.getInt("user",-1));


        back = view.findViewById(R.id.backChangeEmail);
        oldEmail = view.findViewById(R.id.oldEmail);
        oldEmail.setText(user.getEmail());
        newEmail = view.findViewById(R.id.newEmail);
        save = view.findViewById(R.id.buttonSaveEmail);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new ProfileFragment()).commit();
            }
        });

        newEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newEmail.setBackgroundResource(R.drawable.borderfield_grey);
            }
        });

        newEmail.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    controll();
                    newEmail.setBackgroundResource(R.drawable.bordererror);
                }
                return false;
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controll();
            }
        });

        return view;
    }


    public void controll(){
        if (inputValid()){
            if(checkFields()){
                Toast.makeText(getContext(), "Email Aggiornata" , Toast.LENGTH_LONG).show();
                db.changeEmail(LogIn.sharedPref.getInt("user",-1), newEmail.getText().toString());
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new ProfileFragment()).commit();
            }
        }
    }

    private Boolean inputValid(){
        if (newEmail.getText().toString().equals("")) {
            this.newEmail.setBackgroundResource(R.drawable.bordererror);
            this.newEmail.setError("Errore! Inserisci l'email!");
            return false;
        }
        return true;
    }



    private Boolean checkFields(){
        Boolean value = true;
        if (!newEmail.getText().toString().matches(
                "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@"
                        + "[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")) {
            newEmail.setBackgroundResource(R.drawable.bordererror);
            newEmail.setError("Errore! Inserisci un' email valida!");
            value = false;
        }

        if(db.findUserByEmail(newEmail.getText().toString()) != null) {
            newEmail.setBackgroundResource(R.drawable.bordererror);
            newEmail.setError("Errore! Email già utilizzata!");
            value = false;
        }
            return value;
    }
}