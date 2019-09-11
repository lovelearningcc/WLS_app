package com.wls.jzgy.http;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.wls.jzgy.utils.WebUrls;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpUtils3 {
    /**
     * mdiatype 这个需要和服务端保持一致
     */
    private  final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    MediaType mediaType = MediaType.parse("application/json; charset=utf-8");//"类型,字节码"

    /**
     * 获取类名
     */
    private  final String TAG = OkHttpUtils3.class.getSimpleName();
    /**
     * 单利引用
     */
    private static volatile OkHttpUtils3 mInstance;

    /**
     * get请求
     */
//    public static final int TYPE_GET = 0;

    /**
     * post请求参数为json
     */
    public static final int TYPE_POST_JSON = 0;

    /**
     * post请求参数为表单
     */
//    public static final int TYPE_POST_FORM = 2;

    /**
     * okHttpClient 实例
     */
    private OkHttpClient mOkHttpClient;

    /**
     * 全局处理子线程和M主线程通信
     */
    private Handler okHttpHandler;

    /**
     * 初始化RequestManager
     */
    public OkHttpUtils3(Context context) {
        //初始化OkHttpClient
        mOkHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(10, TimeUnit.SECONDS)//设置写入超时时间
                .build();
        //初始化Handler
        okHttpHandler = new Handler(context.getMainLooper());
    }

    /**
     * 获取单例引用
     *
     * @return
     */
    public static OkHttpUtils3 getInstance(Context context) {
        OkHttpUtils3 inst = mInstance;
        if (inst == null) {
            synchronized (OkHttpUtils3.class) {
                inst = mInstance;
                if (inst == null) {
                    inst = new OkHttpUtils3(context.getApplicationContext());
                    mInstance = inst;
                }
            }
        }
        return inst;
    }


    /**
     * TODO okHttp异步请求统一入口
     *
     * @param actionUrl   接口地址
     * @param requestType 请求类型
     * @param paramsMap   请求参数
     * @param callBack    请求返回数据回调
     * @param <T>         数据泛型
     **/
    public <T> Call requestAsyn(String url, int requestType, String jsonStr, ReqCallBack<T> callBack) {
        Call call = null;
        switch (requestType) {
//            case TYPE_GET:
//                call = requestGetByAsyn(actionUrl, paramsMap, callBack);
//                break;
            case TYPE_POST_JSON:
                call = post1(url, jsonStr, callBack);
                break;
//            case TYPE_POST_FORM:
//                call = requestPostByAsynWithForm(actionUrl, paramsMap, callBack);
//                break;
        }
        return call;
    }


    public <T> Call post1(String url, String jsonStr, final ReqCallBack<T> callBack) {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");//"类型,字节码"
        try {
            //1.创建OkHttpClient对象
            mOkHttpClient = new OkHttpClient();
            //2.通过RequestBody.create 创建requestBody对象
            RequestBody requestBody = RequestBody.create(mediaType, jsonStr);
            //3.创建Request对象，设置URL地址，将RequestBody作为post方法的参数传入
            final Request request = new Request.Builder().url(url).post(requestBody).build();
            //4.创建一个call对象,参数就是Request请求对象
            Call call = mOkHttpClient.newCall(request);
            //5.请求加入调度,重写回调方法
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println("￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥");
                    System.out.println("请求失败");
                    failedCallBack("访问失败", callBack);
                    Log.e(TAG, e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String string = response.body().string();
                        System.out.println("请求成功");
                        System.out.println(string);

                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        Log.e(TAG, "response ------------------->" + string);
                        successCallBack((T) string, callBack);
                    } else {
                        failedCallBack("服务器错误", callBack);
                    }
                }
            });
            return call;
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return null;
    }


    public void post(String url, String jsonStr) {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");//"类型,字节码"
        try {
            //1.创建OkHttpClient对象
            OkHttpClient okHttpClient = new OkHttpClient();
            //2.通过RequestBody.create 创建requestBody对象
            RequestBody requestBody = RequestBody.create(mediaType, jsonStr);
            //3.创建Request对象，设置URL地址，将RequestBody作为post方法的参数传入
            final Request request = new Request.Builder().url(url).post(requestBody).build();
            //4.创建一个call对象,参数就是Request请求对象
            Call call = okHttpClient.newCall(request);
            //5.请求加入调度,重写回调方法
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println("请求失败");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

//                    str0 = response.body().string();
//                    Log.d("请求成功", str0);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }


    /**
     * okHttp post异步请求
     *
     * @param actionUrl 接口地址
     * @param paramsMap 请求参数
     * @param callBack  请求返回数据回调
     * @param <T>       数据泛型
     * @return
     */
//    private <T> Call requestPostByAsyn(String actionUrl, HashMap<String, String> paramsMap, final ReqCallBack<T> callBack) {
//
//        try {
//
//            String params = JSON.toJSONString(paramsMap);
//
//            RequestBody body = RequestBody.create(mediaType, params);
//
//            String requestUrl = String.format("%s", actionUrl);
//            Log.e("YY--------------->", "actionUrl："+ actionUrl  + "\n");
//            final Request request = addHeaders().url(requestUrl).post(body).build();
//            final Call call = mOkHttpClient.newCall(request);
//            call.enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    System.out.println("￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥");
//
//                    System.out.println("请求失败");
//
//                    failedCallBack("访问失败", callBack);
//                    Log.e(TAG, e.toString());
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    if (response.isSuccessful()) {
//                        String string1 = response.body().string();
//                        System.out.println("请求成功");
//                        System.out.println(string1);
//
//                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//                        Log.e(TAG, "response ------------------->" + string1);
//                        successCallBack((T) string1, callBack);
//                    } else {
//                        failedCallBack("服务器错误", callBack);
//                    }
//                }
//            });
//            return call;
//        } catch (Exception e) {
//            Log.e(TAG, e.toString());
//        }
//        return null;
//    }



    /**
     * 统一同意处理成功信息
     *
     * @param result
     * @param callBack
     * @param <T>
     */
    private <T> void successCallBack(final T result, final ReqCallBack<T> callBack) {
        okHttpHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onReqSuccess(result);
                }
            }
        });
    }
    /**
     * 统一处理失败信息
     *
     * @param errorMsg
     * @param callBack
     * @param <T>
     */
    private <T> void failedCallBack(final String errorMsg, final ReqCallBack<T> callBack) {
        okHttpHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onReqFailed(errorMsg);
                }
            }
        });
    }
}
