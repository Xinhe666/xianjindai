package com.ant.cashant.ui.activity;

import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ant.cashant.R;
import com.ant.cashant.base.BaseActivity;
import com.ant.cashant.ui.adpater.MyViewPagerAdapter;
import com.ant.cashant.ui.adpater.NoTouchViewPager;
import com.ant.cashant.ui.fragment.CenterFragment;
import com.ant.cashant.ui.fragment.CreditFragment;
import com.ant.cashant.ui.fragment.HomeFragment;
import com.ant.cashant.ui.fragment.LoanFragment;
import com.ant.cashant.utils.StatusBarUtil;
import com.ant.cashant.utils.ToastUtils;
import com.ant.cashant.view.NormalItemView;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;


/**
 * @author yanshihao
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.tab)
    PageNavigationView tab;
    @BindView(R.id.viewPager)
    NoTouchViewPager viewPager;
    public static NavigationController navigationController;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        StatusBarUtil.setTranslucentForImageViewInFragment(this, null);
        AndPermission.with(this)
                .requestCode(200)
                .permission(Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .callback(permListener)
                .start();
    }


    private void initView() {
        ArrayList<Fragment> mFragments= new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new LoanFragment());
        mFragments.add(new CenterFragment());
        navigationController = tab.custom()
                .addItem(newItem(R.mipmap.iv_home, R.mipmap.iv_home_select, "首页"))
                .addItem(newItem(R.mipmap.iv_welfare, R.mipmap.iv_welfare_select, "贷款大全"))
                .addItem(newItem(R.mipmap.iv_center, R.mipmap.iv_center_select, "信用卡"))
                .build();

        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), mFragments);
        viewPager.setAdapter(myViewPagerAdapter);
        navigationController.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(mFragments.size());
    }

    //创建一个Item
    private BaseTabItem newItem(int drawable, int checkedDrawable, String text) {
        NormalItemView normalItemView = new NormalItemView(this);
        normalItemView.initialize(drawable, checkedDrawable, text);
        normalItemView.setTextDefaultColor(Color.GRAY);
        normalItemView.setTextCheckedColor(getResources().getColor(R.color.color_blue));
        return normalItemView;
    }


    private long mLastBackTime = 0;

    @Override
    public void onBackPressed() {
            if ((System.currentTimeMillis() - mLastBackTime) < 1000) {
                finish();
            } else {
                mLastBackTime = System.currentTimeMillis();
                ToastUtils.showToast( "再按一次退出");
            }
    }

    private PermissionListener permListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // 权限申请成功回调。
            // 和onActivityResult()的requestCode一样，用来区分多个不同的请求。
            if (requestCode == 200) {
               // updateDiy();
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。
            ToastUtils.showToast( "为了您的账号安全,请打开设备权限");
            if (requestCode == 200) {
                if ((AndPermission.hasAlwaysDeniedPermission(MainActivity.this, deniedPermissions))) {
                    AndPermission.defaultSettingDialog(MainActivity.this, 500).show();
                }
            }
        }
    };


}
