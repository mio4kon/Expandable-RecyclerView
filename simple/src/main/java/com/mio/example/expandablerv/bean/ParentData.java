package com.mio.example.expandablerv.bean;


import com.mio.expandablereclcerview.bean.ParentItem;

import java.util.List;

/**
 * Created by mio4kon on 16/9/8
 *
 * @param <P> parent Type
 * @param <C> child  Type
 */
public class ParentData<P, C> implements ParentItem<P, C> {

    private List<C> mChildDatas;

    private P mParentData;

    public ParentData(P parentData) {
        mParentData = parentData;
    }

    public ParentData(P parentData, List<C> childDatas) {
        mParentData = parentData;
        mChildDatas = childDatas;
    }

    public void setChildren(List<C> childDatas) {
        mChildDatas = childDatas;
    }

    @Override
    public List<C> getChildren() {
        return mChildDatas;
    }

    @Override
    public P getParent() {
        return mParentData;
    }
}
