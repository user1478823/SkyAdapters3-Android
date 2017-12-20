package com.example.skyadapters3.recycler;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ttlnisoffice on 12/20/17.
 */

public class RvManager {

    private Activity a;
    private RecyclerView rv;
    private int customRow;
    private int rvSize;
    private ArrayList<Integer> ids;

    public RvManager(Activity a) {
        this.a = a;
    }

    public RvManager oneInitRv(int rvID) {
        rv = (RecyclerView) a.findViewById(rvID);
        return this;
    }

    public RvManager twoCustomRow() {
        this.customRow = customRow;
        return this;
    }

    public RvManager threeRvSize(int rvSize) {
        this.rvSize = rvSize;
        return this;
    }

    public RvManager fourHolderIDS(ArrayList<Integer> ids) {
        this.ids = ids;
        return this;
    }

    public RvManager fiveLayoutManager(RecyclerView.LayoutManager manager) {
        rv.setLayoutManager(manager);
        return this;
    }

    public void sixOnBind(RvAdapter.RvInterface rvInterface) {
        rv.setAdapter(new RvAdapter(rvSize, ids, customRow, rvInterface));
    }
}
