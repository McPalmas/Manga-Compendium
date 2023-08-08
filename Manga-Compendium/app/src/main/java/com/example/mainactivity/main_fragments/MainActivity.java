package com.example.mainactivity.main_fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mainactivity.HelpFragment;
import com.example.mainactivity.LogIn;
import com.example.mainactivity.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    public static final String DATA_EXTRA = "com.example.mainActivity.manga";
    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment=new HomeFragment();
    SearchFragment searchFragment=new SearchFragment();
    ForumFragment forumFragment=new ForumFragment();
    LibraryFragment libraryFragment=new LibraryFragment();
    ProfileFragment profileFragment=new ProfileFragment();


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                Toast.makeText(this, "LOGOUT", Toast.LENGTH_SHORT).show();
                finish();
                SharedPreferences.Editor edt = LogIn.sharedPref.edit();
                edt.putInt("user", -1);
                edt.apply();
                return true;
            case R.id.theme:
                Toast.makeText(this, "TEMA CAMBIATO", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.help:
                getSupportFragmentManager().beginTransaction().add(R.id.container,new HelpFragment()).addToBackStack(null).commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.barHome);
        setSupportActionBar(myToolbar);

        bottomNavigationView= findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                        return true;
                    case R.id.search:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,searchFragment).commit();
                        return true;
                    case R.id.helpForum:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,forumFragment).commit();
                        return true;
                    case R.id.library:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,libraryFragment).commit();
                        return true;
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,profileFragment).commit();
                        return true;
                }

                return false;
            }
        });

    }
}