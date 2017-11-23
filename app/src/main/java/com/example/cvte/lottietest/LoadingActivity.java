package com.example.cvte.lottietest;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.OnCompositionLoadedListener;
import com.github.nuptboyzhb.lib.SuperSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CVTE on 2017/11/22.
 */

public class LoadingActivity extends AppCompatActivity{

    private SuperSwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private List<String> mNameList;
    private LottieAnimationView mAnimationView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        mSwipeRefreshLayout = findViewById(R.id.control_swipeRefreshLayout);
        mRecyclerView = findViewById(R.id.show_data_recyclerView);

        mSwipeRefreshLayout.setHeaderView(createHeaderView());
        mSwipeRefreshLayout.setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                        mAnimationView.setProgress(1f);
                        mAnimationView.cancelAnimation();
                    }
                },2000);
            }

            @Override
            public void onPullDistance(int i) {

            }

            @Override
            public void onPullEnable(boolean b) {
                mAnimationView.loop(true);
                mAnimationView.playAnimation();
            }
        });

        initData();
        mRecyclerView.setAdapter(new ShowListAdapter(this,mNameList));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private View createHeaderView() {
        mAnimationView = (LottieAnimationView) getLayoutInflater().inflate(R.layout.app_intro_animation_view, null);
        LottieComposition.Factory.fromRawFile(this, R.raw.loading, new OnCompositionLoadedListener() {
            @Override
            public void onCompositionLoaded(@Nullable LottieComposition composition) {
                if(composition != null)
                mAnimationView.setComposition(composition);
            }
        });

        return mAnimationView;
    }

    protected void initData()
    {
        mNameList = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++)
        {
            mNameList.add("" + (char) i);
        }
    }
}
