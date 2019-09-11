package com.wls.jzgy;

import android.Manifest;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.wls.jzgy.activity.LoginActivity;
import com.wls.jzgy.entity.UserData;
import com.wls.jzgy.entity.ZhiShui;
import com.wls.jzgy.fragment.QuaryDataAdapter;
import com.wls.jzgy.http.NewRequestManager;
import com.wls.jzgy.http.OkHttpUtils3;
import com.wls.jzgy.http.ReqCallBack;
import com.wls.jzgy.utils.WebUrls;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.os.Environment.getExternalStorageDirectory;

/**
 * Author：  wls-gcc
 * Date on： 2018\12\29 0029 17:30
 * Version： v1.0
 */
public class SplashActivity extends AppCompatActivity implements OnBannerListener, View.OnClickListener {


    ProgressDialog pd;
    static final int UPDATA_CLIENT = 11;
    static final int DOWN_ERROR = 12;

    private static final String TAG = "SplashActivity";
    private String version, url1, content;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    //轮播图
    private Banner banner;
    private static List<String> images = new ArrayList<String>();

    private int recLen = 8;//跳过倒计时提示10秒
    private TextView tv;
    private Timer timer = new Timer();
    private Handler handler;
    private Runnable runnable;


    private int count = 0;

    private Thread thread;

    private String url10 = "http://47.107.140.34/dmrbell/picture/js1.jpg";
    private String url2 = "http://47.107.140.34/dmrbell/picture/js2.jpg";
    private String url3 = "http://47.107.140.34/dmrbell/picture/js3.jpg";
    private String url4 = "http://47.107.140.34/dmrbell/picture/js4.jpg";
    private String url5 = "http://47.107.140.34/dmrbell/picture/js5.jpg";
    private String url6 = "http://47.107.140.34/dmrbell/picture/js6.jpg";
    private String url7 = "http://47.107.140.34/dmrbell/picture/js7.jpg";
    private String url8 = "http://47.107.140.34/dmrbell/picture/js8.jpg";
    private String url9 = "http://47.107.140.34/dmrbell/picture/js9.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.system);

        sp = this.getSharedPreferences("xtb_data", Context.MODE_PRIVATE);
        editor = sp.edit();
        init();
        images.add(url10);
        images.add(url2);
        images.add(url3);
        images.add(url4);
        images.add(url5);
        images.add(url6);
        images.add(url7);
        images.add(url8);
        images.add(url9);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);

        banner.setImages(images)
                .setImageLoader(new GlideImageLoader())
                .setOnBannerListener(this)
                .start();


        getUserData12();


        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        count++;
                        Thread.sleep(1500);
                        myhandler.sendEmptyMessage(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        });
