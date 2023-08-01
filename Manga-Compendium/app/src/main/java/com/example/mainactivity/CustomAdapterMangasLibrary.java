package com.example.mainactivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainactivity.db.DbManager;
import com.example.mainactivity.db.DbStrings;

import java.util.ArrayList;

public class CustomAdapterMangasLibrary extends RecyclerView.Adapter<CustomAdapterMangasLibrary.ViewHolder>
implements  PopupMenu.OnMenuItemClickListener {

    ArrayList<MangaState> mangas ;

    public DbManager db = DbManager.getInstance();
    Context context;
    FragmentActivity activity;
    String currentManga;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView image;
        private final TextView title;
        private final TextView state;
        private final TextView delete;
        private final ImageView circle;
        private final View menu;

        public ViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.imagerowLibrary);
            title = view.findViewById(R.id.textrowLibrary);
            state = view.findViewById(R.id.stateMangaLibrary);
            circle = view.findViewById(R.id.stateMangaCircle);
            menu = view.findViewById(R.id.menuItemMangaLibrary);
            delete = view.findViewById(R.id.deleteMangaLibrary);
        }

        public ImageView getImageView() {
            return image;
        }

        public TextView getTextViewTitle() {
            return title;
        }

        public TextView getTextViewState() {
            return state;
        }

        public View getImageCircle() {
            return circle;
        }

        public View getViewMenu() {
            return menu;
        }

        public View getViewDelete() {
            return delete;
        }

    }


    public CustomAdapterMangasLibrary(ArrayList<MangaState> dataSet, Context context, FragmentActivity activity) {
        this.context = context;
        mangas = dataSet;
        this.activity = activity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_list_item_manga_library, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

       /* viewHolder.getViewMenu().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Elimina");
                builder.setMessage("Sei sicuro di voler eliminare il manga dalla libreria ?");
                builder.setCancelable(false);
                builder.setPositiveButton("SI", null);
                builder.setNegativeButton("NO", null);
                builder.show();
            }
        });*/



        viewHolder.getViewMenu().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    PopupMenu popup = new PopupMenu(context, view);
                    MenuInflater inflater = popup.getMenuInflater();
                    inflater.inflate(R.menu.popup_menu, popup.getMenu());
                    popup.show();
                    currentManga = mangas.get(viewHolder.getAdapterPosition()).getTitle();

                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            System.out.println("eliminatoooooooooooo"+viewHolder.getTextViewTitle().getText().toString());
                            db.deleteUserManga(LogIn.sharedPref.getInt("user",-1),viewHolder.getTextViewTitle().getText().toString());
                            mangas.remove(viewHolder.getAdapterPosition());
                            notifyDataSetChanged();
                            return false;
                        }
                    });
            }
        });


        viewHolder.getViewDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.getViewDelete().setVisibility(View.INVISIBLE);
                Toast.makeText(context, "Eliminato", Toast.LENGTH_SHORT).show();

            }
        });


        viewHolder.getImageView().setImageURI(Uri.parse(mangas.get(position).getImg()));
        viewHolder.getTextViewTitle().setText(mangas.get(position).getTitle());

        switch (mangas.get(position).getState()) {
            case 0 : viewHolder.getTextViewState().setText(DbStrings.State.NON_LETTO.toString());
                viewHolder.getImageCircle().setBackground(context.getDrawable(R.drawable.state_circle_red));
                break;
            case 1 : viewHolder.getTextViewState().setText(DbStrings.State.IN_CORSO.toString());
                viewHolder.getImageCircle().setBackground(context.getDrawable(R.drawable.state_circle_yellow));
                break;
            case 2 : viewHolder.getTextViewState().setText(DbStrings.State.LETTO.toString());
                viewHolder.getImageCircle().setBackground(context.getDrawable(R.drawable.state_circle_green));
                break;
        }

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
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        System.out.println(currentManga + "aoooooo**********************          ");
        Toast.makeText(context, "Eliminato", Toast.LENGTH_SHORT).show();
        db.deleteUserManga(LogIn.sharedPref.getInt("user", -1), currentManga);
        return true;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mangas.size();
    }

    public void replaceAll(ArrayList<MangaState> models) {
        mangas=models;
        notifyDataSetChanged();
    }
}

