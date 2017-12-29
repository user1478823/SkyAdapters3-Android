package com.example.skyadapters3.recycler;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ttlnisoffice on 12/29/17.
 */

public abstract class RvBase extends AppCompatActivity {

    private List list;
    private RvAdapter adapter;
    private RecyclerView rv;

    public void initRv() {
        ViewGroup vg = (ViewGroup) getLayoutInflater().inflate(getView(), null);
        Integer rvID = null;
        for (int i = 0; i < vg.getChildCount(); i++) {
            if (vg.getChildAt(i) instanceof RecyclerView) {
                rvID = vg.getChildAt(i).getId();
            }
        }
        rv = (RecyclerView) findViewById(rvID);
        rv.setLayoutManager(getLayoutManager());
    }

    public void populateRv(List list) {
        if (this.list == null) {
            this.list = list;
        } else {
            this.list.addAll(list);
            adapter.notifyDataSetChanged();
        }

        if (adapter == null) {
            adapter = new RvAdapter(this.list.size(),
                    getRvCustomRow_holderIDS().subList(1, getRvCustomRow_holderIDS().size()),
                    getRvCustomRow_holderIDS().get(0),
                    getRvOnBind());
            rv.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    public abstract Integer getView();
    public abstract RecyclerView.LayoutManager getLayoutManager();
    public abstract ArrayList<Integer> getRvCustomRow_holderIDS();
    public abstract RvAdapter.RvInterface getRvOnBind();
}
