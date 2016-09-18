package com.mio.expandablereclcerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mio.expandablereclcerview.bean.ParentWrapper;
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

    protected List<ParentWrapper<P, C>> mItems;
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
        IViewModel viewModel = mViewModel.get(position);
        if (holder instanceof ParentViewHolder && viewModel instanceof IParentViewModel) {
            ((ParentViewHolder) holder).trigger(this);
            IParentViewModel parentViewModel = (IParentViewModel) viewModel;
            //刷新,滚动的时候调用bind后要设置一下之前展开,收缩的状态,状态存储在ParentWapper中
            ParentWrapper<P, C> parentWrapper = mItems.get(parentViewModel.getParentIndex());
            int childSize = parentWrapper.getChildren().size();
            ((ParentViewHolder) holder).initExpanded(parentWrapper.isExpanded(), childSize);
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

    public void notifyDataSetChanged(List<ParentWrapper<P, C>> parentWrappers) {
        mItems.clear();
        mViewModel.clear();
        mItems.addAll(parentWrappers);
        mViewModel.addAll(createParentModels(parentWrappers));
        notifyDataSetChanged();
    }

    @NonNull
    protected abstract RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType, LayoutInflater inflater);

    protected abstract Collection<? extends IViewModel> createParentModels(List<ParentWrapper<P, C>> parentWrappers);

    protected abstract Collection<? extends IViewModel> createChildModels(List<C> children);

    @Override
    public void expand(int position, boolean expanded) {
        IViewModel iViewModel = mViewModel.get(position);
        if (iViewModel instanceof IParentViewModel) {
            int parentIndex = ((IParentViewModel) iViewModel).getParentIndex();
            ParentWrapper<P, C> parentWrapper = mItems.get(parentIndex);
            if (expanded) {
                collapseViews(parentWrapper.getChildren(), position);
            } else {
                expandViews(parentWrapper.getChildren(), position);
            }
            parentWrapper.setExpanded(!expanded);
        }
    }


    private void expandViews(List<C> children, int expandPosition) {
        Collection<? extends IViewModel> childModels = createChildModels(children);
        mViewModel.addAll(expandPosition + 1, childModels);
        notifyItemRangeInserted(expandPosition + 1, childModels.size());
    }

    private void collapseViews(List<C> children, int collapsePosition) {
        Collection<? extends IViewModel> childModels = createChildModels(children);
        for (int i = childModels.size() - 1; i >= 0; i--) {
            mViewModel.remove(collapsePosition + i + 1);
        }
        notifyItemRangeRemoved(collapsePosition + 1, childModels.size());
    }

}
