package com.binguner.crazytalk.UI;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.binguner.crazytalk.R;
import com.binguner.crazytalk.Utils.StatusBarUtil;
import com.google.common.collect.MapMaker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapActivity extends AppCompatActivity {

    @BindView(R.id.myMap) MapView myMap;
    @BindView(R.id.map_floating_action_button_located) FloatingActionButton map_floating_action_button_located;
    private AMap aMap;
    private MyLocationStyle myLocationStyle;
    private UiSettings mUiSettings;
    private AMapLocationClient mLocationClient = null;
    private AMapLocationClientOption mLocationOption = null;
    private Marker locationMarker = null;
    private Marker marker;
    private LatLng mLatLng;

    //private LatLng latLng = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
        StatusBarUtil.setStatusBarColor(this,R.color.colorWhite);
        StatusBarUtil.setStatusbarTextBlack(this);
        myMap.onCreate(savedInstanceState);
        askPermission();
        initMap();
        singleTapToRefresh();

        UIControl();

    }

    private void singleTapToRefresh() {
        mLocationClient = new AMapLocationClient(getApplicationContext());
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setOnceLocation(true); //设置为单次定位模式
        mLocationOption.setNeedAddress(true); //返回地址描述
        mLocationOption.setHttpTimeOut(10000); //设置请求超时时间
        mLocationClient.setLocationOption(mLocationOption);

        //设置定位回调监听器
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if(aMapLocation != null){
                    LatLng latLng = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                    if(locationMarker == null){
                        locationMarker = aMap.addMarker(new MarkerOptions()
                                .position(latLng)
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.center_marker2)));
                    }else{
                        locationMarker.setPosition(latLng);
                    }
                    //将标记移动到定位点，使用animateCamera就有动画效果
                    aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
                }else{
                    Toast.makeText(MapActivity.this, "定位失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void UIControl() {
        mUiSettings = aMap.getUiSettings();
        mUiSettings.setZoomControlsEnabled(false); //缩放按钮的显示与隐藏
        mUiSettings.setCompassEnabled(false); //指南针的显示与隐藏
        mUiSettings.setScaleControlsEnabled(false); //比例尺的显示与隐藏
        mUiSettings.setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_LEFT); //         设置LOGO位置
        mUiSettings.setRotateGesturesEnabled(false); //禁止旋转
    }

    private void askPermission() {
        List<String> permissionList = new ArrayList<>();
        if(ContextCompat.checkSelfPermission(MapActivity.this, Manifest.
                permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if(ContextCompat.checkSelfPermission(MapActivity.this, Manifest.
                permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if(ContextCompat.checkSelfPermission(MapActivity.this, Manifest.
                permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(!permissionList.isEmpty()){
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MapActivity.this, permissions, 1);
        }
    }

    private void initMap() {
        if(aMap == null){
            aMap = myMap.getMap();
        }

        myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
        //myLocationStyle.interval(2000);

        //MyLocationStyle myLocationIcon(BitmapDescriptor myLocationIcon);//设置定位蓝点的icon图标方法，需要用到BitmapDescriptor类对象作为参数。
        myLocationStyle.strokeColor(0);//设置定位蓝点精度圆圈的边框颜色的方法。
        myLocationStyle.radiusFillColor(0);//设置定位蓝点精度圆圈的填充颜色的方法。

        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        //将定位风格设置传给地图控制器
        aMap.setMyLocationStyle(myLocationStyle);aMap.setMyLocationEnabled(true);
        aMap.moveCamera(CameraUpdateFactory.zoomTo(17)); //设置缩放级别为17
        aMap.showIndoorMap(true); //显示室内地图

        aMap.setOnMapClickListener(new AMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                //Toast.makeText(MapActivity.this,latLng.toString(),Toast.LENGTH_SHORT).show();
                if(null != marker){
                    marker.destroy();
                }
                marker = aMap.addMarker(new MarkerOptions().position(latLng));
                mLatLng = latLng;
                Toast.makeText(MapActivity.this,mLatLng.)
            }
        });
    }

    @OnClick(R.id.map_floating_action_button_located)
    public void locatedTheNowPlace(){
        if(null != mLocationClient){
            //mLocationClient.startLocation();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myMap.onDestroy();
        mLocationClient.onDestroy();
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
