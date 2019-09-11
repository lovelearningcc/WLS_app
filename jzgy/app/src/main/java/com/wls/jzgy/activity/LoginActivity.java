package com.wls.jzgy.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.onlynight.waveview.WaveView;
import com.wls.jzgy.MainActivity;
import com.wls.jzgy.R;
import com.wls.jzgy.activity.numinei.PermissionUtil;
import com.wls.jzgy.activity.numinei.SimConnectReceive;
import com.wls.jzgy.activity.numinei.SimStateReceive;
import com.wls.jzgy.activity.numinei.SimUtils;
import com.wls.jzgy.adapter.OptionsAdapter;
import com.wls.jzgy.business.AccountDao;
import com.wls.jzgy.business.HistoryInfo;
import com.wls.jzgy.http.NewRequestManager;
import com.wls.jzgy.http.ReqCallBack;
import com.wls.jzgy.persions.AlertDialogConfirm;
import com.wls.jzgy.temp.domain.UpdateTimeData;
import com.wls.jzgy.utils.DatabaseHelper;
import com.wls.jzgy.utils.WebUrls;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class LoginActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";
    private EditText userET;     //用户名
    private EditText pwdET;      //密码
    private CheckBox rememberPwd;   //记住密码
    private boolean isRemember;
    private CheckBox autoLogin;  //自动登录
    private CheckBox historyCB;  //历史记录
    private Button loginBtn;     //登录
    private Button register;     //注册
    private AccountDao accountDao;

    private WaveView mWaveView1;
    ProgressBar progressBar;
    private DatabaseHelper dh;

    int userState;//是否是体验用户 0-是   1-不是
    String user;  //用户名
    String pwd = ""; //密码
    String age;   //年龄
    String sex;    //性别
    String realname;  //真实姓名
    String phonenum;  //电话号码

    String userid = ""; //用户id
    int add = 0;
    int del = 0;
    int edit = 0;
    int cha = 0;

    //下拉框选项数据源
    ArrayList<HistoryInfo> datas = new ArrayList<HistoryInfo>();
    private List<HistoryInfo> historyList;
    private RelativeLayout pwdBottom, dropDown;
    private PopupWindow selectPopupWindow;
    private ListView listview;
    private OptionsAdapter optionsAdapter;

    private boolean isHidePwd = true;// 输入框密码是否是隐藏的，默认为true
    private String uid;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;


    //gcc_add
    public static final int PERMISSION_REQUEST_CODE_BASIC_INFORMATION = 1;
    private SimConnectReceive mSimConnectReceive;
    private SimStateReceive mSimStateReceive;

    private String akm, ump9;
    private long backpresstime = 0;
    //gcc_end

    private String bbh;  //版本号

    private AlertDialogConfirm dialogConfirm;

    private boolean mIsExit; //退出标志

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        sp = this.getSharedPreferences("xtb_data", Context.MODE_PRIVATE);
        editor = sp.edit();

        if (!PermissionUtil.hasSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE)) {
            requestPermissions(
                    new String[]{Manifest.permission.READ_PHONE_STATE},
                    PERMISSION_REQUEST_CODE_BASIC_INFORMATION);
        } else {
            init1();
        }

        mWaveView1 = (WaveView) findViewById(R.id.waveView1);
        mWaveView1.start();
        initView();
        initListener();
        initData();

        //监听记住密码多选框按钮事件
        rememberPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rememberPwd.isChecked()) {
                    System.out.println("记住密码已选中");
                    sp.edit().putBoolean("ISCHECK", true).commit();
                } else {
                    System.out.println("记住密码没有选中");
                    sp.edit().putBoolean("ISCHECK", false).commit();
                }
            }
        });

        //监听自动登录多选框事件
        autoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (autoLogin.isChecked()) {
                    System.out.println("自动登录已选中");
                    sp.edit().putBoolean("AUTO_ISCHECK", true).commit();

                } else {
                    System.out.println("自动登录没有选中");
                    sp.edit().putBoolean("AUTO_ISCHECK", false).commit();
                }
            }
        });
    }

    private void init1() {

        if (mSimConnectReceive == null) {
            mSimConnectReceive = new SimConnectReceive();
            IntentFilter filter = new IntentFilter();
            filter.addAction(SimConnectReceive.ACTION_SIM_STATE_CHANGED);
            registerReceiver(mSimConnectReceive, filter);
        }
        if (mSimStateReceive == null) {
            mSimStateReceive = new SimStateReceive();
            IntentFilter filter1 = new IntentFilter();
            filter1.addAction(SimStateReceive.ACTION_SIM_STATE_CHANGED);
            registerReceiver(mSimStateReceive, filter1);
        }

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        //gcc_add
        String number1 = SimUtils.getSimPhonenumber(this, 0);
        String number2 = SimUtils.getSimPhonenumber(this, 1);


        if (!TextUtils.isEmpty(number1)) {
            akm = number1;
        } else {
            if (!TextUtils.isEmpty(number2)) {
                akm = number2;
            } else {
                akm = "0000";
            }
        }

        Log.e(TAG, "akm------------>" + akm);
        String imei1 = SimUtils.getSimImei(this, 0);
        String imei2 = SimUtils.getSimImei(this, 1);
        if (!TextUtils.isEmpty(imei1)) {
            ump9 = imei1;
        } else {
            if (!TextUtils.isEmpty(imei2)) {
                ump9 = imei2;
            } else {
                ump9 = "0000";
            }
        }

        Log.e(TAG, "ump9------------>" + ump9);
        saveAccount8();
    }


    @SuppressLint("ClickableViewAccessibility")
    private void initView() {
        userET = (EditText) findViewById(R.id.et_login_user); //账号
        pwdET = (EditText) findViewById(R.id.et_login_pwd);  //密码
        rememberPwd = findViewById(R.id.cb_remember_pwd);    //记住密码
        autoLogin = findViewById(R.id.cb_auto_login);        //自动登录

        //判断记住密码多选框
        if (sp.getBoolean("ISCHECK", false)) {
            //设置默认是记录密码状态
            rememberPwd.setChecked(true);
            userET.setText(sp.getString("USER_NAME", ""));
            pwdET.setText(sp.getString("PASSWORD", ""));

            //判断自动登录多选框状态
            if (sp.getBoolean("AUTO_ISCHECK", false)) {
                //默认是自动登录状态
                autoLogin.setChecked(true);
                //跳转界面
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        }
        //gcc_end

        historyCB = (CheckBox) findViewById(R.id.historyCB); //用户登录历史
        loginBtn = findViewById(R.id.btn_login); //登录按钮
//        register = findViewById(R.id.btn_register); //注册按钮
        progressBar = findViewById(R.id.progressBar); //进度条
        pwdBottom = (RelativeLayout) findViewById(R.id.rl_bottom);//账号以下的部分
        accountDao = new AccountDao(this);
        dh = new DatabaseHelper(this);

//        et_pwd = (EditText) view.findViewById(R.id.et_pwd);
        final Drawable[] drawables = pwdET.getCompoundDrawables();
        final int eyeWidth = drawables[2].getBounds().width();// 眼睛图标的宽度
        final Drawable drawableEyeOpen = getResources().getDrawable(R.drawable.yanjing1);
        drawableEyeOpen.setBounds(drawables[2].getBounds());//这一步不能省略
        pwdET.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    // getWidth,getHeight必须在这里处理
                    float et_pwdMinX = v.getWidth() - eyeWidth - pwdET.getPaddingRight();
                    float et_pwdMaxX = v.getWidth();
                    float et_pwdMinY = 0;
                    float et_pwdMaxY = v.getHeight();
                    float x = event.getX();
                    float y = event.getY();
                    if (x < et_pwdMaxX && x > et_pwdMinX && y > et_pwdMinY && y < et_pwdMaxY) {
                        // 点击了眼睛图标的位置
                        isHidePwd = !isHidePwd;
                        if (isHidePwd) {
//                            pwdET.setCompoundDrawables(drawables[0] , drawables[2] , drawables[3]);
                            pwdET.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);

                            pwdET.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        } else {
                            pwdET.setCompoundDrawables(drawables[0], drawables[1], drawableEyeOpen, drawables[3]);
                            pwdET.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        }
                    }
                }
                return false;
            }
        });
    }

    private void initListener() {
        loginBtn.setOnClickListener(this);
        pwdBottom.setOnClickListener(this);
    }

    private void initData() {
        //是否显示历史登录列表
        historyCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    initPopuWindow();//显示历史列表
                    if (historyList.size() == 0) {
                        pwdBottom.setVisibility(View.VISIBLE);
                    } else {
                        pwdBottom.setVisibility(View.VISIBLE);
                    }
                } else {
                    //都表示不可见，invisible时仍然占据layout空间，而gone时则不会占据空间
                    selectPopupWindow.dismiss(); //隐藏列表
                    pwdBottom.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    /**
     * 登录列表数据
     * 初始化填充Adapter所用List数据
     */
    private void initAddNum() {
        datas.clear();
        historyList = (List<HistoryInfo>) accountDao.queryAll();
        Comparator<HistoryInfo> comparator = new Comparator<HistoryInfo>() {
            @Override
            public int compare(HistoryInfo t1, HistoryInfo t2) {
                if (Long.parseLong(t1.getTime() + "") < Long.parseLong(t2.getTime() + "")) {
                    return 1;
                }
                if (Long.parseLong(t1.getTime() + "") > Long.parseLong(t2.getTime() + "")) {
                    return -1;
                }
                return 1;
            }
        };
        Collections.sort(historyList, comparator);
        if (historyList.size() > 5) {
            historyList = historyList.subList(0, 5);
        }
        datas.addAll(historyList);

    }

    /**
     * 初始化PopupWindow
     */
    private void initPopuWindow() {
        initAddNum();
        //PopupWindow浮动下拉框布局
        View view = LayoutInflater.from(LoginActivity.this).inflate(R.layout.options, null);
        selectPopupWindow = new PopupWindow(view);
        selectPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        selectPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        listview = (ListView) view.findViewById(R.id.list);
        optionsAdapter = new OptionsAdapter(this, datas, accountDao);
        listview.setAdapter(optionsAdapter);
        selectPopupWindow.setFocusable(true);
        selectPopupWindow.setTouchable(true);

        selectPopupWindow.showAsDropDown(historyCB, 0, 0);
        selectPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String phone = datas.get(position).getPhone();
                userET.setText(phone);
                selectPopupWindow.dismiss();
                historyCB.setChecked(false);
                pwdBottom.setVisibility(View.VISIBLE);
                optionsAdapter.notifyDataSetChanged();
            }
        });
    }

    public void setVivi() {
        if (datas.size() == 0) {
            pwdBottom.setVisibility(View.VISIBLE);
            historyCB.setChecked(false);
        }
    }


    /**
     * 判断是否平板设备
     *
     * @param context
     * @return true:平板,false:手机
     */
    private boolean isTabletDevice(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >=
                Configuration.SCREENLAYOUT_SIZE_LARGE;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_login:

                if (isTabletDevice(this)) {
                    Log.e(TAG, "你当前使用的是平板，请换成安卓手机继续操作");
                } else {
                    Log.e(TAG, "当前值为---------》》》" + isTabletDevice(this));
                    if (validate()) {

                        getUid();
                        login();

//                        saveCurrentUserInfo();


                        HistoryInfo historyInfo = new HistoryInfo(userET.getText().toString(), "Tom", new Date().getTime());
                        accountDao.insert(historyInfo);
                    }
                    Log.d(TAG, "login");
                }
                break;
        }
    }

    public void saveAccount8() {
        editor.putString("akm", akm);
        editor.putString("ump9", ump9);
        editor.commit();
    }

    public void saveAccount6() {
        editor.putString("uid", uid); //设备id
        editor.commit();
    }

    public void saveAccount10() {
        editor.putString("lastaccount", user);       //临时保存当前用户信息
        editor.putString("lastpassword", user);
        editor.commit();
    }

    public boolean validate() {
        user = userET.getText().toString().trim();

        pwd = pwdET.getText().toString().trim();
        if (TextUtils.isEmpty(user)) {
            userET.setError("账号不能为空");
            userET.requestFocus();
            return false;
        } else {
            userET.setError(null);
        }
        if (TextUtils.isEmpty(pwd)) {
            pwdET.setError("密码不能为空");
            pwdET.requestFocus();
            return false;
        } else {
            pwdET.setError(null);
        }
        return true;
    }

    public void login() {    //登录

        //gcc_add
        HashMap<String, String> map = new HashMap<>();

        map.put("account", user);
        map.put("password", pwd);

//        map.put("username","zyykznuser");
//        map.put("password","zyykzn2019");

        Log.e(TAG, "登录账号密码为：" + map);
        Log.e(TAG, "账号：" + user + "\n密码：" + pwd);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isAvailable()) {
            //当前无可用网络
            Toast.makeText(LoginActivity.this, "当前网络不可用！！！", Toast.LENGTH_SHORT).show();
        } else {
            //当前有可用网络
            NewRequestManager myNewRequestManager = new NewRequestManager(this);
            Log.d(TAG, "之前------------>" + map);
            myNewRequestManager.requestAsyn(WebUrls.getloginurl, NewRequestManager.TYPE_POST_JSON, map, new ReqCallBack<String>() {
                @Override
                public void onReqSuccess(String result) {
                    Log.e(TAG, "onResponse回调结果------------>" + result);

                    try {
                        //gcc_add
                        JSONObject re = new JSONObject(result);
                        String code = re.getString("code");
                        switch (code) {
                            case "0":
                                Log.d("WLS：", "onResponse回调结果------------>" + code);
                                saveAccount10();
                                if (rememberPwd.isChecked()) {
                                    //记住用户名、密码

                                    editor.putString("USER_NAME", user);
                                    editor.putString("PASSWORD", pwd);
                                    editor.commit();
                                }

                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onReqFailed(String errorMsg) {

                    Log.d(TAG, "登录失败------------>");
                    Toast.makeText(LoginActivity.this, "用户名跟密码不匹配1111！！！", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    public void getUid() {    //获取用户uid
        //gcc_add
        HashMap<String, String> map = new HashMap<>();

        map.put("account", user);
        map.put("password", pwd);

        Log.e(TAG, "账号：" + user + "\n密码：" + pwd);

        NewRequestManager myNewRequestManager = new NewRequestManager(this);
        myNewRequestManager.requestAsyn(WebUrls.getuidurl, NewRequestManager.TYPE_POST_JSON, map, new ReqCallBack<String>() {
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
                            uid = dataobject.getString("uid");
                            saveAccount6();
                            break;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onReqFailed(String errorMsg) {
                Log.d(TAG, "获取失败------------>");
            }
        });
    }

    void showAlertDialog(String information) {
        new AlertDialog.Builder(this).setTitle("提示").setMessage(information).setPositiveButton("确定", null).show();
    }


    @Subscribe()
    public void Events(UpdateTimeData event) {
        Log.i(TAG, "event:" + event);
    }

//    @Override
//    public void onBackPressed() {
//        if ((System.currentTimeMillis() - backpresstime) > 2000) {
//            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
//            backpresstime = System.currentTimeMillis();
//        } else {
//            finish();
//        }
//    }

    /**
     * 双击返回键退出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mIsExit) {
                this.finish();

            } else {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                mIsExit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mIsExit = false;
                    }
                }, 2000);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}






















