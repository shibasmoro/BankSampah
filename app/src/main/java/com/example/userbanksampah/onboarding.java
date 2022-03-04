package com.example.userbanksampah;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.userbanksampah.databinding.ActivityOnboardingBinding;

public class onboarding extends AppCompatActivity {
    ViewPager mSlideViewPager;
    TextView[] dots;
    ViewPageAdapter viewPageAdapter;
    private ActivityOnboardingBinding Binding;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding = ActivityOnboardingBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());

        Binding.skipbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(onboarding.this,LoginActivity.class);
                startActivity(i);
                finish();

            }
        });
        mSlideViewPager = Binding.slideViewPager;
        viewPageAdapter = new ViewPageAdapter(this);
        mSlideViewPager.setAdapter(viewPageAdapter);
        setUpindicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setUpindicator(int position){
        dots = new TextView[4];
        Binding.indicatorlayout.removeAllViews();
        for (int i = 0; i<dots.length;i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.inactive,getApplicationContext().getTheme()));
            Binding.indicatorlayout.addView(dots[i]);
        }
        dots[position].setTextColor(getResources().getColor(R.color.active,getApplicationContext().getTheme()));
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onPageSelected(int position) {
            setUpindicator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private int getitem(int i){
        return mSlideViewPager.getCurrentItem();
    }
}