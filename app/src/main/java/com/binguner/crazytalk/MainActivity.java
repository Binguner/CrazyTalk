package com.binguner.crazytalk;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.binguner.crazytalk.UI.FragmentEarth;
import com.binguner.crazytalk.UI.FragmentProfile;
import com.binguner.crazytalk.Utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    //@BindView(R.id.main_aty_toolbar) Toolbar main_aty_toolbar;
    /*@BindView(R.id.main_aty_choose_area) TextView main_aty_choose_area;
    @BindView(R.id.main_aty_choose_arrow) ImageView main_aty_choose_arrow;*/
    //@BindView(R.id.main_aty_search) ImageView main_aty_search;
    @BindView(R.id.main_fragment) FrameLayout main_fragment;
    @BindView(R.id.main_btn_square) Button main_btn_square;
    @BindView(R.id.main_btn_profile) Button main_btn_profile;

    FragmentEarth fragmentEarth;
    FragmentProfile fragmentProfile;
    FragmentManager fragmentManager;

    PopupMenu popupMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        hideActionbarAndTransparentStatusbar();
        initViews();
    }

    private void initViews() {
        fragmentEarth = FragmentEarth.getInstance();
        fragmentProfile = FragmentProfile.getInstance();
        fragmentManager = getFragmentManager();

    }

    // 设置状态栏和隐藏 ActionBar
    private void hideActionbarAndTransparentStatusbar() {
        // 隐藏 ActionBar
        getSupportActionBar().hide();
        // 透明化状态栏
        StatusBarUtil.transparentStatusbar(this);
        // 设置状态栏颜色
        StatusBarUtil.setStatusBarColor(this,R.color.colorToolbar);
        // 设置状态栏字体颜色
        StatusBarUtil.setStatusbarTextBlack(this);
    }

    /*@OnClick(R.id.main_aty_search)
    public void search_click(){
        Toast.makeText(this,"search_click!",Toast.LENGTH_SHORT).show();
    }*/

    @OnClick(R.id.main_btn_square)
    public void gotoSquareClick(View view){
        fragmentManager.beginTransaction().replace(R.id.main_fragment,fragmentEarth).commit();
    }
    @OnClick(R.id.main_btn_profile)
    public void gotoProfile(View view){
        fragmentManager.beginTransaction().replace(R.id.main_fragment,fragmentProfile).commit();
    }

    /*@OnClick(R.id.main_aty_choose_area)
    public void main_aty_choose_area_Click(View view){
        wakePop = view;
        chooseArea(view);
    }
    @OnClick(R.id.main_aty_choose_arrow)
    public void main_aty_choose_arrow_Click(View view){
        //chooseArea(view);
        main_aty_choose_area_Click(wakePop);
    }
*/
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
