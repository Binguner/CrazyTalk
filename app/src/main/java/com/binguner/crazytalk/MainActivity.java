package com.binguner.crazytalk;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.binguner.crazytalk.CallbackIntetface.EarthViewPagerChangeListener;
import com.binguner.crazytalk.UI.FragmentEarth;
import com.binguner.crazytalk.UI.FragmentNews;
import com.binguner.crazytalk.UI.FragmentProfile;
import com.binguner.crazytalk.UI.PlusActivity;
import com.binguner.crazytalk.UI.PlusHelpActivity;
import com.binguner.crazytalk.Utils.StatusBarUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity {

    //@BindView(R.id.main_aty_toolbar) Toolbar main_aty_toolbar;
    /*@BindView(R.id.main_aty_choose_area) TextView main_aty_choose_area;
    @BindView(R.id.main_aty_choose_arrow) ImageView main_aty_choose_arrow;*/
    //@BindView(R.id.main_aty_search) ImageView main_aty_search;
    @BindView(R.id.main_fragment) FrameLayout main_fragment;

    @BindView(R.id.main_btn_square) ImageButton main_btn_square;
    @BindView(R.id.main_btn_near_activity) ImageButton main_btn_near_activity;
    @BindView(R.id.main_btn_profile) ImageButton main_btn_profile;
    @BindView(R.id.main_btn_new_post) ImageButton main_btn_new_post;
    @BindView(R.id.main_btn_add) ImageButton main_btn_add;

    Fragment temp;
    FragmentEarth fragmentEarth;    // 这里面有「动态」「兴趣星」「活动」
    FragmentProfile fragmentProfile;    // 这是 个人页面
    FragmentNews fragmentNews;      // 这是 「动态」
    com.binguner.crazytalk.UI.FragmentActivity fragmentActivity; //这是「附近活动」

    android.support.v4.app.FragmentManager fragmentManager;
    private int currentItemInViewPager = 1;

    PopupMenu popupMenu;
    //<!--d39011b068bb64fa0aba982cac6a938f-->
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        hideActionbarAndTransparentStatusbar();
        initViews();
        setListener();
        //getSupportFragmentManager()
    }

    private void setListener() {
        fragmentEarth.addViewPagerChangeListener(new EarthViewPagerChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if(position == 0 || position == 2){
                    //main_btn_add.setImageResource(R.drawable.ic_add_circle_black_36dp);
                    //main_btn_profile.setImageResource(R.drawable.ic_account_circle_black_36dp);
                    //main_btn_square.setImageResource(R.drawable.ic_earth_black_36dp);
                    //currentItemInViewPager = position;
                }else {
                    //main_btn_add.setImageResource(R.drawable.ic_add_circle_white_36dp);
                    //main_btn_profile.setImageResource(R.drawable.ic_account_circle_white_36dp);
                    //main_btn_square.setImageResource(R.drawable.ic_earth_white_36dp);
                    //currentItemInViewPager = position;
                }
            }
        });
    }

    private void initViews() {
        fragmentEarth = FragmentEarth.getInstance();
        fragmentProfile = FragmentProfile.getInstance();
        fragmentActivity = com.binguner.crazytalk.UI.FragmentActivity.getInstance();
        fragmentNews = FragmentNews.getInstance();
        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.main_fragment,fragmentEarth).commit();
        temp = fragmentEarth;
    }

    // 设置状态栏和隐藏 ActionBar
    private void hideActionbarAndTransparentStatusbar() {
        // 隐藏 ActionBar
        //getSupportActionBar().hide();
        //getActionBar().hide();
        // 透明化状态栏
        StatusBarUtil.transparentStatusbar(this);
        // 设置状态栏颜色
        StatusBarUtil.setStatusBarColor(this,R.color.colorToolbar);
        // 设置状态栏字体颜色
        StatusBarUtil.setStatusbarTextBlack(this);
    }

    @OnClick(R.id.main_btn_add)
    public void goToCreateActivity(View view){
        //Intent intent = new Intent(this, PlusActivity.class);
        //startActivity(intent);
        goToStartAtyOrHelp();
    }

    @OnClick(R.id.main_btn_square)
    public void gotoSquareClick(View view){
        /*List<Fragment> list = fragmentManager.getFragments();
        for(Fragment f:list){
            Log.d("rara",f.toString());
        }*/
        if(currentItemInViewPager == 1){
            //main_btn_add.setImageResource(R.drawable.ic_add_circle_white_36dp);
            //main_btn_profile.setImageResource(R.drawable.ic_account_circle_white_36dp);
            //main_btn_square.setImageResource(R.drawable.ic_earth_white_36dp);
        }
        main_btn_square.setSelected(true);
        main_btn_new_post.setSelected(false);
        main_btn_add.setSelected(false);
        main_btn_near_activity.setSelected(false);
        main_btn_profile.setSelected(false);
        switchFragment(fragmentEarth);
    }

    @OnClick(R.id.main_btn_profile)
    public void gotoProfile(View view){
        //main_btn_add.setImageResource(R.drawable.ic_add_circle_black_36dp);
        //main_btn_profile.setImageResource(R.drawable.ic_account_circle_black_36dp);
        //main_btn_square.setImageResource(R.drawable.ic_earth_black_36dp);
        main_btn_square.setSelected(false);
        main_btn_add.setSelected(false);
        main_btn_near_activity.setSelected(false);
        main_btn_profile.setSelected(true);
        main_btn_new_post.setSelected(false);
        switchFragment(fragmentProfile);
    }

    @OnClick(R.id.main_btn_near_activity)
    public void fotoNearActivity(View view) {
        main_btn_square.setSelected(false);
        main_btn_add.setSelected(false);
        main_btn_near_activity.setSelected(true);
        main_btn_new_post.setSelected(false);
        main_btn_profile.setSelected(false);
        switchFragment(fragmentActivity);
    }

    @OnClick(R.id.main_btn_new_post)
    public void goToNews(View view){
        main_btn_square.setSelected(false);
        main_btn_add.setSelected(false);
        main_btn_near_activity.setSelected(false);
        main_btn_new_post.setSelected(true);
        main_btn_profile.setSelected(false);
        switchFragment(fragmentNews);
    }

    private void switchFragment(Fragment fragment){
        if(fragment != temp){
            if(!fragment.isAdded()){
                getSupportFragmentManager().beginTransaction().hide(temp).add(R.id.main_fragment,fragment).commit();
            }else {
                getSupportFragmentManager().beginTransaction().hide(temp).show(fragment).commit();
            }
            temp = fragment;
        }
    }


    private void goToStartAtyOrHelp(){
        View view = LayoutInflater.from(this).inflate(R.layout.pop_add_layout,null,false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        final AlertDialog dialog = builder.create();

        WindowManager windowManager = this.getWindowManager();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();

        int width = windowManager.getDefaultDisplay().getWidth();
        params.width = (width/2 + 200);
        dialog.getWindow().setAttributes(params);

        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.Theme_AppCompat_Dialog_Alert);

        dialog.show();

        Button pop_add_aty = view.findViewById(R.id.pop_add_aty);
        Button pop_add_help = view.findViewById(R.id.pop_add_help);

        pop_add_aty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PlusActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        pop_add_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PlusHelpActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

    }


    public void chooseArea(View view){
        Toast.makeText(this,"Choose area click!",Toast.LENGTH_SHORT).show();
        Context wrapper = new ContextThemeWrapper(this,R.style.MenuTextStyle);
        popupMenu = new PopupMenu(wrapper,view);
        //popupMenu.setGravity();
        MenuInflater inflater = popupMenu.getMenuInflater();

        inflater.inflate(R.menu.main_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.main_square:
                        Toast.makeText(MainActivity.this,"Square",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.main_friendCircle:
                        Toast.makeText(MainActivity.this,"FriendCircle",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.main_activity:
                        Toast.makeText(MainActivity.this,"Activity",Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }


}
