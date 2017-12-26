package com.example.skyadapters3.recycler;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.example.skyadapters3.R;
import com.example.skyadapters3.ToolbarAdapter;
import com.example.skyadapters3.ToolbarCustomizer;

import java.util.ArrayList;

/**
 * Created by ttlnisoffice on 12/22/17.
 */

public abstract class RvActivityWithNavDrawer extends RvActivity {

    private ActionBarDrawerToggle toggle;
    private ArrayList<Integer> rvs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        setupRV();

        ToolbarAdapter toolbarAdapter = new ToolbarAdapter(this, getActivityView());
        if (this.getClass().getSimpleName().contains("MainActivity")) {
            toggle = toolbarAdapter.buildToolbarWithNavDrawer(
                    getActivityView(),
                    getDrawerActivitiesToLaunch(),
                    getDrawerMenuID(),
                    getDrawerCustomLayoutID(),
                    getDrawerLayoutManager(),
                    getDrawerItemColor(),
                    rvs.get(1)
            );
        } else {
            toolbarAdapter.buildToolbarWithHomeUp();
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

    public abstract Class[] getDrawerActivitiesToLaunch();
    public abstract int getDrawerMenuID();
    public abstract int getDrawerCustomLayoutID();
    public abstract RecyclerView.LayoutManager getDrawerLayoutManager();
    public abstract int getDrawerItemColor();

    @Override
    public int getView() {
        return getActivityView();
    }

    @Override
    public int initRv() {
        return rvs.get(0);
    }



    @Override
    public int rvCustomRow() {
        return rvCustomRow_rvSize_holderIDS().get(0);
    }

    @Override
    public int rvSize() {
        return rvCustomRow_rvSize_holderIDS().get(1);
    }

    @Override
    public ArrayList<Integer> holderIDS() {
        return (ArrayList<Integer>) rvCustomRow_rvSize_holderIDS().subList(2, rvCustomRow_rvSize_holderIDS().size());

    }

    @Override
    public RecyclerView.LayoutManager rvLayoutManager() {
        return getRvLayoutManager();
    }

    @Override
    public RvAdapter.RvInterface rvOnBind() {
        return getRvOnBind();
    }

    public abstract RvAdapter.RvInterface getRvOnBind();
    public abstract RecyclerView.LayoutManager getRvLayoutManager();
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

