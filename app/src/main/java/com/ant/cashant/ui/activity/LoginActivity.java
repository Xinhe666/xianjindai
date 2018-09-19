package com.ant.cashant.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.SuperButton;
import com.ant.cashant.R;
import com.ant.cashant.base.BaseActivity;
import com.ant.cashant.common.Api;
import com.ant.cashant.common.ApiService;
import com.ant.cashant.common.Contacts;
import com.ant.cashant.inter.OnRequestDataListener;
import com.ant.cashant.model.LoginEvent;
import com.ant.cashant.utils.BrowsingHistory;
import com.ant.cashant.utils.CaptchaTimeCount;
import com.ant.cashant.utils.RegexUtils;
import com.ant.cashant.utils.SPUtil;
import com.ant.cashant.utils.StatusBarUtil;
import com.ant.cashant.utils.ToastUtils;
import com.ant.cashant.view.editext.PowerfulEditText;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author apple
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.toolbar_back)
    ImageView toolbarBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.ed_phone)
    PowerfulEditText edPhone;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.ed_code)
    PowerfulEditText edCode;
    @BindView(R.id.bt_logon)
    SuperButton btLogon;
    private CaptchaTimeCount captchaTimeCount;
    private KProgressHUD hud;

    private String sign;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 40);
        initView();
        setListener();
    }

    private void setListener() {
        edPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edPhone.getText().toString().length() == 11 && !s.toString().isEmpty()) {
                    btLogon.setEnabled(true);
                    btLogon.setUseShape();
                    btLogon.setTextColor(Color.WHITE);
                } else {
                    btLogon.setEnabled(false);
                    btLogon.setUseShape();
                    btLogon.setTextColor(getResources().getColor(R.color.gay));

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edPhone.addTextListener(new PowerfulEditText.TextListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                if (edPhone.getText().toString().length() == 11 && s.toString().length() == 4) {
                    btLogon.setEnabled(true);
                    btLogon.setUseShape();
                    btLogon.setTextColor(Color.WHITE);
                } else {
                    btLogon.setEnabled(false);
                    btLogon.setUseShape();
                    btLogon.setTextColor(getResources().getColor(R.color.gay));
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initView() {
        captchaTimeCount = new CaptchaTimeCount(Contacts.MILLIS_IN_TOTAL, Contacts.COUNT_DOWN_INTERVAL, tvCode, this);
        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(0.5f);

    }


    @OnClick({R.id.toolbar_back, R.id.tv_code, R.id.bt_logon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.tv_code:
                String phone = edPhone.getText().toString();
                if (!TextUtils.isEmpty(phone) && RegexUtils.isMobileSimple(phone)) {
                    getCode(phone);
                } else {
                    ToastUtils.showToast("请输入正确的手机号");
                }
                break;
            case R.id.bt_logon:
                login();
                break;
            default:
                break;
        }
    }

    private void login() {
        hud.show();
        final String phone = edPhone.getText().toString();
        String code = edCode.getText().toString();
        Map<String, String> map = new HashMap<>();
        map.put("mobile", phone);
        map.put("code", code);
        map.put("sign", sign);
        map.put("version", "1");
        ApiService.GET_SERVICE(Api.CHECKCODE, map, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject json) {
                try {
                    JSONObject data = json.getJSONObject("data");
                    String accessToken = data.getString("accessToken");
                    SPUtil.putString(Contacts.TOKEN,accessToken);
                    SPUtil.putString(Contacts.PHONE,phone);
                    EventBus.getDefault().post(new LoginEvent(phone));
                    String title = getIntent().getStringExtra("title");
                    String link = getIntent().getStringExtra("link");
                    if (!TextUtils.isEmpty(title)) {
                        String id = getIntent().getStringExtra("id");
                        new BrowsingHistory().execute(id,Contacts.PRODUCT_TYPE);
                        Intent intent = new Intent(LoginActivity.this, HtmlActivity.class);
                        intent.putExtra("title", title);
                        intent.putExtra("link", link);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent();
                        intent.putExtra("phone", phone);
                        setResult(200, intent);
                    }
                    finish();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void requestFailure(int code, String msg) {

                ToastUtils.showToast(msg);
            }

            @Override
            public void onFinish() {
                hud.dismiss();
            }
        });




    }

    /**
     * 发送验证码
     *
     * @param phone
     */
    private void getCode(String phone) {
        captchaTimeCount.start();
        Map<String, String> map = new HashMap<>();
        map.put("mobile", phone);

        ApiService.GET_SERVICE(Api.CODE, map, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject json) {
                try {
                    JSONObject data = json.getJSONObject("data");
                    sign = data.getString("sign");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void requestFailure(int code, String msg) {
                ToastUtils.showToast(msg);
            }

            @Override
            public void onFinish() {

            }
        });

    }
}
