package com.example.cvte.lottietest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import com.airbnb.lottie.LottieAnimationView;
import com.matthewtamlin.sliding_intro_screen_library.buttons.IntroButton;
import com.matthewtamlin.sliding_intro_screen_library.core.IntroActivity;
import com.matthewtamlin.sliding_intro_screen_library.core.LockableViewPager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by CVTE on 2017/11/22.
 * 在引导界面使用Lottie
 */

public class SplashActivity extends IntroActivity {

    private float[] ANIMATION_TIMES = new float[]{0.1f, 0.3333f, 0.6666f, 1f, 1f};
    private static final int SPLASH_NUMBER = 4;
    private LottieAnimationView animationView;
    private LockableViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        animationView = (LottieAnimationView) getLayoutInflater().inflate(R.layout.app_intro_animation_view
                , getRootView(), false);
        Log.i("info",animationView.isHardwareAccelerated() + "硬件加速");
        getRootView().addView(animationView,0);
        viewPager = findViewById(R.id.intro_activity_viewPager);
        setViewPagerScroller();

        addPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                setAnimationProgress(position, positionOffset);
            }
        });
    }

    /**
     * 根据ViewPager的滑动量确定动画的进度
     *
     * @param position       滑动的页码
     * @param positionOffset 当前滑动的偏移量
     */
    private void setAnimationProgress(int position, float positionOffset) {
        float startProgress = ANIMATION_TIMES[position];
        float endProgress = ANIMATION_TIMES[position + 1];
        Log.i("info","position" + position);

        float animationProgress = startProgress + positionOffset * (endProgress - startProgress);
        animationView.setProgress(animationProgress);
    }

    /**
     * 利用反射改变Scroller的滑动速度
     */
    private void setViewPagerScroller() {
        try {
            Field scrollerFiled = ViewPager.class.getDeclaredField("mScroller");
            scrollerFiled.setAccessible(true);
            Field interpolatorFiled = ViewPager.class.getDeclaredField("sInterpolator");
            interpolatorFiled.setAccessible(true);
            Scroller scroller = new Scroller(this, (Interpolator) interpolatorFiled.get(null)) {
                @Override
                public void startScroll(int startX, int startY, int dx, int dy, int duration) {
                    super.startScroll(startX, startY, dx, dy, duration * 7);
                }
            };
            scrollerFiled.set(viewPager, scroller);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Collection<? extends Fragment> generatePages(Bundle savedInstanceState) {
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < SPLASH_NUMBER; i++) {
            fragments.add(new EmptyFragment());
        }
        return fragments;
    }

    @Override
    protected IntroButton.Behaviour generateFinalButtonBehaviour() {
        return new IntroButton.Behaviour() {
            @Override
            public void setActivity(IntroActivity activity) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }

            @Override
            public IntroActivity getActivity() {
                return null;
            }

            @Override
            public void run() {

            }
        };
    }
}
