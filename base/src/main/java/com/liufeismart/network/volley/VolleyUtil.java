package com.liufeismart.network.volley;

public class VolleyUtil { //implements NetworkAPI {
//    private static VolleyUtil instance;
//    private RequestQueue queue;
//
//    private VolleyUtil() {
//        queue = Volley.newRequestQueue(WeiboApp.getApplication().getApplicationContext());
//    }
//
//    public static VolleyUtil getInstance() {
//        if(instance == null) {
//            instance = new VolleyUtil();
//        }
//        return instance;
//    }
//
//    @Override
//    public <T> void getAttention(final Callback<T> callback, final Class<T> TClass) {
//        String accesstoken = "0";
//        String urlStr = BASE_URL+"statuses/home_timeline.json" + "?access_token = "+ accesstoken;
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlStr, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Gson gson = new Gson();
//                T t = gson.fromJson(response, TClass);
//                callback.onSuccess(t);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                callback.onFailure(error.getMessage());
//            }
//        }
//        );
//        queue.add(stringRequest);
//    }
//
//    @Override
//    public void getUserInfo(Callback<UserInfoBean> callback) {
//
//    }
}
