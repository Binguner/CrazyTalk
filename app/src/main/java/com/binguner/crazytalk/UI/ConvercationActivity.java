package com.binguner.crazytalk.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.binguner.crazytalk.Adapters.ConvercationAdapters.ConvercationAllOneAdapter;
import com.binguner.crazytalk.Model.ConvercationModel.ConvercationOneListModel;
import com.binguner.crazytalk.Model.ConvercationModel.ConvercationAllListModelOk;
import com.binguner.crazytalk.R;
import com.binguner.crazytalk.Utils.StatusBarUtil;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConvercationActivity extends AppCompatActivity {

    @BindView(R.id.convercation_recyclerview) RecyclerView convercation_recyclerview;
    @BindView(R.id.convercation_goBack) ImageView convercation_goBack;

    private ConvercationAllOneAdapter allOneAdapter;
    private List<MultiItemEntity> list;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_convercation);
        ButterKnife.bind(this);
        StatusBarUtil.setStatusbarTextBlack(this);
        StatusBarUtil.setStatusBarColor(this,R.color.colorWhite);
        initRecyclerview();
    }

    @OnClick(R.id.convercation_goBack)
    public void goToTalkView(View view){
        this.finish();
    }

    private void initRecyclerview() {
        list = new ArrayList<>();
        for(int i = 0 ; i < 4 ; i ++){
            ConvercationAllListModelOk modelOk = new ConvercationAllListModelOk();
            for(int j = 0; j < 3; j++){
                ConvercationOneListModel listModelOk = new ConvercationOneListModel();
                modelOk.addSubItem(listModelOk);
            }
            list.add(modelOk);
        }

        allOneAdapter = new ConvercationAllOneAdapter(list,this);
        linearLayoutManager = new LinearLayoutManager(this);

        convercation_recyclerview.setAdapter(allOneAdapter);
        convercation_recyclerview.setLayoutManager(linearLayoutManager);

        allOneAdapter.expandAll();


    }
}
