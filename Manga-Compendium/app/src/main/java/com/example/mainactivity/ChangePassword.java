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
import com.example.mainactivity.main_fragments.ProfileFragment;

public class ChangePassword extends Fragment {

    View view;
    public EditText oldPsw, newPsw, newPswRepeat;
    public Button save;

    public DbManager db = DbManager.getInstance();

    User user;

    View back;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_change_password, container, false);

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();


        user = db.getUserById(LogIn.sharedPref.getInt("user",-1));


        back = view.findViewById(R.id.backChangePassword);
        oldPsw = view.findViewById(R.id.oldPsw);
        newPsw = view.findViewById(R.id.newPsw);
        newPswRepeat = view.findViewById(R.id.repeatNewPsw);
        save = view.findViewById(R.id.buttonSavePassword);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new ProfileFragment()).commit();
            }
        });

        oldPsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oldPsw.setBackgroundResource(R.drawable.borderfield_grey);
            }
        });

        newPsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPsw.setBackgroundResource(R.drawable.borderfield_grey);
            }
        });

        newPswRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPswRepeat.setBackgroundResource(R.drawable.borderfield_grey);
            }
        });

        newPswRepeat.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    controll();
                    newPswRepeat.setBackgroundResource(R.drawable.bordererror);
                }
                return false;
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               controll();
                /*user.setPassword(newPsw.getText().toString());
                oldPsw.setText("");
                newPsw.setText("");
                newPswRepeat.setText("");
                oldPsw.setBackgroundResource(R.drawable.borderfield_grey);
                newPsw.setBackgroundResource(R.drawable.borderfield_grey);
                newPswRepeat.setBackgroundResource(R.drawable.borderfield_grey);*/
            }
        });

        return view;
    }



    public void controll(){
        if (inputValid()){
            if(checkFields()){
                Toast.makeText(getContext(), "Password Aggiornata" , Toast.LENGTH_LONG).show();
                db.changePassword(LogIn.sharedPref.getInt("user",-1), newPsw.getText().toString());
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new ProfileFragment()).commit();
            }
        }
    }

    private Boolean inputValid(){
        boolean valid = true;

        if (oldPsw.getText().toString().equals("")) {
            this.oldPsw.setBackgroundResource(R.drawable.bordererror);
            this.oldPsw.setError("Errore! Inserisci la vecchia password!");
            valid = false;
        }

        if (newPsw.getText().toString().equals("")) {
            this.newPsw.setBackgroundResource(R.drawable.bordererror);
            this.newPsw.setError("Errore! Inserisci la nuova password!");
            valid = false;
        }

        if (newPswRepeat.getText().toString().equals("")) {
            this.newPswRepeat.setBackgroundResource(R.drawable.bordererror);
            this.newPswRepeat.setError("Errore! Le password non combaciano!");
            valid = false;
        }

        return valid;
    }



    private Boolean checkFields(){
        boolean valid = true;

        if(!oldPsw.getText().toString().equals(user.getPassword())){
            oldPsw.setBackgroundResource(R.drawable.bordererror);
            oldPsw.setError("Errore! La password corrente è errata");
            valid = false;
        }

        if(!newPsw.getText().toString().matches("^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,}$")) {
            newPsw.setBackgroundResource(R.drawable.bordererror);
            newPsw.setError("Errore! Password deve contenere ALMENO 8 caratteri, una lettera minuscola, una maiuscola, un numero ed un carattere speciale.");
            valid = false;
        }

        if(!newPsw.getText().toString().equals(newPswRepeat.getText().toString())){
            newPswRepeat.setBackgroundResource(R.drawable.bordererror);
            newPswRepeat.setError("Errore! Le due password non combaciano!");
            valid = false;
        }

        if(newPsw.getText().toString().equals(oldPsw.getText().toString())){
            newPsw.setBackgroundResource(R.drawable.bordererror);
            newPsw.setError("Errore! La nuova password password deve essere diversa dalla password corrente");
            valid = false;
        }

        return valid;
    }
}