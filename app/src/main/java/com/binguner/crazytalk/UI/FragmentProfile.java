package com.binguner.crazytalk.UI;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
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
import android.widget.Toolbar;

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

    @BindView(R.id.my_page_talk_btn) ImageView my_page_talk_btn;
    @BindView(R.id.my_page_collection_btn) Button my_page_collection_btn;
    @BindView(R.id.my_page_money_btn) Button my_page_money_btn;
    @BindView(R.id.my_page_shopping_car_btn) Button my_page_shopping_car_btn;
    @BindView(R.id.my_page_vip_btn) Button my_page_vip_btn;
    @BindView(R.id.my_page_setting_btn) Button my_page_setting_btn;

    @BindView(R.id.my_page_cardview1) CardView my_page_cardview1;
    private OnFragmentInteractionListener mListener;
    private static FragmentProfile fragmentProfile;

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
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_my_page_layout,container,false);
        ButterKnife.bind(this,view);
        setListener();
        initViews();
        return view;
    }


    private void setListener() {
    }

    @OnClick(R.id.my_page_cardview1)
    public void goToPersonSetting(View view){
        Intent intent = new Intent(getContext(),UserSettingActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.my_page_talk_btn)
    public void foToTalk(View view){
        //Toast.makeText(getContext(),"Talk",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(),ConvercationActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.my_page_collection_btn)
    public void goToCollection(View view){
        Toast.makeText(getContext(),"Collection",Toast.LENGTH_SHORT).show();
    }
    @OnClick(R.id.my_page_money_btn)
    public void goToMoney(View view){
        Toast.makeText(getContext(),"Money",Toast.LENGTH_SHORT).show();
    }
    @OnClick(R.id.my_page_shopping_car_btn)
    public void goToShoppingCar(View view){
        Toast.makeText(getContext(),"shopping_car",Toast.LENGTH_SHORT).show();
    }
    @OnClick(R.id.my_page_vip_btn)
    public void goToVip(View view){
        Toast.makeText(getContext(),"Vip",Toast.LENGTH_SHORT).show();
    }
    @OnClick(R.id.my_page_setting_btn)
    public void goToSetting(View view){
        //Toast.makeText(getContext(),"Setting",Toast.LENGTH_SHORT).show();
        profile_setting_onClick(view);
    }

    private void initViews() {
        //main_activity_collapsingToolbarLayout.setlisten
    }

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
