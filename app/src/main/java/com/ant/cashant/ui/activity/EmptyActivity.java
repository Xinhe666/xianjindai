package com.ant.cashant.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ant.cashant.R;
import com.ant.cashant.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author apple
 */
public class EmptyActivity extends BaseActivity {


    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nodate)
    ImageView nodate;
    @BindView(R.id.toolbar_back)
    ImageView toolbarBack;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_empty;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbarBack.setVisibility(View.VISIBLE);
        String title = getIntent().getStringExtra("title");
        toolbarTitle.setText(title);
    }

    @OnClick(R.id.toolbar_back)
    public void onViewClicked() {
        finish();
    }
}
