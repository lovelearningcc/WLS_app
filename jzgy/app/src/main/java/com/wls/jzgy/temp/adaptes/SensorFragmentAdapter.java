package com.wls.jzgy.temp.adaptes;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wls.jzgy.R;
import com.wls.jzgy.temp.domain.SensorFragData;
import com.wls.jzgy.widget.CircleProgressText;

import java.util.ArrayList;

/**
 * @Author kklu
 * @Email W258840818@163.com
 * @Data 2019/1/22 10:58
 * @Description 传感器部分适配器
 */
public class SensorFragmentAdapter extends RecyclerView.Adapter<SensorFragmentAdapter.ViewHolder> {

    private static final String TAG = "SensorFragmentAdapter1";
    private ArrayList<SensorFragData> dataArrayList = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frag_sensor_item_user_data, parent, false);
        return new ViewHolder(view);
    }

    public void setData(ArrayList<SensorFragData> dataArrayList) {
        this.dataArrayList = dataArrayList;
        notifyDataSetChanged();
        Log.i(TAG, new Gson().toJson(dataArrayList) + "");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SensorFragData data = dataArrayList.get(position);
        Long time = data.getdTime();
        String distance = data.getDistance();
        int userid = data.getUserId();

        holder.tv_time.setText("触发时间：" + System.currentTimeMillis());
        holder.tv_total_time.setText("触发次数：" + distance);
        holder.tv_content.setText("" + userid);
        holder.tv_percent.setText("用户ID");
    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_time;
        TextView tv_startend_time;
        TextView tv_total_time;
        TextView tv_content;
        TextView tv_percent;
        CircleProgressText progressText;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_startend_time = itemView.findViewById(R.id.tv_startend_time);
            tv_total_time = itemView.findViewById(R.id.tv_total_time);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_percent = itemView.findViewById(R.id.tv_percent);
            progressText = itemView.findViewById(R.id.progress);
        }
    }
}
