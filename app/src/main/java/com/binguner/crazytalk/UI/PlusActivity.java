package com.binguner.crazytalk.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.binguner.crazytalk.R;
import com.binguner.crazytalk.Utils.StatusBarUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus);
        ButterKnife.bind(this);
        StatusBarUtil.setStatusBarColor(this,R.color.colorWhite);
        StatusBarUtil.setStatusbarTextBlack(this);
        initViews();
    }

    private void initViews() {

    }


    @OnClick(R.id.plus_back)
    public void goBack(View view){
        this.finish();
    }
}
