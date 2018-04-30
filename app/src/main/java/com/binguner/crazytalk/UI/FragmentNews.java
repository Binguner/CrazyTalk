package com.binguner.crazytalk.UI;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.binguner.crazytalk.Adapters.FriendsCircleAdapter;
import com.binguner.crazytalk.Listener.AppBarStateChangeListener;
import com.binguner.crazytalk.Model.FriendCircleModel;
import com.binguner.crazytalk.R;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FragmentNews extends Fragment {


    @BindView(R.id.fragment_news_recyclerview) RecyclerView fragment_news_recyclerview;
   // @BindView(R.id.fragment_news_head) ImageView fragment_news_head;
    private OnFragmentInteractionListener mListener;
    private static FragmentNews fragment;
    private LinearLayoutManager linearLayoutManager;
    private List<FriendCircleModel> list;
    private FriendsCircleAdapter adapter;
    private int lastIndexOfItem;

    public FragmentNews() {
        // Required empty public constructor
    }


    public synchronized static FragmentNews getInstance() {
        if(null == fragment){
            fragment = new FragmentNews();
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_news, container, false);
        ButterKnife.bind(this,view);
        setListener();
        initRecyclerview();
        return view;
    }

    @OnClick(R.id.fragment_news_goToTalk)
    public void goToTalk(View view){
        Intent intent = new Intent(getContext(),ConvercationActivity.class);
        startActivity(intent);
    }

    private void initRecyclerview() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        list = new ArrayList<>();
        for (int i = 0 ; i <=10 ; i++){
            FriendCircleModel model = new FriendCircleModel();
            list.add(model);
        }
        adapter = new FriendsCircleAdapter(getContext(),R.layout.freind_circle_crad_layout,list);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);

        View footView = LayoutInflater.from(getContext()).inflate(R.layout.foot_wave_view,null,false);
        adapter.addFooterView(footView);

        View headView = LayoutInflater.from(getContext()).inflate(R.layout.friend_circle_head_view,null,false);
        ImageView head_square = headView.findViewById(R.id.head_square);
        head_square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MyProfileActivity.class);
                startActivity(intent);
            }
        });
        adapter.addHeaderView(headView);

        fragment_news_recyclerview.setLayoutManager(linearLayoutManager);
        fragment_news_recyclerview.setAdapter(adapter);

        fragment_news_recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE && lastIndexOfItem + 3 >= linearLayoutManager.getItemCount()){
                    addNewsSeeds();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastIndexOfItem = linearLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    private void setListener(){

    }

    private void addNewsSeeds(){
        fragment_news_recyclerview.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(null != adapter){
                    for (int i = 0; i <= 10 ; i++){
                        adapter.addData(new FriendCircleModel());
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
