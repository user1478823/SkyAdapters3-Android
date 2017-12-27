package com.example.skyadapters3.recycler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.example.skyadapters3.ToolbarAdapter;
import com.example.skyadapters3.ToolbarCustomizer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ttlnisoffice on 12/22/17.
 */

public abstract class RvActivityWithNavDrawer extends AppCompatActivity {

    private ActionBarDrawerToggle toggle;
    private ArrayList<Integer> rvs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityView());
        ViewGroup vg = (ViewGroup) getLayoutInflater().inflate(getActivityView(), null);

        DrawerLayout drawerLayout = null;
        for (int i = 0; i < vg.getChildCount(); i++) {
            if (vg.getChildAt(i) instanceof DrawerLayout) {
                drawerLayout = (DrawerLayout) findViewById(vg.getChildAt(i).getId());
            }
        }

        rvs = new ArrayList<>();
        for (int i = 0; i < drawerLayout.getChildCount(); i++) {
            if (drawerLayout.getChildAt(i) instanceof RecyclerView) {
                rvs.add(drawerLayout.getChildAt(i).getId());
            }
        }

        RecyclerView rv = (RecyclerView) findViewById(rvs.get(0));
        rv.setLayoutManager(rvLayoutManager());
        RvAdapter adapter = new RvAdapter(rvCustomRow_rvSize_holderIDS().get(1),
                rvCustomRow_rvSize_holderIDS().subList(2, rvCustomRow_rvSize_holderIDS().size()),
                rvCustomRow_rvSize_holderIDS().get(0),
                rvOnBind());
        rv.setAdapter(adapter);

        ToolbarAdapter toolbarAdapter = new ToolbarAdapter(this, getActivityView());
        toggle = toolbarAdapter.buildToolbarWithNavDrawer(
                getActivityView(),
                getDrawerActivitiesToLaunch(),
                drawerMenuID_drawerCustomLayoutID_drawerItemColor()[0],
                drawerMenuID_drawerCustomLayoutID_drawerItemColor()[1],
                getDrawerLayoutManager(),
                drawerMenuID_drawerCustomLayoutID_drawerItemColor()[2],
                rvs.get(1));

        if (toggle != null) {
            toggle.syncState();
        }

        if (customizeToolbar() != null) {
            if (customizeToolbar().setToolbarTitle() != null) {
                toolbarAdapter.setToolbarTitle(customizeToolbar().setToolbarTitle());
            }
            if (customizeToolbar().setToolbarColor() != null) {
                toolbarAdapter.setToolbarColor(customizeToolbar().setToolbarColor());
            }
            if (customizeToolbar().setToolbarTextColor() != null) {
                toolbarAdapter.setToolbarTextColor(customizeToolbar().setToolbarTextColor());
            }
            if (customizeToolbar().setToolbarTypeFace() != null) {
                toolbarAdapter.setToolbarTypeFace(customizeToolbar().setToolbarTypeFace());
            }
        }
    }

    public abstract int getActivityView();

    public Integer getRvID() {
        ViewGroup vg = (ViewGroup) getLayoutInflater().inflate(getActivityView(), null);
        Integer rvID = null;
        for (int i = 0; i < vg.getChildCount(); i++) {
            if (vg.getChildAt(i) instanceof RecyclerView) {
                rvID = vg.getChildAt(i).getId();
            }
        }
        return rvID;
    }

    public abstract Class[] getDrawerActivitiesToLaunch();
    public abstract Integer[] drawerMenuID_drawerCustomLayoutID_drawerItemColor();
    public abstract RecyclerView.LayoutManager getDrawerLayoutManager();


    public abstract RvAdapter.RvInterface rvOnBind();
    public abstract RecyclerView.LayoutManager rvLayoutManager();
    public abstract ArrayList<Integer> rvCustomRow_rvSize_holderIDS();

    public abstract ToolbarCustomizer customizeToolbar();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (this.getClass().getSimpleName().contains("MainActivity") && toggle.onOptionsItemSelected(item)) {
            return true;
        } else {
            return false;
        }
    }
}

