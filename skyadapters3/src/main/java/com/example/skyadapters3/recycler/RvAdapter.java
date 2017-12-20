package com.example.skyadapters3.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by ttlnisoffice on 12/19/17.
 */

public class RvAdapter extends RecyclerView.Adapter<RvHolder> {

    private int rvSize;
    ArrayList<Integer> ids;
    private int customRow;
    private RvInterface rvInterface;

    public RvAdapter(int rvSize, ArrayList<Integer> ids, int customRow, RvInterface rvInterface) {
        this.rvSize = rvSize;
        this.ids = ids;
        this.customRow = customRow;
        this.rvInterface = rvInterface;
    }

    @Override
    public RvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new RvHolder(inflater.inflate(customRow, parent, false), ids);
    }

    @Override
    public void onBindViewHolder(RvHolder holder, int position) {
        rvInterface.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return rvSize;
    }

    public interface RvInterface {
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position);
    }
}
