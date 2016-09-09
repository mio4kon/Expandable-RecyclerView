package com.mio.example.expandablerv.model;


import com.mio.example.expandablerv.ViewType;
import com.mio.expandablereclcerview.model.IParentViewModel;
import com.mio.expandablereclcerview.model.IViewModel;

/**
 * Created by mio4kon on 16/9/7.
 */
public class ClickModel<T> implements IParentViewModel, IViewModel<T> {

    private T mData;
    private int mParentIndex;

    public ClickModel(T data, int parentIndex) {
        this.mData = data;
        this.mParentIndex = parentIndex;
    }

    public T getData() {
        return mData;
    }

    @Override
    public int getViewType() {
        return ViewType.VIEW_TYPE_CLICK;
    }

    @Override
    public int getParentIndex() {
        return mParentIndex;
    }
}
