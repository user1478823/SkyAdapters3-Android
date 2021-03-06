package com.example.skyadapters3.recycler;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.skyadapters3.RxBackground;
import com.example.skyadapters3.ToolbarAdapter;
import com.example.skyadapters3.ToolbarCustomizer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ttlnisoffice on 12/22/17.
 */

public abstract class RvActivityWithNavDrawer extends RvBase implements RxBackground.RxBackgroundInterface {

    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getMyTheme() != null) {
            Integer theme = getMyTheme().getTheme();
            setTheme(theme);
        }
        setContentView(getActivityView());
        ViewGroup vg = (ViewGroup) getLayoutInflater().inflate(getActivityView(), null);

        DrawerLayout drawerLayout = null;
        for (int i = 0; i < vg.getChildCount(); i++) {
            if (vg.getChildAt(i) instanceof DrawerLayout) {
                drawerLayout = (DrawerLayout) findViewById(vg.getChildAt(i).getId());
            }
        }

        ArrayList<Integer> rvs = new ArrayList<>();
        for (int i = 0; i < drawerLayout.getChildCount(); i++) {
            if (drawerLayout.getChildAt(i) instanceof RecyclerView) {
                rvs.add(drawerLayout.getChildAt(i).getId());
            }
            if (drawerLayout.getChildAt(i) instanceof LinearLayout) {
                LinearLayout linearLayout = (LinearLayout) findViewById(drawerLayout.getChildAt(i).getId());
                for (int j = 0; j < linearLayout.getChildCount(); j++) {
                    if (linearLayout.getChildAt(j) instanceof RecyclerView) {
                        rvs.add(linearLayout.getChildAt(j).getId());
                    }
                }
            }
        }

        initRv(rvs.get(0));
        new RxBackground().executeInBackground(this, this);

        ToolbarAdapter toolbarAdapter = new ToolbarAdapter(this, getActivityView());
        toggle = toolbarAdapter.buildToolbarWithNavDrawer(
                getActivityView(),
                drawerActivitiesToLaunch(),
                drawerMenuID_drawerCustomLayoutID_drawerItemColor()[0],
                drawerMenuID_drawerCustomLayoutID_drawerItemColor()[1],
                drawerLayoutManager(),
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

    public abstract Intent[] drawerActivitiesToLaunch();
    public abstract RecyclerView.LayoutManager drawerLayoutManager();
    public abstract Integer[] drawerMenuID_drawerCustomLayoutID_drawerItemColor();

    @Override
    public RvAdapter.RvInterface getRvOnBind() {
        return rvOnBind();
    }

    @Override
    public Integer getView() {
        return getActivityView();
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return rvLayoutManager();
    }

    @Override
    public ArrayList<Integer> getRvCustomRow_holderIDS() {
        return rvCustomRow_holderIDS();
    }

    public abstract RvAdapter.RvInterface rvOnBind();
    public abstract RecyclerView.LayoutManager rvLayoutManager();
    public abstract ArrayList<Integer> rvCustomRow_holderIDS();

    public abstract ToolbarCustomizer customizeToolbar();
    public abstract ThemeManager getMyTheme();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (this.getClass().getSimpleName().contains("MainActivity") && toggle.onOptionsItemSelected(item)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List getDoInBackground() {
        return doInBackground();
    }

    @Override
    public void getOnResultReceived(List value) {
        onResultReceived(value);
    }

    public abstract List doInBackground();
    public abstract void onResultReceived(List value);
}

