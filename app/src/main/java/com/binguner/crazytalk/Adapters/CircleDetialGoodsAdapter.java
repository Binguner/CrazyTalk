package com.binguner.crazytalk.Adapters;

import android.support.annotation.Nullable;

import com.binguner.crazytalk.Model.CircleDetialGoodsModel;
import com.binguner.crazytalk.ViewHolder.CircleDetialGoodsViewholder;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

public class CircleDetialGoodsAdapter extends BaseQuickAdapter<CircleDetialGoodsModel,CircleDetialGoodsViewholder> {

    public CircleDetialGoodsAdapter(int layoutResId, @Nullable List<CircleDetialGoodsModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(CircleDetialGoodsViewholder helper, CircleDetialGoodsModel item) {

    }
}
