package com.mio.example.expandablerv.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mio.example.expandablerv.R;
import com.mio.example.expandablerv.bean.Data;
import com.mio.example.expandablerv.model.HeadModel;
import com.mio.expandablereclcerview.holder.IViewHolder;


/**
 * Created by mio4kon on 16/9/7.
 */
public class HeadHolder extends RecyclerView.ViewHolder implements IViewHolder<HeadModel> {


    private final TextView tvHead;

    public HeadHolder(View itemView) {
        super(itemView);
        tvHead = (TextView) itemView.findViewById(R.id.tv_head);
    }


    @Override
    public void bindView(HeadModel viewModel) {
        Data data = viewModel.getData();
        tvHead.setText(data.getDataHead());
    }
}
