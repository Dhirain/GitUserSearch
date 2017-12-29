package com.dhirain.myapplication.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

/**
 * Created by Dhirain Jain on 29-12-2017.
 */

public class ImageUtils {

    public static void setImage(Context context, String url, ImageView imageView, int stubImage) {
        Glide.with(context).load(url)
                .centerCrop().fitCenter()
                .placeholder(ContextCompat.getDrawable(context, stubImage))
                .error(ContextCompat.getDrawable(context, stubImage)).
                into(imageView);
    }

    public static void setCircularImage(final Context context, String url, final ImageView imageView, int stubImage){
        Glide.with(context).load(url).asBitmap().centerCrop()
                .placeholder(ContextCompat.getDrawable(context, stubImage))
                .error(ContextCompat.getDrawable(context, stubImage))
                .into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imageView.setImageDrawable(circularBitmapDrawable);
            }
        });
    }
}
