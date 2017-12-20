package com.example.skyadapters3.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by ttlnisoffice on 12/19/17.
 */

public class RvHolder extends RecyclerView.ViewHolder {

    public View[] views;

    public RvHolder(View itemView, ArrayList<Integer> ids) {
        super(itemView);
        for (int i = 0; i < ids.size(); i++) {
            views[i] = itemView.findViewById(ids.get(i));
        }
    }
}
