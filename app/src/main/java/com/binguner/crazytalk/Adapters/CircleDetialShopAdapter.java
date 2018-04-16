package com.binguner.crazytalk.Adapters;

import android.support.annotation.Nullable;

import com.binguner.crazytalk.Model.CircleDetialShopModel;
import com.binguner.crazytalk.ViewHolder.CircleDetialShopViewholder;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

public class CircleDetialShopAdapter extends BaseQuickAdapter<CircleDetialShopModel,CircleDetialShopViewholder> {

    public CircleDetialShopAdapter(int layoutResId, @Nullable List<CircleDetialShopModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(CircleDetialShopViewholder helper, CircleDetialShopModel item) {

    }
}
