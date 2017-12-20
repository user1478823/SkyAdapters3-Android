package com.example.skyadapters3.recycler;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

/**
 * Created by ttlnisoffice on 12/20/17.
 */

public class RvManager {

    private Activity a;
    private RecyclerView rv;

    public RvManager(Activity a) {
        this.a = a;
    }

    public RvManager initRv(int rvID) {
        rv = (RecyclerView) a.findViewById(rvID);
        return this;
    }

    public RvManager setLayoutManager(RecyclerView.LayoutManager manager) {
        rv.setLayoutManager(manager);
        return this;
    }

    public RvManager withAdapter(RvAdapter rvAdapter) {
        rv.setAdapter(rvAdapter);
        return this;
    }
}
