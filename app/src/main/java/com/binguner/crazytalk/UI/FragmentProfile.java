package com.binguner.crazytalk.UI;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.binguner.crazytalk.Adapters.FriendsCircleAdapter;
import com.binguner.crazytalk.Listener.AppBarStateChangeListener;
import com.binguner.crazytalk.Model.FriendCircleModel;
import com.binguner.crazytalk.R;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FragmentProfile extends Fragment {

    private OnFragmentInteractionListener mListener;
    private static FragmentProfile fragmentProfile;
    @BindView(R.id.main_activity_collapsingToolbarLayout) CollapsingToolbarLayout main_activity_collapsingToolbarLayout;
    @BindView(R.id.friend_profile_appbarlayou) AppBarLayout friend_profile_appbarlayou;
    @BindView(R.id.friend_profile_recyclerview) RecyclerView friend_profile_recyclerview;
    //@BindView(R.id.friend_profile_avator) CircleImageView friend_profile_avator;

    private int lastItemPosition;
    private List<FriendCircleModel> list = null;
    //private RecyclerView.LayoutManager layoutManager = null;
    private LinearLayoutManager layoutManager = null;
    private FriendsCircleAdapter adapter = null;

    public FragmentProfile() {
        // Required empty public constructor
    }


    public static synchronized FragmentProfile getInstance() {
        if(fragmentProfile == null){
            fragmentProfile = new FragmentProfile();
        }
        return fragmentProfile;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_profile,container,false);
        ButterKnife.bind(this,view);
        //setListener();
        initRecyclerView();
        initViews();
        return view;
    }

    private void initRecyclerView() {
        layoutManager = new LinearLayoutManager(getContext());
        list = new ArrayList<>();
        for (int i = 0 ; i <= 15 ; i++){
            FriendCircleModel mode = new FriendCircleModel();
            list.add(mode);
        }
        adapter = new FriendsCircleAdapter(getContext(),R.layout.freind_circle_crad_layout,list);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);

        View mWaveView = LayoutInflater.from(getContext()).inflate(R.layout.foot_wave_view,null,false);
        //layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        adapter.addFooterView(mWaveView);

        friend_profile_recyclerview.setAdapter(adapter);
        friend_profile_recyclerview.setLayoutManager(layoutManager);
        /*adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                addNewDatas();
            }
        });*/
//        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//                addNewSeeds();
//            }
//        },friend_profile_recyclerview);

       friend_profile_recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE && lastItemPosition+3 >= layoutManager.getItemCount()){
                    addNewSeeds();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastItemPosition = layoutManager.findLastVisibleItemPosition();
            }
        });
    }

    private void addNewSeeds(){
        friend_profile_recyclerview.postDelayed(new Runnable() {
            @Override
            public void run() {
                if( null != adapter){
                    for(int i = 0 ; i < 10 ; i++){
                        adapter.addData(new FriendCircleModel());
                    }
                }
                adapter.loadMoreComplete();
            }
        },500);
    }

    private void setListener() {
        friend_profile_appbarlayou.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.EXPANDED){
                    // 展开
                   // friend_profile_avator.setVisibility(View.VISIBLE);

                }
                if(state == State.IDLE){
                    // 中间
                    Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.animation_to_be_smail);
                    //friend_profile_avator.startAnimation(animation);
                }
                if(state == State.COLLAPSED){
                    // 折叠
                    //friend_profile_avator.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void initViews() {
        //main_activity_collapsingToolbarLayout.setlisten
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
