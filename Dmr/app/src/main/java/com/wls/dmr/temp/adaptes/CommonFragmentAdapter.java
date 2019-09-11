package com.wls.dmr.temp.adaptes;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wls.dmr.R;
import com.wls.dmr.temp.domain.CommonFragData;
import com.wls.dmr.widget.CircleProgressText;

import java.util.ArrayList;

import static com.wls.dmr.temp.utils.UrlUtils.URL_CHECK_USER;
import static com.wls.dmr.temp.utils.UrlUtils.URL_DAY;
import static com.wls.dmr.temp.utils.UrlUtils.URL_LASE_WEEK;
import static com.wls.dmr.temp.utils.UrlUtils.URL_MONTH;
import static com.wls.dmr.temp.utils.UrlUtils.URL_PAY_MENT;
import static com.wls.dmr.temp.utils.UrlUtils.URL_THIS_WEEK;

/**
 * @Author kklu
 * @Email W258840818@163.com
 * @Data 2019/1/22 10:58
 * @Description  除了传感器其他部分的适配器
 */
public class CommonFragmentAdapter extends RecyclerView.Adapter<CommonFragmentAdapter.ViewHolder> {

    private static final String TAG = "CommonFragmentAdapter1";
    private ArrayList<CommonFragData> dataArrayList = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        Log.e("!!!!!!!!!!!", "222222222222222222222222222222222》》》");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frag_common_item_user_data, parent, false);
        return new ViewHolder(view);
    }

    public void setData(ArrayList<CommonFragData> dataArrayList) {
        this.dataArrayList = dataArrayList;
        notifyDataSetChanged();
        Log.i(TAG, new Gson().toJson(dataArrayList)+"");
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CommonFragData data = dataArrayList.get(position);
        Log.e("!!!!!!!!!!!", "333333333333333333333333333》》》");

        String time = data.getTime();
        int mData = data.getData();
        int total = data.getTotal();
        int userId = data.getUserId();
        String urlTag = data.getUrlTag();
        if (urlTag.equals(URL_DAY)) {//今天
            holder.tv_time.setText("佩戴时间：" + time);
            holder.tv_total_time.setText("数据总条数：" + total +"条");
//            holder.tv_content.setText(""+MyUtils.changeTime(mData));
            holder.tv_percent.setText("单次佩戴时间");
            if(mData>0){
                holder.progressText.setProgress(Math.round(mData*10/18));
            }
        } else if (urlTag.equals(URL_THIS_WEEK)) {//本周
            holder.tv_time.setText("佩戴时间：" + time);
            holder.tv_total_time.setText("数据总条数：" + total +"条");
//            holder.tv_content.setText("" + MyUtils.changeTime(mData));
            holder.tv_percent.setText("单次佩戴时间");
            if(mData>0){
                holder.progressText.setProgress(Math.round(mData*10/18));
            }
        } else if (urlTag.equals(URL_MONTH)) {//本月
            holder.tv_time.setText("佩戴时间：" + time);
            holder.tv_total_time.setText("数据总条数：" + total +"条");
//            holder.tv_content.setText("" + MyUtils.changeTime(mData));
            holder.tv_percent.setText("单次佩戴时间");
            if(mData>0){
                holder.progressText.setProgress(Math.round(mData*10/18));
            }
        } else if (urlTag.equals(URL_LASE_WEEK)) {//上周
            holder.tv_time.setText("佩戴时间：" + time);
            holder.tv_total_time.setText("数据总条数：" + total +"条");
//            holder.tv_content.setText("" + MyUtils.changeTime(mData));
            holder.tv_percent.setText("单次佩戴时间");
            if(mData>0){
                holder.progressText.setProgress(Math.round(mData*10/18));
            }
        } else if (urlTag.equals(URL_PAY_MENT)) {//支付
            holder.tv_time.setText("支付时间：" + time);
            holder.tv_total_time.setText("数据总条数：" + total +"条");
//            holder.tv_content.setText("" + MyUtils.changeTime(mData));
            holder.tv_percent.setText("单次佩戴时间");

        } else if (urlTag.equals(URL_CHECK_USER)) {//不合格
            holder.tv_time.setText("运行时间：" + time);
            holder.tv_total_time.setText("数据总条数：" + total +"条");
            holder.tv_content.setText("无");
            holder.tv_percent.setText("单次佩戴时间");
            if(mData>0){
                holder.progressText.setProgress(Math.round(mData*10/18));
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_time;
        TextView tv_total_time;
        TextView tv_content;
        TextView tv_percent;
        CircleProgressText progressText;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_time = itemView.findViewById(R.id.tv_time);
//            tv_startend_time = itemView.findViewById(R.id.tv_startend_time);
            tv_total_time = itemView.findViewById(R.id.tv_total_time);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_percent = itemView.findViewById(R.id.tv_percent);
            progressText = itemView.findViewById(R.id.progress);
        }
    }
}
