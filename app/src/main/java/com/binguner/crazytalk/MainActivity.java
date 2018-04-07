package com.binguner.crazytalk;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.binguner.crazytalk.UI.FragmentEarth;
import com.binguner.crazytalk.UI.FragmentProfile;
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
    @BindView(R.id.main_btn_square) ImageView main_btn_square;
    @BindView(R.id.main_btn_profile) ImageView main_btn_profile;

    Fragment temp;
    FragmentEarth fragmentEarth;
    FragmentProfile fragmentProfile;
    android.support.v4.app.FragmentManager fragmentManager;

    PopupMenu popupMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        hideActionbarAndTransparentStatusbar();
        initViews();
        //getSupportFragmentManager()
    }

    private void initViews() {
        fragmentEarth = FragmentEarth.getInstance();
        fragmentProfile = FragmentProfile.getInstance();
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


    @OnClick(R.id.main_btn_square)
    public void gotoSquareClick(View view){
        List<Fragment> list = fragmentManager.getFragments();
        for(Fragment f:list){
            Log.d("rara",f.toString());
        }
        switchFragment(fragmentEarth);
    }
    @OnClick(R.id.main_btn_profile)
    public void gotoProfile(View view){
        switchFragment(fragmentProfile);
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
