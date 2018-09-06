package com.Michael.xianjinka.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.Michael.xianjinka.R;
import com.Michael.xianjinka.base.BaseActivity;
import com.Michael.xianjinka.utils.AppUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author apple
 */
public class AboutActivity extends BaseActivity {

    @BindView(R.id.toolbar_back)
    ImageView toolbarBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.version)
    TextView version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        toolbarBack.setVisibility(View.VISIBLE);
        toolbarTitle.setText("关于我们");
        String appVersionName = AppUtils.getAppVersionName();
        version.setText(getString(R.string.app_name)+"V"+appVersionName);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @OnClick(R.id.toolbar_back)
    public void onViewClicked() {
        finish();
    }
}
