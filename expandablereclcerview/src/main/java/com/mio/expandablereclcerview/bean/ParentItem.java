package com.mio.expandablereclcerview.bean;

import java.util.List;

/**
 * Created by mio4kon on 16/9/8.
 * 父节点
 * @param <P> parent Type
 * @param <C> child  Type
 */
public interface ParentItem<P, C> {

    List<C> getChildren();

    P getParent();

}
