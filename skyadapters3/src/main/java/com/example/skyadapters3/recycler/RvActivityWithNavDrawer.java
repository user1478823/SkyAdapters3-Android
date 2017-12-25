package com.example.skyadapters3.recycler;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.skyadapters3.ToolbarAdapter;
import com.example.skyadapters3.ToolbarCustomizer;

import java.util.ArrayList;

/**
 * Created by ttlnisoffice on 12/22/17.
 */

public abstract class RvActivityWithNavDrawer extends RvActivity {

    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ToolbarAdapter toolbarAdapter = new ToolbarAdapter(this);
        if (this.getClass().getSimpleName().contains("MainActivity")) {
            toggle = toolbarAdapter.buildToolbarWithNavDrawer(
                    getActivityView(),
                    getDrawerActivitiesToLaunch(),
                    getDrawerMenuID(),
                    getDrawerCustomLayoutID(),
                    getDrawerLayoutManager(),
                    getDrawerItemColor()
            );
        } else {
            toolbarAdapter.buildToolbarWithHomeUp(getToolbarID());
            /*if (getToolbarTitle() != null) {
                toolbarAdapter.setToolbarTitle(getToolbarTitle());
            }
            if (getToolbarColor() != null) {
                toolbarAdapter.setToolbarColor(getToolbarColor());
            }
            if (getToolbarTextColor() != null) {
                toolbarAdapter.setToolbarTextColor(getToolbarTextColor());
            }
            if (getToolbarTypeFace() != null) {
                toolbarAdapter.setToolbarTypeFace(getToolbarTypeFace());
            }*/
        }
        if (customizeToolbar() != null) {
            toolbarAdapter.setToolbarTitle(customizeToolbar().setToolbarTitle());
            toolbarAdapter.setToolbarColor(customizeToolbar().setToolbarColor());
            toolbarAdapter.setToolbarTextColor(customizeToolbar().setToolbarTextColor());
            toolbarAdapter.setToolbarTypeFace(customizeToolbar().setToolbarTypeFace());
        }
    }

    public abstract Class[] getDrawerActivitiesToLaunch();
    public abstract int getDrawerMenuID();
    public abstract int getDrawerCustomLayoutID();
    public abstract RecyclerView.LayoutManager getDrawerLayoutManager();
    public abstract int getDrawerItemColor();

    public abstract int getToolbarID();

    @Override
    public int getView() {
        return getActivityView();
    }

    @Override
    public int initRv() {
        return getRvID();
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
    public abstract int getRvID();
    public abstract int getRvCustomRow();
    public abstract int getRvSize();
    public abstract ArrayList<Integer> getHolderIDS();
    public abstract RecyclerView.LayoutManager getRvLayoutManager();

    public abstract ToolbarCustomizer customizeToolbar();

    /*public abstract String   getToolbarTitle();
    public abstract Integer  getToolbarColor();
    public abstract Typeface getToolbarTypeFace();
    public abstract Integer  getToolbarTextColor();*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (this.getClass().getSimpleName().contains("MainActivity") && toggle.onOptionsItemSelected(item)) {
            return true;
        } else {
            return false;
        }
    }
}

