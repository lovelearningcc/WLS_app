package com.wls.dmr.persions;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnPageChangeListener;
import com.wls.dmr.MainActivity;
import com.wls.dmr.R;
import com.wls.dmr.utils.DownloadUtil;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HelpManualActivity extends AppCompatActivity implements OnPageChangeListener {

    private PDFView pdfView;
    private File file;
    private String filepath = "";

    private String urlString = "http://47.107.140.34/dmrbell/dm.pdf";

    private static final int COMPLETED = 0;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == COMPLETED) {
                filepath = Environment.getExternalStorageDirectory()
                        + "/pdf/dm.pdf";
                Log.e("pdf文件目录------》", "filepath：" + filepath);
                showpdf(filepath);
            }
        }
    };

     //工作线程
    private class WorkerThread extends Thread {
        @Override
        public void run() {
            //处理完成后给handler发消息
            Message msg = new Message();
            msg.what = COMPLETED;
            handler.sendMessage(msg);
            super.run();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g_help);

        initview();
    }

    private void initview() {
        pdfView = findViewById(R.id.pdfview);
        checkPemission();
    }

    public void checkPemission() {
        Log.e("TAG", "进入了checkPemission()");
        int hasWritePermission = PermissionChecker.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWritePermission != PermissionChecker.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 9);
        } else {
//            downFile();
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    downFile(urlString);
                }
            }.start();
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 9 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            downFile(urlString);
        }
    }

    private void showpdf(String filepath) {
        if (filepath.equals("") || filepath.equals(null)) {
            Toast.makeText(HelpManualActivity.this, "文件不存在！", Toast.LENGTH_SHORT);
        } else {
            file = new File(filepath);
            pdfView.fromFile(file)       //设置pdf文件地址
                    .defaultPage(1)      //设置默认显示第1页
                    .onPageChange(this)  //设置翻页监听
                    .showMinimap(false)  //pdf放大的时候，是否在屏幕的右上角生成小地图
                    .enableSwipe(true)   //是否允许翻页，默认是允许翻
                    .swipeVertical(true)//pdf文档翻页是否是垂直翻页，默认是左右滑动翻页
                    .load();
        }
    }


    private void openPDF(File file) {
        filepath = Environment.getExternalStorageDirectory()
                + "/downloads/dm.pdf";
        Log.e("pdf文件目录------》", "filepath：" + filepath);
        if (file.exists()) {
            showpdf(filepath);
        }
    }

    @Override
    public void onPageChanged(int page, int pageCount) {

    }



    /**
     * 文件下载
     *
     */
    private void downFile(String url) {
        DownloadUtil.get().download(url, Environment.getExternalStorageDirectory() + "/pdf/", "test.pdf",
                new DownloadUtil.OnDownloadListener() {


                    @Override
                    public void onDownloadSuccess(File file) {

                         Log.e("TAG", "下载成功！！！！！！！！！！");
                        openPDF(file);//打开PDF文件
                    }

                    @Override
                    public void onDownloading(int progress) {

                    }

                    @Override
                    public void onDownloadFailed(Exception e) {

                    }

                });
    }

}
