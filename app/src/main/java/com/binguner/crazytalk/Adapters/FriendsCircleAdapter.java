package com.binguner.crazytalk.Adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.binguner.crazytalk.Model.FriendCircleModel;
import com.binguner.crazytalk.ViewHolder.FriendCircleViewHolder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class FriendsCircleAdapter extends BaseQuickAdapter<FriendCircleModel,FriendCircleViewHolder> {


    public FriendsCircleAdapter(int layoutResId, @Nullable List<FriendCircleModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(FriendCircleViewHolder helper, FriendCircleModel item) {
        int positon = helper.getLayoutPosition();

    }
}
