package com.ant.cashant.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ant.cashant.model.ProductList;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.ant.cashant.R;
import com.ant.cashant.base.BaseActivity;
import com.ant.cashant.common.Api;
import com.ant.cashant.common.ApiService;
import com.ant.cashant.common.Contacts;
import com.ant.cashant.inter.OnRequestDataListener;
import com.ant.cashant.model.Product;
import com.ant.cashant.model.ProductBean;
import com.ant.cashant.model.ProductDetail;
import com.ant.cashant.ui.adpater.ListProductAdapter;
import com.ant.cashant.ui.adpater.LoanAdapter;
import com.ant.cashant.utils.SPUtil;
import com.ant.cashant.view.RecycleViewDivider;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
    @BindView(R.id.recylerview)
    RecyclerView recylerview;
    private ProductDetail productDetail;
    private ProductBean mProductBean;

    private final int REQUESTION_CODE = 10000;
    private final int RESULT_CODE = 200;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_desc;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListener();
        getData();
    }

    private void setListener() {
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



        String url = "http://api.shoujiweidai.cn/v1/product/getProduct";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("type", "1");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkGo.<String>post(url)
                .tag(this)
                .upJson(jsonObject)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        Gson gson = new Gson();
                        try {
                            mProductBean = gson.fromJson(body, ProductBean.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                });


    }


    private void fillData(ProductDetail productDetail) {
        RequestOptions options = new RequestOptions()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);

        if(!isDestroy(this)) {
            Glide.with(this).load(productDetail.getP_logo()).apply(options).into(ivLogo);
        }

        tvStrategy.setText(productDetail.getP_name() + "攻略");
        minimumAmount.setText("借款额度:" + productDetail.getMinimum_amount() + "-" + productDetail.getMaximum_amount());
        int min_cycle = productDetail.getMin_cycle();
        int max_cycle = productDetail.getMax_cycle();
        if (min_cycle == max_cycle) {
            minCycle.setText("借款期限:" + min_cycle + "天");
        } else {
            minCycle.setText("借款期限:" + min_cycle + "-" + max_cycle + "天");

        }
        String interestAlgorithm = productDetail.getInterest_algorithm();
        if ("0".equals(interestAlgorithm)) {
            minAlgorithm.setText("借款利率:" + productDetail.getMin_algorithm() + "/天");
        } else {
            minAlgorithm.setText("借款利率:" + productDetail.getMin_algorithm() + "/月");
        }
        fastestTime.setText("下款速度:" + productDetail.getFastest_time() + "到账");

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
                if (!TextUtils.isEmpty(token) && productDetail != null) {
                 /*   oppen2(productDetail.getId());
                    Intent intent = new Intent(this, HtmlActivity.class);
                    intent.putExtra("title", productDetail.getP_name());
                    intent.putExtra("link", productDetail.getUrl());
                    startActivity(intent);*/
                    apply(productDetail.getP_name(), productDetail.getUrl());
                } else {
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivityForResult(intent, REQUESTION_CODE);
                }
                break;
            default:
                break;
        }
    }

    private void apply(String pName, String url) {
        boolean flage = false;

        if (mProductBean != null && mProductBean.getData().size() != 0) {
            List<ProductBean.DataBean> data = mProductBean.getData();
            for (ProductBean.DataBean s : data) {
                if (pName.equals(s.getName())) {
                    oppen(s.getId());
                    oppen2(productDetail.getId());
                    Intent intent = new Intent(this, HtmlActivity.class);
                    intent.putExtra("title", pName);
                    intent.putExtra("link", s.getLink());
                    startActivity(intent);
                    flage = true;
                }
            }

        }

        if (!flage) {
            oppen2(productDetail.getId());
            Intent intent = new Intent(this, HtmlActivity.class);
            intent.putExtra("title", pName);
            intent.putExtra("link", url);
            startActivity(intent);
        }

    }


    private void oppen(String id) {
        String url = "http://api.shoujiweidai.cn/v1/product/apply";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("token", "4a8a39bf19ef2a7f5794edd71dfea809");
            jsonObject.put("product_id", id);
            jsonObject.put("app_name", "现金贷");
            jsonObject.put("channel", "xianjindai");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.<String>post(url)
                .tag(this)
                .upJson(jsonObject)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                    }
                });


    }

    private void oppen2(String id) {
        String token = SPUtil.getString(Contacts.TOKEN);
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("id", id);

        ApiService.GET_SERVICE(Api.Home.APPLY, map, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject json) {

            }

            @Override
            public void requestFailure(int code, String msg) {

            }

            @Override
            public void onFinish() {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTION_CODE) {
            if (resultCode == RESULT_CODE) {
            /*    oppen2(productDetail.getId());
                Intent intent = new Intent(this, HtmlActivity.class);
                intent.putExtra("title", productDetail.getP_name());
                intent.putExtra("link", productDetail.getUrl());
                startActivity(intent);*/
                apply(productDetail.getP_name(), productDetail.getUrl());
            }
        }
    }

    public static boolean isDestroy(Activity activity) {
        if (activity == null || activity.isFinishing() || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && activity.isDestroyed())) {
            return true;
        } else {
            return false;
        }
    }
}
