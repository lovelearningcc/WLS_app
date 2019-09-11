package com.wls.jzgy.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.wls.jzgy.R;
import com.wls.jzgy.entity.ConsTant;
import com.wls.jzgy.entity.ZhiShui;
import com.wls.jzgy.http.OkHttpUtils3;
import com.wls.jzgy.utils.Hex;
import com.wls.jzgy.utils.InputFilterMinMax;
import com.wls.jzgy.utils.TC;
import com.wls.jzgy.utils.WebUrls;

import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时制水
 */
public class TimingActivity extends AppCompatActivity implements View.OnClickListener {

    private View view;
    private EditText shour1, smin1, shour2, smin2, shour3, smin3, shour4, smin4, shour5, smin5, shour6, smin6, shour7, smin7, yhour1, ymin1;
    private EditText ehour1, emin1, ehour2, emin2, ehour3, emin3, ehour4, emin4, ehour5, emin5, ehour6, emin6, ehour7, emin7;

    private String yt1, yt2, yt3, yt4, yt5, yt6;
    private TextView title_bar_title;
    private ImageView back;
    private Button ssend1, ssend2, ssend3, ssend4, ssend5, ssend6, ssend7, ysend1;
    private Button esend1, esend2, esend3, esend4, esend5, esend6, esend7;

    private boolean state = true;

    private String rng = "0001";
    private String ig = "0000";

    private String nhour1, nmin1, nhour2, nmin2, nhour3, nmin3, nhour4, nmin4, nhour5, nmin5, nhour6, nmin6, nhour7, nmin7, stryhour1, strymin1;
    private String fhour1, fmin1, fhour2, fmin2, fhour3, fmin3, fhour4, fmin4, fhour5, fmin5, fhour6, fmin6, fhour7, fmin7;

    private String slnhour1, slnmin1, slnhour2, slnmin2, slnhour3, slnmin3, slnhour4, slnmin4, slnhour5, slnmin5, slnhour6, slnmin6, slnhour7, slnmin7, slstryhour1, slstrymin1;
    private String slfhour1, slfmin1, slfhour2, slfmin2, slfhour3, slfmin3, slfhour4, slfmin4, slfhour5, slfmin5, slfhour6, slfmin6, slfhour7, slfmin7;

    private String str;
    private EditText text;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private LinearLayout linearLayout;

    private EditText e_delaytime;
    private Button b_delayshutdown;

    private Timer timer;
    private int count = 100;

    private String mr;

    private static final String TAG = "TimingActivity";
    private String phonenumber1, imei1, uid1, date3;

