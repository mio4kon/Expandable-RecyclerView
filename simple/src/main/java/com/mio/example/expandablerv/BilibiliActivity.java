package com.mio.example.expandablerv;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.mio.example.expandablerv.adapter.BiliBiliAdapter;
import com.mio.example.expandablerv.bean.BilibiliData;
import com.mio.example.expandablerv.util.GsonUtils;

public class BilibiliActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private BiliBiliAdapter mAdapter;
    private BilibiliData mBilibiliData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilibili);
        mBilibiliData = GsonUtils.getBeanFromAssetJson(this, "bilibili.json", BilibiliData.class);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new BiliBiliAdapter();
        mRecyclerView.setAdapter(mAdapter);
//        getWapperData();
//        mAdapter.notifyDataSetChanged();
    }

//    public Object getWapperData() {
//        return wapperData;
//    }
}
