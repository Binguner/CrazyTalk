package com.binguner.crazytalk.ViewHolder;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.binguner.crazytalk.R;
import com.chad.library.adapter.base.BaseViewHolder;

public class FriendCircleViewHolder extends BaseViewHolder {

    //public RecyclerView friend_cicle_recyclerview;
    public ImageView friend_circle_image1;
    public ImageView friend_circle_image2;
    public ImageView friend_circle_image3;
    public ImageView friend_circle_image4;
    public ImageView friend_circle_image5;
    public ImageView friend_circle_image6;
    public ConstraintLayout friend_circle_constrantlayout;

    public FriendCircleViewHolder(View view) {
        super(view);

        /*friend_circle_image1 = view.findViewById(R.id.friend_circle_image1);
        friend_circle_image2 = view.findViewById(R.id.friend_circle_image2);
        friend_circle_image3 = view.findViewById(R.id.friend_circle_image3);
        friend_circle_image4 = view.findViewById(R.id.friend_circle_image4);
        friend_circle_image5 = view.findViewById(R.id.friend_circle_image5);
        friend_circle_image6 = view.findViewById(R.id.friend_circle_image6);*/
        //friend_cicle_recyclerview = view.findViewById(R.id.friend_circle_recyclerview);
    }

}
