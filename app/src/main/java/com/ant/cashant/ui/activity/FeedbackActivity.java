package com.ant.cashant.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ant.cashant.R;
import com.ant.cashant.base.BaseActivity;
import com.ant.cashant.utils.NetworkUtils;
import com.ant.cashant.utils.StatusBarUtil;
import com.ant.cashant.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author apple
 *         问题反馈
 */
public class FeedbackActivity extends BaseActivity {

    @BindView(R.id.toolbar_back)
    ImageView toolbarBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.et_message)
    EditText etMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 40);
        initView();
    }

    private void initView() {
        toolbarBack.setVisibility(View.VISIBLE);
        toolbarTitle.setText("问题反馈");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @OnClick({R.id.toolbar_back, R.id.apply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.apply:
                boolean available = NetworkUtils.isAvailable(this);
                if(available){
                    if(!TextUtils.isEmpty(etMessage.getText().toString())){
                        ToastUtils.showToast("感觉您的宝贵意见，我们将稍后作答");
                        finish();
                    }
                }else {
                    ToastUtils.showToast("网络无法连接");
                }
                break;
            default:
                break;
        }
    }
}
