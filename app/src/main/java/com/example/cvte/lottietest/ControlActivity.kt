package com.example.cvte.lottietest

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.airbnb.lottie.LottieComposition
import kotlinx.android.synthetic.main.activity_control.*

class ControlActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control)

        start_button.setOnClickListener { startAnim() }
        end_button.setOnClickListener { stopAnim() }

        like_lottieAnimationView.setOnClickListener {
            like_lottieAnimationView.playAnimation()
        }

        check_lottieAnimationView.setOnClickListener {
            if(check_lottieAnimationView.isAnimating){
                return@setOnClickListener
            }
            check_lottieAnimationView.playAnimation()
        }

         //通过这种方式实现倒着播放动画
        check_lottieAnimationView.addAnimatorListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                check_lottieAnimationView.reverseAnimationSpeed()
            }
        })

        LottieComposition.Factory.fromAssetFileName(this, "LottieLogo.json") { composition ->
            if (composition != null) {
                control_lottieAnimationView.setComposition(composition)
                control_lottieAnimationView.progress = 0.333f
                control_lottieAnimationView.playAnimation()
            }
        }

        control_lottieAnimationView.addAnimatorUpdateListener { animation -> anim_progress_textView.text = "动画进度：${animation.animatedFraction * 100}%" }
    }

    private fun startAnim() {
        val isPlaying = control_lottieAnimationView.isAnimating
        if (!isPlaying) {
            control_lottieAnimationView.progress = 0f
            control_lottieAnimationView.playAnimation()
        }
    }

    private fun stopAnim() {
        val isPlaying = control_lottieAnimationView.isAnimating
        if (isPlaying) {
            control_lottieAnimationView.cancelAnimation()
        }
    }

}