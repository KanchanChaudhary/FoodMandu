package com.kanchan.foodmandu.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.kanchan.foodmandu.R;
import com.kanchan.foodmandu.R;


public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Integer [] images={R.drawable.coke,R.drawable.momo,R.drawable.pizza,R.drawable.coke};
    private Object LayoutInflater;

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = ((LayoutInflater) LayoutInflater).inflate(R.layout.imageslider,null);
        ImageView imageView=(ImageView) view.findViewById(R.id.imageView);
        imageView.setImageResource(images[position]);

        ViewPager vp=(ViewPager) container;
        vp.addView(view,0);
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        ViewPager vp=(ViewPager) container;
        View view=(View) object;
        vp.removeView(view);
    }
}
