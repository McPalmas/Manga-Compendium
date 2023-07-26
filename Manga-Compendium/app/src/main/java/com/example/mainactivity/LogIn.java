package com.example.mainactivity;

import com.example.mainactivity.db.DbManager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mainactivity.main_fragments.MainActivity;

public class LogIn extends AppCompatActivity {

    EditText username, password;
    TextView registration;
    Button button;

    public static final String USER_EXTRA = "com.example.esercitazione.User";

    public static SharedPreferences sharedPref;
    public DbManager db = DbManager.getInstance();



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        int userId = sharedPref.getInt("user", -1);

        if (userId != -1) {
            Intent home;
            home = new Intent(LogIn.this, MainActivity.class);
            startActivity(home);
        }


        db.createDB(this);

        username = findViewById(R.id.usernameLogin);
        password = findViewById(R.id.passwordLogin);
        registration = findViewById(R.id.loginWritten2);
        button = findViewById(R.id.buttonLogin);


        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username.setBackgroundResource(R.drawable.borderfield_grey);
            }
        });

        /*username.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER))
                    controll();
                return false;
            }
        });*/

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password.setBackgroundResource(R.drawable.borderfield_grey);
            }
        });

        password.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER))
                    controll();
                return false;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controll();
            }
        });


        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registration = new Intent(LogIn.this, Registration.class);
                startActivity(registration);
            }
        });


    }


    public void controll(){
        if (inputValid()) {
            Intent home;
            home = new Intent(LogIn.this, MainActivity.class);

            Integer id = db.userLogin(username.getText().toString(), password.getText().toString());

            if (id!=null) {
                SharedPreferences.Editor edt = sharedPref.edit();
                edt.putInt("user", id);
                edt.apply();
                startActivity(home);
            }else{
                username.setBackgroundResource(R.drawable.bordererror);
                username.setError("Errore! I dati inseriti non sono corretti!");
                password.setBackgroundResource(R.drawable.bordererror);
                password.setError("Errore! I dati inseriti non sono corretti!");
            }
        }

    }


    private Boolean inputValid(){
        boolean valid = true;

        if (username.getText().toString().equals("")) {
            this.username.setBackgroundResource(R.drawable.bordererror);
            this.username.setError("Errore! Inserisci l'username!");
            valid = false;
        }

        if (password.getText().toString().equals("")) {
            this.password.setBackgroundResource(R.drawable.bordererror);
            this.password.setError("Errore! Inserisci la password!");
            valid = false;
        }

        return valid;
    }


}