package com.binguner.crazytalk.UI;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binguner.crazytalk.Adapters.FriendActivityAdapter;
import com.binguner.crazytalk.Adapters.FriendsCircleAdapter;
import com.binguner.crazytalk.Model.FriendActivityModel;
import com.binguner.crazytalk.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.animation.BaseAnimation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FragmentActivity extends Fragment {


    @BindView(R.id.friend_activity_recyclerview) RecyclerView friend_activity_recyclerview;
    private OnFragmentInteractionListener mListener;
    private static FragmentActivity fragmentActivity;

    private FriendActivityAdapter adapter = null;
    private List<FriendActivityModel> list = null;
    private RecyclerView.LayoutManager layoutManager = null;

    public FragmentActivity() {
    }


    public synchronized static FragmentActivity getInstance() {
        if(fragmentActivity == null){
            fragmentActivity = new FragmentActivity();
        }
        return fragmentActivity;
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
        View view = inflater.inflate(R.layout.fragment_activity, container, false);
        ButterKnife.bind(this,view);
        initRecyclerView();
        return view;
    }

    @OnClick(R.id.friend_activity_gotoTalk)
    public void goToTalk(View view){
        Intent intent = new Intent(getContext(),ConvercationActivity.class);
        startActivity(intent);
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        for(int i = 0 ; i < 10 ; i++){
            list.add(new FriendActivityModel());
        }
        adapter = new FriendActivityAdapter(R.layout.friend_activity_card_layout,list);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);

        View mWaveView = LayoutInflater.from(getActivity()).inflate(R.layout.foot_wave_view,null,false);
        adapter.addFooterView(mWaveView);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                addFriendAtyeeds();
            }
        });

        friend_activity_recyclerview.setAdapter(adapter);
        friend_activity_recyclerview.setLayoutManager(layoutManager);

    }

    private void addFriendAtyeeds(){
        friend_activity_recyclerview.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(null !=  adapter){
                    for(int i = 0 ;  i < 15; i++){
                        list.add(new FriendActivityModel());
                    }
                }
                adapter.loadMoreComplete();
            }
        },1000);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
