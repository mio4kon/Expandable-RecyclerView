package com.mio.expandablereclcerview.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * Created by mio4kon on 16/9/9.
 */
public abstract class ParentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    private View mViewTrigger;
    private ExpandController mExpandController;
    private boolean mExpanded;
    private int mChildSize;

    public ParentViewHolder(View itemView) {
        super(itemView);
        mExpanded = false;
    }


    public void trigger(ExpandController expandController) {
        mViewTrigger = getViewTrigger();
        mExpandController = expandController;
        mViewTrigger.setOnClickListener(this);
    }

    public void initExpanded(boolean expanded, int childSize) {
        mExpanded = expanded;
        mChildSize = childSize;
        onExpandChange(expanded, childSize);
    }

    public abstract View getViewTrigger();

    public abstract void onExpandChange(boolean expanded,int childSize);

    @Override
    public void onClick(View view) {
        //点击展开View,显示展开View的事情是Adapter做的
        if (mExpandController != null) {
            mExpandController.expand(getAdapterPosition(), mExpanded);
            mExpanded = !mExpanded;
            onExpandChange(mExpanded,mChildSize);
        }
    }


    public interface ExpandController {
        void expand(int expandPosition, boolean expanded);
    }
}
