package com.liufeismart.weibo.image;

import android.widget.ImageView;

import com.liufeismart.weibo.image.glide.GlideUtil;

public class ImageUtil implements  ImageAPI{

    private ImageAPI imageAPI;

    private static ImageUtil instance;

    private ImageUtil() {
        imageAPI = GlideUtil.getInstance();
    }

    public static ImageUtil getInstance() {
        if(instance == null) {
            instance = new ImageUtil();
        }
        return instance;
    }

    public void setImage(ImageView view, String url) {
        imageAPI.setImage(view, url);
    }

    @Override
    public void setPortrait(ImageView view, String url) {
        imageAPI.setPortrait(view, url);
    }

}
