package com.example.skyadapters3;


import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by ttlnisoffice on 11/17/17.
 */
public class ToolbarAdapter {

    private AppCompatActivity a;
    private RvAdapter rvAdapter;
    private Toolbar toolbar = null;

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

    public ActionBarDrawerToggle buildToolbarWithNavDrawer(int vgID,
                                                           Class[] activitiesToLaunch,
                                                           int menuID,
                                                           int customLayoutID,
                                                           RecyclerView.LayoutManager layoutManager,
                                                           int drawerItemColor){

        ViewGroup vg = (ViewGroup) a.getLayoutInflater().inflate(vgID,null);

        DrawerLayout drawerLayout = null;
        for (int i = 0; i < vg.getChildCount(); i++) {
            if (vg.getChildAt(i) instanceof Toolbar) {
                toolbar = (Toolbar) a.findViewById(vg.getChildAt(i).getId());
            }
            if (vg.getChildAt(i) instanceof  DrawerLayout) {
                drawerLayout = (DrawerLayout) a.findViewById(vg.getChildAt(i).getId());
            }
        }

        Menu menu = new PopupMenu(a, null).getMenu();
        a.getMenuInflater().inflate(menuID, menu);

        rvAdapter = new RvAdapter(a, menu, activitiesToLaunch, customLayoutID, layoutManager, drawerItemColor);
        
        ActionBarDrawerToggle toggleBtn = null;
        if (drawerLayout != null) {
            toggleBtn = new ActionBarDrawerToggle(a, drawerLayout,
                    R.string.drawer_open, R.string.drawer_closed);
            drawerLayout.addDrawerListener(toggleBtn);
            toggleBtn.syncState();
        } else {
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(a);
            alertBuilder.setTitle("Error");
            alertBuilder.setMessage("DrawerLayout is null, did you add DrawerLayout in xml or did you give it id?");
            alertBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });

            AlertDialog alert = alertBuilder.create();
            alert.show();
        }

        if (toolbar != null) {
            a.setSupportActionBar(toolbar);
            a.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } else {
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(a);
            alertBuilder.setTitle("Error");
            alertBuilder.setMessage("Toolbar is null, did you add Toolbar in xml or did you give it id?");
            alertBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });

            AlertDialog alert = alertBuilder.create();
            alert.show();
        }

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

