package com.binguner.crazytalk.Adapters;

import android.support.annotation.Nullable;
import android.view.View;

import com.binguner.crazytalk.Model.CircleChatMessageModel;
import com.binguner.crazytalk.ViewHolder.CircleChatViewholder;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

public class CircleChatAdapter extends BaseQuickAdapter<CircleChatMessageModel,CircleChatViewholder> {

    public CircleChatAdapter(int layoutResId, @Nullable List<CircleChatMessageModel> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(CircleChatViewholder helper, CircleChatMessageModel item) {
        if(null != helper && null != item) {
            if (item.getType() == CircleChatMessageModel.MESSAGE_LEFT) {
                helper.circle_chat_right_message.setVisibility(View.GONE);
                helper.circle_chat_left_message.setVisibility(View.VISIBLE);
                helper.circle_chat_left_message.setText(item.getMsg());
            } else if (item.getType() == CircleChatMessageModel.MESSAGE_RIGHT) {
                helper.circle_chat_right_message.setVisibility(View.VISIBLE);
                helper.circle_chat_left_message.setVisibility(View.INVISIBLE);
                helper.circle_chat_right_message.setText(item.getMsg());
            }
        }
    }


}
