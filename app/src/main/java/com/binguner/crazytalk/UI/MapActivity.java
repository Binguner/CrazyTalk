package com.binguner.crazytalk.UI;

import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.binguner.crazytalk.R;
import com.binguner.crazytalk.Utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapActivity extends AppCompatActivity {

    @BindView(R.id.myMap) MapView myMap;
    @BindView(R.id.map_floating_action_button) FloatingActionButton map_floating_action_button;
    AMap aMap;
    MyLocationStyle myLocationStyle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
        StatusBarUtil.setStatusBarColor(this,R.color.colorWhite);
        StatusBarUtil.setStatusbarTextBlack(this);
        myMap.onCreate(savedInstanceState);

        initMap();
    }

    private void initMap() {
        if(aMap == null){
            aMap = myMap.getMap();
        }

        myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
        myLocationStyle.interval(2000);

        //MyLocationStyle myLocationIcon(BitmapDescriptor myLocationIcon);//设置定位蓝点的icon图标方法，需要用到BitmapDescriptor类对象作为参数。
        myLocationStyle.strokeColor(0);//设置定位蓝点精度圆圈的边框颜色的方法。
        myLocationStyle.radiusFillColor(0);//设置定位蓝点精度圆圈的填充颜色的方法。

        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        //将定位风格设置传给地图控制器
        aMap.setMyLocationStyle(myLocationStyle);aMap.setMyLocationEnabled(true);
        aMap.moveCamera(CameraUpdateFactory.zoomTo(17)); //设置缩放级别为17
        aMap.showIndoorMap(true); //显示室内地图

    }

    private void onRefresh(){

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myMap.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        myMap.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        myMap.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        myMap.onSaveInstanceState(outState);
    }
}
