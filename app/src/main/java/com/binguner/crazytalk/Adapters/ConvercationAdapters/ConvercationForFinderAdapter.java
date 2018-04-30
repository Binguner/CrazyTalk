package com.binguner.crazytalk.Adapters.ConvercationAdapters;

import android.support.annotation.Nullable;

import com.binguner.crazytalk.Model.ConvercationModel.ConvercationOneListModel;
import com.binguner.crazytalk.ViewHolder.ConvercationForFinderViewHolder;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

public class ConvercationForFinderAdapter extends BaseQuickAdapter<ConvercationOneListModel,ConvercationForFinderViewHolder> {

    public ConvercationForFinderAdapter(int layoutResId, @Nullable List<ConvercationOneListModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ConvercationForFinderViewHolder helper, ConvercationOneListModel item) {

    }
}
