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

public class CustomAdapterMangasSearch extends RecyclerView.Adapter<CustomAdapterMangasSearch.ViewHolder>{

    ArrayList<Manga> mangas ;

    public DbManager db = DbManager.getInstance();
    Context context;

    FragmentActivity activity;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView image;
        private final TextView title;
        private final TextView author;

        public ViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.imagerow2);
            title = view.findViewById(R.id.titleRowSearch2);
            author = view.findViewById(R.id.authorRowSearch2);
        }

        public ImageView getImageView() {
            return image;
        }

        public TextView getTextViewTitle() {
            return title;
        }

        public TextView getTextViewAuthor() {
            return author;
        }

    }

    public CustomAdapterMangasSearch(ArrayList<Manga> dataSet, Context context, FragmentActivity activity) {
        this.context=context;
        mangas = dataSet;
        this.activity = activity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_search, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        // Get element from your dataset at this position and replace the contents of the view with that element
        viewHolder.getImageView().setImageURI(Uri.parse(mangas.get(position).getImg()));
        viewHolder.getTextViewTitle().setText(mangas.get(position).getTitle());
        viewHolder.getTextViewAuthor().setText(mangas.get(position).getAuthor());


        viewHolder.getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer id = db.findMangaByTitle(viewHolder.getTextViewTitle().getText().toString());
                MangaFragment fragment = new MangaFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                fragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().add(R.id.container,fragment).addToBackStack(null).commit();
            }
        });

        viewHolder.getTextViewTitle().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer id = db.findMangaByTitle(viewHolder.getTextViewTitle().getText().toString());
                MangaFragment fragment = new MangaFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                fragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().add(R.id.container,fragment).addToBackStack(null).commit();
            }
        });

        viewHolder.getTextViewAuthor().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer id = db.findMangaByTitle(viewHolder.getTextViewTitle().getText().toString());
                MangaFragment fragment = new MangaFragment();
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
        return mangas.size();
    }

    public void replaceAll(ArrayList<Manga> models) {
        mangas=models;
        notifyDataSetChanged();
    }
}
