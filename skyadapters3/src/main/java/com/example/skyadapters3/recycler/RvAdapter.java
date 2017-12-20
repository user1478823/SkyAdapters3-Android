package com.example.skyadapters3.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by ttlnisoffice on 12/19/17.
 */

public class RvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int rvSize;
    private RecyclerView.ViewHolder holder;
    RvInterface rvInterface;

    public RvAdapter(int rvSize, RecyclerView.ViewHolder holder, RvInterface rvInterface) {
        this.rvSize = rvSize;
        this.holder = holder;
        this.rvInterface = rvInterface;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        rvInterface.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return rvSize;
    }

    interface RvInterface {
        void onBindViewHolder(RecyclerView.ViewHolder holder, int position);
    }
}
