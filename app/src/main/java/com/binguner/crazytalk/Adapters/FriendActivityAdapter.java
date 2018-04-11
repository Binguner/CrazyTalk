package com.binguner.crazytalk.Adapters;

import android.support.annotation.Nullable;

import com.binguner.crazytalk.Model.FriendActivityModel;
import com.binguner.crazytalk.ViewHolder.FriendActivityViewHolder;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

public class FriendActivityAdapter extends BaseQuickAdapter<FriendActivityModel,FriendActivityViewHolder> {

    public FriendActivityAdapter(int layoutResId, @Nullable List<FriendActivityModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(FriendActivityViewHolder helper, FriendActivityModel item) {

    }
}
