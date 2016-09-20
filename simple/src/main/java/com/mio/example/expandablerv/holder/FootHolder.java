package com.mio.example.expandablerv.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mio.example.expandablerv.R;
import com.mio.example.expandablerv.bean.Data;
import com.mio.example.expandablerv.model.FootModel;
import com.mio.expandablereclcerview.holder.IViewHolder;


/**
 * Created by mio4kon on 16/9/7.
 */
public class FootHolder extends RecyclerView.ViewHolder implements IViewHolder<FootModel> {


    private final TextView tvFoot;

    public FootHolder(View itemView) {
        super(itemView);
        tvFoot = (TextView) itemView.findViewById(R.id.tv_foot);

    }


    @Override
    public void bindView(FootModel viewModel) {

        Data data = viewModel.getData();
        tvFoot.setText(data.getDataFoot());
    }
}
