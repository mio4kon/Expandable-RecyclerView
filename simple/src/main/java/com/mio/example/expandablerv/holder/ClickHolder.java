package com.mio.example.expandablerv.holder;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mio.example.expandablerv.R;
import com.mio.expandablereclcerview.holder.IViewHolder;
import com.mio.expandablereclcerview.model.IViewModel;
import com.mio.expandablereclcerview.holder.ParentViewHolder;


/**
 * Created by mio4kon on 16/9/7.
 */
public class ClickHolder extends ParentViewHolder implements IViewHolder {

    private final RelativeLayout relativeLayout;
    private final TextView tvExpend;

    public ClickHolder(View itemView) {
        super(itemView);
        relativeLayout = (RelativeLayout) itemView.findViewById(R.id.rl);
        tvExpend = (TextView) relativeLayout.findViewById(R.id.tv_expend);
    }

    @Override
    public void bindView(IViewModel viewModel) {

    }


    @Override
    public View getViewTrigger() {
        return relativeLayout;
    }

    @Override
    public void onExpandChange(boolean expanded) {
        tvExpend.setText(expanded ? "收起" : "展开");
    }


}
