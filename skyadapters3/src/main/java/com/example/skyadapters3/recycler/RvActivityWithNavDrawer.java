package com.example.skyadapters3.recycler;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;

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
        ArrayList<Integer> rvs = new ArrayList<>();
        for (int i = 0; i < vg.getChildCount(); i++) {
            if (vg.getChildAt(i) instanceof RecyclerView) {
                rvs.add(vg.getChildAt(i).getId());
            }
        }

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
        return getRvCustomRow();
    }

    @Override
    public int rvSize() {
        return getRvSize();
    }

    @Override
    public ArrayList<Integer> holderIDS() {
        return getHolderIDS();
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
    public abstract int getActivityView();
    public abstract int getRvCustomRow();
    public abstract int getRvSize();
    public abstract ArrayList<Integer> getHolderIDS();
    public abstract RecyclerView.LayoutManager getRvLayoutManager();

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

