package com.binguner.crazytalk.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.binguner.crazytalk.R;
import com.binguner.crazytalk.Utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlusActivity extends AppCompatActivity {

    @BindView(R.id.plus_place_ed) EditText plus_place_ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus);
        ButterKnife.bind(this);
        StatusBarUtil.setStatusBarColor(this,R.color.colorWhite);
        StatusBarUtil.setStatusbarTextBlack(this);
        initViews();

        setListener();
    }

    private void setListener() {
        plus_place_ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(PlusActivity.this,MapActivity.class);
                startActivity(intent);*/
            }
        });

    }

    @OnClick(R.id.fake_btn)
    public void goToMap(View view){
        Intent intent = new Intent(PlusActivity.this,MapActivity.class);
        startActivity(intent);
    }

    private void initViews() {

    }


    @OnClick(R.id.plus_back)
    public void goBack(View view){
        this.finish();
    }
}
