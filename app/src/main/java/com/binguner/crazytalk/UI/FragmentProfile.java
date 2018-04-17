package com.binguner.crazytalk.UI;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
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


public class FragmentProfile extends Fragment {

    private OnFragmentInteractionListener mListener;
    private static FragmentProfile fragmentProfile;
    @BindView(R.id.main_activity_collapsingToolbarLayout) CollapsingToolbarLayout main_activity_collapsingToolbarLayout;
    @BindView(R.id.friend_profile_appbarlayou) AppBarLayout friend_profile_appbarlayou;
    @BindView(R.id.friend_profile_recyclerview) RecyclerView friend_profile_recyclerview;
    @BindView(R.id.profile_setting_btn) ImageView profile_setting_btn;
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
        setListener();
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
                    profile_setting_btn.setVisibility(View.INVISIBLE);
                }
                if(state == State.IDLE){
                    // 中间
                    Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.animation_to_be_smail);
                    profile_setting_btn.setVisibility(View.INVISIBLE);
                    //friend_profile_avator.startAnimation(animation);
                }
                if(state == State.COLLAPSED){
                    // 折叠
                    //friend_profile_avator.setVisibility(View.INVISIBLE);
                    profile_setting_btn.setVisibility(View.VISIBLE);

                }
            }
        });
    }

    private void initViews() {
        //main_activity_collapsingToolbarLayout.setlisten
    }

    @OnClick(R.id.profile_setting_btn)
    public void profile_setting_onClick(View view){

        View view1 = LayoutInflater.from(getContext()).inflate(R.layout.pop_profile_setting_layout,null,false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view1);
        final AlertDialog dialog = builder.create();

        WindowManager windowManager = getActivity().getWindowManager();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        dialog.show();

        int width = windowManager.getDefaultDisplay().getWidth();
        params.width = (width/2 + 200);
        dialog.getWindow().setAttributes(params);

        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.Theme_AppCompat_Dialog_Alert);

        Button pop_profile_setting_person = view1.findViewById(R.id.pop_profile_setting_person);
        pop_profile_setting_person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),UserSettingActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        Button pop_profile_setting_circle = view1.findViewById(R.id.pop_profile_setting_circle);
        pop_profile_setting_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),CircleSettingActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        Button pop_profile_setting_system = view1.findViewById(R.id.pop_profile_setting_system);
        pop_profile_setting_system.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),SystemSettingActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

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
