package com.example.mainactivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
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
        private final TextView title, info;

        private  final  ConstraintLayout container;

        private final View threadIcon;

        public ViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.imageRowThread);
            title = view.findViewById(R.id.textRowThread);
            info = view.findViewById(R.id.infoThread);
            threadIcon = view.findViewById(R.id.addedThreadIcon);
            container = view.findViewById(R.id.threadContainer);
        }

        public ImageView getImageView() {
            return image;
        }
        public TextView getTextViewTitle() {
            return title;
        }

        public View getViewIcon() {
            return threadIcon;
        }
        public TextView getTextViewInfo() {
            return info;
        }

        public ConstraintLayout getContainer() {
            return container;
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
        if(!threads.get(position).getImage().equals(""))
            viewHolder.getImageView().setImageBitmap(FileHelper.loadBitmap(threads.get(position).getImage()));

        viewHolder.getTextViewTitle().setText(threads.get(position).getTitle());

        User user = db.getUserById(threads.get(position).getId_creator());
        viewHolder.getTextViewInfo().setText(user.getUsername());

        if(db.findUserThread(LogIn.sharedPref.getInt("user", -1), threads.get(viewHolder.getAdapterPosition()).getId()))
            viewHolder.getViewIcon().setVisibility(View.VISIBLE);
        else
            viewHolder.getViewIcon().setVisibility(View.GONE);


        viewHolder.getContainer().setOnClickListener(new View.OnClickListener() {
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

    @Override
    public int getItemCount() {
        return threads.size();
    }

    public void replaceAll(ArrayList<Thread> models) {
        threads = models;
        notifyDataSetChanged();
    }
}
