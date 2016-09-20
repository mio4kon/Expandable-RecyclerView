package com.mio.example.expandablerv.model;


import com.mio.example.expandablerv.ViewType;
import com.mio.example.expandablerv.bean.Data;
import com.mio.expandablereclcerview.model.IParentViewModel;
import com.mio.expandablereclcerview.model.IViewModel;

/**
 * Created by mio4kon on 16/9/7.
 */
public class ClickModel implements IParentViewModel, IViewModel<Data> {

    private Data mData;
    private int mParentIndex;

    public ClickModel(Data data, int parentIndex) {
        this.mData = data;
        this.mParentIndex = parentIndex;
    }

    public Data getData() {
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
