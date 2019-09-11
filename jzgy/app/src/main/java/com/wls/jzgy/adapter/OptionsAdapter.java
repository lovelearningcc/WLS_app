package com.wls.jzgy.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wls.jzgy.R;
import com.wls.jzgy.activity.LoginActivity;
import com.wls.jzgy.business.AccountDao;
import com.wls.jzgy.business.HistoryInfo;

import java.util.ArrayList;


/**
 * Created by louliang on 2018/05/02.
 */

public class OptionsAdapter extends BaseAdapter {
    private ArrayList <HistoryInfo> list = new ArrayList <HistoryInfo>();
    private LoginActivity activity = null;
    AccountDao dao;


    public OptionsAdapter(Activity activity, ArrayList <HistoryInfo> list, AccountDao accountDao) {
        this.activity = (LoginActivity) activity;
        this.dao = accountDao;
        this.list = list;


    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            //下拉项布局
            convertView = LayoutInflater.from(activity).inflate(R.layout.option_item, null);
            holder.textView = (TextView) convertView.findViewById(R.id.item_text);
//            holder.nickName = (TextView) convertView.findViewById(R.id.nick);
            holder.imageView = (ImageView) convertView.findViewById(R.id.delImage);
            holder.rl_item = (RelativeLayout) convertView.findViewById(R.id.rl_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final HistoryInfo historyInfo = list.get(position);
        holder.textView.setText(historyInfo.getPhone());
//        holder.nickName.setText(historyInfo.getName());
        //为下拉框选项删除图标部分设置事件，最终效果是点击将该选项删除
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity,R.style.dialog);
                //        builder.setIcon(R.drawable.picture);
//                builder.setTitle("温馨提示");
                builder.setMessage("是否删除！");
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        list.remove(historyInfo);
                        dao.delete(historyInfo.getPhone());
                        activity.setVivi();
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("否", null);
                AlertDialog dialog = builder.create();
                dialog.show();
//                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#2a96c5"));
//                dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.WHITE);

//                list.remove(historyInfo);
//                dao.delete(historyInfo.getPhone());
//                activity.setVivi();
//                notifyDataSetChanged();
            }
        });
        notifyDataSetChanged();
        return convertView;
    }

    static class ViewHolder {
        TextView textView;
//        TextView nickName;
        ImageView imageView;
        RelativeLayout rl_item;
    }
}

