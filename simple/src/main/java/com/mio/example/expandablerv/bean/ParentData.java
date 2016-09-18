package com.mio.example.expandablerv.bean;


import com.mio.expandablereclcerview.bean.ParentWrapper;

import java.util.List;

/**
 * Created by mio4kon on 16/9/8
 *
 * @param <P> parent Type
 * @param <C> child  Type
 */
public class ParentData<P, C> extends ParentWrapper<P, C> {

    public ParentData(P parentData, List<C> childDatas) {
        super(parentData, childDatas);
    }

}
