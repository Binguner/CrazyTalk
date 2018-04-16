package com.binguner.crazytalk.Adapters;

import android.support.annotation.Nullable;

import com.binguner.crazytalk.Model.CiecleDetialActivityModel;
import com.binguner.crazytalk.ViewHolder.CiecleDetialActivityViewholder;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

public class CiecleDetialActivityAdapter extends BaseQuickAdapter<CiecleDetialActivityModel,CiecleDetialActivityViewholder> {

    public CiecleDetialActivityAdapter(int layoutResId, @Nullable List<CiecleDetialActivityModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(CiecleDetialActivityViewholder helper, CiecleDetialActivityModel item) {

    }
}
