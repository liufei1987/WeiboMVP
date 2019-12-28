package com.liufeismart.image.glide;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.liufeismart.R;
import com.liufeismart.image.ImageAPI;

public class GlideUtil implements ImageAPI {

    private static GlideUtil instance;

    private GlideUtil() {

    }

    public static GlideUtil getInstance() {
        if(instance == null) {
            instance = new GlideUtil();
        }
        return instance;
    }

    @Override
    public void setImage(ImageView view, String url) {
        Glide.with(view)
                .load(url)
                .into(view);
    }


    public void setPortrait(ImageView view, String url, int placehodler) {
        Glide.with(view)
                .load(url)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .placeholder(placehodler)//R.drawable.icon_protrait_default
                .into(view);
    }
}
