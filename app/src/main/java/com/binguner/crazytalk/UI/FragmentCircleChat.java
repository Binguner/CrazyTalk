package com.binguner.crazytalk.UI;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.binguner.crazytalk.Adapters.CircleChatAdapter;
import com.binguner.crazytalk.Model.CircleChatMessageModel;
import com.binguner.crazytalk.R;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentCircleChat extends Fragment {

    private OnFragmentInteractionListener mListener;
    private static FragmentCircleChat fragmentCircleChat;

    @BindView(R.id.circle_chat_recyclerview) RecyclerView circle_chat_recyclerview;
    @BindView(R.id.circle_chat_edittext) EditText circle_chat_edittext;
    @BindView(R.id.circle_chat_send) Button circle_chat_send;

    private LinearLayoutManager linearLayoutManager;
    private List<CircleChatMessageModel> list;
    private CircleChatAdapter adapter;
    private CircleChatMessageModel messageModel_l;
    private CircleChatMessageModel messageModel_r;

    public FragmentCircleChat() {
        // Required empty public constructor
    }

    public synchronized static FragmentCircleChat getInstance() {
        if(null == fragmentCircleChat){
            fragmentCircleChat = new FragmentCircleChat();
        }
        return fragmentCircleChat;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_circle_chat, container, false);
        ButterKnife.bind(this,view);
        setListener();
        initViews();

        return view;
    }

    private void setListener() {
        //messageModel = new M
    }


    private void initViews() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        list = new ArrayList<>();

        adapter = new CircleChatAdapter(R.layout.circle_chat_message_layout,list);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);

        circle_chat_recyclerview.setAdapter(adapter);
        circle_chat_recyclerview.setLayoutManager(linearLayoutManager);

    }

    @OnClick(R.id.circle_chat_send)
    public void sendMessage(View view){
        final String msg = circle_chat_edittext.getText().toString();
        messageModel_r = new CircleChatMessageModel(msg,CircleChatMessageModel.MESSAGE_RIGHT);
        circle_chat_recyclerview.post(new Runnable() {
            @Override
            public void run() {
                if(null != adapter){
                    adapter.addData(messageModel_r);
                }
                adapter.loadMoreComplete();
                if(list.size() > -1) {
                    circle_chat_recyclerview.smoothScrollToPosition(list.size()+1);
                }
            }
        });

        messageModel_l = new CircleChatMessageModel(msg,CircleChatMessageModel.MESSAGE_LEFT);
        circle_chat_recyclerview.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(null != adapter){
                    adapter.addData(messageModel_l);
                }
                adapter.loadMoreComplete();
                if(list.size() > -1) {
                    circle_chat_recyclerview.smoothScrollToPosition(list.size()+1);
                }
            }
        },500);

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
