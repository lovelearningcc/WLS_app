package com.wls.jzgy.persions;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.wls.jzgy.R;

public class AlertDialogConfirm {

    private Context context;
    private Dialog dialog;
    private LinearLayout lLayout_bg;
    private EditText edit_pwd;
    private Button btn_neg;
    private Button btn_pos;
    private Display display;

    private onClickAlertDialog onAlertDialog;
    public AlertDialogConfirm(Context context, onClickAlertDialog onAlertDialog) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
        this.onAlertDialog=onAlertDialog;
    }

    public AlertDialogConfirm builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(R.layout.alert_dialog_save, null);
        // 获取自定义Dialog布局中的控件
        lLayout_bg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
        edit_pwd = (EditText) view.findViewById(R.id.edit_pwd);
        edit_pwd.setVisibility(View.VISIBLE);
        btn_neg = (Button) view.findViewById(R.id.btn_neg);
        btn_neg.setVisibility(View.VISIBLE);
        btn_pos = (Button) view.findViewById(R.id.btn_pos);
        btn_pos.setVisibility(View.VISIBLE);

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(view);

        // 调整dialog背景大小
        lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.80), LayoutParams.WRAP_CONTENT));

        btn_pos.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onAlertDialog.onClickConfirm(edit_pwd.getText().toString().trim());
                dialog.dismiss();
            }
        });
        btn_neg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onAlertDialog.onClickCancel();
                dialog.dismiss();
            }
        });
        return this;
    }

    public void show() {
        dialog.show();
    }

    public interface onClickAlertDialog{
        void onClickConfirm(String value);
        void onClickCancel();
    }
}

