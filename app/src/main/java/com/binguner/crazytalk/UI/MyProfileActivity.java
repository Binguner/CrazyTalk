package com.binguner.crazytalk.UI;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.binguner.crazytalk.Adapters.FriendsCircleAdapter;
import com.binguner.crazytalk.Listener.AppBarStateChangeListener;
import com.binguner.crazytalk.Model.FriendCircleModel;
import com.binguner.crazytalk.R;
import com.binguner.crazytalk.Utils.StatusBarUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyProfileActivity extends AppCompatActivity {

    @BindView(R.id.friend_profile_appbarlayou) AppBarLayout friend_profile_appbarlayou;
    @BindView(R.id.friend_profile_recyclerview) RecyclerView friend_profile_recyclerview;
    @BindView(R.id.profile_back_btn) ImageView profile_back_btn;
    private int lastItemPosition;
    private List<FriendCircleModel> list = null;
    //private RecyclerView.LayoutManager layoutManager = null;
    private LinearLayoutManager layoutManager = null;
    private FriendsCircleAdapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_profile);
        ButterKnife.bind(this);
        StatusBarUtil.setStatusbarTextBlack(this);
        StatusBarUtil.setStatusBarColor(this,R.color.colorWhite);
        initRecyclerView();
        setListener();
    }

    private void setListener() {
        friend_profile_appbarlayou.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if(state == State.IDLE){
                    profile_back_btn.setVisibility(View.INVISIBLE);
                }else if(state == State.EXPANDED){
                    profile_back_btn.setVisibility(View.INVISIBLE);
                }else if(state == State.COLLAPSED){
                    profile_back_btn.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @OnClick(R.id.profile_back_btn)
    public void goBack(View view){
        this.finish();
    }

    private void initRecyclerView() {
        layoutManager = new LinearLayoutManager(this);
        list = new ArrayList<>();
        for (int i = 0 ; i <= 15 ; i++){
            FriendCircleModel mode = new FriendCircleModel();
            list.add(mode);
        }
        adapter = new FriendsCircleAdapter(this,R.layout.freind_circle_crad_layout,list);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);

        View mWaveView = LayoutInflater.from(this).inflate(R.layout.foot_wave_view,null,false);
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
}
