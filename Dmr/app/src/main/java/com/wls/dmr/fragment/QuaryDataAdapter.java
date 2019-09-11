package com.wls.dmr.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wls.dmr.R;
import com.wls.dmr.entity.UserData;
import com.wls.dmr.functions.RTDActivity;
import com.wls.dmr.widget.CircleProgressText;

import java.util.List;

/**
 * Author：chenjr .
 * UpdateTime：2018-06-15
 * Version：v1.0 查询数据适配
 */

public class QuaryDataAdapter extends BaseAdapter {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private Context context;
    private List<UserData> lists;
    private int flag=0;

//    //gcc_add —— 心跳包


    String eid1,type1;
    public QuaryDataAdapter(Context context, List<UserData> lists, int flag) {
        this.context = context;
        this.lists = lists;
        this.flag=flag;
//      this.toal=toal;
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

        sp = this.context.getSharedPreferences("xtb_data", Context.MODE_PRIVATE);
        editor = sp.edit();

        if (convertView == null) {
            holder = new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(R.layout.item_user_data, null);
            holder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            holder.tv_percent = convertView.findViewById(R.id.tv_percent);
            holder.progressText = (CircleProgressText) convertView.findViewById(R.id.progress);
            holder.tz = convertView.findViewById(R.id.rl_tz);

            holder.tv_num = convertView.findViewById(R.id.tv_num);

            holder.tv_eid = convertView.findViewById(R.id.tv_eid);
            holder.tv_type = convertView.findViewById(R.id.tv_type);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (0==flag){
            holder.tv_eid.setText( "设备  eid:"+"  " + lists.get(position).getEid());
            holder.tv_type.setText("设备类型:"+ "  " + lists.get(position).getType());
            holder.tv_content.setText(lists.get(position).getProvince());
            holder.tv_percent.setText(lists.get(position).getCity());
            holder.tv_num.setText(lists.get(position).getNum());
        }else if(1==flag){
            holder.tv_eid.setText( "设备  eid:"+"  " + lists.get(position).getEid());
            holder.tv_type.setText("设备类型:"+ "  " + lists.get(position).getType());
            holder.tv_content.setText(lists.get(position).getProvince());
            holder.tv_percent.setText(lists.get(position).getCity());
            holder.tv_num.setText(lists.get(position).getNum());
            holder.tz.setEnabled(false);
        }


        holder.tz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.startActivity(new Intent(context, RTDActivity.class));
                eid1 = lists.get(position).getEid();
                type1 = lists.get(position).getType();

                saveAccount();
            }
        });
        if (lists.get(position).getData()>0){
            holder.progressText.setProgress(Math.round(lists.get(position).getData() *10/ 18));
        }
        return convertView;
    }

    public void saveAccount() {

        editor.putString("eid1", eid1);
        editor.putString("type1", type1);
        editor.commit();
    }

    class ViewHolder{

        TextView tv_content;
        TextView tv_eid;
        TextView tv_type;
        TextView tv_percent;
        TextView tv_num;
        CircleProgressText progressText;

        RelativeLayout tz;
        //end
    }
}
