package com.example.userbanksampah;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import org.w3c.dom.Text;

public class ViewPageAdapter extends PagerAdapter {
    Context context;

    int images[] = {
            R.drawable.onboarding,
            R.drawable.onboarding2,
            R.drawable.onboarding1,
            R.drawable.loginpage1


    };
    int heading[] = {
            R.string.heading_one,
            R.string.heading_two,
            R.string.heading_three,
            R.string.heading_fourth

    };
    int description[] = {
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
        return view == (LinearLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater)  context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout,container,false);

        ImageView slideimage= (ImageView) view.findViewById(R.id.imagetitle);
        TextView slidetitle= (TextView) view.findViewById(R.id.heading);
        TextView slideskripsi= (TextView) view.findViewById(R.id.description);

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
