package com.mio.example.expandablerv.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mio.example.expandablerv.R;
import com.mio.example.expandablerv.ViewType;
import com.mio.example.expandablerv.holder.ClickHolder;
import com.mio.example.expandablerv.holder.FootHolder;
import com.mio.example.expandablerv.holder.HeadHolder;
import com.mio.example.expandablerv.holder.LineHolder;
import com.mio.example.expandablerv.holder.MiddleHolder;
import com.mio.example.expandablerv.model.ClickModel;
import com.mio.example.expandablerv.model.FootModel;
import com.mio.example.expandablerv.model.HeadModel;
import com.mio.example.expandablerv.model.LineModel;
import com.mio.example.expandablerv.model.MiddelModel;
import com.mio.expandablereclcerview.ExpandMultiHolderAdapter;
import com.mio.expandablereclcerview.model.IViewModel;
import com.mio.expandablereclcerview.bean.ParentWrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by mio4kon on 16/9/9.
 */
public class ExampleAdapter<P, C> extends ExpandMultiHolderAdapter<P, C> {


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
            default:
                return new LineHolder(inflater, parent);
        }
    }

    @Override
    protected Collection<? extends IViewModel> createParentModels(List<ParentWrapper<P, C>> parentWrappers) {
        List<IViewModel> parentModels = new ArrayList<>();
        for (int i = 0; i < parentWrappers.size(); i++) {
            P parent = parentWrappers.get(i).getParent();
            parentModels.add(new HeadModel(parent));
            parentModels.add(new MiddelModel(parent));
            parentModels.add(new FootModel(parent));
            if (parentWrappers.get(i).getChildren().size() > 0) {
                parentModels.add(new ClickModel(parent, i));
            }
            parentModels.add(new LineModel(parent));
        }
        return parentModels;
    }

    @Override
    protected Collection<? extends IViewModel> createChildModels(List<C> children) {
        List<IViewModel> childModels = new ArrayList<>();
        for (int i = 0; i < children.size(); i++) {
            //不同的child可能有不同的Model或不同的组合方式
            if (i == 0) {
                childModels.add(new HeadModel(children.get(i)));
                childModels.add(new FootModel(children.get(i)));
            } else {
                childModels.add(new HeadModel(children.get(i)));
                childModels.add(new MiddelModel(children.get(i)));
                childModels.add(new FootModel(children.get(i)));
            }
        }
        return childModels;
    }
}
