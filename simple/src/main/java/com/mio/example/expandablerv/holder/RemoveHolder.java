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
    private OnRemoveListener mRemoveListener;

    public RemoveHolder(View itemView, OnRemoveListener removeListener) {
        super(itemView);
        btRemove = (Button) itemView.findViewById(R.id.bt_remove);
        mRemoveListener = removeListener;
    }


    @Override
    public void bindView(RemoveModel viewModel) {
        Data data = viewModel.getData();
        btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRemoveListener != null) {
                    mRemoveListener.remove(getAdapterPosition());
                }
            }
        });

    }

    public interface OnRemoveListener {
        void remove(int adapterPosition);
    }

}
