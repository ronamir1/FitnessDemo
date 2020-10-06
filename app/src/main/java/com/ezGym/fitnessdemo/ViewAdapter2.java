package com.ezGym.fitnessdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import static com.ezGym.fitnessdemo.ExercisesActivity.BASIC_STANDING_POINTERS;
import static com.ezGym.fitnessdemo.ExercisesActivity.CHEST_BEFORE_SHOULDERS;
import static com.ezGym.fitnessdemo.ExercisesActivity.CHEST_POINTERS;
import static com.ezGym.fitnessdemo.ExercisesActivity.CORE_ENGAGED;
import static com.ezGym.fitnessdemo.ExercisesActivity.FACE_PULL_POINTERS;
import static com.ezGym.fitnessdemo.ExercisesActivity.LEGS_POINTER1;
import static com.ezGym.fitnessdemo.ExercisesActivity.LEGS_POINTER2;
import static com.ezGym.fitnessdemo.ExercisesActivity.LEGS_POINTER3;
import static com.ezGym.fitnessdemo.ExercisesActivity.LINE_DROP;
import static com.ezGym.fitnessdemo.ExercisesActivity.OVERHEAD_PRESS_POINTERS;

public class ViewAdapter2 extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private int workout;
    private ImageView gif;
    private TextView gifTextView;
    int[] workout1 = {R.drawable.bench_press, R.drawable.pull_up_back, R.drawable.lateral_raise, R.drawable.waiter_curls_front, R.drawable.french_press, R.drawable.squat};
    int[] workout2 = {R.drawable.dips_side, R.drawable.one_arm_high_cable_row_right, R.drawable.overhead_press, R.drawable.robot_curls_side, R.drawable.triceps_extension_side, R.drawable.lunges};
    int[] workout3 = {R.drawable.decline_push_up, R.drawable.barbell_row_side, R.drawable.ucv_raise, R.drawable.dumbbell_hammer_curls_side, R.drawable.triceps_extension_side, R.drawable.romanian_deadlift};
    int[] workout4 = {R.drawable.cable_crossover, R.drawable.lat_pulldown, R.drawable.face_pull, R.drawable.barbell_curls_front, R.drawable.french_press, R.drawable.bulgarian_split_squat};
    int[][] gifs = {workout1, workout2, workout3, workout4};

    String[] workout1tips = {"Bench press" + LINE_DROP+LINE_DROP + CHEST_POINTERS, "Pull up", "Lateral raise" + LINE_DROP + BASIC_STANDING_POINTERS, "Waiter's curls" + LINE_DROP + BASIC_STANDING_POINTERS, "French press", "Squat"+ LINE_DROP + LINE_DROP + CORE_ENGAGED + LINE_DROP + LEGS_POINTER1 + LINE_DROP + LEGS_POINTER3};
    String[] workout2tips = {"Dips" + LINE_DROP+ LINE_DROP + CHEST_POINTERS, "One arm high row - right hand", "Overhead press - barbell"+ LINE_DROP + LINE_DROP + OVERHEAD_PRESS_POINTERS + BASIC_STANDING_POINTERS, "Robot hammer curls" + LINE_DROP + BASIC_STANDING_POINTERS, "Triceps extension" + LINE_DROP + BASIC_STANDING_POINTERS, "Lunges" + LINE_DROP+ LINE_DROP + CORE_ENGAGED + LINE_DROP + LEGS_POINTER3};
    String[] workout3tips = {"Decline push up"+ LINE_DROP + LINE_DROP + CHEST_POINTERS, "Barbell row - side", "UCV raise" + LINE_DROP + BASIC_STANDING_POINTERS, "Dumbbell hammer curls"+ LINE_DROP + BASIC_STANDING_POINTERS, "Triceps extension" + LINE_DROP + BASIC_STANDING_POINTERS, "Romanian deadlift"+ LINE_DROP + LINE_DROP + CORE_ENGAGED + LINE_DROP + LEGS_POINTER1 + LINE_DROP + LEGS_POINTER2};
    String[] workout4tips = {"Cable crossover"+ LINE_DROP + LINE_DROP + CHEST_BEFORE_SHOULDERS, "Lat pulldown", "Face pull"+ LINE_DROP + LINE_DROP + FACE_PULL_POINTERS, "Barbell curls"+ LINE_DROP + BASIC_STANDING_POINTERS, "French press", "Bulgarian split squat"+ LINE_DROP + LINE_DROP + CORE_ENGAGED + LINE_DROP + LEGS_POINTER3};
    String[][] workoutTips = {workout1tips, workout2tips, workout3tips, workout4tips};

    ViewAdapter2(Context context, int workout) {
        this.context = context;
        this.workout = workout - 6; // make it 0-3 instead of 6-9
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item2, null);
        gif = view.findViewById(R.id.fullBodyGif);
        gifTextView = view.findViewById(R.id.gifTextView);

        Glide.with(this.context).load(gifs[this.workout][position]).into(gif);
        gifTextView.setText(workoutTips[this.workout][position]);

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
