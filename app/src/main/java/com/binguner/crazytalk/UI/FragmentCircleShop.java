package com.binguner.crazytalk.UI;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binguner.crazytalk.Adapters.CircleDetialShopAdapter;
import com.binguner.crazytalk.Model.CircleDetialShopModel;
import com.binguner.crazytalk.R;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentCircleShop extends Fragment {

    @BindView(R.id.circle_shop_recyclerview) RecyclerView circle_shop_recyclerview;
    private OnFragmentInteractionListener mListener;
    private static FragmentCircleShop fragmentCircleShop;

    private CircleDetialShopAdapter adapter;
    private List<CircleDetialShopModel> list;
    private LinearLayoutManager linearLayoutManager;
    private int lastItemPosition;

    public FragmentCircleShop() {
        // Required empty public constructor
    }

    public synchronized static FragmentCircleShop getInstance() {
        if(null == fragmentCircleShop){
            fragmentCircleShop = new FragmentCircleShop();
        }
        return fragmentCircleShop;
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
        View view = inflater.inflate(R.layout.fragment_fragment_circle_shop, container, false);
        ButterKnife.bind(this,view);
        initViews();
        return view;
    }

    private void initViews() {
        View footView = LayoutInflater.from(getContext()).inflate(R.layout.foot_wave_view,null,false);
        list = new ArrayList<>();
        for(int i = 0 ; i < 15 ;i ++){
            list.add(new CircleDetialShopModel());
        }
        linearLayoutManager = new LinearLayoutManager(getContext());
        adapter = new CircleDetialShopAdapter(R.layout.circle_shop_card_layout,list);
        adapter.addFooterView(footView);

        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);

        circle_shop_recyclerview.setAdapter(adapter);
        circle_shop_recyclerview.setLayoutManager(linearLayoutManager);

        circle_shop_recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE && lastItemPosition + 3 >= linearLayoutManager.getItemCount()){
                    addNewSeeds();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastItemPosition = linearLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    private void addNewSeeds(){
        circle_shop_recyclerview.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(null != adapter){
                    for(int i = 0 ; i < 15 ;i ++){
                        adapter.addData(new CircleDetialShopModel());
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
