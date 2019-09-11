package com.wls.jzgy.persions;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wls.jzgy.R;

public class UserInfoActivity extends Activity {

    private TextView title_bar_title;
    private ImageView back;

//    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g_userinfo);

//        img = (ImageView)findViewById(R.id.img);

        title_bar_title = findViewById(R.id.tv_title_bar_title);
        title_bar_title.setText("个人资料");
        back = findViewById(R.id.iv_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

//
//    public void shibieOCR(View view){
//        Intent intent = new Intent(this,ScanCardActivity_q.class);
//        startActivityForResult(intent,123);
//    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode){
//            case 123:
//                if(data == null){
//                    return;
//                }
//                Bitmap bitmapy = ScanCardActivity_q.bitmap;
////                CardInfo info = (CardInfo) data.getSerializableExtra("card");
////                tv_name.setText(info.getName());
////                tv_num.setText(info.getNum());
////                //zy_and
////                et_number.setText(info.getNum());
//                //end
//                if(bitmapy == null){
//                    return;
//                }
//                img.setImageBitmap(bitmapy);
//                break;
//        }
//    }
}

