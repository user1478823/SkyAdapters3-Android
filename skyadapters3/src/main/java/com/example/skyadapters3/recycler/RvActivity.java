package com.example.skyadapters3.recycler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by ttlnisoffice on 12/20/17.
 */

public abstract class RvActivity extends AppCompatActivity {

    RvAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getView());
        /*new RvManager(this).oneInitRv(initRv())
                .twoCustomRow(rvCustomRow())
                .threeRvSize(rvSize())
                .fourHolderIDS(holderIDS())
                .fiveLayoutManager(rvLayoutManager())
                .sixFinnalOnBind(rvOnBind());*/
        RecyclerView rv = (RecyclerView) findViewById(initRv());
        rv.setLayoutManager(rvLayoutManager());
        adapter = new RvAdapter(rvSize(), holderIDS(), rvCustomRow(), rvOnBind());
        rv.setAdapter(adapter);
    }

    public abstract int getView();
    public abstract int initRv();
    public abstract int rvCustomRow();
    public abstract int rvSize();
    public abstract ArrayList<Integer> holderIDS();
    public abstract RecyclerView.LayoutManager rvLayoutManager();
    public abstract RvAdapter.RvInterface rvOnBind();

    public void updateRV() {
        adapter.notifyDataSetChanged();
    }
}