    private Timer mTimer = null;
    private Timer nTimer = null;
    private TimerTask mTimerTask = null;
    private TimerTask nTimerTask = null;
    private static int delay = 0;  //1s
    private static int period = 6000;  //6s

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_timing);

        sp = getSharedPreferences("xtb_data", Context.MODE_PRIVATE);
        editor = sp.edit();

        mr = sp.getString("eid1", "");

        Log.e(TAG, "魔人的值为——————————》》》" + mr);

        phonenumber1 = sp.getString("akm", "");
        imei1 = sp.getString("ump9", "");
        uid1 = sp.getString("uid", "");


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

                    if (count >= 5) {
                        getUserData24();  //定时开始制水   取数据
                    } else {
                        Log.e(TAG, "现在count的值为——————————》》》" + count);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    //gcc_add
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
// TODO Auto-generated method stub
            Log.d("TAG", "afterTextChanged--------------->");
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
// TODO Auto-generated method stub
            Log.d("TAG", "beforeTextChanged--------------->");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            Log.d("TAG", "onTextChanged--------------->");

            stryhour1 = yhour1.getText().toString().trim();
            strymin1 = ymin1.getText().toString().trim();

//            slstryhour1 = Integer.toHexString(Integer.parseInt(stryhour1));
//            slstrymin1 = Integer.toHexString(Integer.parseInt(strymin1));

            yhour1.setSelection(stryhour1.length());
            ymin1.setSelection(strymin1.length());

            nhour1 = shour1.getText().toString().trim();
            nmin1 = smin1.getText().toString().trim();
            nhour2 = shour2.getText().toString().trim();
            nmin2 = smin2.getText().toString().trim();
            nhour3 = shour3.getText().toString().trim();
            nmin3 = smin3.getText().toString().trim();
            nhour4 = shour4.getText().toString().trim();
            nmin4 = smin4.getText().toString().trim();
            nhour5 = shour5.getText().toString().trim();
            nmin5 = smin5.getText().toString().trim();
            nhour6 = shour6.getText().toString().trim();
            nmin6 = smin6.getText().toString().trim();
            nhour7 = shour7.getText().toString().trim();
            nmin7 = smin7.getText().toString().trim();

//            Hex hex = new Hex();
//            hex.codeAddOne(s, 4);
//            slstr0 = Integer.toHexString(Integer.parseInt(tystr0));
//            slnhour1 = Integer.toHexString(Integer.parseInt(nhour1));

//            slnhour1 = hex.codeAddOne(Integer.toHexString(Integer.valueOf(nhour1, 10)), 4);
//
//            slnhour2 = hex.codeAddOne(Integer.toHexString(Integer.valueOf(nhour2, 10)), 4);
//            slnhour3 = Integer.toHexString(Integer.parseInt(nhour3));
//            slnhour4 = Integer.toHexString(Integer.parseInt(nhour4));
//            slnhour5 = Integer.toHexString(Integer.parseInt(nhour5));
//            slnhour6 = Integer.toHexString(Integer.parseInt(nhour6));
//            slnhour7 = Integer.toHexString(Integer.parseInt(nhour7));
//
//            slnmin1 = Integer.toHexString(Integer.parseInt(nmin1));
//            slnmin2 = Integer.toHexString(Integer.parseInt(nmin2));
//            slnmin3 = Integer.toHexString(Integer.parseInt(nmin3));
//            slnmin4 = Integer.toHexString(Integer.parseInt(nmin4));
//            slnmin5 = Integer.toHexString(Integer.parseInt(nmin5));
//            slnmin6 = Integer.toHexString(Integer.parseInt(nmin6));
//            slnmin7 = Integer.toHexString(Integer.parseInt(nmin7));

            shour1.setSelection(nhour1.length());
            smin1.setSelection(nmin1.length());
            shour2.setSelection(nhour2.length());
            smin2.setSelection(nmin2.length());
            shour3.setSelection(nhour3.length());
            smin3.setSelection(nmin3.length());
            shour4.setSelection(nhour4.length());
            smin4.setSelection(nmin4.length());
            shour5.setSelection(nhour5.length());
            smin5.setSelection(nmin5.length());
            shour6.setSelection(nhour6.length());
            smin6.setSelection(nmin6.length());
            shour7.setSelection(nhour7.length());
            smin7.setSelection(nmin7.length());

            fhour1 = ehour1.getText().toString().trim();
            fmin1 = emin1.getText().toString().trim();
            fhour2 = ehour2.getText().toString().trim();
            fmin2 = emin2.getText().toString().trim();
            fhour3 = ehour3.getText().toString().trim();
            fmin3 = emin3.getText().toString().trim();
            fhour4 = ehour4.getText().toString().trim();
            fmin4 = emin4.getText().toString().trim();
            fhour5 = ehour5.getText().toString().trim();
            fmin5 = emin5.getText().toString().trim();
            fhour6 = ehour6.getText().toString().trim();
            fmin6 = emin6.getText().toString().trim();
            fhour7 = ehour7.getText().toString().trim();
            fmin7 = emin7.getText().toString().trim();

//            slfhour1 = Integer.toHexString(Integer.parseInt(fhour1));
//            slfmin1 = Integer.toHexString(Integer.parseInt(fmin1));
//            slfhour2 = Integer.toHexString(Integer.parseInt(fhour2));
//            slfmin2 = Integer.toHexString(Integer.parseInt(fmin2));
//            slfhour3 = Integer.toHexString(Integer.parseInt(fhour3));
//            slfmin3 = Integer.toHexString(Integer.parseInt(fmin3));
//            slfhour4 = Integer.toHexString(Integer.parseInt(fhour4));
//            slfmin4 = Integer.toHexString(Integer.parseInt(fmin4));
//            slfhour5 = Integer.toHexString(Integer.parseInt(fhour5));
//            slfmin5 = Integer.toHexString(Integer.parseInt(fmin5));
//            slfhour6 = Integer.toHexString(Integer.parseInt(fhour6));
//            slfmin6 = Integer.toHexString(Integer.parseInt(fmin6));
//            slfhour7 = Integer.toHexString(Integer.parseInt(fhour7));
//            slfmin7 = Integer.toHexString(Integer.parseInt(fmin7));

            ehour1.setSelection(fhour1.length());
            emin1.setSelection(fmin1.length());
            ehour2.setSelection(fhour2.length());
            emin2.setSelection(fmin2.length());
            ehour3.setSelection(fhour3.length());
            emin3.setSelection(fmin3.length());
            ehour4.setSelection(fhour4.length());
            emin4.setSelection(fmin4.length());
            ehour5.setSelection(fhour5.length());
            emin5.setSelection(fmin5.length());
            ehour6.setSelection(fhour6.length());
            emin6.setSelection(fmin6.length());
            ehour7.setSelection(fhour7.length());
            emin7.setSelection(fmin7.length());
        }
    };

    @SuppressLint("ClickableViewAccessibility")
    private void initView() {


        //延时关机
        yhour1 = findViewById(R.id.et_y_hour1);
        yhour1.addTextChangedListener(textWatcher);
        ymin1 = findViewById(R.id.et_y_min1);
        ymin1.addTextChangedListener(textWatcher);

        //开始制水
        shour1 = findViewById(R.id.et_s_hour1);
        shour1.addTextChangedListener(textWatcher);
        shour1.clearFocus();
        smin1 = findViewById(R.id.et_s_min1);
        smin1.addTextChangedListener(textWatcher);

        shour2 = findViewById(R.id.et_s_hour2);
        shour2.addTextChangedListener(textWatcher);
        smin2 = findViewById(R.id.et_s_min2);
        smin2.addTextChangedListener(textWatcher);

        shour3 = findViewById(R.id.et_s_hour3);
        shour3.addTextChangedListener(textWatcher);
        smin3 = findViewById(R.id.et_s_min3);
        smin3.addTextChangedListener(textWatcher);

        shour4 = findViewById(R.id.et_s_hour4);
        shour4.addTextChangedListener(textWatcher);
        smin4 = findViewById(R.id.et_s_min4);
        smin4.addTextChangedListener(textWatcher);

        shour5 = findViewById(R.id.et_s_hour5);
        shour5.addTextChangedListener(textWatcher);
        smin5 = findViewById(R.id.et_s_min5);
        smin5.addTextChangedListener(textWatcher);

        shour6 = findViewById(R.id.et_s_hour6);
        shour6.addTextChangedListener(textWatcher);
        smin6 = findViewById(R.id.et_s_min6);
        smin6.addTextChangedListener(textWatcher);

        shour7 = findViewById(R.id.et_s_hour7);
        shour7.addTextChangedListener(textWatcher);
        smin7 = findViewById(R.id.et_s_min7);
        smin7.addTextChangedListener(textWatcher);

        //停止制水
        ehour1 = findViewById(R.id.et_e_hour1);
        ehour1.addTextChangedListener(textWatcher);
        emin1 = findViewById(R.id.et_e_min1);
        emin1.addTextChangedListener(textWatcher);

        ehour2 = findViewById(R.id.et_e_hour2);
        ehour2.addTextChangedListener(textWatcher);
        emin2 = findViewById(R.id.et_e_min2);
        emin2.addTextChangedListener(textWatcher);

        ehour3 = findViewById(R.id.et_e_hour3);
        ehour3.addTextChangedListener(textWatcher);
        emin3 = findViewById(R.id.et_e_min3);
        emin3.addTextChangedListener(textWatcher);

        ehour4 = findViewById(R.id.et_e_hour4);
        ehour4.addTextChangedListener(textWatcher);
        emin4 = findViewById(R.id.et_e_min4);
        emin4.addTextChangedListener(textWatcher);

        ehour5 = findViewById(R.id.et_e_hour5);
        ehour5.addTextChangedListener(textWatcher);
        emin5 = findViewById(R.id.et_e_min5);
        emin5.addTextChangedListener(textWatcher);

        ehour6 = findViewById(R.id.et_e_hour6);
        ehour6.addTextChangedListener(textWatcher);
        emin6 = findViewById(R.id.et_e_min6);
        emin6.addTextChangedListener(textWatcher);

        ehour7 = findViewById(R.id.et_e_hour7);
        ehour7.addTextChangedListener(textWatcher);
        emin7 = findViewById(R.id.et_e_min7);
        emin7.addTextChangedListener(textWatcher);

        yhour1.setFilters(new InputFilter[]{new InputFilterMinMax("0", "24")});
        ymin1.setFilters(new InputFilter[]{new InputFilterMinMax("0", "60")});
        shour1.setFilters(new InputFilter[]{new InputFilterMinMax("0", "24")});
        smin1.setFilters(new InputFilter[]{new InputFilterMinMax("0", "60")});
        shour2.setFilters(new InputFilter[]{new InputFilterMinMax("0", "24")});
        smin2.setFilters(new InputFilter[]{new InputFilterMinMax("0", "60")});
        shour3.setFilters(new InputFilter[]{new InputFilterMinMax("0", "24")});
        smin3.setFilters(new InputFilter[]{new InputFilterMinMax("0", "60")});
        shour4.setFilters(new InputFilter[]{new InputFilterMinMax("0", "24")});
        smin4.setFilters(new InputFilter[]{new InputFilterMinMax("0", "60")});
        shour5.setFilters(new InputFilter[]{new InputFilterMinMax("0", "24")});
        smin5.setFilters(new InputFilter[]{new InputFilterMinMax("0", "60")});
        shour6.setFilters(new InputFilter[]{new InputFilterMinMax("0", "24")});
        smin6.setFilters(new InputFilter[]{new InputFilterMinMax("0", "60")});
        shour7.setFilters(new InputFilter[]{new InputFilterMinMax("0", "24")});
        smin7.setFilters(new InputFilter[]{new InputFilterMinMax("0", "60")});


        ehour1.setFilters(new InputFilter[]{new InputFilterMinMax("0", "24")});
        emin1.setFilters(new InputFilter[]{new InputFilterMinMax("0", "60")});
        ehour2.setFilters(new InputFilter[]{new InputFilterMinMax("0", "24")});
        emin2.setFilters(new InputFilter[]{new InputFilterMinMax("0", "60")});
        ehour3.setFilters(new InputFilter[]{new InputFilterMinMax("0", "24")});
        emin3.setFilters(new InputFilter[]{new InputFilterMinMax("0", "60")});
        ehour4.setFilters(new InputFilter[]{new InputFilterMinMax("0", "24")});
        emin4.setFilters(new InputFilter[]{new InputFilterMinMax("0", "60")});
        ehour5.setFilters(new InputFilter[]{new InputFilterMinMax("0", "24")});
        emin5.setFilters(new InputFilter[]{new InputFilterMinMax("0", "60")});
        ehour6.setFilters(new InputFilter[]{new InputFilterMinMax("0", "24")});
        emin6.setFilters(new InputFilter[]{new InputFilterMinMax("0", "60")});
        ehour7.setFilters(new InputFilter[]{new InputFilterMinMax("0", "24")});
        emin7.setFilters(new InputFilter[]{new InputFilterMinMax("0", "60")});



        ssend1 = findViewById(R.id.btn_ssend1);
        esend1 = findViewById(R.id.btn_esend1);
        ssend2 = findViewById(R.id.btn_ssend2);
        esend2 = findViewById(R.id.btn_esend2);
        ssend3 = findViewById(R.id.btn_ssend3);
        esend3 = findViewById(R.id.btn_esend3);
        ssend4 = findViewById(R.id.btn_ssend4);
        esend4 = findViewById(R.id.btn_esend4);
        ssend5 = findViewById(R.id.btn_ssend5);
        esend5 = findViewById(R.id.btn_esend5);
        ssend6 = findViewById(R.id.btn_ssend6);
        esend6 = findViewById(R.id.btn_esend6);
        ssend7 = findViewById(R.id.btn_ssend7);
        esend7 = findViewById(R.id.btn_esend7);

        ysend1 = findViewById(R.id.btn_ysend1);
        ysend1.setOnClickListener(this);
        ssend1.setOnClickListener(this);
        esend1.setOnClickListener(this);
        ssend2.setOnClickListener(this);
        esend2.setOnClickListener(this);
        ssend3.setOnClickListener(this);
        esend3.setOnClickListener(this);
        ssend4.setOnClickListener(this);
        esend4.setOnClickListener(this);
        ssend5.setOnClickListener(this);
        esend5.setOnClickListener(this);
        ssend6.setOnClickListener(this);
        esend6.setOnClickListener(this);
        ssend7.setOnClickListener(this);
        esend7.setOnClickListener(this);




        //gcc_add -----监听完成键动作
        shour1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (nhour1.length() < 2) {
                        shour1.setText("0" + nhour1);
                    } else {
                        shour1.setText(nhour1);
                    }
                    if (nmin1.length() < 2) {
                        smin1.setText("0" + nmin1);
                    } else {
                        smin1.setText(nmin1);
                    }
                    return true;
                }
                return false;
            }
        });
        shour2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (nhour2.length() < 2) {
                        shour2.setText("0" + nhour2);
                    } else {
                        shour2.setText(nhour2);
                    }
                    if (nmin2.length() < 2) {
                        smin2.setText("0" + nmin1);
                    } else {
                        smin2.setText(nmin2);
                    }
                    return true;
                }
                return false;
            }
        });
        shour3.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (nhour3.length() < 2) {
                        shour3.setText("0" + nhour3);
                    } else {
                        shour3.setText(nhour3);
                    }
                    if (nmin3.length() < 2) {
                        smin3.setText("0" + nmin3);
                    } else {
                        smin3.setText(nmin3);
                    }
                    return true;
                }
                return false;
            }
        });
        shour4.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (nhour4.length() < 2) {
                        shour4.setText("0" + nhour4);
                    } else {
                        shour4.setText(nhour4);
                    }
                    if (nmin4.length() < 2) {
                        smin4.setText("0" + nmin4);
                    } else {
                        smin4.setText(nmin4);
                    }
                    return true;
                }
                return false;
            }
        });
        shour5.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (nhour5.length() < 2) {
                        shour5.setText("0" + nhour5);
                    } else {
                        shour5.setText(nhour5);
                    }
                    if (nmin5.length() < 2) {
                        smin5.setText("0" + nmin5);
                    } else {
                        smin5.setText(nmin5);
                    }
                    return true;
                }
                return false;
            }
        });
        shour6.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (nhour6.length() < 2) {
                        shour6.setText("0" + nhour6);
                    } else {
                        shour6.setText(nhour6);
                    }
                    if (nmin6.length() < 2) {
                        smin6.setText("0" + nmin6);
                    } else {
                        smin6.setText(nmin6);
                    }
                    return true;
                }
                return false;
            }
        });
        shour7.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (nhour7.length() < 2) {
                        shour7.setText("0" + nhour7);
                    } else {
                        shour7.setText(nhour7);
                    }
                    if (nmin7.length() < 2) {
                        smin7.setText("0" + nmin7);
                    } else {
                        smin7.setText(nmin7);
                    }
                    return true;
                }
                return false;
            }
        });


        ehour1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (fhour1.length() < 2) {
                        ehour1.setText("0" + fhour1);
                    } else {
                        ehour1.setText(fhour1);
                    }
                    if (emin1.length() < 2) {
                        emin1.setText("0" + fmin1);
                    } else {
                        emin1.setText(fmin1);
                    }
                    return true;
                }
                return false;
            }
        });

        ehour2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (fhour2.length() < 2) {
                        ehour2.setText("0" + fhour2);
                    } else {
                        ehour2.setText(fhour2);
                    }
                    if (emin2.length() < 2) {
                        emin2.setText("0" + fmin2);
                    } else {
                        emin2.setText(fmin2);
                    }
                    return true;
                }
                return false;
            }
        });

        ehour3.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (fhour3.length() < 2) {
                        ehour3.setText("0" + fhour3);
                    } else {
                        ehour3.setText(fhour3);
                    }
                    if (emin3.length() < 2) {
                        emin3.setText("0" + fmin3);
                    } else {
                        emin3.setText(fmin3);
                    }
                    return true;
                }
                return false;
            }
        });

        ehour4.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (fhour4.length() < 2) {
                        ehour4.setText("0" + fhour4);
                    } else {
                        ehour4.setText(fhour4);
                    }
                    if (emin4.length() < 2) {
                        emin4.setText("0" + fmin4);
                    } else {
                        emin4.setText(fmin4);
                    }
                    return true;
                }
                return false;
            }
        });

        ehour5.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (fhour5.length() < 2) {
                        ehour5.setText("0" + fhour5);
                    } else {
                        ehour5.setText(fhour5);
                    }
                    if (emin5.length() < 2) {
                        emin5.setText("0" + fmin5);
                    } else {
                        emin5.setText(fmin5);
                    }
                    return true;
                }
                return false;
            }
        });

        ehour6.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (fhour6.length() < 2) {
                        ehour6.setText("0" + fhour6);
                    } else {
                        ehour6.setText(fhour6);
                    }
                    if (emin6.length() < 2) {
                        emin6.setText("0" + fmin6);
                    } else {
                        emin6.setText(fmin6);
                    }
                    return true;
                }
                return false;
            }
        });

        ehour7.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (fhour7.length() < 2) {
                        ehour7.setText("0" + fhour7);
                    } else {
                        ehour7.setText(fhour7);
                    }
                    if (emin7.length() < 2) {
                        emin7.setText("0" + fmin7);
                    } else {
                        emin7.setText(fmin7);
                    }
                    return true;
                }
                return false;
            }
        });


        //gcc_end


        initListen();

        title_bar_title = findViewById(R.id.tv_title_bar_title);
        title_bar_title.setText("定时制水");
        back = findViewById(R.id.iv_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initListen() {

        shour1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    shour1.setText("");
                }
            }
        });
        shour2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    shour2.setText("");
                }
            }
        });
        shour3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    shour3.setText("");
                }
            }
        });
        shour4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    shour4.setText("");
                }
            }
        });
        shour5.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    shour5.setText("");
                }
            }
        });
        shour6.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    shour6.setText("");
                }
            }
        });
        shour7.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    shour7.setText("");
                }
            }
        });
        smin1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    smin1.setText("");
                }
            }
        });
        smin2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    smin2.setText("");
                }
            }
        });
        smin3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    smin3.setText("");
                }
            }
        });
        smin4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    smin4.setText("");
                }
            }
        });
        smin5.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    smin5.setText("");
                }
            }
        });
        smin6.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    smin6.setText("");
                }
            }
        });
        smin7.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    smin7.setText("");
                }
            }
        });

        ehour1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ehour1.setText("");
                }
            }
        });

        emin1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    emin1.setText("");
                }
            }
        });
        ehour2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ehour2.setText("");
                }
            }
        });

        emin2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    emin2.setText("");
                }
            }
        });
        ehour3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ehour3.setText("");
                }
            }
        });

        emin3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    emin3.setText("");
                }
            }
        });
        ehour4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ehour4.setText("");
                }
            }
        });

        emin4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    emin4.setText("");
                }
            }
        });
        ehour5.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ehour5.setText("");
                }
            }
        });

        emin5.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    emin5.setText("");
                }
            }
        });
        ehour6.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ehour6.setText("");
                }
            }
        });

        emin6.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    emin6.setText("");
                }
            }
        });
        ehour7.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ehour7.setText("");
                }
            }
        });

        emin7.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    emin7.setText("");
                }
            }
        });
        //gcc_end


        ssend1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ssend1.setFocusable(true);
                ssend1.setFocusableInTouchMode(true);
                ssend1.requestFocus();
                return false;
            }
        });
        esend1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                esend1.setFocusable(true);
                esend1.setFocusableInTouchMode(true);
                esend1.requestFocus();
                return false;
            }
        });
        ssend2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ssend2.setFocusable(true);
                ssend2.setFocusableInTouchMode(true);
                ssend2.requestFocus();
                return false;
            }
        });
        esend2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                esend2.setFocusable(true);
                esend2.setFocusableInTouchMode(true);
                esend2.requestFocus();
                return false;
            }
        });
        ssend3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ssend3.setFocusable(true);
                ssend3.setFocusableInTouchMode(true);
                ssend3.requestFocus();
                return false;
            }
        });
        esend3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                esend3.setFocusable(true);
                esend3.setFocusableInTouchMode(true);
                esend3.requestFocus();
                return false;
            }
        });
        ssend4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ssend4.setFocusable(true);
                ssend4.setFocusableInTouchMode(true);
                ssend4.requestFocus();
                return false;
            }
        });
        esend4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                esend4.setFocusable(true);
                esend4.setFocusableInTouchMode(true);
                esend4.requestFocus();
                return false;
            }
        });
        ssend5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ssend5.setFocusable(true);
                ssend5.setFocusableInTouchMode(true);
                ssend5.requestFocus();
                return false;
            }
        });
        esend5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                esend5.setFocusable(true);
                esend5.setFocusableInTouchMode(true);
                esend5.requestFocus();
                return false;
            }
        });

        ssend6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ssend6.setFocusable(true);
                ssend6.setFocusableInTouchMode(true);
                ssend6.requestFocus();
                return false;
            }
        });
        esend6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                esend6.setFocusable(true);
                esend6.setFocusableInTouchMode(true);
                esend6.requestFocus();
                return false;
            }
        });
        ssend7.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ssend7.setFocusable(true);
                ssend7.setFocusableInTouchMode(true);
                ssend7.requestFocus();
                return false;
            }
        });
        esend7.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                esend7.setFocusable(true);
                esend7.setFocusableInTouchMode(true);
                esend7.requestFocus();
                return false;
            }
        });

        ysend1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ysend1.setFocusable(true);
                ysend1.setFocusableInTouchMode(true);
                ysend1.requestFocus();
                return false;
            }
        });
    }

    TC tc1 = new TC();
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_ssend1:
                count = 0;
                Hex nhex1 = new Hex();
                slnhour1 = nhex1.codeAddOne(Integer.toHexString(Integer.valueOf(nhour1, 10)), 4);
                slnmin1 = nhex1.codeAddOne(Integer.toHexString(Integer.valueOf(nmin1, 10)), 4);

                if (state) {
//                    gsend(1, nhour1, true, nmin1);


//                    slnhour2 = hex.codeAddOne(Integer.toHexString(Integer.valueOf(nhour2, 10)), 4);
                    String spj1 = tc1.testStringBuilder0(rng, slnhour1, slnmin1);
                    dmrbell1(spj1,"0100");
                    ssend1.setBackgroundResource(R.drawable.btn_bg_round_click2);
                    ssend1.setText("使能");
                    state = false;
                } else {
//                    slnhour1 = hex.codeAddOne(Integer.toHexString(Integer.valueOf(nhour1, 10)), 4);
//                    slnmin1 = hex.codeAddOne(Integer.toHexString(Integer.valueOf(nmin1, 10)), 4);
                    String spj1 = tc1.testStringBuilder0(ig, slnhour1, slnmin1);
                    dmrbell1(spj1,"0100");
                    ssend1.setBackgroundResource(R.drawable.btn_bg_round_click);
                    ssend1.setText("禁止");
                    state = true;
//                    gsend(1, nhour1, false, nmin1);

                }
                break;
            case R.id.btn_esend1:
                count = 0;
                Hex ehex1 = new Hex();
                slfhour1 = ehex1.codeAddOne(Integer.toHexString(Integer.valueOf(fhour1, 10)), 4);
                slfmin1 = ehex1.codeAddOne(Integer.toHexString(Integer.valueOf(fmin1, 10)), 4);
                if (state) {
                    state = false;
//                    gsend1(1, fhour1, true, fmin1);



                    String epj1 = tc1.testStringBuilder0(rng, slfhour1, slfmin1);
                    dmrbell1(epj1,"0118");
                    esend1.setBackgroundResource(R.drawable.btn_bg_round_click2);
                    esend1.setText("使能");
                } else {
                    state = true;
//                    gsend1(1, fhour1, false, fmin1);
//                    slfhour1 = hex.codeAddOne(Integer.toHexString(Integer.valueOf(fhour1, 10)), 4);
//                    slfmin1 = hex.codeAddOne(Integer.toHexString(Integer.valueOf(fmin1, 10)), 4);
                    String epj1 = tc1.testStringBuilder0(ig, slfhour1, slfmin1);
                    dmrbell1(epj1,"0118");
                    esend1.setBackgroundResource(R.drawable.btn_bg_round_click);
                    esend1.setText("禁止");
                }
                break;
            case R.id.btn_ssend2:
                count = 0;
                Hex nhex2 = new Hex();
                slnhour2 = nhex2.codeAddOne(Integer.toHexString(Integer.valueOf(nhour2, 10)), 4);
                slnmin2 = nhex2.codeAddOne(Integer.toHexString(Integer.valueOf(nmin2, 10)), 4);
                if (state) {
//                    gsend(2, nhour2, true, nmin2);
                    String spj2 = tc1.testStringBuilder0(rng, slnhour2, slnmin2);
                    dmrbell1(spj2,"0103");
                    ssend2.setBackgroundResource(R.drawable.btn_bg_round_click2);
                    ssend2.setText("使能");
                    state = false;
                } else {
//                    gsend(2, nhour2, false, nmin2);
                    String spj2 = tc1.testStringBuilder0(ig, slnhour2, slnmin2);
                    dmrbell1(spj2,"0103");
                    ssend2.setBackgroundResource(R.drawable.btn_bg_round_click);
                    ssend2.setText("禁止");
                    state = true;
                }
                break;
            case R.id.btn_esend2:
                count = 0;
                Hex ehex2 = new Hex();
                slfhour2 = ehex2.codeAddOne(Integer.toHexString(Integer.valueOf(fhour2, 10)), 4);
                slfmin2 = ehex2.codeAddOne(Integer.toHexString(Integer.valueOf(fmin2, 10)), 4);
                if (state) {
//                    gsend1(2, fhour2, true, fmin2);
                    String epj2 = tc1.testStringBuilder0(rng, slfhour2, slfmin2);
                    dmrbell1(epj2,"011b");
                    esend2.setBackgroundResource(R.drawable.btn_bg_round_click2);
                    esend2.setText("使能");
                    state = false;
                } else {
//                    gsend1(2, fhour2, false, fmin2);
                    String epj2 = tc1.testStringBuilder0(ig, slfhour2, slfmin2);
                    dmrbell1(epj2,"011b");
                    esend2.setBackgroundResource(R.drawable.btn_bg_round_click);
                    esend2.setText("禁止");
                    state = true;
                }
                break;
            case R.id.btn_ssend3:
                count = 0;
                Hex nhex3 = new Hex();
                slnhour3 = nhex3.codeAddOne(Integer.toHexString(Integer.valueOf(nhour3, 10)), 4);
                slnmin3 = nhex3.codeAddOne(Integer.toHexString(Integer.valueOf(nmin3, 10)), 4);
                if (state) {
//                    gsend(3, nhour3, true, nmin3);
                    String spj3 = tc1.testStringBuilder0(rng, slnhour3, slnmin3);
                    dmrbell1(spj3,"0106");
                    ssend3.setBackgroundResource(R.drawable.btn_bg_round_click2);
                    ssend3.setText("使能");
                    state = false;
                } else {
//                    gsend(3, nhour3, false, nmin3);
                    String spj3 = tc1.testStringBuilder0(ig, slnhour3, slnmin3);
                    dmrbell1(spj3,"0106");
                    ssend3.setBackgroundResource(R.drawable.btn_bg_round_click);
                    ssend3.setText("禁止");
                    state = true;
                }
                break;
            case R.id.btn_esend3:
                count = 0;
                Hex ehex3 = new Hex();
                slfhour3 = ehex3.codeAddOne(Integer.toHexString(Integer.valueOf(fhour3, 10)), 4);
                slfmin3 = ehex3.codeAddOne(Integer.toHexString(Integer.valueOf(fmin3, 10)), 4);
                if (state) {
//                    gsend1(3, fhour3, true, fmin3);
                    String epj3 = tc1.testStringBuilder0(rng, slfhour3, slfmin3);
                    dmrbell1(epj3,"0126");
                    esend3.setBackgroundResource(R.drawable.btn_bg_round_click2);
                    esend3.setText("使能");
                    state = false;
                } else {
//                    gsend1(3, fhour3, false, fmin3);
                    String epj3 = tc1.testStringBuilder0(ig, slfhour3, slfmin3);
                    dmrbell1(epj3,"0126");
                    esend3.setBackgroundResource(R.drawable.btn_bg_round_click);
                    esend3.setText("禁止");
                    state = true;
                }
                break;
            case R.id.btn_ssend4:
                count = 0;
                Hex nhex4 = new Hex();
                slnhour4 = nhex4.codeAddOne(Integer.toHexString(Integer.valueOf(nhour4, 10)), 4);
                slnmin4 = nhex4.codeAddOne(Integer.toHexString(Integer.valueOf(nmin4, 10)), 4);
                if (state) {
//                    gsend(4, nhour4, true, nmin4);
                    String spj4 = tc1.testStringBuilder0(rng, slnhour4, slnmin4);
                    dmrbell1(spj4,"0109");
                    ssend4.setBackgroundResource(R.drawable.btn_bg_round_click2);
                    ssend4.setText("使能");
                    state = false;
                } else {
//                    gsend(4, nhour4, false, nmin4);
                    String spj4 = tc1.testStringBuilder0(ig, slnhour4, slnmin4);
                    dmrbell1(spj4,"0109");
                    ssend4.setBackgroundResource(R.drawable.btn_bg_round_click);
                    ssend4.setText("禁止");
                    state = true;
                }
                break;
            case R.id.btn_esend4:
                count = 0;
                Hex ehex4 = new Hex();
                slfhour4 = ehex4.codeAddOne(Integer.toHexString(Integer.valueOf(fhour4, 10)), 4);
                slfmin4 = ehex4.codeAddOne(Integer.toHexString(Integer.valueOf(fmin4, 10)), 4);
                if (state) {
//                    gsend1(4, fhour4, true, fmin4);
                    String epj4 = tc1.testStringBuilder0(rng, slfhour4, slfmin4);
                    dmrbell1(epj4,"0121");
                    esend4.setBackgroundResource(R.drawable.btn_bg_round_click2);
                    esend4.setText("使能");
                    state = false;
                } else {
//                    gsend1(4, fhour4, false, fmin4);
                    String epj4 = tc1.testStringBuilder0(ig, slfhour4, slfmin4);
                    dmrbell1(epj4,"0121");
                    esend4.setBackgroundResource(R.drawable.btn_bg_round_click);
                    esend4.setText("禁止");
                    state = true;
                }
                break;
            case R.id.btn_ssend5:
                count = 0;
                Hex nhex5 = new Hex();
                slnhour5 = nhex5.codeAddOne(Integer.toHexString(Integer.valueOf(nhour5, 10)), 4);
                slnmin5 = nhex5.codeAddOne(Integer.toHexString(Integer.valueOf(nmin5, 10)), 4);
                if (state) {
//                    gsend(5, nhour5, true, nmin5);
                    String spj5 = tc1.testStringBuilder0(rng, slnhour5, slnmin5);
                    dmrbell1(spj5,"010c");
                    ssend5.setBackgroundResource(R.drawable.btn_bg_round_click2);
                    ssend5.setText("使能");
                    state = false;
                } else {
//                    gsend(5, nhour5, false, nmin5);
                    String spj5 = tc1.testStringBuilder0(ig, slnhour5, slnmin5);
                    dmrbell1(spj5,"010c");
                    ssend5.setBackgroundResource(R.drawable.btn_bg_round_click);
                    ssend5.setText("禁止");
                    state = true;
                }
                break;
            case R.id.btn_esend5:
                count = 0;
                Hex ehex5 = new Hex();
                slfhour5 = ehex5.codeAddOne(Integer.toHexString(Integer.valueOf(fhour5, 10)), 4);
                slfmin5 = ehex5.codeAddOne(Integer.toHexString(Integer.valueOf(fmin5, 10)), 4);
                if (state) {
//                    gsend1(5, fhour5, true, fmin5);
                    String epj5 = tc1.testStringBuilder0(rng, slfhour5, slfmin5);
                    dmrbell1(epj5,"0124");
                    esend5.setBackgroundResource(R.drawable.btn_bg_round_click2);
                    esend5.setText("使能");
                    state = false;
                } else {
//                    gsend1(5, fhour5, false, fmin5);
                    String epj5 = tc1.testStringBuilder0(ig, slfhour5, slfmin5);
                    dmrbell1(epj5,"0124");
                    esend5.setBackgroundResource(R.drawable.btn_bg_round_click);
                    esend5.setText("禁止");
                    state = true;
                }
                break;
            case R.id.btn_ssend6:
                count = 0;
                Hex nhex6 = new Hex();
                slnhour6 = nhex6.codeAddOne(Integer.toHexString(Integer.valueOf(nhour6, 10)), 4);
                slnmin6 = nhex6.codeAddOne(Integer.toHexString(Integer.valueOf(nmin6, 10)), 4);
                if (state) {
//                    gsend(6, nhour6, true, nmin6);
                    String spj6 = tc1.testStringBuilder0(rng, slnhour6, slnmin6);
                    dmrbell1(spj6,"010f");
                    ssend6.setBackgroundResource(R.drawable.btn_bg_round_click2);
                    ssend6.setText("使能");
                    state = false;
                } else {
//                    gsend(6, nhour6, false, nmin6);
                    String spj6 = tc1.testStringBuilder0(ig, slnhour6, slnmin6);
                    dmrbell1(spj6,"010f");
                    ssend6.setBackgroundResource(R.drawable.btn_bg_round_click);
                    ssend6.setText("禁止");
                    state = true;
                }
                break;
            case R.id.btn_esend6:
                count = 0;
                Hex ehex6 = new Hex();
                slfhour6 = ehex6.codeAddOne(Integer.toHexString(Integer.valueOf(fhour6, 10)), 4);
                slfmin6 = ehex6.codeAddOne(Integer.toHexString(Integer.valueOf(fmin6, 10)), 4);
                if (state) {
//                    gsend1(6, fhour6, true, fmin6);
                    String epj6 = tc1.testStringBuilder0(rng, slfhour6, slfmin6);
                    dmrbell1(epj6,"0127");
                    esend6.setBackgroundResource(R.drawable.btn_bg_round_click2);
                    esend6.setText("使能");
                    state = false;
                } else {
//                    gsend1(6, fhour6, false, fmin6);
                    String epj6 = tc1.testStringBuilder0(ig, slfhour6, slfmin6);
                    dmrbell1(epj6,"0127");
                    esend6.setBackgroundResource(R.drawable.btn_bg_round_click);
                    esend6.setText("禁止");
                    state = true;
                }
                break;
            case R.id.btn_ssend7:
                count = 0;
                Hex nhex7 = new Hex();
                slnhour7 = nhex7.codeAddOne(Integer.toHexString(Integer.valueOf(nhour7, 10)), 4);
                slnmin7 = nhex7.codeAddOne(Integer.toHexString(Integer.valueOf(nmin7, 10)), 4);
                if (state) {
//                    gsend(7, nhour7, true, nmin7);
                    String spj7 = tc1.testStringBuilder0(rng, slnhour7, slnmin7);
                    dmrbell1(spj7,"0112");
                    ssend7.setBackgroundResource(R.drawable.btn_bg_round_click2);
                    ssend7.setText("使能");
                    state = false;
                } else {
//                    gsend(7, nhour7, false, nmin7);
                    String spj7 = tc1.testStringBuilder0(ig, slnhour7, slnmin7);
                    dmrbell1(spj7,"0112");
                    ssend7.setBackgroundResource(R.drawable.btn_bg_round_click);
                    ssend7.setText("禁止");
                    state = true;
                }
                break;
            case R.id.btn_esend7:
                count = 0;
                Hex ehex7 = new Hex();
                slfhour7 = ehex7.codeAddOne(Integer.toHexString(Integer.valueOf(fhour7, 10)), 4);
                slfmin7 = ehex7.codeAddOne(Integer.toHexString(Integer.valueOf(fmin7, 10)), 4);
                if (state) {
//                    gsend1(7, fhour7, true, fmin7);
                    String epj7 = tc1.testStringBuilder0(rng, slfhour7, slfmin7);
                    dmrbell1(epj7,"012a");
                    esend7.setBackgroundResource(R.drawable.btn_bg_round_click2);
                    esend7.setText("使能");
                    state = false;
                } else {
//                    gsend1(7, fhour7, false, fmin7);
                    String epj7 = tc1.testStringBuilder0(ig, slfhour7, slfmin7);
                    dmrbell1(epj7,"012a");
                    esend7.setBackgroundResource(R.drawable.btn_bg_round_click);
                    esend7.setText("禁止");
                    state = true;
                }
                break;
            case R.id.btn_ysend1:
                count = 0;

                Hex yhex1 = new Hex();
                slstryhour1 = yhex1.codeAddOne(Integer.toHexString(Integer.valueOf(stryhour1, 10)), 4);
                slstrymin1 = yhex1.codeAddOne(Integer.toHexString(Integer.valueOf(strymin1, 10)), 4);
                if (state) {

                    String yspj1 = tc1.testStringBuilder0(rng, slstryhour1, slstrymin1);
                    dmrbell1(yspj1,"012d");
//                    dmrbell("03E8", "012d");
                    ysend1.setBackgroundResource(R.drawable.btn_bg_round_click2);
                    state = false;
                } else {
                    String yspj1 = tc1.testStringBuilder0(ig, slstryhour1, slstrymin1);
                    dmrbell1(yspj1,"012d");
                    ysend1.setBackgroundResource(R.drawable.btn_bg_round_click);
                    state = true;
                }
                break;


            default:
                break;
        }
    }

    //gcc_add —— 定时制水实时显示控制
