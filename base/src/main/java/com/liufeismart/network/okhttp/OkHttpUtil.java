package com.liufeismart.network.okhttp;

public class OkHttpUtil { //implements NetworkAPI {
//    private static OkHttpUtil instance;
//
//    //第一步，创建OkHttpClient对象
//    OkHttpClient client = new OkHttpClient();
//
//    private OkHttpUtil() {
//
//    }
//
//    public static OkHttpUtil getInstance() {
//        if(instance == null) {
//            instance = new OkHttpUtil();
//        }
//        return instance;
//    }
//
//    @Override
//    public <T> void getAttention(final Callback<T> callback, final Class<T> TClass) {
//        String accesstoken = "0";
//        String urlStr = BASE_URL + "?access_token = "+ accesstoken;
//        //第二步，创建Request对象
//        Request request = new Request.Builder().url(urlStr).build();
//        //第三步，创建Call对象，并放入队列
//        Call call = client.newCall(request);
//        call.enqueue(new okhttp3.Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                callback.onFailure(e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String respStr = response.body().string();
//                Gson gson = new Gson();
//                T t = gson.fromJson(respStr, TClass);
//                callback.onSuccess(t);
//            }
//        });
//
//    }
//
//    @Override
//    public void getUserInfo(Callback<UserInfoBean> callback) {
//
//    }
}
