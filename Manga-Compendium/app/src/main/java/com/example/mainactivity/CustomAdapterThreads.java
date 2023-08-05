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

public class CustomAdapterThreads extends RecyclerView.Adapter<CustomAdapterThreads.ViewHolder>{

    ArrayList<Thread> threads;

    public DbManager db = DbManager.getInstance();
    Context context;
    FragmentActivity activity;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView image;
        private final TextView title;

        public ViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.imageRowThread);
            title = view.findViewById(R.id.textRowThread);
        }

        public ImageView getImageView() {
            return image;
        }
        public TextView getTextViewTitle() {
            return title;
        }

    }

    public CustomAdapterThreads(ArrayList<Thread> dataSet, Context context, FragmentActivity activity) {
        this.context=context;
        threads = dataSet;
        this.activity = activity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_list_thread, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getImageView().setImageURI(Uri.parse(threads.get(position).getImage()));
        viewHolder.getTextViewTitle().setText(threads.get(position).getTitle());

        viewHolder.getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer id = threads.get(viewHolder.getAdapterPosition()).getId();
                ThreadFragment fragment = new ThreadFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                fragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().add(R.id.container,fragment).addToBackStack(null).commit();
            }
        });

        viewHolder.getTextViewTitle().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer id = threads.get(viewHolder.getAdapterPosition()).getId();
                ThreadFragment fragment = new ThreadFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                fragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().add(R.id.container,fragment).addToBackStack(null).commit();
            }
        });



    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return threads.size();
    }

    public void replaceAll(ArrayList<Thread> models) {
        threads = models;
        notifyDataSetChanged();
    }
}
