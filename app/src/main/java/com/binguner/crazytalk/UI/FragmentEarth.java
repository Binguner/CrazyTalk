package com.binguner.crazytalk.UI;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.binguner.crazytalk.R;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentEarth extends Fragment {

    private static FragmentEarth fragmentEarth;

    @BindView(R.id.fragment_earth_toolbar) android.support.v7.widget.Toolbar fragment_earth_toolbar;
    @BindView(R.id.fragment_earth_search) ImageView fragment_earth_search;
    @BindView(R.id.fragment_earth_tabLayout) SlidingTabLayout fragment_earth_tabLayout;
    @BindView(R.id.fragment_earth_viewPager) ViewPager fragment_earth_viewPager;

    List<Fragment> fragmentList;
    String [] titles = {"动态","兴趣星","活动"};

    FragmentSquare fragmentSquare;
    FragmentFriendCircle fragmentFriendCircle;
    FragmentActivity fragmentActivity;

    TabFragmentPagerAdapter tabFragmentPagerAdapter;

    public FragmentEarth() {

    }


    public synchronized static FragmentEarth getInstance() {
        if(fragmentEarth == null){
            fragmentEarth = new FragmentEarth();
        }
        return fragmentEarth;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_earth,container,false);
        ButterKnife.bind(this,view);
        initViews();

        return view;
    }

    private void initViews() {
        fragmentList = new ArrayList<>();

        fragmentSquare = FragmentSquare.getInstance();
        fragmentFriendCircle = FragmentFriendCircle.getInstance();
        fragmentActivity = FragmentActivity.getInstance();

        fragmentList.add(fragmentFriendCircle);
        fragmentList.add(fragmentSquare);
        fragmentList.add(fragmentActivity);

        tabFragmentPagerAdapter = new TabFragmentPagerAdapter(getActivity().getSupportFragmentManager(),fragmentList);
        fragment_earth_viewPager.setAdapter(tabFragmentPagerAdapter);
        fragment_earth_tabLayout.setViewPager(fragment_earth_viewPager,titles);
    }

    @OnClick(R.id.fragment_earth_search)
    public void fragment_earth_search_click(View view){
        Toast.makeText(getActivity(),"Search",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}

class TabFragmentPagerAdapter extends FragmentPagerAdapter{

    private FragmentManager fragmentManager;
    private List<Fragment> fragments;

    public TabFragmentPagerAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        this.fragmentManager = fm;
        this.fragments = list;
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

