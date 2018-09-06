package com.Michael.xianjinka.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.Michael.xianjinka.R;
import com.Michael.xianjinka.base.BaseActivity;
import com.Michael.xianjinka.utils.SPUtil;

import butterknife.BindView;


/**
 * @author apple
 */
public class SplashActivity extends BaseActivity {
    @BindView(R.id.splash_iv)
    ImageView mImageView;

    private boolean mIsFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mIsFirst = SPUtil.getBoolean("isFirst", true);

        mImageView.setAlpha(0.3f);

        mImageView.animate().alpha(1.0f)
                .setDuration(2000)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        if (mIsFirst) {
                            Intent intent=new Intent(SplashActivity.this,GuideActivity.class);
                            startActivity(intent);
                        }else {
                            Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                        finish();
                    }
                }).start();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }
}


