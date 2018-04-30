package com.binguner.crazytalk.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.binguner.crazytalk.Adapters.CircleChatAdapter;
import com.binguner.crazytalk.Model.CircleChatMessageModel;
import com.binguner.crazytalk.R;
import com.binguner.crazytalk.Utils.StatusBarUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TalkActivity extends AppCompatActivity {

    @BindView(R.id.talk_recyclerview) RecyclerView talk_recyclerview;
    @BindView(R.id.talk_edittext) EditText talk_edittext;
    @BindView(R.id.talk_send) Button talk_send;
    @BindView(R.id.talk_back) ImageView talk_back;
    @BindView(R.id.talk_more) ImageView talk_more;

    private LinearLayoutManager linearLayoutManager;
    private List<CircleChatMessageModel> list;
    private CircleChatAdapter adapter;
    private CircleChatMessageModel messageModel_l;
    private CircleChatMessageModel messageModel_r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_talk);
        ButterKnife.bind(this);
        StatusBarUtil.setStatusBarColor(this,R.color.colorWhite);
        StatusBarUtil.setStatusbarTextBlack(this);
        initViews();
    }

    private void initViews() {
        linearLayoutManager = new LinearLayoutManager(this);
        list = new ArrayList<>();

        adapter = new CircleChatAdapter(R.layout.circle_chat_message_layout,list);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);

        talk_recyclerview.setAdapter(adapter);
        talk_recyclerview.setLayoutManager(linearLayoutManager);
    }
    @OnClick(R.id.talk_more)
    public void gotoMore(View view){
        Intent intent = new Intent(this,CricleActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.talk_back)
    public void goBack(View view){
        this.finish();
    }
    @OnClick(R.id.talk_send)
    public void senfMessages(View view){
        final String msg = talk_edittext.getText().toString();
        if( null == msg || msg.equals("")){
            return;
        }
        messageModel_r = new CircleChatMessageModel(msg,CircleChatMessageModel.MESSAGE_RIGHT);
        talk_recyclerview.post(new Runnable() {
            @Override
            public void run() {
                if(null != adapter){
                    adapter.addData(messageModel_r);
                }
                adapter.loadMoreComplete();
                if(list.size() > -1) {
                    talk_recyclerview.smoothScrollToPosition(list.size()+1);
                    talk_edittext.setText("");
                }
            }
        });

        messageModel_l = new CircleChatMessageModel(msg,CircleChatMessageModel.MESSAGE_LEFT);
        talk_recyclerview.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(null != adapter){
                    adapter.addData(messageModel_l);
                }
                adapter.loadMoreComplete();
                if(list.size() > -1) {
                    talk_recyclerview.smoothScrollToPosition(list.size()+1);
                    talk_edittext.setText("");
                }
            }
        },500);
    }
}
