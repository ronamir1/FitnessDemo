package com.ezGym.fitnessdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ViewAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    final String tip1 = "No pain no gain is NOT true.\nThere is a BIG difference between muscles hurting from a workout or from an injury. Listen to your body and rest if needed.";
    final String tip2 = "Warm up is necessary, before each exercise do one slow set of 12-15 reps with low weight.";
    final String tip3 = "Work on each body part (i.e. chest/shoulders/back/legs etc.) 1-2 times a week.";
    final String tip4 = "The workouts are divided into 3 sections:\nChest and shoulders, Back and Biceps/Triceps, Legs.\nOn each workout, choose one of the sections and work it.\nRegarding abs &amp; core, you can do them after each workout (no more than 3-4 times a week).";
    final String tip5 = "your Max weight in an exercise is the maximum weight you can do 6-8 reps in that specific exercise.";
    final String tip6 = "Change your workout every few weeks. You can either change the order of the exercises or choose different exercises.";
    final String tip7 = "The most important exercises to put effort into are:\nPull up, Bench press, Squat, Deadlift, Dips (this exercises increase testosterone levels).";
    final String tip8 = "Try to raise weight every other week, if it feels too hard you can wait another week or two.";
    private String[] strings = {tip1, tip2, tip3, tip4, tip5, tip6, tip7, tip8};

    ViewAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return strings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view== object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item, null);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(strings[position]);
        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager2 = (ViewPager) container;
        View view = (View) object;
        viewPager2.removeView(view);
    }
}
