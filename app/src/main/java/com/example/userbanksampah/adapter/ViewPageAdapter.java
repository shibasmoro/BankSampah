package com.example.userbanksampah.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.userbanksampah.R;

public class ViewPageAdapter extends PagerAdapter {
    Context context;

    int[] images = {
            R.drawable.ob1,
            R.drawable.ob2,
            R.drawable.ob3,
            R.drawable.ob4


    };
    int[] heading = {
            R.string.heading_one,
            R.string.heading_two,
            R.string.heading_three,
            R.string.heading_fourth

    };
    int[] description = {
            R.string.desc_one,
            R.string.desc_two,
            R.string.desc_three,
            R.string.desc_fourth

    };
    public ViewPageAdapter(Context context ){

        this.context = context;
    }
    @Override
    public int getCount() {
            return heading.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout,container,false);

        ImageView slideimage= view.findViewById(R.id.imagetitle);
        TextView slidetitle= view.findViewById(R.id.heading);
        TextView slideskripsi= view.findViewById(R.id.description);

        slideimage.setImageResource(images[position]);
        slidetitle.setText(heading[position]);
        slideskripsi.setText(description[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
