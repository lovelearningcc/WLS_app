package com.wls.jzgy.persions;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wls.jzgy.R;

/**
 * Author：zy .
 * UpdateTime：2018/9/28
 * Version：v1.0
 */
public class PhoneActivity extends Activity {
    private static final int REQUEST_CALL = 1;
    private static final String SERVICE_TEL1 = "tel:82000808";
    private static final String SERVICE_TEL2 = "tel:18349112013";

    private RadioGroup numberRg;
    private RadioButton num1Radio;
    private RadioButton num2Radio;
    private Button btcancel, btdial;
    private boolean isNum1;
    private boolean isNum2;

    private TextView title_bar_title;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone);
        numberRg = (RadioGroup) findViewById(R.id.rgnumber);
        num1Radio = (RadioButton) findViewById(R.id.number1);
        num2Radio = (RadioButton) findViewById(R.id.number2);
        btcancel = (Button) findViewById(R.id.btn_cancel);
        btdial = (Button) findViewById(R.id.btn_dial);

        title_bar_title = findViewById(R.id.tv_title_bar_title);
        back = findViewById(R.id.iv_back);
        title_bar_title.setText("联系客服");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        numberRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.number1:
                        isNum1 = true;
                        isNum2 = false;
                        break;
                    case R.id.number2:
                        isNum1 = false;
                        isNum2 = true;
                        break;
                }
            }
        });

        btcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        btdial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneCall();
            }
        });
    }

    private void phoneCall() {
        if (num1Radio.isChecked() && !num2Radio.isChecked()) {
            //记录联系客服操作
            int haslocationPermission = PermissionChecker.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
            if (haslocationPermission != PermissionChecker.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                //startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(SERVICE_TEL1)));

                //为了避免有的设备没有拨号权限
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(SERVICE_TEL1));
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException exception) {
                    Toast.makeText(this, "未检测到SIM卡！", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (!num1Radio.isChecked() && num2Radio.isChecked()) {
            //记录联系客服操作
            int haslocationPermission = PermissionChecker.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
            if (haslocationPermission != PermissionChecker.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                //startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(SERVICE_TEL2)));

                //为了避免有的设备没有拨号权限
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(SERVICE_TEL2));
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException exception) {
                    Toast.makeText(this, "未检测到SIM卡！", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
}
