package com.example.mainactivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainactivity.db.DbManager;

import java.util.ArrayList;

public class CustomAdapterMessages extends RecyclerView.Adapter<CustomAdapterMessages.ViewHolder>{

    ArrayList<Message> messages;

    public DbManager db = DbManager.getInstance();
    Context context;
    FragmentActivity activity;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView username, date, text;

        public ViewHolder(View view) {
            super(view);
            username = view.findViewById(R.id.userNameMessage);
            date = view.findViewById(R.id.dateMessage);
            text = view.findViewById(R.id.textMessage);
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

    }

    public CustomAdapterMessages(ArrayList<Message> dataSet, Context context, FragmentActivity activity) {
        this.context=context;
        messages = dataSet;
        this.activity = activity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_message, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getUsername().setText(db.getUserById(messages.get(position).getId_user()).getUsername());
        viewHolder.getDate().setText(messages.get(position).getDate());
        viewHolder.getText().setText(messages.get(position).getText());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void replaceAll(ArrayList<Message> models) {
        messages = models;
        notifyDataSetChanged();
    }
}
