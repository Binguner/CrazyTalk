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

import com.binguner.crazytalk.Adapters.CircleDetialGoodsAdapter;
import com.binguner.crazytalk.Model.CircleDetialGoodsModel;
import com.binguner.crazytalk.R;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentCircleGoods extends Fragment {

    @BindView(R.id.circle_goods_recyclerview) RecyclerView circle_goods_recyclerview;

    private OnFragmentInteractionListener mListener;
    private static FragmentCircleGoods fragmentCircleGoods;

    private CircleDetialGoodsAdapter adapter;
    private List<CircleDetialGoodsModel> list;
    private LinearLayoutManager linearLayoutManager;
    private int lastItemIndex;

    public FragmentCircleGoods() {
        // Required empty public constructor
    }

    public synchronized static FragmentCircleGoods getInstance() {
        if(null == fragmentCircleGoods){
            fragmentCircleGoods = new FragmentCircleGoods();
        }
        return fragmentCircleGoods;
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
        View view = inflater.inflate(R.layout.fragment_fragment_circle_goods, container, false);
        ButterKnife.bind(this,view);
        initViews();
        return view;
    }

    private void initViews() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        list = new ArrayList<>();
        for( int i = 0 ; i < 15; i++){
            list.add(new CircleDetialGoodsModel());
        }
        adapter = new CircleDetialGoodsAdapter(R.layout.circle_goods_card_layout,list);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        View footView = LayoutInflater.from(getContext()).inflate(R.layout.foot_wave_view,null,false);
        adapter.addFooterView(footView);

        circle_goods_recyclerview.setAdapter(adapter);
        circle_goods_recyclerview.setLayoutManager(linearLayoutManager);

        circle_goods_recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE && lastItemIndex +3 >= linearLayoutManager.getItemCount()){
                    addNewSeeds();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastItemIndex = linearLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    private void  addNewSeeds(){
        circle_goods_recyclerview.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(null != adapter){
                    for(int i = 0 ; i < 15 ; i++ ){
                        adapter.addData(new CircleDetialGoodsModel());
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
