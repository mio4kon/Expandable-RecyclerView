package com.mio.expandablereclcerview.holder;


import com.mio.expandablereclcerview.model.IViewModel;

/**
 * Created by mio4kon on 16/9/7.
 */
public interface IViewHolder<T extends IViewModel> {
    void bindView(T viewModel);
}
