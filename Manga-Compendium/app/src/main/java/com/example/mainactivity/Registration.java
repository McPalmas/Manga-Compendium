package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mainactivity.db.DbManager;

public class Registration extends AppCompatActivity {

    EditText username, password, confirmPassword, email;

    View back;

    Button buttonReg;

    DbManager db = DbManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        back = findViewById(R.id.backReg);
        email = findViewById(R.id.emailReg);
        username = findViewById(R.id.usernameReg);
        password = findViewById(R.id.passwordReg);
        confirmPassword = findViewById(R.id.confirmPasswordReg);
        buttonReg = findViewById(R.id.buttonReg);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email.setBackgroundResource(R.drawable.borderfield_grey);
            }
        });

        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    checkEmailUse();
                    if( email.getText().toString().equals("")){
                        email.setBackgroundResource(R.drawable.bordererror);
                        email.setError("Errore! Inserisci un e-mail");
                    }else if(!checkEmailCorrectness()){
                        email.setBackgroundResource(R.drawable.bordererror);
                    }

                }
            }
        });

        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username.setBackgroundResource(R.drawable.borderfield_grey);
            }
        });

        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    if( username.getText().toString().equals("")) {
                        username.setBackgroundResource(R.drawable.bordererror);
                        username.setError("Errore! Inserisci l'username!");
                    }else
                        checkUsername();
                }
            }
        });

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password.setBackgroundResource(R.drawable.borderfield_grey);
            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    if( password.getText().toString().equals("")){
                        password.setBackgroundResource(R.drawable.bordererror);
                        password.setError("Errore! Inserisci una password");
                    }
                }
            }
        });

        confirmPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmPassword.setBackgroundResource(R.drawable.borderfield_grey);
            }
        });

        confirmPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    if( confirmPassword.getText().toString().equals("")){
                        confirmPassword.setBackgroundResource(R.drawable.bordererror);
                        confirmPassword.setError("Errore! Inserisci la conferma della password");
                    }
                }
            }
        });

        confirmPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    controll();
                    confirmPassword.setBackgroundResource(R.drawable.bordererror);
                }
                return false;
            }
        });


        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controll();
            }
        });

    }


    public  void controll(){
        if (inputValid()){
            if(checkFields()){
                db.saveUser(username.getText().toString(), email.getText().toString(), password.getText().toString(),"");
                finish();
            }
        }
    }

    private Boolean inputValid(){
        boolean valid = true;

        if (email.getText().toString().equals("")) {
            this.email.setBackgroundResource(R.drawable.bordererror);
            this.email.setError("Errore! Inserisci l'email!");
            valid = false;
        }

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

        if (confirmPassword.getText().toString().equals("")) {
            this.confirmPassword.setBackgroundResource(R.drawable.bordererror);
            this.confirmPassword.setError("Errore! Inserisci la conferma della password!");
            valid = false;
        }


        return valid;
    }


    private Boolean checkFields(){
        boolean valid = true;

        valid = checkUsername();

        valid=checkEmailCorrectness();

        valid=checkEmailUse();

        if(!password.getText().toString().matches("^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,}$")) {
            password.setBackgroundResource(R.drawable.bordererror);
            password.setError("Errore! Password deve contenere ALMENO 8 caratteri, una lettera minuscola, una maiuscola, un numero ed un carattere speciale.");
            valid = false;
        }

        if(!password.getText().toString().equals(confirmPassword.getText().toString())){
            confirmPassword.setBackgroundResource(R.drawable.bordererror);
            confirmPassword.setError("Errore! Le due password non combaciano!");
            valid = false;
        }


        return valid;
    }



    private boolean checkUsername(){

        if(db.findUserByUsername(username.getText().toString()) != null) {
            username.setBackgroundResource(R.drawable.bordererror);
            username.setError("Il seguente username è già stato utilizzato!");
            return false;
        }
        return true;
    }


    private boolean checkEmailCorrectness() {
        if (!email.getText().toString().matches(
                "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@"
                        + "[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")) {
            email.setBackgroundResource(R.drawable.bordererror);
            email.setError("Errore! Inserisci una e-mail valida!");
            return false;
        }
        return true;
    }
        private boolean checkEmailUse(){
            if(db.findUserByEmail(email.getText().toString()) != null) {
                email.setBackgroundResource(R.drawable.bordererror);
                email.setError("Errore! E-mail già utilizzata!");
                return false;
            }
            return true;
        }
    }




