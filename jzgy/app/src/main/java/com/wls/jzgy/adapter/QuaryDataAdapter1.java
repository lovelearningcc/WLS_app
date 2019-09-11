package com.wls.jzgy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wls.jzgy.R;
import com.wls.jzgy.entity.UserData;
import com.wls.jzgy.widget.CircleProgressText;

import java.util.List;

/**
 * Author：chenjr .
 * UpdateTime：2018-06-15
 * Version：v1.0 查询数据适配
 */

public class QuaryDataAdapter1 extends BaseAdapter {

    private Context context;
    private List<UserData> lists;
    private int flag = 0;
    private int toal;

    public QuaryDataAdapter1(Context context, List<UserData> lists, int flag, int toal) {
        this.context = context;
        this.lists = lists;
        this.flag = flag;
        this.toal = toal;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_user_data, null);
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
//            holder.tv_startend_time = (TextView) convertView.findViewById(R.id.tv_startend_time);
            holder.tv_total_time = (TextView) convertView.findViewById(R.id.tv_total_time);
            holder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            holder.progressText = (CircleProgressText) convertView.findViewById(R.id.progress);
            //zy_and
            //holder.tv_section = (TextView) convertView.findViewById(R.id.tv_section);
            //end
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //holder.tv_time.setText("佩戴时间："+lists.get(position).getTime());
//        holder.tv_startend_time.setText("起止时间："+lists.get(position).getTime());
        if (0 == flag) {
            holder.tv_time.setText("佩戴时间：" + lists.get(position).getTime());
//            holder. tv_total_time.setText("今日累计时长："+ MyUtils.changeTime(toal));
        } else if (1 == flag) {
            holder.tv_time.setText("佩戴时间：" + lists.get(position).getTime());
//            holder. tv_total_time.setText("本周累计时长："+ MyUtils.changeTime(toal));
        } else if (2 == flag) {
            holder.tv_time.setText("佩戴时间：" + lists.get(position).getTime());
//            holder. tv_total_time.setText("本月累计时长："+ MyUtils.changeTime(toal));
        }
        //zy_and
        else if (3 == flag) {
            holder.tv_time.setText("佩戴时间：" + lists.get(position).getTime());
//            holder. tv_total_time.setText("上周累计时长："+ MyUtils.changeTime(toal));
        } else if (4 == flag) {
            holder.tv_time.setText("干预日期：" + lists.get(position).getTime());
//            holder. tv_total_time.setText("干预时间："+ MyUtils.changeTime(toal));
        } else if (5 == flag) {
            holder.tv_time.setText("轨迹运行时间：" + lists.get(position).getTime());
//            holder. tv_total_time.setText("轨迹时间："+ MyUtils.changeTime(toal));
        } else if (6 == flag) {
            holder.tv_time.setText("运行时间：" + lists.get(position).getTime());
//            holder. tv_total_time.setText("不合格："+ MyUtils.changeTime(toal));
        } else if (7 == flag) {
            holder.tv_time.setText("支付时间：" + lists.get(position).getTime());
//            holder. tv_total_time.setText("支付时间："+ MyUtils.changeTime(toal));
        }
        //end
//        holder.tv_content.setText(MyUtils.changeTime(lists.get(position).getData()));
        if (lists.get(position).getData() > 0) {
            holder.progressText.setProgress(Math.round(lists.get(position).getData() * 10 / 18));
        }
        return convertView;
    }

    class ViewHolder {
        TextView tv_time, tv_startend_time, tv_total_time, tv_content;
        CircleProgressText progressText;
        //zy_and
        TextView tv_section;
        //end
    }
}
