package com.wls.dmr.persions;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wls.dmr.R;

public class UserInfoActivity extends Activity {

    private TextView title_bar_title;
    private LinearLayout back;
    private ImageView back1;

//    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g_userinfo);

        title_bar_title = findViewById(R.id.tv_title_bar_title);
        title_bar_title.setText("个人资料");
        back = findViewById(R.id.lin_back);
        back1 = findViewById(R.id.iv_back);
        back.setOnClickListener(v -> finish());
        back1.setOnClickListener(v -> finish());
    }
}

