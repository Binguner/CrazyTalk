package com.binguner.crazytalk.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.binguner.crazytalk.R;
import com.binguner.crazytalk.Utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlusHelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus_help);
        ButterKnife.bind(this);
        StatusBarUtil.setStatusbarTextBlack(this);
        StatusBarUtil.setStatusBarColor(this,R.color.colorWhite);
    }

    @OnClick(R.id.plus_help_back)
    public void goBack(View view){
        this.finish();
    }
}
