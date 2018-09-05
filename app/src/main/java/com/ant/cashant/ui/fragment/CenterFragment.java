package com.ant.cashant.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ant.cashant.R;
import com.ant.cashant.base.BaseFragment;
import com.ant.cashant.common.Contacts;
import com.ant.cashant.model.LoginEvent;
import com.ant.cashant.ui.activity.EmptyActivity;
import com.ant.cashant.ui.activity.FeedbackActivity;
import com.ant.cashant.ui.activity.HtmlActivity;
import com.ant.cashant.ui.activity.SettingActivity;
import com.ant.cashant.ui.activity.login.LoginActivity;
import com.ant.cashant.utils.ActivityUtils;
import com.ant.cashant.utils.SPUtil;
import com.ant.cashant.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CenterFragment extends BaseFragment {


    @BindView(R.id.login)
    TextView login;
    private final int LOGIN_REQUESTION=10000;
    private final int LOAN_REQUESTION=20000;
    private final int FREE_REQUESTION=30000;
    private final int RESULT_CODE=200;

    private String token;
    public CenterFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_center;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        initView();

    }


    @Subscribe
    public void getLogin(LoginEvent event){
        if(event.msg!=null){
            login.setText(event.msg);
        }else {
            login.setText("未登录");
        }
        token = SPUtil.getString(Contacts.TOKEN);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    private void initView() {
        token = SPUtil.getString(Contacts.TOKEN);
        String phone = SPUtil.getString(Contacts.PHONE);
        if(!TextUtils.isEmpty(phone)){
            login.setText(phone);
        }

    }

    @OnClick({R.id.header, R.id.login, R.id.setting, R.id.loan, R.id.free, R.id.safety, R.id.feedback})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.header:
            case R.id.login:
                token = SPUtil.getString(Contacts.TOKEN);
                if(TextUtils.isEmpty(token)){
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivityForResult(intent,LOGIN_REQUESTION);
                }
                break;
            case R.id.setting:
                ActivityUtils.startActivity(SettingActivity.class);
                break;
            case R.id.loan:
                 token = SPUtil.getString(Contacts.TOKEN);
                if(TextUtils.isEmpty(token)){
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivityForResult(intent,LOAN_REQUESTION);
                }else {
                    Intent intent = new Intent(getActivity(), EmptyActivity.class);
                    intent.putExtra("title","贷款进度");
                    startActivity(intent);
                }
                break;
            case R.id.free:
                token = SPUtil.getString(Contacts.TOKEN);
                if(TextUtils.isEmpty(token)){
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivityForResult(intent,FREE_REQUESTION);
                }else {
                    Intent intent = new Intent(getActivity(), EmptyActivity.class);
                    intent.putExtra("title","我的免息券");
                    startActivity(intent);
                }
                break;
            case R.id.safety:
                Intent intent = new Intent(getActivity(), HtmlActivity.class);
                intent.putExtra("title", "安全小贴士");
                intent.putExtra("link", "http://m.anwenqianbao.com/#/minTips");
                startActivity(intent);
                break;
            case R.id.feedback:
                ActivityUtils.startActivity(FeedbackActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case LOGIN_REQUESTION:
                if(resultCode==RESULT_CODE){
                    String phone = data.getStringExtra("phone");
                    login.setText(phone);
                    token = SPUtil.getString(Contacts.TOKEN);
                }
                break;
            case LOAN_REQUESTION:
                if(resultCode==RESULT_CODE){
                    token = SPUtil.getString(Contacts.TOKEN);
                        Intent intent = new Intent(getActivity(), EmptyActivity.class);
                        intent.putExtra("title","贷款进度");
                        startActivity(intent);
                }
                break;
            case FREE_REQUESTION:
                if(resultCode==RESULT_CODE){
                    token = SPUtil.getString(Contacts.TOKEN);
                        Intent intent = new Intent(getActivity(), EmptyActivity.class);
                        intent.putExtra("title","我的免息券");
                        startActivity(intent);
                }
                break;
            default:
                break;
        }
    }
}
