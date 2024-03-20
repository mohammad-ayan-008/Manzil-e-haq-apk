package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottom;
    public FragmentManager fragment;
    public FragmentTransaction transactionl;
    public DrawerLayout layout;
    public ActionBarDrawerToggle toogle;

    public Toolbar toolbar;
    public NavigationView nav;
    public Fragment home,ayat;
    private BottomSheetDialog dialogue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottom = findViewById(R.id.navids);
        layout = findViewById(R.id.drawer);
        nav = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.toolbar);

        home = new BlankFragment2();
        ayat = new BlankFragment();
        setup_Drawer();




        bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id==R.id.home){
                    add(home,true);

                }else if(id==R.id.ayat){
                    add(ayat,false);
                }
                return true;
            }
        });
     bottom.setSelectedItemId(R.id.home);
    }
    public void add(Fragment frag,boolean flag){
        fragment = getSupportFragmentManager();
        transactionl = fragment.beginTransaction();
        transactionl.replace(R.id.frames, frag);
        transactionl.commit();

    }
     public void setup_Drawer(){
      toogle = new ActionBarDrawerToggle(this,layout,toolbar,R.string.open,R.string.close);
      layout.addDrawerListener(toogle);
      toogle.syncState();
      nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
          @Override
          public boolean onNavigationItemSelected(@NonNull MenuItem item) {
              int home=R.id.nav_abt;

              int id = item.getItemId();
              if(id==home) {
                 show_Dialodue();
              }
              layout.closeDrawer(GravityCompat.START);
              return true;
          }
      });
     }

     void show_Dialodue(){
        dialogue = new BottomSheetDialog(this);
        dialogue.setContentView(R.layout.bottomsheet_d);
        dialogue.show();
     }
}