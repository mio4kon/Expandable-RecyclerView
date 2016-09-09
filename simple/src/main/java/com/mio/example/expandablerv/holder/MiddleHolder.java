package com.mio.example.expandablerv.holder;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mio.example.expandablerv.R;
import com.mio.example.expandablerv.bean.Data;
import com.mio.example.expandablerv.model.MiddelModel;
import com.mio.expandablereclcerview.holder.IViewHolder;

/**
 * Created by mio4kon on 16/9/7.
 */
public class MiddleHolder extends RecyclerView.ViewHolder implements IViewHolder<MiddelModel<Data>> {

    private TextView tvMiddle;

    public MiddleHolder(View itemView) {
        super(itemView);
        tvMiddle = (TextView) itemView.findViewById(R.id.tv_middle);
    }


    @Override
    public void bindView(MiddelModel<Data> viewModel) {
        Data data = viewModel.getData();
        tvMiddle.setText(data.getDataMiddle());

    }
}
