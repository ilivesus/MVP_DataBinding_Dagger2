package com.example.pc.basemvp.utils;

import android.annotation.SuppressLint;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.text.TextUtils;
import android.widget.ImageView;

public final class BindingUtils {
    private static final String TAG = "BindingUtils";

    public BindingUtils() {
        // No-op
    }

    @BindingAdapter(value = { "recyclerAdapter", "enableAnimate" }, requireAll = false)
    public static void setAdapterForRecyclerView(RecyclerView recyclerView,
            RecyclerView.Adapter adapter, boolean enableAnimate) {
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(
                enableAnimate);
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({ "itemDecorator" })
    public static void setItemDecorator(RecyclerView recyclerView,
            RecyclerView.ItemDecoration itemDecoration) {
        recyclerView.addItemDecoration(itemDecoration);
    }

    @BindingAdapter({ "currentPage" })
    public static void setCurrentViewPager(final ViewPager viewPager, final int currentPage) {
        viewPager.setCurrentItem(currentPage);
    }

    @BindingAdapter({ "viewPagerAdapter" })
    public static void setViewPagerAdapter(final ViewPager viewPager, final PagerAdapter adapter) {
        viewPager.setAdapter(adapter);
    }

    @BindingAdapter("viewPager")
    public static void setViewPagerTabs(final TabLayout tabLayout, final ViewPager viewPager) {
        tabLayout.setupWithViewPager(viewPager, true);
    }

    @SuppressLint("CheckResult")
    @BindingAdapter(value = { "imageUrl", "placeHolder" }, requireAll = false)
    public static void loadImage(ImageView imageView, String url, Drawable placeHolder) {
        if (TextUtils.isEmpty(url)) {
            url = "";
        }
        //TODO config later
       /* GlideApp.with(imageView.getContext())
                .load(url)
                .placeholder(placeHolder)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(imageView);*/
    }
}
