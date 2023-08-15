package com.example.mainactivity.main_fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mainactivity.ChangeEmail;
import com.example.mainactivity.ChangePassword;
import com.example.mainactivity.FileHelper;
import com.example.mainactivity.LogIn;
import com.example.mainactivity.R;
import com.example.mainactivity.User;
import com.example.mainactivity.db.DbManager;

import java.io.IOException;

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

        if(!user.getImg().equals("")) {
            Bitmap b;
            try {
                  b = MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(), Uri.parse(user.getImg()));
                  FileHelper.saveUserImgToInternalStorage(b,db,getActivity());
                  img.setImageBitmap(b);
               // img.setImageURI(Uri.parse(user.getImg()));
            } catch (IOException e) {
                img.setImageBitmap(FileHelper.loadBitmap(user.getImg()));
            }
        }



        username = (TextView) view.findViewById(R.id.usernameProfile);
        username.setText(user.getUsername());

        email = (TextView) view.findViewById(R.id.emailProfile);
        email.setText(user.getEmail());

        changeEmail = (TextView) view.findViewById(R.id.buttonChangeEmail);
        changePassword = (TextView) view.findViewById(R.id.buttonChangePassword);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 3);
            }
        });

        changeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new ChangeEmail()).commit();
            }
        });


        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new ChangePassword()).commit();
            }
        });


        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            selectedImage = data.getData();
             /*img.setImageURI(selectedImage);
             db.changeImage(LogIn.sharedPref.getInt("user",-1), selectedImage.toString());*/

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                img.setImageBitmap(bitmap);
                FileHelper.saveUserImgToInternalStorage(bitmap,db,getActivity());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

}