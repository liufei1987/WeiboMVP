package com.liufeismart.weibo.image;

import android.widget.ImageView;

public interface ImageAPI {
    void setImage(ImageView view, String url);

    void setPortrait(ImageView view, String url);
}
