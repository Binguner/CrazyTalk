package com.binguner.crazytalk.UI;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.binguner.crazytalk.Adapters.FriendsCircleAdapter;
import com.binguner.crazytalk.Listener.AppBarStateChangeListener;
import com.binguner.crazytalk.R;
import com.binguner.crazytalk.Utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CricleActivity extends AppCompatActivity {

    //@BindView(R.id.circle_collapsing_toolbar_layout) CollapsingToolbarLayout circle_collapsing_toolbar_layout;
    //@BindView(R.id.circle_appbar_Layout) AppBarLayout circle_appbar_Layout;
    @BindView(R.id.circle_table_layout) TabLayout circle_table_layout;
    @BindView(R.id.circle_name) TextView circle_name;
    @BindView(R.id.circle_toolbar_setting) ImageView circle_toolbar_setting;
    @BindView(R.id.circle_viewpager) ViewPager circle_viewpager;

    private List<Fragment> fragments;
    private String[] titles = {"商城", "精品", "聊天","活动"};
    private CircleFragmentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cricle);
        ButterKnife.bind(this);
        StatusBarUtil.setStatusBarColor(this,R.color.colorWhite);
        StatusBarUtil.setStatusbarTextBlack(this);
        init();
        setListener();
    }

    private void init() {
        fragments = new ArrayList<>();
        fragments.add(FragmentCircleShop.getInstance());
        fragments.add(FragmentCircleGoods.getInstance());
        fragments.add(FragmentCircleChat.getInstance());
        fragments.add(FragmentCircleActivity.getInstance());

        adapter = new CircleFragmentAdapter(getSupportFragmentManager());
        circle_viewpager.setAdapter(adapter);
        circle_viewpager.setCurrentItem(3);
        circle_table_layout.setupWithViewPager(circle_viewpager);
    }

    private void setListener() {
//        circle_appbar_Layout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
//            @Override
//            public void onStateChanged(AppBarLayout appBarLayout, State state) {
//                if (state == State.EXPANDED){
//                    // 展开
//                    circle_name.setVisibility(View.INVISIBLE);
//                    circle_toolbar_setting.setVisibility(View.INVISIBLE);
//                }
//                if(state == State.IDLE){
//                    // 中间
//                    circle_name.setVisibility(View.VISIBLE);
//                    circle_toolbar_setting.setVisibility(View.VISIBLE);
//                }
//                if(state == State.COLLAPSED){
//                    // 折叠
//                    circle_name.setVisibility(View.VISIBLE);
//                    circle_toolbar_setting.setVisibility(View.VISIBLE);
//                }
//            }
//        });
    }

    @OnClick(R.id.circle_back)
    public void goBack(View view){
        this.finish();
    }


    class CircleFragmentAdapter extends FragmentPagerAdapter{

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        public CircleFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}


