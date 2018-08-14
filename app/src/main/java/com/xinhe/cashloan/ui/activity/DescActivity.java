package com.xinhe.cashloan.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.xinhe.cashloan.R;
import com.xinhe.cashloan.base.BaseActivity;
import com.xinhe.cashloan.common.Api;
import com.xinhe.cashloan.common.ApiService;
import com.xinhe.cashloan.common.Contacts;
import com.xinhe.cashloan.glide.GlideCircleTransform;
import com.xinhe.cashloan.inter.OnRequestDataListener;
import com.xinhe.cashloan.model.CreditBean;
import com.xinhe.cashloan.model.Product;
import com.xinhe.cashloan.model.ProductDetail;
import com.xinhe.cashloan.ui.activity.login.LoginActivity;
import com.xinhe.cashloan.utils.SPUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class DescActivity extends BaseActivity {

    @BindView(R.id.toolbar_back)
    ImageView toolbarBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.tv_strategy)
    TextView tvStrategy;
    @BindView(R.id.minimum_amount)
    TextView minimumAmount;
    @BindView(R.id.min_cycle)
    TextView minCycle;
    @BindView(R.id.min_algorithm)
    TextView minAlgorithm;
    @BindView(R.id.fastest_time)
    TextView fastestTime;
    @BindView(R.id.app_condition)
    TextView appCondition;
    private ProductDetail productDetail;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_desc;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        getData();
    }

    private void initView() {
        String title = getIntent().getStringExtra("title");
        toolbarTitle.setText(title);
        toolbarBack.setVisibility(View.VISIBLE);
    }

    private void getData() {
        String id = getIntent().getStringExtra("id");
        Map<String, String> map = new HashMap<>();
        map.put("id", id);

        ApiService.GET_SERVICE(Api.Home.PRODUCT_DETAIL, map, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject json) {
                try {
                    String data = json.getString("data");
                    Gson gson = new Gson();
                    productDetail = gson.fromJson(data, ProductDetail.class);
                    fillData(productDetail);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void requestFailure(int code, String msg) {

            }

            @Override
            public void onFinish() {

            }
        });

    }

    private void fillData(ProductDetail productDetail) {
        RequestOptions options = new RequestOptions()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(this).load(productDetail.getP_logo()).apply(options).into(ivLogo);
        tvStrategy.setText(productDetail.getP_name() + "攻略");
        minimumAmount.setText("借款额度:"+productDetail.getMinimum_amount()+"-"+productDetail.getMaximum_amount());
        int min_cycle = productDetail.getMin_cycle();
        int max_cycle = productDetail.getMax_cycle();
        if(min_cycle==max_cycle){
            minCycle.setText("借款期限:"+min_cycle+"天");
        }else {
            minCycle.setText("借款期限:"+min_cycle+"-"+max_cycle+"天");

        }
        String interestAlgorithm = productDetail.getInterest_algorithm();
        if ("0".equals(interestAlgorithm)) {
            minAlgorithm.setText("借款利率:"+productDetail.getMin_algorithm()+"/天");
        } else {
            minAlgorithm.setText("借款利率:"+productDetail.getMin_algorithm()+"/月");
        }
        fastestTime.setText("下款速度:"+productDetail.getFastest_time()+"到账");

        appCondition.setText(productDetail.getProduct_details());
    }



    @OnClick({R.id.toolbar_back, R.id.apply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.apply:

                String token = SPUtil.getString(Contacts.TOKEN);
                if(!TextUtils.isEmpty(token)){
                    Intent intent = new Intent(this, HtmlActivity.class);
                    intent.putExtra("title", productDetail.getP_name());
                    intent.putExtra("link", productDetail.getUrl());
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(this, LoginActivity.class);
                    intent.putExtra("title", productDetail.getP_name());
                    intent.putExtra("link", productDetail.getUrl());
                    startActivity(intent);
                }

                break;
            default:
                break;
        }
    }
}
