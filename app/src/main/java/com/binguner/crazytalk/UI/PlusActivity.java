package com.binguner.crazytalk.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.binguner.crazytalk.Listener.MapClickListener;
import com.binguner.crazytalk.R;
import com.binguner.crazytalk.Utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlusActivity extends AppCompatActivity {

    @BindView(R.id.plus_place_ed) EditText plus_place_ed;
    @BindView(R.id.plus_aty_name_ed) EditText plus_aty_name_ed;
    @BindView(R.id.plus_type_sam_ed) EditText plus_type_sam_ed;
    @BindView(R.id.plus_time_ed) EditText plus_time_ed;
    @BindView(R.id.plus_start) Button plus_start;
    @BindView(R.id.plus_checkBox) CheckBox plus_checkBox;
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
        MapActivity.setCallBack(new MapClickListener() {
            @Override
            public void MapClicked(String msg) {
                plus_place_ed.setText(msg);
            }
        });
        plus_checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(PlusActivity.this,"此活动所有人可见",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(PlusActivity.this,"此活动仅本圈内人可见",Toast.LENGTH_SHORT).show();
                }
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

    @OnClick(R.id.plus_start)
    public void goToStart(View view){
        if(plus_aty_name_ed.getText().toString().equals("") || plus_aty_name_ed.getText().toString().equals(" ") || plus_aty_name_ed.getText().toString() == null ){
            Toast.makeText(this,"请输入活动名称",Toast.LENGTH_SHORT).show();
            return;
        }
        if(plus_place_ed.getText().toString().equals("") || plus_place_ed.getText().toString().equals(" ") || plus_place_ed.getText().toString() == null ){
            Toast.makeText(this,"请输入活动地点",Toast.LENGTH_SHORT).show();
            return;
        }
        if(plus_type_sam_ed.getText().toString().equals("") || plus_type_sam_ed.getText().toString().equals(" ") || plus_type_sam_ed.getText().toString() == null ){
            Toast.makeText(this,"请输入活动类型",Toast.LENGTH_SHORT).show();
            return;
        }
        if(plus_time_ed.getText().toString().equals("") || plus_time_ed.getText().toString().equals(" ") || plus_time_ed.getText().toString() == null ){
            Toast.makeText(this,"请输入活动时间",Toast.LENGTH_SHORT).show();
            return;
        }else{
            Toast.makeText(this,"活动已发起，正在等待其他人的加入！",Toast.LENGTH_SHORT).show();
        }
    }
}
