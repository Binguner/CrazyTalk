package com.binguner.crazytalk.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import com.binguner.crazytalk.R;
import com.binguner.crazytalk.Utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CircleSettingActivity extends AppCompatActivity {

    @BindView(R.id.activity_circle_setting_back) ImageView activity_circle_setting_back;
    @BindView(R.id.activity_circle_setting_circle1) CardView activity_circle_setting_circle1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_setting);
        ButterKnife.bind(this);
        StatusBarUtil.setStatusBarColor(this,R.color.colorWhite);
        StatusBarUtil.setStatusbarTextBlack(this);
    }

    @OnClick(R.id.activity_circle_setting_back)
    public void goback(View view){
        this.finish();
    }

    @OnClick(R.id.activity_circle_setting_circle1)
    public void goToCircle1(View view){
        Intent intent = new Intent(this,CricleActivity.class);
        startActivity(intent);
    }
}
