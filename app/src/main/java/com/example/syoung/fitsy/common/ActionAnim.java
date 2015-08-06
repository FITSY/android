package com.example.syoung.fitsy.common;

import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.example.syoung.fitsy.R;

/**
 * Created by HyunJoo on 2015. 8. 1..
 */
public class ActionAnim {

    public ActionAnim(){

    }
    public static void slide_down(Context ctx, View v){

        android.view.animation.Animation a = AnimationUtils.loadAnimation(ctx, R.anim.slide_down);
        if(a != null){
            a.reset();
            if(v != null){
                v.clearAnimation();
                v.startAnimation(a);
            }
        }

    }

    public static void slide_up(Context ctx, View v){

        android.view.animation.Animation a = AnimationUtils.loadAnimation(ctx, R.anim.slide_up);
        if(a != null){
            a.reset();
            if(v != null){
                v.clearAnimation();
                v.startAnimation(a);
            }
        }

    }

    public static void translate_to_up(Context ctx, View v){

        android.view.animation.Animation a = AnimationUtils.loadAnimation(ctx, R.anim.translate_to_up);
        if(a != null){
            a.reset();
            if(v != null){
                v.clearAnimation();
                v.startAnimation(a);
            }
        }

    }

    public static void translate_to_down (Context ctx, View v){

        android.view.animation.Animation a = AnimationUtils.loadAnimation(ctx, R.anim.translate_to_down);
        if(a != null){
            a.reset();
            if(v != null){
                v.clearAnimation();
                v.startAnimation(a);
            }
        }

    }
}
