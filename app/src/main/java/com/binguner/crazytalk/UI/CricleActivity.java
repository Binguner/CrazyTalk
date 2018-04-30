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
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    @BindView(R.id.circle_toolbar) Toolbar circle_toolbar;
    @BindView(R.id.circle_name) TextView circle_name;
    //@BindView(R.id.circle_toolbar_setting) ImageView circle_toolbar_setting;
    @BindView(R.id.circle_viewpager) ViewPager circle_viewpager;
    @BindView(R.id.circle_toolbar_ed) EditText circle_toolbar_ed;

    private List<Fragment> fragments;
    private String[] titles = {"商城", "精品", "发现","活动"};
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
        //fragments.add(FragmentCircleChat.getInstance());
        fragments.add(FragmentCircleFinder.getInstance());
        fragments.add(FragmentCircleActivity.getInstance());
        setSupportActionBar(circle_toolbar);
        circle_toolbar.setTitleTextColor(getResources().getColor(R.color.colorTransparent));
        adapter = new CircleFragmentAdapter(getSupportFragmentManager());
        circle_viewpager.setAdapter(adapter);
        circle_viewpager.setCurrentItem(3);
        circle_table_layout.setupWithViewPager(circle_viewpager);
        circle_viewpager.setOffscreenPageLimit(4);
    }

    private void setListener() {
        circle_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CricleActivity.this.finish();
            }
        });
        circle_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 2){
                    circle_toolbar_ed.setVisibility(View.VISIBLE);
                    circle_name.setVisibility(View.GONE);
                }else {
                    circle_toolbar_ed.setVisibility(View.GONE);
                    circle_name.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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

    //@OnClick(R.id.circle_back)
    //public void goBack(View view){
    //    this.finish();
    //}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Toast.makeText(this,"Search",Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
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