//    public class Threads extends Thread {
//        public void run() {
//            try {
//                while (!stopThread) {
    public void getUserData24() {
        try {
            //定时开始制水   取数据
            String str1 = sp.getString("s21", ""); //周一时
            String str2 = sp.getString("s22", ""); //周一分
            String str3 = sp.getString("s23", ""); //周二时
            String str4 = sp.getString("s24", ""); //周二分
            String str5 = sp.getString("s25", ""); //周三时
            String str6 = sp.getString("s26", ""); //周三分
            String str7 = sp.getString("s27", ""); //周四时
            String str8 = sp.getString("s28", ""); //周四分
            String str9 = sp.getString("s29", ""); //周五时
            String str10 = sp.getString("s30", ""); //周五分
            String str11 = sp.getString("s31", ""); //周六时
            String str12 = sp.getString("s32", ""); //周六分
            String str13 = sp.getString("s33", ""); //周天时
            String str14 = sp.getString("s34", ""); //周天分

            if (str1.length() < 2) {
                shour1.setText("0" + str1);
            } else {
                shour1.setText(str1);
            }
            if (str3.length() < 2) {
                shour2.setText("0" + str3);
            } else {
                shour2.setText(str3);
            }
            if (str5.length() < 2) {
                shour3.setText("0" + str5);
            } else {
                shour3.setText(str5);
            }
            if (str7.length() < 2) {
                shour4.setText("0" + str7);
            } else {
                shour4.setText(str7);
            }
            if (str9.length() < 2) {
                shour5.setText("0" + str9);
            } else {
                shour5.setText(str9);
            }
            if (str11.length() < 2) {
                shour6.setText("0" + str11);
            } else {
                shour6.setText(str11);
            }
            if (str13.length() < 2) {
                shour7.setText("0" + str13);
            } else {
                shour7.setText(str13);
            }


            if (str2.length() < 2) {
                smin1.setText("0" + str2);
            } else {
                smin1.setText(str2);
            }
            if (str4.length() < 2) {
                smin2.setText("0" + str4);
            } else {
                smin2.setText(str4);
            }
            if (str6.length() < 2) {
                smin3.setText("0" + str6);
            } else {
                smin3.setText(str6);
            }
            //定时开始制水

            if (str8.length() < 2) {
                smin4.setText("0" + str8);
            } else {
                smin4.setText(str8);
            }
            if (str10.length() < 2) {
                smin5.setText("0" + str10);
            } else {
                smin5.setText(str10);
            }
            if (str12.length() < 2) {
                smin6.setText("0" + str12);
            } else {
                smin6.setText(str12);
            }
            if (str14.length() < 2) {
                smin7.setText("0" + str14);
            } else {
                smin7.setText(str14);
            }


//            if (fhour2.length() < 2) {
//                ehour2.setText("0" + fhour2);
//            } else {
//                ehour2.setText(fhour2);
//            }

//            ehour2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//                @Override
//                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                    if (actionId == EditorInfo.IME_ACTION_DONE) {
//                        if (fhour2.length() < 2) {
//                            ehour2.setText("0" + fhour2);
//                        } else {
//                            ehour2.setText(fhour2);
//                        }
//                        if (emin2.length() < 2) {
//                            emin2.setText("0" + fmin2);
//                        } else {
//                            emin2.setText(fmin2);
//                        }
//                        return true;
//                    }
//                    return false;
//                }
//            });

            //定时开始制水
            String ms1 = sp.getString("suo1", ""); //周一使能
            String ms2 = sp.getString("suo2", ""); //周二使能
            String ms3 = sp.getString("suo3", ""); //周三使能
            String ms4 = sp.getString("suo4", ""); //周四使能
            String ms5 = sp.getString("suo5", ""); //周五使能
            String ms6 = sp.getString("suo6", ""); //周六使能
            String ms7 = sp.getString("suo7", ""); //周天使能

            if (ms1.equals("0001")) {
                ssend1.setBackgroundResource(R.drawable.btn_bg_round_click2);
                ssend1.setText("使能");
            } else {
                ssend1.setBackgroundResource(R.drawable.btn_bg_round_click);
                ssend1.setText("禁止");
            }
            if (ms2.equals("0001")) {
                ssend2.setBackgroundResource(R.drawable.btn_bg_round_click2);
                ssend2.setText("使能");
            } else {
                ssend2.setBackgroundResource(R.drawable.btn_bg_round_click);
                ssend2.setText("禁止");
            }
            if (ms3.equals("0001")) {
                ssend3.setBackgroundResource(R.drawable.btn_bg_round_click2);
                ssend3.setText("使能");
            } else {
                ssend3.setBackgroundResource(R.drawable.btn_bg_round_click);
                ssend3.setText("禁止");
            }
            if (ms4.equals("0001")) {
                ssend4.setBackgroundResource(R.drawable.btn_bg_round_click2);
                ssend4.setText("使能");
            } else {
                ssend4.setBackgroundResource(R.drawable.btn_bg_round_click);
                ssend4.setText("禁止");
            }
            if (ms5.equals("0001")) {
                ssend5.setBackgroundResource(R.drawable.btn_bg_round_click2);
                ssend5.setText("使能");
            } else {
                ssend5.setBackgroundResource(R.drawable.btn_bg_round_click);
                ssend5.setText("禁止");
            }
            if (ms6.equals("0001")) {
                ssend6.setBackgroundResource(R.drawable.btn_bg_round_click2);
                ssend6.setText("使能");
            } else {
                ssend6.setBackgroundResource(R.drawable.btn_bg_round_click);
                ssend6.setText("禁止");
            }
            if (ms7.equals("0001")) {
                ssend7.setBackgroundResource(R.drawable.btn_bg_round_click2);
                ssend7.setText("使能");
            } else {
                ssend7.setBackgroundResource(R.drawable.btn_bg_round_click);
                ssend7.setText("禁止");
            }

            //定时停止制水   取数据
            String str15 = sp.getString("s35", ""); //周一时
            String str16 = sp.getString("s36", ""); //周一分
            String str17 = sp.getString("s37", ""); //周二时
            String str18 = sp.getString("s38", ""); //周二分
            String str19 = sp.getString("s39", ""); //周三时
            String str20 = sp.getString("s40", ""); //周三分
            String str21 = sp.getString("s41", ""); //周四时
            String str22 = sp.getString("s42", ""); //周四分
            String str23 = sp.getString("s43", ""); //周五时
            String str24 = sp.getString("s44", ""); //周五分
            String str25 = sp.getString("s45", ""); //周六时
            String str26 = sp.getString("s46", ""); //周六分
            String str27 = sp.getString("s47", ""); //周天时
            String str28 = sp.getString("s48", ""); //周天分

            //定时停止制水

            if (str15.length() < 2) {
                ehour1.setText("0" + str15);
            } else {
                ehour1.setText(str15);
            }
            if (str16.length() < 2) {
                emin1.setText("0" + str16);
            } else {
                emin1.setText(str16);
            }
            if (str17.length() < 2) {
                ehour2.setText("0" + str17);
            } else {
                ehour2.setText(str17);
            }
            if (str18.length() < 2) {
                emin2.setText("0" + str18);
            } else {
                emin2.setText(str18);
            }
            if (str19.length() < 2) {
                ehour3.setText("0" + str19);
            } else {
                ehour3.setText(str19);
            }
            if (str20.length() < 2) {
                emin3.setText("0" + str20);
            } else {
                emin3.setText(str20);
            }

            if (str21.length() < 2) {
                ehour4.setText("0" + str21);
            } else {
                ehour4.setText(str21);
            }
            if (str22.length() < 2) {
                emin4.setText("0" + str22);
            } else {
                emin4.setText(str22);
            }
            if (str23.length() < 2) {
                ehour5.setText("0" + str23);
            } else {
                ehour5.setText(str23);
            }
            if (str24.length() < 2) {
                emin5.setText("0" + str24);
            } else {
                emin5.setText(str24);
            }
            if (str25.length() < 2) {
                ehour6.setText("0" + str25);
            } else {
                ehour6.setText(str25);
            }
            if (str26.length() < 2) {
                emin6.setText("0" + str26);
            } else {
                emin6.setText(str26);
            }

            if (str27.length() < 2) {
                ehour7.setText("0" + str27);
            } else {
                ehour7.setText(str27);
            }
            if (str28.length() < 2) {
                emin7.setText("0" + str28);
            } else {
                emin7.setText(str28);
            }


            //定时停止制水
            String ms8 = sp.getString("suo8", ""); //周一使能
            String ms9 = sp.getString("suo9", ""); //周二使能
            String ms10 = sp.getString("suo10", ""); //周三使能
            String ms11 = sp.getString("suo11", ""); //周四使能
            String ms12 = sp.getString("suo12", ""); //周五使能
            String ms13 = sp.getString("suo13", ""); //周六使能
            String ms14 = sp.getString("suo14", ""); //周天使能

            if (ms8.equals("0001")) {
                esend1.setBackgroundResource(R.drawable.btn_bg_round_click2);
                esend1.setText("使能");
            } else {
                esend1.setBackgroundResource(R.drawable.btn_bg_round_click);
                esend1.setText("禁止");
            }
            if (ms9.equals("0001")) {
                esend2.setBackgroundResource(R.drawable.btn_bg_round_click2);
                esend2.setText("使能");
            } else {
                esend2.setBackgroundResource(R.drawable.btn_bg_round_click);
                esend2.setText("禁止");
            }
            if (ms10.equals("0001")) {
                esend3.setBackgroundResource(R.drawable.btn_bg_round_click2);
                esend3.setText("使能");
            } else {
                esend3.setBackgroundResource(R.drawable.btn_bg_round_click);
                esend3.setText("禁止");
            }
            if (ms11.equals("0001")) {
                esend4.setBackgroundResource(R.drawable.btn_bg_round_click2);
                esend4.setText("使能");
            } else {
                esend4.setBackgroundResource(R.drawable.btn_bg_round_click);
                esend4.setText("禁止");
            }
            if (ms12.equals("0001")) {
                esend5.setBackgroundResource(R.drawable.btn_bg_round_click2);
                esend5.setText("使能");
            } else {
                esend5.setBackgroundResource(R.drawable.btn_bg_round_click);
                esend5.setText("禁止");
            }
            if (ms13.equals("0001")) {
                esend6.setBackgroundResource(R.drawable.btn_bg_round_click2);
                esend6.setText("使能");
            } else {
                esend6.setBackgroundResource(R.drawable.btn_bg_round_click);
                esend6.setText("禁止");
            }
            if (ms14.equals("0001")) {
                esend7.setBackgroundResource(R.drawable.btn_bg_round_click2);
                esend7.setText("使能");
            } else {
                esend7.setBackgroundResource(R.drawable.btn_bg_round_click);
                esend7.setText("禁止");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //通用协议接口
    public void dmrbell(String content, String startadd) {

        ZhiShui zhiShui1 = new ZhiShui();
        zhiShui1.setCommond("0110"); //固定指令，当前远程控制0110
        zhiShui1.setContent(content); //远程控制内容
        zhiShui1.setDate(String.valueOf(new Date().getTime()));
        zhiShui1.setDeviceNum(mr); // 设备号
        zhiShui1.setEtypecode(ConsTant.ETYPECODE_JZGY);
        zhiShui1.setIemi(imei1);
        zhiShui1.setNum("0001"); //修改寄存器数量
        zhiShui1.setNumLen("02"); //修改字节长度
        zhiShui1.setPhone(phonenumber1);
        zhiShui1.setStartAdd(startadd); //远程修改寄存器起始地址
        zhiShui1.setUid(uid1);
        String json = JSON.toJSONString(zhiShui1);
        OkHttpUtils3 okHttpUtils = new OkHttpUtils3(this);
        okHttpUtils.post(WebUrls.getcustomurl, json);
    }

    //通用协议接口
    public void dmrbell1(String content, String startadd) {

        ZhiShui zhiShui1 = new ZhiShui();
        zhiShui1.setCommond("0110"); //固定指令，当前远程控制0110
        zhiShui1.setContent(content); //远程控制内容
        zhiShui1.setDate(String.valueOf(new Date().getTime()));
        zhiShui1.setDeviceNum(mr); // 设备号
        zhiShui1.setEtypecode(ConsTant.ETYPECODE_JZGY);
        zhiShui1.setIemi(imei1);
        zhiShui1.setNum("0003"); //修改寄存器数量
        zhiShui1.setNumLen("06"); //修改字节长度
        zhiShui1.setPhone(phonenumber1);
        zhiShui1.setStartAdd(startadd); //远程修改寄存器起始地址
        zhiShui1.setUid(uid1);
        String json = JSON.toJSONString(zhiShui1);
        OkHttpUtils3 okHttpUtils = new OkHttpUtils3(this);
        okHttpUtils.post(WebUrls.getcustomurl, json);
    }

    // 封装函数  《==》  传参

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTimer.cancel();//取消任务
        handler.removeCallbacks(mTimerTask);//取消任务
        Log.d("疾风剑豪2：", "onDestroy被调用了------------>");

    }
}








