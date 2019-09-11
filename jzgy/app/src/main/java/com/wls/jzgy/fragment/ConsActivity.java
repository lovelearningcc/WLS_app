package com.wls.jzgy.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wls.jzgy.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 耗材
 */
public class ConsActivity extends AppCompatActivity {

    private TextView title_bar_title;
    private ImageView back;
    private TextView ttan, tsha, tshuzhi, tfstm;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private int count = 100;
    private Timer mTimer = null;
    private Timer nTimer = null;
    private TimerTask mTimerTask = null;
    private TimerTask nTimerTask = null;
    private static int delay = 0;  //1s
    private static int period = 6000;  //6s
    private static final String TAG = "ConsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_consumable);

        sp = getSharedPreferences("xtb_data", Context.MODE_PRIVATE);
        editor = sp.edit();
        initView();
        startTimer();
    }


    private void startTimer() {
        if (mTimer == null) {
            mTimer = new Timer();
        }

        if (mTimerTask == null) {
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);

                    count++;

                    Log.i(TAG, "定时器启动------------------》" + count);
                }
            };
        }

        if (mTimer != null && mTimerTask != null)
            mTimer.schedule(mTimerTask, delay, period);
    }


    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:

                    if (count >= 3) {
                        getUserData3();  //定时开始制水   取数据
                    } else {
                        Log.e(TAG, "现在count的值为——————————》》》" + count);
                    }
                    break;
                default:
                    break;
            }
        }
    };


    private void initView() {

        title_bar_title = findViewById(R.id.tv_title_bar_title);
        title_bar_title.setText("耗材使用时间及更换周期");
        back = findViewById(R.id.iv_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ttan = findViewById(R.id.tv_tan);
        tsha = findViewById(R.id.tv_sha);
//        tshuzhi = findViewById(R.id.tv_shuzhi);
//        tfstm = findViewById(R.id.tv_fstm);
    }


    public void getUserData3() {
        try {
            String str = sp.getString("s54", ""); //炭，砂，树脂，反渗透膜使用时间
            if (str.equals("") || str.equals(null)) {
                Log.e(TAG, "暂无在线设备------------->>>");
            } else {
                Float value = Float.intBitsToFloat(Integer.valueOf(str));
                String value1 = String.format("%1.2f", value);
                ttan.setText(String.valueOf(value1));
                tsha.setText(String.valueOf(value1));
//                tshuzhi.setText(String.valueOf(value1));
//                tfstm.setText(String.valueOf(value1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTimer.cancel();//取消任务
        handler.removeCallbacks(mTimerTask);//取消任务
        Log.d("疾风剑豪1：", "onDestroy被调用了------------>");

    }
}
