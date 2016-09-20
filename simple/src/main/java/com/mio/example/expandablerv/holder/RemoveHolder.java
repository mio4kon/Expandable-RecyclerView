package com.mio.example.expandablerv.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mio.example.expandablerv.R;
import com.mio.example.expandablerv.bean.Data;
import com.mio.example.expandablerv.model.FootModel;
import com.mio.example.expandablerv.model.RemoveModel;
import com.mio.expandablereclcerview.holder.IViewHolder;


/**
 * Created by mio4kon on 16/9/7.
 */
public class RemoveHolder extends RecyclerView.ViewHolder implements IViewHolder<RemoveModel> {


    private final Button btRemove;
    private View.OnClickListener onClickListener;

    public RemoveHolder(View itemView, View.OnClickListener onClickListener) {
        super(itemView);
        btRemove = (Button) itemView.findViewById(R.id.bt_remove);
        this.onClickListener = onClickListener;
    }


    @Override
    public void bindView(RemoveModel viewModel) {
        Data data = viewModel.getData();
        btRemove.setOnClickListener(onClickListener);
    }



}
