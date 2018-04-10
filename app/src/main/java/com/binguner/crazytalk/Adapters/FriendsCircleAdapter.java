package com.binguner.crazytalk.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.binguner.crazytalk.Model.FriendCircleModel;
import com.binguner.crazytalk.R;
import com.binguner.crazytalk.ViewHolder.FriendCircleViewHolder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class FriendsCircleAdapter extends BaseQuickAdapter<FriendCircleModel,FriendCircleViewHolder> {


    private Context context;
    public FriendsCircleAdapter(Context context,int layoutResId, @Nullable List<FriendCircleModel> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(FriendCircleViewHolder helper, FriendCircleModel item) {
      /*  helper.setIsRecyclable(true);
        helper.friend_circle_image1.setImageResource(R.mipmap.head1);
        helper.friend_circle_image2.setImageResource(R.mipmap.head1);
        helper.friend_circle_image3.setImageResource(R.mipmap.head1);
        helper.friend_circle_image4.setImageResource(R.mipmap.head1);
        helper.friend_circle_image5.setImageResource(R.mipmap.head1);
        helper.friend_circle_image6.setImageResource(R.mipmap.head1);

        helper.friend_circle_image1.setImageDrawable(context.getResources().getDrawable(R.mipmap.head1));
        helper.friend_circle_image2.setImageDrawable(context.getResources().getDrawable(R.mipmap.head2));
        helper.friend_circle_image3.setImageDrawable(context.getResources().getDrawable(R.mipmap.head3));
        helper.friend_circle_image4.setImageDrawable(context.getResources().getDrawable(R.mipmap.head4));
        helper.friend_circle_image5.setImageDrawable(context.getResources().getDrawable(R.mipmap.head1));
        helper.friend_circle_image6.setImageDrawable(context.getResources().getDrawable(R.mipmap.head1));*/
        int positon = helper.getLayoutPosition();

    }


}
