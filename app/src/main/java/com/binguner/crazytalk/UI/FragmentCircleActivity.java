package com.binguner.crazytalk.UI;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binguner.crazytalk.Adapters.CiecleDetialActivityAdapter;
import com.binguner.crazytalk.Model.CiecleDetialActivityModel;
import com.binguner.crazytalk.R;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentCircleActivity extends Fragment {


    @BindView(R.id.circle_activity_recyclerview) RecyclerView circle_activity_recyclerview;
    private OnFragmentInteractionListener mListener;
    private static FragmentCircleActivity fragmentCircleActivity;

    private CiecleDetialActivityAdapter adapter;
    private List<CiecleDetialActivityModel> list;
    private LinearLayoutManager linearLayoutManager;
    private int lastItemIndex;

    public FragmentCircleActivity() {
        // Required empty public constructor
    }

    public synchronized static FragmentCircleActivity getInstance() {
        if(null == fragmentCircleActivity){
            fragmentCircleActivity = new FragmentCircleActivity();
        }
        return fragmentCircleActivity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_circle, container, false);
        ButterKnife.bind(this,view);
        initViews();
        return view;
    }

    private void initViews() {
        list = new ArrayList<>();
        for ( int i = 0 ; i < 15 ;i ++){
            list.add(new CiecleDetialActivityModel());
        }
        View footView = LayoutInflater.from(getContext()).inflate(R.layout.foot_wave_view,null,false);
        linearLayoutManager = new LinearLayoutManager(getContext());
        adapter = new CiecleDetialActivityAdapter(R.layout.friend_activity_card_layout,list);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        adapter.addFooterView(footView);

        circle_activity_recyclerview.setAdapter(adapter);
        circle_activity_recyclerview.setLayoutManager(linearLayoutManager);
        circle_activity_recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

    private void addNewSeeds() {
        circle_activity_recyclerview.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(adapter != null){
                    for( int i = 0 ; i < 15 ; i++){
                        adapter.addData(new CiecleDetialActivityModel());
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
        void onFragmentInteraction(Uri uri);
    }
}
