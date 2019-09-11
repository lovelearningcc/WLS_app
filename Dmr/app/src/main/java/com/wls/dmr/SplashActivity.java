package com.wls.dmr;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wls.dmr.activity.LoginActivity;
import com.wls.dmr.entity.Contants;
import com.wls.dmr.http.NewRequestManager;
import com.wls.dmr.http.ReqCallBack;
import com.wls.dmr.utils.SharedPreferencesUtil;
import com.wls.dmr.utils.WebUrls;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import static android.os.Environment.getExternalStorageDirectory;

/**
 * Author：  wls-gcc
 * Date on： 2018\12\29 0029 17:30
 * Version： v1.0
 */
public class SplashActivity extends AppCompatActivity {


    ProgressDialog pd;
    static final int UPDATA_CLIENT = 11;
    static final int DOWN_ERROR = 12;

    private LinearLayout tv_lin;
    private LinearLayout tv_hide_lin;
    private ImageView logo;

    private static final String TAG = "SplashActivity";
    private String version, url1, content;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public static boolean isFirstStart;  //是否是第一次安装
    private boolean isFirstStart1;
    private Contants contants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.system);

        SharedPreferencesUtil shared = new SharedPreferencesUtil(SplashActivity.this, Contants.CONFIG);
        isFirstStart = shared.getBoolean(Contants.IS_FIRST_START);

        sp = this.getSharedPreferences("xtb_data", Context.MODE_PRIVATE);
        editor = sp.edit();

        tv_lin = (LinearLayout) findViewById(R.id.text_lin);//要显示的字体
        tv_hide_lin = (LinearLayout) findViewById(R.id.text_hide_lin);//所谓的布
        logo = (ImageView) findViewById(R.id.image);//图片

        getUserData12();
    }


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
            }
            super.handleMessage(msg);
        }
    };


    /**
     * 倒计时跳
     */
    private void skipActivity(int min) {

        Animation animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.splash);
        logo.startAnimation(animation);//开始执行动画
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //第一个动画执行完后执行第二个动画就是那个字体显示那部分
                animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.text_splash_position);
                tv_lin.startAnimation(animation);
                animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.text_canvas);
                tv_hide_lin.startAnimation(animation);

                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        Intent it = new Intent(SplashActivity.this, LoginActivity.class);
                        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(it);
                    }
                }, 600);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void getUserData12() { //获取最新版本app

        Log.d(TAG, "进入了getUserData12方法------------>");
        String url = "";
        url = WebUrls.getupdataurl;    //获取服务器版本号
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isAvailable()) {
            //当前无可用网络
            Toast.makeText(this, "当前网络不可用！！！", Toast.LENGTH_SHORT).show();
        } else {
            //当前有可用网络
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
                                url1 = dataobject.getString("url");  //apk下载地址
//                                url1 = "http://39.105.78.205/apk/wls_sj.apk";
                                new CheckVersionTask().start();
                                break;
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onReqFailed(String errorMsg) {
                    Toast.makeText(SplashActivity.this, "最新版app地址请求失败，请检查网络" + errorMsg, Toast.LENGTH_SHORT).show();
                }
            });
        }
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
            skipActivity(2);
        });
        AlertDialog dialog = builer.create();
        dialog.show();
    }

    public void checkPemission() {
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
                    sleep(1000);
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

        Log.e("TAG", "进入了installApk(File file)");


        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri data;
        // 判断版本大于等于7.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // "net.csdn.blog.ruancoder.fileprovider"即是在清单文件中配置的authorities
            data = FileProvider.getUriForFile(SplashActivity.this, "com.wls.dmr", file);
            // 给目标应用一个临时授权
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            data = Uri.fromFile(file);
        }
        intent.setDataAndType(data, "application/vnd.android.package-archive");
        startActivity(intent);
    }

    public class CheckVersionTask extends Thread {

        public void run() {
            try {
                // 从资源文件获取服务器地址

                Log.e(TAG, "9999999999999999999999     " + version);
                Log.e(TAG, "9999999999999999999999     " + url1);
                Log.e(TAG, "9999999999999999999999     " + content);
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
                    skipActivity(2);

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
        Log.d("疾风剑豪：", "onDestroy被调用了------------>");

    }
}
