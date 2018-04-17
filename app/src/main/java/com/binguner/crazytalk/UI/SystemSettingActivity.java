package com.binguner.crazytalk.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.binguner.crazytalk.R;
import com.binguner.crazytalk.Utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SystemSettingActivity extends AppCompatActivity {

    @BindView(R.id.system_setting_back) ImageView system_setting_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_setting);
        ButterKnife.bind(this);
        StatusBarUtil.setStatusBarColor(this,R.color.colorWhite);
        StatusBarUtil.setStatusbarTextBlack(this);
    }

    @OnClick(R.id.system_setting_back)
    public void goBack(View view){
        this.finish();
    }
}
