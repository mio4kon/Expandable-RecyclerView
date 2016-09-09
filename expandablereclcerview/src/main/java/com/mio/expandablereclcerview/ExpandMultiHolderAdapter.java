package com.mio.expandablereclcerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mio.expandablereclcerview.bean.ParentItem;
import com.mio.expandablereclcerview.holder.IViewHolder;
import com.mio.expandablereclcerview.holder.ParentViewHolder;
import com.mio.expandablereclcerview.model.IParentViewModel;
import com.mio.expandablereclcerview.model.IViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by mio4kon on 16/9/7.
 *
 * @param <P> parent Type
 * @param <C> child  Type
 */
public abstract class ExpandMultiHolderAdapter<P, C> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ParentViewHolder.ExpandController {

    protected List<ParentItem<P, C>> mItems;
    private List<IViewModel> mViewModel;

    public ExpandMultiHolderAdapter() {
        mItems = new ArrayList();
        mViewModel = new ArrayList<>();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return onCreateViewHolder(parent, viewType, inflater);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ParentViewHolder) {
            ((ParentViewHolder) holder).trigger(this);
        }
        IViewHolder iViewHolder = (IViewHolder) holder;
        iViewHolder.bindView(mViewModel.get(position));
    }

    @Override
    public int getItemCount() {
        return mViewModel.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mViewModel.get(position).getViewType();
    }

    public void notifyDataSetChanged(List<ParentItem<P, C>> parentItems) {
        mItems.clear();
        mViewModel.clear();
        mItems.addAll(parentItems);
        mViewModel.addAll(createParentModels(parentItems));
    }

    @NonNull
    protected abstract RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType, LayoutInflater inflater);

    protected abstract Collection<? extends IViewModel> createParentModels(List<ParentItem<P, C>> parentItems);

    protected abstract Collection<? extends IViewModel> createChildModels(List<C> children);

    @Override
    public void expand(int position, boolean expanded) {
        IViewModel iViewModel = mViewModel.get(position);
        if (iViewModel instanceof IParentViewModel) {
            int parentIndex = ((IParentViewModel) iViewModel).getParentIndex();
            ParentItem<P, C> parentItem = mItems.get(parentIndex);
            if (expanded) {
                collapseViews(parentItem.getChildren(), position);
            } else {
                expandViews(parentItem.getChildren(), position);
            }
        }
    }


    private void expandViews(List<C> children, int expandPosition) {
        Log.d("mio4kon", "点击展开View :" + expandPosition);
        Collection<? extends IViewModel> childModels = createChildModels(children);
        mViewModel.addAll(expandPosition + 1, childModels);
        notifyItemRangeInserted(expandPosition + 1, childModels.size());
    }

    private void collapseViews(List<C> children, int collapsePosition) {
        Log.d("mio4kon", "点击收缩View :" + collapsePosition);
        Collection<? extends IViewModel> childModels = createChildModels(children);
        for (int i = childModels.size() - 1; i >= 0; i--) {
            mViewModel.remove(collapsePosition + i + 1);
        }
        notifyItemRangeRemoved(collapsePosition + 1, childModels.size());
    }

}
