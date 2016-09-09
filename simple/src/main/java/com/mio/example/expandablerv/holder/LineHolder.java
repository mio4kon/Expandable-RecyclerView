package com.mio.example.expandablerv.holder;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mio.example.expandablerv.R;
import com.mio.expandablereclcerview.holder.IViewHolder;
import com.mio.expandablereclcerview.model.IViewModel;

/**
 * Created by mio4kon on 16/9/7.
 */
public class LineHolder extends RecyclerView.ViewHolder implements IViewHolder {


    public LineHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.holder_line, parent, false));
    }

    @Override
    public void bindView(IViewModel viewModel) {

    }
}
