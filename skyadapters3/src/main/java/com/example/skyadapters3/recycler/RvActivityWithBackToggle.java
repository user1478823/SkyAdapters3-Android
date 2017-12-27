package com.example.skyadapters3.recycler;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.skyadapters3.ToolbarAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ttlnisoffice on 12/20/17.
 */

public abstract class RvActivityWithBackToggle extends RvActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ToolbarAdapter toolbarAdapter = new ToolbarAdapter(this, getActivityView());
        toolbarAdapter.buildToolbarWithHomeUp();
        if (getToolbarTitle() != null) {
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
        }

        setupRV();
    }

    public abstract int getActivityView();

    @Override
    public int getView() {
        return getActivityView();
    }

    @Override
    public int initRv() {
        ViewGroup vg = (ViewGroup) getLayoutInflater().inflate(getActivityView(), null);
        Integer rvID = null;
        for (int i = 0; i < vg.getChildCount(); i++) {
            if (vg.getChildAt(i) instanceof RecyclerView) {
                rvID = vg.getChildAt(i).getId();
            }
        }
        return rvID;
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
    public List<Integer> holderIDS() {
        return rvCustomRow_rvSize_holderIDS().subList(2, rvCustomRow_rvSize_holderIDS().size());
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
    
    public abstract String   getToolbarTitle();
    public abstract Integer  getToolbarColor();
    public abstract Typeface getToolbarTypeFace();
    public abstract Integer  getToolbarTextColor();
}
