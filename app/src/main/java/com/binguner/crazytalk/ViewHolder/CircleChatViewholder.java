package com.binguner.crazytalk.ViewHolder;

import android.view.View;
import android.widget.TextView;

import com.binguner.crazytalk.R;
import com.chad.library.adapter.base.BaseViewHolder;

import de.hdodenhof.circleimageview.CircleImageView;

public class CircleChatViewholder extends BaseViewHolder {

    public TextView circle_chat_left_message;
    public TextView circle_chat_right_message;
    public CircleImageView circle_chat_avator;
    public CircleChatViewholder(View view) {
        super(view);
        circle_chat_left_message = view.findViewById(R.id.circle_chat_left_message);
        circle_chat_right_message = view.findViewById(R.id.circle_chat_right_message);
        circle_chat_avator = view.findViewById(R.id.circle_chat_avator);
    }
}
