package com.mio.expandablereclcerview;

import android.content.Context;
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
    /**
     * whole model mapping the size
     */
    private List<Integer> mViewModelSizeMapping;
    protected Context mContext;

    public ExpandMultiHolderAdapter() {
        mItems = new ArrayList();
        mViewModel = new ArrayList<>();
        mViewModelSizeMapping = new ArrayList();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return onCreateViewHolder(parent, viewType, inflater);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        IViewModel viewModel = mViewModel.get(position);
        if (holder instanceof ParentViewHolder && viewModel instanceof IParentViewModel) {
            ((ParentViewHolder) holder).trigger(this);
            IParentViewModel parentViewModel = (IParentViewModel) viewModel;
            //when refresh,scrolling,must init expanded state which save in parentWrapper
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
        mViewModelSizeMapping.clear();
        mItems.addAll(parentWrappers);
        for (int i = 0; i < parentWrappers.size(); i++) {
            List<? extends IViewModel> parentModels = createParentModels(parentWrappers.get(i), i);
            mViewModel.addAll(parentModels);
            mViewModelSizeMapping.add(parentModels.size());
        }
        notifyDataSetChanged();
    }

    @NonNull
    protected abstract RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType, LayoutInflater inflater);

    protected abstract List<? extends IViewModel> createParentModels(ParentWrapper<P, C> parentWrapper, int parentIndex);

    protected abstract List<? extends IViewModel> createChildModels(List<C> children);

    @Override
    public void expand(int position, boolean expanded) {
        IViewModel iViewModel = mViewModel.get(position);
        if (iViewModel instanceof IParentViewModel) {
            int parentIndex = ((IParentViewModel) iViewModel).getParentIndex();
            ParentWrapper<P, C> parentWrapper = mItems.get(parentIndex);
            //set expanded postion which model will be expanded
            parentWrapper.setExpandedPositionInModel(position);
            if (expanded) {
                collapseViews(parentWrapper.getChildren(), position, parentIndex);
            } else {
                expandViews(parentWrapper.getChildren(), position, parentIndex);
            }
            //save expanded state
            parentWrapper.setExpanded(!expanded);
        }
    }


    private void expandViews(List<C> children, int expandPosition, int parentIndex) {
        Collection<? extends IViewModel> childModels = createChildModels(children);
        mViewModel.addAll(expandPosition + 1, childModels);
        int orgSize = mViewModelSizeMapping.get(parentIndex);
        mViewModelSizeMapping.set(parentIndex, orgSize + children.size());
        notifyItemRangeInserted(expandPosition + 1, childModels.size());
    }

    private void collapseViews(List<C> children, int collapsePosition, int parentIndex) {
        Collection<? extends IViewModel> childModels = createChildModels(children);
        int orgSize = mViewModelSizeMapping.get(parentIndex);
        for (int i = childModels.size() - 1; i >= 0; i--) {
            orgSize--;
            mViewModel.remove(collapsePosition + i + 1);
        }
        mViewModelSizeMapping.set(parentIndex, orgSize);
        notifyItemRangeRemoved(collapsePosition + 1, childModels.size());
    }

    public void removeItem(int parentIndex) {
        ParentWrapper<P, C> removedParentWrapper = mItems.get(parentIndex);
        int parentModelSize = createParentModels(removedParentWrapper, parentIndex).size();
        int childModelSize = removedParentWrapper.isExpanded() ? createChildModels(removedParentWrapper.getChildren()).size() : 0;
        int lenth = parentModelSize + childModelSize;
        int sumSize = 0;
        for (int i = 0; i < parentIndex; i++) {
            sumSize += mViewModelSizeMapping.get(i);
        }
        int temp = sumSize;
        for (int i = 0; i < lenth; i++) {
            mViewModel.remove(temp + i);
            temp--;
        }
        mItems.remove(parentIndex);
        mViewModelSizeMapping.remove(parentIndex);
        mViewModel.clear();
        for (int i = 0; i < mItems.size(); i++) {
            List<? extends IViewModel> parentModels = createParentModels(mItems.get(i), i);
            mViewModel.addAll(parentModels);
            if (mItems.get(i).isExpanded()) {
                mViewModel.addAll(mItems.get(i).getExpandedPositionInModel() + 1, createChildModels(mItems.get(i).getChildren()));
            }
        }
        notifyItemRangeRemoved(sumSize, lenth);

    }

}
