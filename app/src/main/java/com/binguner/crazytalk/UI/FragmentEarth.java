package com.binguner.crazytalk.UI;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.binguner.crazytalk.CallbackIntetface.EarthViewPagerChangeListener;
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
    @BindView(R.id.fragment_earth_mycircle) ImageView fragment_earth_mycircle;
    @BindView(R.id.fragment_earth_gototalk) ImageView fragment_earth_gototalk;

    Button pop_search_circle_choose_age_btn1,pop_search_circle_choose_age_btn2,pop_search_circle_choose_place_btn1,pop_search_circle_choose_place_btn2,
            pop_search_circle_choose_hobby_btn1,pop_search_circle_choose_hobby_btn2;

    String[] hobbyArray = {};

    EarthViewPagerChangeListener listener;

    List<Fragment> fragmentList;
    String [] titles = {"兴趣星"};

    Context context;

    FragmentSquare fragmentSquare;
    //FragmentFriendCircle fragmentFriendCircle;
    //FragmentActivity fragmentActivity;

    TabFragmentPagerAdapter tabFragmentPagerAdapter;

    public FragmentEarth() {
        tryToGetContext();
    }

    private void tryToGetContext() {
        this.context = getActivity();
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
        //fragmentFriendCircle = FragmentFriendCircle.getInstance();
        //fragmentActivity = FragmentActivity.getInstance();

        //fragmentList.add(fragmentFriendCircle);
        fragmentList.add(fragmentSquare);
        //fragmentList.add(fragmentActivity);

        tabFragmentPagerAdapter = new TabFragmentPagerAdapter(getActivity().getSupportFragmentManager(),fragmentList);
        fragment_earth_viewPager.setAdapter(tabFragmentPagerAdapter);
        fragment_earth_tabLayout.setViewPager(fragment_earth_viewPager,titles);

        fragment_earth_viewPager.setCurrentItem(1);
        fragment_earth_viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(null != listener) {
                    listener.onPageSelected(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void addViewPagerChangeListener(EarthViewPagerChangeListener listener ){
        this.listener = listener;
        //listener.onPageSelected(positon);
    }

    @OnClick(R.id.fragment_earth_search)
    public void fragment_earth_search_click(View view){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.pop_search_circle_layout,null,false);
        final EditText pop_search_circle_edittext = view1.findViewById(R.id.pop_search_circle_edittext);
        pop_search_circle_choose_age_btn1 = view1.findViewById(R.id.pop_search_circle_choose_age_btn1);
        pop_search_circle_choose_age_btn2 = view1.findViewById(R.id.pop_search_circle_choose_age_btn2);
        pop_search_circle_choose_place_btn1 = view1.findViewById(R.id.pop_search_circle_choose_place_btn1);
        pop_search_circle_choose_place_btn2 = view1.findViewById(R.id.pop_search_circle_choose_place_btn2);
        pop_search_circle_choose_hobby_btn1 = view1.findViewById(R.id.pop_search_circle_choose_hobby_btn1);
        pop_search_circle_choose_hobby_btn2 = view1.findViewById(R.id.pop_search_circle_choose_hobby_btn2);
        Button pop_search_circle_choose_age_place_hobby_restart = view1.findViewById(R.id.pop_search_circle_choose_age_place_hobby_restart);
        Button pop_search_circle_choose_age_place_hobby_ok = view1.findViewById(R.id.pop_search_circle_choose_age_place_hobby_ok);

        builder.setView(view1);
        AlertDialog dialog = builder.show();
        final String [] placeArray = getResources().getStringArray(R.array.placeArray);
        final String [] hobbyArray = getResources().getStringArray(R.array.hobbyArray);
        pop_search_circle_choose_place_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChooseDialog(pop_search_circle_choose_place_btn2,"请选择一个地区",placeArray);
            }
        });

        pop_search_circle_choose_place_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChooseDialog(pop_search_circle_choose_place_btn2,"请选择一个地区",placeArray);

            }
        });

        pop_search_circle_choose_hobby_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChooseDialog(pop_search_circle_choose_hobby_btn2,"请选择一个兴趣",hobbyArray);

            }
        });

        pop_search_circle_choose_hobby_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChooseDialog(pop_search_circle_choose_hobby_btn2,"请选择一个兴趣",hobbyArray);

            }
        });

        pop_search_circle_choose_age_place_hobby_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop_search_circle_choose_age_btn2.setText("不限");
                pop_search_circle_choose_place_btn2.setText("不限");
                pop_search_circle_choose_hobby_btn2.setText("不限");
                pop_search_circle_edittext.setText("");
            }
        });

        pop_search_circle_choose_age_place_hobby_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"暂时搜索不到这个圈子的信息",Toast.LENGTH_SHORT).show();
            }
        });

        pop_search_circle_choose_age_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"功能正在开发",Toast.LENGTH_SHORT).show();
            }
        });



    }

    @OnClick(R.id.fragment_earth_mycircle)
    public void goToMyCircle(View view){
        Intent intent = new Intent(getContext(),CircleSettingActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.fragment_earth_gototalk)
    public void goToTalk(View view){
        Intent intent = new Intent(getContext(),ConvercationActivity.class);
        startActivity(intent);
    }

    /**
     * @param button
     * @param title
     * @param arrays
     */
    private void showChooseDialog(final Button button, final String title, final String[] arrays){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(title);
                builder.setItems(arrays, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        button.setText(arrays[which]);
                    }
                });
                builder.show();
            }
        });
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

