package com.liufeismart.weibo.bean;

import java.util.Date;

/**
 * Weibo
 *             "created_at": "Fri Feb 24 15:18:31 +0800 2012",
 *             "id": 3416614810943471,
 *             "mid": "3416614810943471",
 *             "idstr": "3416614810943471",
 *             "text": "与大家分享我所喜爱的照片！#ABC晒新年# 。",
 *             "source": "微活动</a>",
 *             "favorited": false,
 *             "truncated": false,
 *             "in_reply_to_status_id": "",
 *             "in_reply_to_user_id": "",
 *             "in_reply_to_screen_name": "",
 *             "thumbnail_pic": "http://ww3.sinaimg.cn/thumbnail/5f0eb04atw1dq4ir5bztkj.jpg",
 *             "bmiddle_pic": "http://ww3.sinaimg.cn/bmiddle/5f0eb04atw1dq4ir5bztkj.jpg",
 *             "original_pic": "http://ww3.sinaimg.cn/large/5f0eb04atw1dq4ir5bztkj.jpg",
 *             "geo": null,
 *             "user": {
 *                 "id": 1594798154,
 *                 "idstr": "1594798154",
 *                 "screen_name": "刘麦",
 *                 "name": "刘麦",
 *                 "province": "34",
 *                 "city": "16",
 *                 "location": "安徽 亳州",
 *                 "description": "新一代世界小童星。",
 *                 "url": "http://blog.sina.com.cn/liumaiduo",
 *                 "profile_image_url": "http://tp3.sinaimg.cn/1594798154/50/5614782838/1",
 *                 "profile_url": "liumaiduo",
 *                 "domain": "liumaiduo",
 *                 "weihao": "",
 *                 "gender": "m",
 *                 "followers_count": 314,
 *                 "friends_count": 555,
 *                 "statuses_count": 1913,
 *                 "favourites_count": 1,
 *                 "created_at": "Sat Jun 11 00:00:00 +0800 2011",
 *                 "following": false,
 *                 "allow_all_act_msg": false,
 *                 "geo_enabled": true,
 *                 "verified": false,
 *                 "verified_type": -1,
 *                 "allow_all_comment": false,
 *                 "avatar_large": "http://tp3.sinaimg.cn/1594798154/180/5614782838/1",
 *                 "verified_reason": "",
 *                 "follow_me": false,
 *                 "online_status": 1,
 *                 "bi_followers_count": 290,
 *                 "lang": "zh-cn"
 *             },
 *             "annotations": [...],
 *             "reposts_count": 0,
 *             "comments_count": 0,
 *             "mlevel": 0,
 *             "visible": {
 *                 "type": 0,
 *                 "list_id": 0
 *             }
 *         },
 *         ...
 *     ],
 *     "total_number": 2543821
 * }
 */
public class WeiboBeanInWeibo {
    private Date created_at;
    private long id;
    private String mid;
    private String idstr;
    private String text;
    private String source;


    private boolean favorited;
    private boolean truncated;
    private String in_reply_to_status_id;
    private String in_reply_to_user_id;
    private String in_reply_to_screen_name;
    private String thumbnail_pic;
    private String bmiddle_pic;
    private String original_pic;
    private String geo;
    private UserBeanInWeibo user;

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getIdstr() {
        return idstr;
    }

    public void setIdstr(String idstr) {
        this.idstr = idstr;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public boolean isTruncated() {
        return truncated;
    }

    public void setTruncated(boolean truncated) {
        this.truncated = truncated;
    }

    public String getIn_reply_to_status_id() {
        return in_reply_to_status_id;
    }

    public void setIn_reply_to_status_id(String in_reply_to_status_id) {
        this.in_reply_to_status_id = in_reply_to_status_id;
    }

    public String getIn_reply_to_user_id() {
        return in_reply_to_user_id;
    }

    public void setIn_reply_to_user_id(String in_reply_to_user_id) {
        this.in_reply_to_user_id = in_reply_to_user_id;
    }

    public String getIn_reply_to_screen_name() {
        return in_reply_to_screen_name;
    }

    public void setIn_reply_to_screen_name(String in_reply_to_screen_name) {
        this.in_reply_to_screen_name = in_reply_to_screen_name;
    }

    public String getThumbnail_pic() {
        return thumbnail_pic;
    }

    public void setThumbnail_pic(String thumbnail_pic) {
        this.thumbnail_pic = thumbnail_pic;
    }

    public String getBmiddle_pic() {
        return bmiddle_pic;
    }

    public void setBmiddle_pic(String bmiddle_pic) {
        this.bmiddle_pic = bmiddle_pic;
    }

    public String getOriginal_pic() {
        return original_pic;
    }

    public void setOriginal_pic(String original_pic) {
        this.original_pic = original_pic;
    }

    public String getGeo() {
        return geo;
    }

    public void setGeo(String geo) {
        this.geo = geo;
    }

    public UserBeanInWeibo getUser() {
        return user;
    }

    public void setUser(UserBeanInWeibo user) {
        this.user = user;
    }
}
