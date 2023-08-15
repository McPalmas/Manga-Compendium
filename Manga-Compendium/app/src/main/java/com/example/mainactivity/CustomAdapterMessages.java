package com.example.mainactivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainactivity.db.DbManager;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class CustomAdapterMessages extends RecyclerView.Adapter<CustomAdapterMessages.ViewHolder>{

    ArrayList<Message> messages;

    public DbManager db = DbManager.getInstance();
    Context context;
    FragmentActivity activity;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView username, date, text;

        private  final  ImageView image;

        public ViewHolder(View view) {
            super(view);
            username = view.findViewById(R.id.userNameMessage);
            date = view.findViewById(R.id.dateMessage);
            text = view.findViewById(R.id.textMessage);
            image = view.findViewById(R.id.imageMessage);
        }

        public TextView getUsername() {
            return username;
        }
        public TextView getDate() {
            return date;
        }
        public TextView getText() {
            return text;
        }
        public ImageView getImage() {
            return image;
        }

    }

    public CustomAdapterMessages(ArrayList<Message> dataSet, Context context, FragmentActivity activity) {
        this.context=context;
        messages = dataSet;
        this.activity = activity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_message, viewGroup, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        User user = db.getUserById(messages.get(position).getId_user());

        if(!user.getImg().equals("")) {
            viewHolder.getImage().setImageBitmap(FileHelper.loadBitmap(user.getImg()));
        }else
            viewHolder.getImage().setImageDrawable(context.getDrawable(R.drawable.baseline_account_circle_24));


        viewHolder.getUsername().setText(user.getUsername());

        getDateDiffFromNow(messages.get(position).getDate(),viewHolder);

        viewHolder.getText().setText(messages.get(position).getText());
    }


    public int getDateDiffFromNow(String date, ViewHolder viewHolder){
        int days = 0;
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy", Locale.getDefault());
            long diff = new Date().getTime() - sdf.parse(date).getTime();
            long seconds = diff / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;
            days = ((int) (long) hours / 24);
            if(days > 1)
                viewHolder.getDate().setText(days + " giorni");
            else if(days == 1)
                viewHolder.getDate().setText("ieri");
            else
                viewHolder.getDate().setText("oggi");
        }catch (Exception e){
            e.printStackTrace();
        }
        return days;
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void replaceAll(ArrayList<Message> models) {
        messages = models;
        notifyDataSetChanged();
    }
}
