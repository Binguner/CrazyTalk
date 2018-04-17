package com.binguner.crazytalk.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.binguner.crazytalk.R;
import com.binguner.crazytalk.Utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserSettingActivity extends AppCompatActivity {

    @BindView(R.id.user_setting_edit) ImageView user_setting_edit;
    @BindView(R.id.user_setting_back) ImageView user_setting_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);
        ButterKnife.bind(this);
        StatusBarUtil.setStatusBarColor(this,R.color.colorWhite);
        StatusBarUtil.setStatusbarTextBlack(this);
        initViews();

    }

    private void initViews() {

    }

    @OnClick(R.id.user_setting_back)
    public void goBack(View view){
        this.finish();
    }

    @OnClick(R.id.user_setting_edit)
    public void goToEdit(View view){
        Toast.makeText(this,"Edit",Toast.LENGTH_SHORT).show();
    }
}
