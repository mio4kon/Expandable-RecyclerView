package com.mio.example.expandablerv;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mio.example.expandablerv.adapter.ExampleAdapter;
import com.mio.example.expandablerv.bean.Data;
import com.mio.example.expandablerv.bean.ParentData;
import com.mio.expandablereclcerview.bean.ParentWrapper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<ParentWrapper<Data, Data>> parentDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ExampleAdapter myAdapter = new ExampleAdapter();
        recyclerView.setAdapter(myAdapter);

        fakeData();
        myAdapter.notifyDataSetChanged(parentDatas);
    }

    private void fakeData() {
        parentDatas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            //造20个父节点

            //随机造0-2个子节点
            int ranCount = i % 3;
            List<Data> children = new ArrayList<>();
            for (int j = 0; j < ranCount; j++) {
                children.add(new Data("child:" + j));
            }
            ParentData<Data, Data> parent = new ParentData<>(new Data("parent:" + i), children);
            parentDatas.add(parent);
        }
    }
}
