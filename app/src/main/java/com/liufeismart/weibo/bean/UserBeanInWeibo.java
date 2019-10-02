package com.liufeismart.weibo.bean;

import java.util.Date;

/**
 * 微博开放平台提供的用户Bean
 *                  "id": 1594798154,
 *                  "idstr": "1594798154",
 *                  "screen_name": "刘麦",
 *                  "name": "刘麦",
 *                  "province": "34",
 *                  "city": "16",
 *                  "location": "安徽 亳州",
 *                  "description": "新一代世界小童星。",
 *                  "url": "http://blog.sina.com.cn/liumaiduo",
 *                  "profile_image_url": "http://tp3.sinaimg.cn/1594798154/50/5614782838/1",
 *                  "profile_url": "liumaiduo",
 *                  "domain": "liumaiduo",
 *                  "weihao": "",
 *                  "gender": "m",
 *                  "followers_count": 314,
 *                  "friends_count": 555,
 *                  "statuses_count": 1913,
 *                  "favourites_count": 1,
 *                  "created_at": "Sat Jun 11 00:00:00 +0800 2011",
 *                  "following": false,
 *                  "allow_all_act_msg": false,
 *                  "geo_enabled": true,
 *                  "verified": false,
 *                  "verified_type": -1,
 *                  "allow_all_comment": false,
 *                  "avatar_large": "http://tp3.sinaimg.cn/1594798154/180/5614782838/1",
 *                  "verified_reason": "",
 *                  "follow_me": false,
 *                  "online_status": 1,
 *                  "bi_followers_count": 290,
 *                  "lang": "zh-cn"
 */
public class UserBeanInWeibo {
   private long id;
   private String idstr;
    private String screen_name;
    private String name;
    private String province;
    private String city;
    private String location;
    private String description;
    private String url;
    private String profile_image_url;
    private String profile_url;
    private String domain;
    private String weihao;
    private String gender;
    private long followers_count;
    private long friends_count;
    private long statuses_count;
    private int favourites_count;
    private Date created_at;
    private boolean  following;
    private boolean allow_all_act_msg;
    private boolean geo_enabled;
    private boolean verified;
    private int verified_type;
    private boolean allow_all_comment;
    private String avatar_large;
    private String verified_reason;
    private boolean follow_me;
    private int online_status;
    private int bi_followers_count;
    private String lang;

}
