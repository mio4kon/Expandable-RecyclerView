package com.mio.example.expandablerv.model;


import com.mio.example.expandablerv.ViewType;
import com.mio.expandablereclcerview.model.IViewModel;

/**
 * Created by mio4kon on 16/9/7.
 */
public class HeadModel<T> implements IViewModel<T> {

    private T data;

    public HeadModel(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    @Override
    public int getViewType() {
        return ViewType.VIEW_TYPE_HEAD;
    }
}
