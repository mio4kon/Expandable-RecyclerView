package com.mio.expandablereclcerview.bean;

import java.util.List;

/**
 * Created by mio4kon on 16/9/8.
 * 父节点
 * @param <P> parent Type
 * @param <C> child  Type
 */
public class ParentWrapper<P, C> {

    private List<C> mOrderChildren;

    private P mOrderParent;

    private boolean mExpanded;

    public ParentWrapper(P orderParent, List<C> orderChildren) {
        mOrderParent = orderParent;
        mOrderChildren = orderChildren;
    }


    public List<C> getChildren() {
        return mOrderChildren;
    }

    public P getParent() {
        return mOrderParent;
    }


    public boolean isExpanded() {
        return mExpanded;
    }

    public void setExpanded(boolean expanded) {
        mExpanded = expanded;
    }

}
