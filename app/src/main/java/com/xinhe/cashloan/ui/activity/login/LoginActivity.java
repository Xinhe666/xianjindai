package com.xinhe.cashloan.ui.activity.login;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.xinhe.cashloan.R;
import com.xinhe.cashloan.base.BaseActivity;
import com.xinhe.cashloan.utils.CaptchaTimeCount;
import com.xinhe.cashloan.utils.CommonUtil;
import com.xinhe.cashloan.utils.ToastUtils;

import butterknife.BindView;

/**
 * @author yanshihao
 */
public class LoginActivity extends BaseActivity
        implements LoginMvpView, VerListener, SeekBar.OnSeekBarChangeListener {

    private LoginPersenter mLoginPersenter;

    @BindView(R.id.et_phone)
    EditText mPhone;
    @BindView(R.id.et_code)
    EditText mCode;
    @BindView(R.id.login_sb)
    SeekBar mSeekBar;
    @BindView(R.id.bt_code)
    Button mBtnCode;
    @BindView(R.id.layout_code)
    RelativeLayout mLayoutCode;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_back)
    ImageView mBack;
    @BindView(R.id.toolbar_title)
    TextView mTitle;
    private KProgressHUD mKProgressHUD;

    private CaptchaTimeCount captchaTimeCount;

    private boolean isVerify;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    protected void initView() {
        mToolbar.setBackgroundColor(Color.parseColor("#ffffff"));
        mTitle.setText("登录");
        mBack.setColorFilter(R.color.text_color_huang);
        mTitle.setTextColor(Color.parseColor("#000000"));
        mSeekBar.setOnSeekBarChangeListener(this);
        mLayoutCode.setVisibility(View.GONE);
        captchaTimeCount = new CaptchaTimeCount(60000, 1000, mBtnCode, this);
        mBtnCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2018/8/8 弹出对话框 防刷
                boolean b = CommonUtil.checkPhone(getAccount(), true);
                if (b) {
                    showDialog();
                }
            }
        });
        mKProgressHUD = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(0.5f);
        mLoginPersenter = new LoginPersenter(this);
    }


    @Override
    public void loginSuccess(String token) {
        saveToken(token);
        launch();
    }

    private void saveToken(String token) {
        // TODO: 2018/8/8 保存token 跳转

    }

    @Override
    public void loginFaild(String error) {
        ToastUtils.showToast(error);
    }

    @Override
    public String getAccount() {
        return mPhone.getText().toString().trim();
    }

    @Override
    public String getPassward() {
        return mCode.getText().toString().trim();
    }

    @Override
    public void showDialog() {
        VerificationFragment verificationFragment = new VerificationFragment();
        verificationFragment.show(getSupportFragmentManager(), "code");
    }


    @Override
    public void isNewUser(int isolduser, String token) {
        //判断新老用户（1是（老用户） 0否（新用户））
        isVerify = true;
        if (1 == isolduser) {
            saveToken(token);
            mLayoutCode.setVisibility(View.GONE);
        } else {
            mLayoutCode.setVisibility(View.VISIBLE);
            captchaTimeCount.start();
            mLoginPersenter.getCode();
        }
    }

    @Override
    public void getCodeSuccess() {
        //获取验证吗成功
        ToastUtils.showToast("验证码已发送");
    }

    @Override
    public void getCodeFaild(String error) {
        ToastUtils.showToast(error);
    }

    @Override
    public void success() {
        // 图片验证码输入正确 判断新老用户
        mLoginPersenter.judgment();
    }

    private void launch() {
        // TODO: 2018/8/10  跳转
        ToastUtils.showToast("登录成功");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        int progress = seekBar.getProgress();
        seekBar.setProgress(0);
        if (progress != 100) {
            return;
        }
        boolean b = CommonUtil.checkPhone(getAccount(), true);
        if (!b) {
            return;
        }
        if (!isVerify) {
            ToastUtils.showToast("请先验证，验证后登录");
            return;
        }
        if (mLayoutCode.getVisibility() == View.VISIBLE) {
            //新用户 验证 code 是否填入正确
            if (!TextUtils.isEmpty(getPassward()) && getPassward().length() == 4) {
                mLoginPersenter.verifyCode(mKProgressHUD);
            } else {
                ToastUtils.showToast("验证码错误");
            }
            return;
        }
        if (isVerify && mLayoutCode.getVisibility() == View.GONE) {
            launch();
        }
    }
}
