package com.example.skyadapters3;


import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;


/**
 * Created by ttlnisoffice on 11/17/17.
 */
public class ToolbarAdapter {

    private AppCompatActivity a;
    private RvAdapter rvAdapter;
    private Toolbar toolbar;

    public ToolbarAdapter(AppCompatActivity a) {
        this.a = a;
    }

    public ToolbarAdapter buildToolbar(int toolbarID, boolean showTitle) {
        Toolbar toolbar = (Toolbar) a.findViewById(toolbarID);
        a.setSupportActionBar(toolbar);
        a.getSupportActionBar().setDisplayShowTitleEnabled(showTitle);
        return this;
    }

    public ToolbarAdapter buildToolbarWithHomeUp(int id) {
        toolbar = (Toolbar) a.findViewById(id);
        a.setSupportActionBar(toolbar);
        a.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a.finish();
            }
        });
        return this;
    }

    public ToolbarAdapter buildToolbarWithCustomBackIcon(int toolbarID, int iconID) {
        toolbar = (Toolbar) a.findViewById(toolbarID);
        a.setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(iconID);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.finish();
            }
        });
        return this;
    }

    public ActionBarDrawerToggle buildToolbarWithNavDrawer(Class[] activitiesToLaunch,
                                                           int menuID,
                                                           int customLayoutID,
                                                           RecyclerView.LayoutManager layoutManager,
                                                           int drawerItemColor){

        Menu menu = new PopupMenu(a, null).getMenu();
        a.getMenuInflater().inflate(menuID, menu);

        rvAdapter = new RvAdapter(a, menu, activitiesToLaunch, customLayoutID, layoutManager, drawerItemColor);

        DrawerLayout drawerLayout = (DrawerLayout) a.findViewById(R.id.sky_drawer_layout);
        ActionBarDrawerToggle toggleBtn = new ActionBarDrawerToggle(a, drawerLayout,
                R.string.drawer_open, R.string.drawer_closed);
        drawerLayout.addDrawerListener(toggleBtn);

        Toolbar toolbar = (Toolbar) a.findViewById(R.id.sky_toolbar);
        a.setSupportActionBar(toolbar);
        a.getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toggleBtn.syncState();

        return toggleBtn;
    }

    public ToolbarAdapter setToolbarTitle(String title){
        a.getSupportActionBar().setTitle(title);
        return this;
    }

    public ToolbarAdapter setToolbarColor(int color) {
        toolbar.setBackgroundColor(color);
        return this;
    }

    public ToolbarAdapter setToolbarTextColor(int color) {
        toolbar.setTitleTextColor(color);
        return this;
    }

    public ToolbarAdapter setToolbarTypeFace(Typeface tf) {
        ((TextView)toolbar.getChildAt(0)).setTypeface(tf);
        return this;
    }
}

