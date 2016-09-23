package com.mio.example.expandablerv.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mio.example.expandablerv.R;
import com.mio.example.expandablerv.ViewType;
import com.mio.example.expandablerv.bean.Data;
import com.mio.example.expandablerv.holder.ClickHolder;
import com.mio.example.expandablerv.holder.FootHolder;
import com.mio.example.expandablerv.holder.HeadHolder;
import com.mio.example.expandablerv.holder.LineHolder;
import com.mio.example.expandablerv.holder.MiddleHolder;
import com.mio.example.expandablerv.holder.RemoveHolder;
import com.mio.expandablereclcerview.ExpandMultiHolderAdapter;
import com.mio.expandablereclcerview.bean.ParentWrapper;
import com.mio.expandablereclcerview.model.IViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mio4kon on 16/9/22.
 */
public class BiliBiliAdapter extends ExpandMultiHolderAdapter<Data, Data> implements RemoveHolder.OnRemoveListener {

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType, LayoutInflater inflater) {
        View itemView;
        switch (viewType) {
            case ViewType.VIEW_TYPE_HEAD:
                itemView = inflater.inflate(R.layout.holder_head, parent, false);
                return new HeadHolder(itemView);
            case ViewType.VIEW_TYPE_MIDDEL:
                itemView = inflater.inflate(R.layout.holder_middle, parent, false);
                return new MiddleHolder(itemView);
            case ViewType.VIEW_TYPE_FOOT:
                itemView = inflater.inflate(R.layout.holder_foot, parent, false);
                return new FootHolder(itemView);
            case ViewType.VIEW_TYPE_CLICK:
                itemView = inflater.inflate(R.layout.holder_click, parent, false);
                return new ClickHolder(itemView);
            case ViewType.VIEW_TYPE_REMOVE:
                itemView = inflater.inflate(R.layout.holder_remove, parent, false);
                return new RemoveHolder(itemView, this);
            default:
                return new LineHolder(inflater, parent);
        }
    }

    @Override
    protected List<? extends IViewModel> createParentModels(ParentWrapper<Data, Data> parentWrapper, int parentIndex) {
        List<IViewModel> parentModels = new ArrayList<>();

        return parentModels;
    }

    @Override
    protected List<? extends IViewModel> createChildModels(List<Data> children) {
        List<IViewModel> childModels = new ArrayList<>();

        return childModels;
    }



    @Override
    public void remove(int adapterPosition) {
        removeItemByAdapterIndex(adapterPosition);
    }
}