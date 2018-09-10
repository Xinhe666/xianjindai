package com.ant.cashant.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ant.cashant.R;
import com.ant.cashant.base.BaseActivity;
import com.ant.cashant.common.Contacts;
import com.ant.cashant.model.LoginEvent;
import com.ant.cashant.utils.ActivityUtils;
import com.ant.cashant.utils.SPUtil;
import com.ant.cashant.utils.StatusBarUtil;
import com.ant.cashant.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author apple
 */
public class SettingActivity extends BaseActivity {

    @BindView(R.id.toolbar_back)
    ImageView toolbarBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.apply)
    Button apply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        toolbarBack.setVisibility(View.VISIBLE);
        toolbarTitle.setText("设置");
        String token = SPUtil.getString(Contacts.TOKEN);
        if(!TextUtils.isEmpty(token)){
            apply.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @OnClick({R.id.toolbar_back, R.id.about, R.id.version, R.id.apply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.about:
                ActivityUtils.startActivity(AboutActivity.class);
                break;
            case R.id.version:
                ToastUtils.showToast("已经是最近版本啦！");
                break;
            case R.id.apply:
                SPUtil.remove(Contacts.PHONE);
                SPUtil.remove(Contacts.TOKEN);
                ActivityUtils.startActivity(LoginActivity.class);
                EventBus.getDefault().post(new LoginEvent(null));
                finish();
                break;
            default:
                break;
        }
    }
}