//        thread.start();

    }


    private void init() {
        tv = findViewById(R.id.tv);//跳过
        tv.setOnClickListener(this);//跳过监听

        banner = findViewById(R.id.banner);


        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        getWindow().setFlags(flag, flag);


//        timer.schedule(task, 1500, 1500);//等待时间一秒，停顿时间一秒


    }


    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() { // UI thread
                @Override
                public void run() {
                    recLen--;
                    tv.setText("跳过 " + recLen);
                    if (recLen < 0) {
                        timer.cancel();
                        tv.setVisibility(View.GONE);//倒计时到0隐藏字体
                    }
                }
            });
        }
    };


    @SuppressLint("HandlerLeak")
    final Handler myhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATA_CLIENT:
                    showUpdataDialog();
                    break;
                case DOWN_ERROR:
                    if (null != pd) {
                        pd.dismiss();
                    }
                    break;
                case 1:

                    if (count > 8) {
//                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));

                        Intent it = new Intent(SplashActivity.this, LoginActivity.class);
                        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(it);
                        thread.interrupt();
                    } else {

                        Log.e(TAG, "现在count的值为——————————》》》" + count);
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };


    public void getUserData12() {

        Log.d(TAG, "进入了getUserData12方法------------>");
        String url = "";
        url = WebUrls.getupdataurl;

        HashMap<String, String> mapDay = new HashMap<>();

        NewRequestManager myNewRequestManager = new NewRequestManager(this);
        myNewRequestManager.requestAsyn(url, NewRequestManager.TYPE_POST_JSON, mapDay, new ReqCallBack<String>() {

            @Override
            public void onReqSuccess(String result) {
                Log.d("WLS", "onResponse回调结果------------>" + result);

                try {
                    //gcc_add

                    JSONObject re = new JSONObject(result);  //解析JSON字符串
                    String code = re.getString("code");
                    switch (code) {
                        case "0":
                            Log.d("WLS：", "onResponse回调结果------------>" + code);


                            //gcc_add_2019.4.19
                            JSONObject dataobject = re.getJSONObject("data");

                            version = dataobject.getString("version");
                            content = dataobject.getString("content");
                            url1 = dataobject.getString("url");

                            Log.e(TAG, "9999999999999999999999     " + version);
                            Log.e(TAG, "9999999999999999999999     " + url1);
                            Log.e(TAG, "9999999999999999999999     " + content);
//                            saveAccount12();

                            new CheckVersionTask().start();
                            break;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onReqFailed(String errorMsg) {
                Toast.makeText(SplashActivity.this, "请求数据失败1234！" + errorMsg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*
     * 获取当前程序的版本名
     */
    private String getVersionName() throws Exception {
        // 获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        //zy_and
        Log.e("TAG", "版本号" + packInfo.versionCode);
        Log.e("TAG", "版本名" + packInfo.versionName);
        //end
        return packInfo.versionName;
    }

    /*
     * 从服务器下载apk
     */
    public File getFileFromServer(String path, ProgressDialog pd) throws Exception {

        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        // 获取到文件的大小
        pd.setMax(conn.getContentLength());
        InputStream is = conn.getInputStream();

        File file = new File(getExternalStorageDirectory(), "downloads");
        if (!file.exists()) {
            file.mkdir();
        }
        File app = new File(file, "sj.apk");

        Log.i("SJ", app.getAbsolutePath());
        FileOutputStream fos = new FileOutputStream(app);
        byte[] buffer = new byte[1024];
        int len;
        int total = 0;
        while ((len = is.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
            total += len;
            // 获取当前下载量
            pd.setProgress(total);
        }
        fos.close();
        is.close();
        return app;

    }

    public void showUpdataDialog() {

        Log.e("TAG", "进入了showUpdataDialog()");
        AlertDialog.Builder builer = new AlertDialog.Builder(this);
        builer.setTitle("有新版本是否升级？");
        builer.setCancelable(false);

        // 当点确定按钮时从服务器上下载 新的apk 然后安装
        builer.setPositiveButton("确定", (dialog, which) -> {

            Log.i("SJ", "下载apk,更新");
            checkPemission();
        });
        // 当点取消按钮时进行登录
        builer.setNegativeButton("取消", (dialog, which) -> {

            // TODO Auto-generated method stub
            Log.i("SJ", "取消升级");

            thread.start();
            timer.schedule(task, 1500, 1500);//等待时间一秒，停顿时间一秒
        });
        AlertDialog dialog = builer.create();
        dialog.show();
    }

    public void checkPemission() {

        Log.e("TAG", "进入了checkPemission()");
        int hasWritePermission = PermissionChecker.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWritePermission != PermissionChecker.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 9);
        } else {
            downLoadApk();
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 9 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            downLoadApk();
        }
    }

    /*
     * 从服务器中下载APK
     */
    public void downLoadApk() {

        Log.e("TAG", "进入了downLoadApk()");

        Log.e(TAG, "9999999999999999999999     " + url1);

        // 进度条对话框
        pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("正在下载更新");
        pd.setCancelable(false);
        pd.show();
        new Thread() {
            @Override
            public void run() {
                try {
                    File file = getFileFromServer(url1, pd);
                    sleep(1500);
                    installApk(file);
                    pd.dismiss(); // 结束掉进度条对话框
                } catch (Exception e) {
                    Message msg = new Message();
                    msg.what = DOWN_ERROR;
                    myhandler.sendMessage(msg);
                    e.printStackTrace();
                }
            }
        }.start();
    }

    // 安装apk
    protected void installApk(File file) {

//        animatorIn.cancel();
//        animatorOut.cancel();

        Log.e("TAG", "进入了installApk(File file)");

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri data;
        // 判断版本大于等于7.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // "net.csdn.blog.ruancoder.fileprovider"即是在清单文件中配置的authorities
            data = FileProvider.getUriForFile(SplashActivity.this, "com.wls.jzgy", file);
            // 给目标应用一个临时授权
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            data = Uri.fromFile(file);
        }
        intent.setDataAndType(data, "application/vnd.android.package-archive");
        startActivity(intent);
    }

    @Override
    public void OnBannerClick(int position) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv:
                //从闪屏界面跳转到首界面
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                if (runnable != null) {
                    handler.removeCallbacks(runnable);
                }
                break;
            default:
                break;
        }
    }

    public class CheckVersionTask extends Thread {

        public void run() {
            try {
                // 从资源文件获取服务器地址
                // 包装成url的对象
                URL url = new URL(url1);
                HttpURLConnection conn = (HttpURLConnection) url
                        .openConnection();
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);

                Log.e(TAG, "yspj1yspj1------------>>>" + version);
                Log.e(TAG, "yspj1yspj1------------>>>" + getVersionName());

                if (version.equals(getVersionName())) {
                    Log.i("SJ", "无需升级");

//                    Log.e("TAG", "点击了无需升级");
//                    skipActivity(2);
//                    startAnimator();


//                    animatorIn.start();
//                    animatorOut.start();
//                    autolunbo();
                    thread.start();
                    timer.schedule(task, 1500, 1500);//等待时间一秒，停顿时间一秒

                } else {
                    Log.i("TKKJ", "提示用户升级 ");
                    Message msg = new Message();
                    msg.what = UPDATA_CLIENT;
                    myhandler.sendMessage(msg);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

//        thread.stop();
        Log.d("疾风剑豪：", "onDestroy被调用了------------>");

    }
}
