package com.mio.example.expandablerv.model;


import com.mio.example.expandablerv.ViewType;
import com.mio.example.expandablerv.bean.Data;
import com.mio.expandablereclcerview.model.IViewModel;

/**
 * Created by mio4kon on 16/9/7.
 */
public class FootModel implements IViewModel<Data> {


    private Data data;

    public FootModel(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }


    @Override
    public int getViewType() {
        return ViewType.VIEW_TYPE_FOOT;
    }
}
