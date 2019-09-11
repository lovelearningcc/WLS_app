package com.wls.jzgy.functions;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.wls.jzgy.R;
import com.wls.jzgy.entity.ConsTant;
import com.wls.jzgy.http.NewRequestManager;
import com.wls.jzgy.http.ReqCallBack;
import com.wls.jzgy.utils.TC;
import com.wls.jzgy.utils.WebUrls;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MapActivity extends Activity implements AMapLocationListener, LocationSource, AMap.OnMapLongClickListener, GeocodeSearch.OnGeocodeSearchListener, AMap.OnMapClickListener {

    private MapView mapView = null;
    private AMap aMap;
    private LocationSource.OnLocationChangedListener mListener = null;//定位监听器
    //标识，用于判断是否只显示一次定位信息和用户重新定位
    private boolean isFirstLoc = true;

    //gcc_add
    private SharedPreferences sp;

    private double latitude;//纬度
    private double longitude; //经度

    private static final String TAG = "MapActivity";

    private TextView title_bar_title;
    private ImageView back;


    private String myuid;
    private String numall1;
    private String numall2;


    //    private double var1,var2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        //获取地图控件引用
        mapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mapView.onCreate(savedInstanceState)，创建地图
        mapView.onCreate(savedInstanceState);

        sp = this.getSharedPreferences("xtb_data", Context.MODE_PRIVATE);
//        editor = sp.edit();

        myuid = sp.getString("uid", "");



        String mprovince = sp.getString("province", ""); //省
        String mcity = sp.getString("city", "");    //市
        String mhospital = sp.getString("hospital", "");  //医院名称

        TC tc = new TC();
        String dz = tc.testStringBuilder0(mprovince, mcity, mhospital);

        Log.e(TAG, "8888888888888   " + dz);

        //初始化地图控制器对象
        init();
        getOnline();
        getOffline();

//        Log.e(TAG, "8888888888888   " + numall1);
//        Log.e(TAG, "8888888888888   " + numall2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RadioButton dev_online = findViewById(R.id.check_online); //在线设备
        RadioButton dev_count = findViewById(R.id.check_count); //设备总数
        RadioButton dev_offline = findViewById(R.id.check_offline); //离线设备
//        RadioButton dev_alarm = findViewById(R.id.check_alarm); //报警设备

        getLatlon(dz);

        dev_offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(latitude, longitude)).title("离线设备" + numall2) // 设置标题
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)).draggable(false);
                ArrayList<MarkerOptions> optionList = new ArrayList<>();
                optionList.add(markerOptions);
                aMap.addMarkers(optionList, true);
            }
        });

        dev_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(latitude, longitude)).title("设备总数" + String.valueOf(Integer.parseInt(numall1) + Integer.parseInt(numall2))) // 设置标题
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)).draggable(true);
                ArrayList<MarkerOptions> optionList = new ArrayList<>();
                optionList.add(markerOptions);
                aMap.addMarkers(optionList, true);
            }
        });

        dev_online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(latitude, longitude)).title("在线设备" + numall1) // 设置标题
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)).draggable(false);
                // 使用ArrayList封装多个MarkerOptions，即可一次添加多个Marker
                ArrayList<MarkerOptions> optionList = new ArrayList<>();
                optionList.add(markerOptions);

                // 批量添加多个Marker
                aMap.addMarkers(optionList, true);
            }
        });
    }

    public void getOnline() {
        String url = "";
        HashMap<String, String> mapDay = new HashMap<>();

        mapDay.put("date", String.valueOf(new Date().getTime()));
        mapDay.put("etypecode", ConsTant.ETYPECODE_JZGY);
        mapDay.put("uid", myuid);
        url = WebUrls.getdeviceonlineurl;
        NewRequestManager myNewRequestManager = new NewRequestManager(this);

        myNewRequestManager.requestAsyn(url, NewRequestManager.TYPE_POST_JSON, mapDay, new ReqCallBack<String>() {
            @Override
            public void onReqSuccess(String result) {

                try {
                    JSONObject re1 = new JSONObject(result);  //解析JSON字符串
                    String code = re1.getString("code");
//                    String data = re.getString("data");
                    switch (code) {
                        case "0":
                            Log.d("WLS：", "onResponse回调结果------------>" + code);

                            //gcc_add_2019.4.19
                            JSONObject dataobject = re1.getJSONObject("data");

                            String onlineNum = dataobject.getString("num");
                            numall1 = dataobject.getString("num");
//                            as1 = Integer.parseInt(numall1);
                            Log.e(TAG, "9999999999999999999999     " + numall1);
                            break;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onReqFailed(String errorMsg) {
                Toast.makeText(MapActivity.this, "请求数据失败333！" + errorMsg, Toast.LENGTH_SHORT).show();
            }
        });

//        return as1;
    }

    public void getOffline() {
        String url = "";
        HashMap<String, String> mapDay = new HashMap<>();

        mapDay.put("date", String.valueOf(new Date().getTime()));
        mapDay.put("etypecode", ConsTant.ETYPECODE_JZGY);
        mapDay.put("uid", myuid);
        url = WebUrls.getdeviceofflineurl;
        NewRequestManager myNewRequestManager = new NewRequestManager(this);

        myNewRequestManager.requestAsyn(url, NewRequestManager.TYPE_POST_JSON, mapDay, new ReqCallBack<String>() {
            @Override
            public void onReqSuccess(String result) {

                try {
                    JSONObject re = new JSONObject(result);  //解析JSON字符串
                    String code = re.getString("code");
                    String data = re.getString("data");
                    switch (code) {
                        case "0":
                            Log.d("WLS：", "onResponse回调结果------------>" + code);

                            //gcc_add_2019.4.19
                            JSONObject dataobject = re.getJSONObject("data");
                            numall2 = dataobject.getString("num");
                            Log.e(TAG, "9999999999999999999999     " + numall2);
                            break;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onReqFailed(String errorMsg) {
                Toast.makeText(MapActivity.this, "请求数据失败444！" + errorMsg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //gcc-add

    private void getLatlon(String cityName) {

        GeocodeSearch geocodeSearch = new GeocodeSearch(MapActivity.this);
        geocodeSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

                if (i == 1000) {
                    if (geocodeResult != null && geocodeResult.getGeocodeAddressList() != null &&
                            geocodeResult.getGeocodeAddressList().size() > 0) {

                        GeocodeAddress geocodeAddress = geocodeResult.getGeocodeAddressList().get(0);
                        latitude = geocodeAddress.getLatLonPoint().getLatitude();//纬度
                        longitude = geocodeAddress.getLatLonPoint().getLongitude();//经度
                        String adcode = geocodeAddress.getAdcode();//区域编码

                        Log.e("地理编码", geocodeAddress.getAdcode() + "");
                        Log.e("纬度latitude", latitude + "");
                        Log.e("经度longititude", longitude + "");

                    } else {
//                        ToastUtils.show(context,"地址名出错");
                        Toast.makeText(MapActivity.this, "地址解析错误！", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        GeocodeQuery geocodeQuery = new GeocodeQuery(cityName.trim(), "29");
        geocodeSearch.getFromLocationNameAsyn(geocodeQuery);
    }
    //gcc_end


    // 初始化AMap对象
    public void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
            //gcc_add
//            LatLng latLng = new LatLng(30.573095, 104.066143);//构造一个位置
//            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11));
            //gcc_end
            // 创建一个设置放大级别的CameraUpdate
            CameraUpdate cu = CameraUpdateFactory.zoomTo(11);
            // 设置地图的默认放大级别
            aMap.moveCamera(cu);
            // 创建一个更改地图倾斜度的CameraUpdate
            CameraUpdate tiltUpdate = CameraUpdateFactory.changeTilt(30);
            // 改变地图的倾斜度
            aMap.moveCamera(tiltUpdate);

            //设置显示定位按钮 并且可以点击
            UiSettings settings = aMap.getUiSettings();
            //设置定位监听
            aMap.setLocationSource(this);
            // 是否显示定位按钮
            settings.setMyLocationButtonEnabled(true);
            // 是否可触发定位并显示定位层
            aMap.setMyLocationEnabled(true);
            //定位的小图标
            MyLocationStyle myLocationStyle = new MyLocationStyle();
            myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.icon_geo));
            myLocationStyle.radiusFillColor(android.R.color.transparent);
            myLocationStyle.strokeColor(android.R.color.transparent);
            aMap.setMyLocationStyle(myLocationStyle);

            //设置地图点击监听
            aMap.setOnMapClickListener(this);
        }

        title_bar_title = findViewById(R.id.tv_title_bar_title);
        title_bar_title.setText("地图");
        back = findViewById(R.id.iv_back);

    }

    //定位回调函数
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {

        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见官方定位类型表
                amapLocation.getLatitude();//获取纬度
                amapLocation.getLongitude();//获取经度
                amapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(amapLocation.getTime());
                df.format(date);//定位时间
                amapLocation.getAddress();  // 地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                amapLocation.getCountry();  // 国家信息
                amapLocation.getProvince();  // 省信息
                amapLocation.getCity();  // 城市信息
                amapLocation.getDistrict();  // 城区信息
                amapLocation.getStreet();  // 街道信息
                amapLocation.getStreetNum();  // 街道门牌号信息
                amapLocation.getCityCode();  // 城市编码
                amapLocation.getAdCode();//地区编码

                // 如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置
                if (isFirstLoc) {
                    //设置缩放级别
                    aMap.moveCamera(CameraUpdateFactory.zoomTo(20));
                    //将地图移动到定位点
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude())));
                    //点击定位按钮 能够将地图的中心移动到定位点
                    mListener.onLocationChanged(amapLocation);
                    //添加图钉
                    aMap.addMarker(getMarkerOptions(amapLocation));
                    //获取定位信息
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(amapLocation.getCountry() + "" + amapLocation.getProvince() + "" + amapLocation.getCity() + "" + amapLocation.getProvince() + "" + amapLocation.getDistrict() + "" + amapLocation.getStreet() + "" + amapLocation.getStreetNum());
                    Toast.makeText(getApplicationContext(), buffer.toString(), Toast.LENGTH_LONG).show();
                    isFirstLoc = false;
                }
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:" + amapLocation.getErrorCode() + ", errInfo:" + amapLocation.getErrorInfo());
                Toast.makeText(getApplicationContext(), "定位失败", Toast.LENGTH_LONG).show();
            }
        }
    }

    //  自定义一个图钉，并且设置图标，当我们点击图钉时，显示设置的信息
    private MarkerOptions getMarkerOptions(AMapLocation amapLocation) {
        //设置图钉选项
        MarkerOptions options = new MarkerOptions();
        //图标
        options.icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_loc));
        //位置
        options.position(new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude()));
        StringBuffer buffer = new StringBuffer();
        buffer.append(amapLocation.getCountry() + "" + amapLocation.getProvince() + "" + amapLocation.getCity() + "" + amapLocation.getDistrict() + "" + amapLocation.getStreet() + "" + amapLocation.getStreetNum());
        //标题
        options.title(buffer.toString());
        //子标题
        options.snippet("（您目前所在的位置）");
        //设置多少帧刷新一次图片资源
        options.period(60);
        return options;
    }

    // 激活定位
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
    }

    // 停止定位
    @Override
    public void deactivate() {
        mListener = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mapView.onDestroy()，销毁地图
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mapView.onResume ()，重新绘制加载地图
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mapView.onPause ()，暂停地图的绘制
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView.onSaveInstanceState(outState);
    }


    //gcc_add
    @Override
    public void onMapLongClick(LatLng latLng) {

    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }
}

