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

import com.binguner.crazytalk.Adapters.FriendsCircleAdapter;
import com.binguner.crazytalk.CustomView.WaveView;
import com.binguner.crazytalk.Model.FriendCircleModel;
import com.binguner.crazytalk.R;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentFriendCircle extends Fragment {

    private OnFragmentInteractionListener mListener;
    private static FragmentFriendCircle fragmentFriendCircle;
    private RecyclerView.LayoutManager layoutManager;
    List<FriendCircleModel> list = null;
    FriendsCircleAdapter adapter = null;

    @BindView(R.id.friend_circle_recyclerview) RecyclerView friend_circle_recyclerview;

    public FragmentFriendCircle() {
        // Required empty public constructor
    }


    public synchronized static FragmentFriendCircle getInstance() {
        if(fragmentFriendCircle == null){
            fragmentFriendCircle = new FragmentFriendCircle();
        }
        return fragmentFriendCircle;
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
        View view = inflater.inflate(R.layout.fragment_friend_circle, container, false);
        ButterKnife.bind(this,view);
        initRecyclerview();
        return view;
    }

    private void initRecyclerview() {
        layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        list = new ArrayList<>();
        for(int i = 0 ; i < 10 ;i++){
            FriendCircleModel model = new FriendCircleModel();
            list.add(model);
        }
        adapter = new FriendsCircleAdapter(getContext(),R.layout.freind_circle_crad_layout,list);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        // 上拉加载

        View mWaveView = LayoutInflater.from(getActivity()).inflate(R.layout.foot_wave_view,null,false);
        adapter.addFooterView(mWaveView);
        friend_circle_recyclerview.setItemViewCacheSize(0);
        friend_circle_recyclerview.setAdapter(adapter);
        friend_circle_recyclerview.setLayoutManager(layoutManager);

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                Log.d("ffTag","Down");
                addNewSeeds();
            }
        });
    }

    private void addNewSeeds(){
        friend_circle_recyclerview.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (null != adapter){
                    for(int i = 0 ; i< 15; i++){
                        list.add(new FriendCircleModel());
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
