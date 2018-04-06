package com.binguner.crazytalk.CustomView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

public class CustomPagetTransformer implements ViewPager.PageTransformer {

    private ChildViewPager childViewPager;
    private int maxTranslateOffsetX;

    public CustomPagetTransformer(Context context){
        this.maxTranslateOffsetX = dp2px(context, 180);
    }

    @Override
    public void transformPage(@NonNull View page, float position) {
        if(null == childViewPager){
            childViewPager = (ChildViewPager) page.getParent();
        }

        int leftInScreen = page.getLeft() - childViewPager.getScrollX();
        int centerXInViewPager = leftInScreen + page.getMeasuredWidth() / 2;
        int offsetX = centerXInViewPager - childViewPager.getMeasuredWidth() / 2;
        float offsetRate = (float) offsetX * 0.38f / childViewPager.getMeasuredWidth();
        float scaleFactor = 1 - Math.abs(offsetRate);
        if (scaleFactor > 0) {
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
            page.setTranslationX(-maxTranslateOffsetX * offsetRate);
        }
    }

    private int dp2px(Context context, float dipValue){
        float m = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * m + 0.5f);
    }

}
