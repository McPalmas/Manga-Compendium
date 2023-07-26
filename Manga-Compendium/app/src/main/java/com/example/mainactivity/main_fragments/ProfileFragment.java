package com.example.mainactivity.main_fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mainactivity.ChangeEmail;
import com.example.mainactivity.ChangePassword;
import com.example.mainactivity.LogIn;
import com.example.mainactivity.R;
import com.example.mainactivity.User;
import com.example.mainactivity.db.DbManager;

public class ProfileFragment extends Fragment {

    View view;
    TextView changeEmail, changePassword;
    ImageView img;
    Uri selectedImage;

    public TextView username,email;

    public DbManager db = DbManager.getInstance();
    User user;

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("distruttoooooooo");
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("PROFILO");
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_profile, container, false);

        user = db.getUserById(LogIn.sharedPref.getInt("user",-1));

        img = view.findViewById(R.id.imageViewUserProfile);

        if(!user.getImg().equals(""))
            img.setImageURI(Uri.parse(user.getImg()));


        username = (TextView) view.findViewById(R.id.usernameProfile);
        username.setText(user.getUsername());

        email = (TextView) view.findViewById(R.id.emailProfile);
        email.setText(user.getEmail());

        changeEmail = (TextView) view.findViewById(R.id.buttonChangeEmail);
        changePassword = (TextView) view.findViewById(R.id.buttonChangePassword);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);

                // pass the constant to compare it with the returned requestCode
                startActivityForResult(Intent.createChooser(i, "Select Picture"), 1);
            }
        });

        changeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent changeEmail;
                changeEmail = new Intent(getContext(), ChangeEmail.class);
                startActivity(changeEmail);*/
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new ChangeEmail()).commit();

            }
        });


        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent changePassword;
                changePassword = new Intent(getContext(), ChangePassword.class);
                startActivity(changePassword);*/
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new ChangePassword()).commit();

            }
        });


        return view;
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