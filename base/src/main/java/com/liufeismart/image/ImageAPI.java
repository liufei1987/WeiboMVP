package com.liufeismart.image;

import android.widget.ImageView;

public interface ImageAPI {
    void setImage(ImageView view, String url);

    void setPortrait(ImageView view, String url, int placeholder);
}
