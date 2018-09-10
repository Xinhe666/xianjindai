package com.ant.cashant.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ant.cashant.R;
import com.ant.cashant.base.BaseFragment;
import com.ant.cashant.common.Api;
import com.ant.cashant.common.ApiService;
import com.ant.cashant.common.Contacts;
import com.ant.cashant.inter.OnRequestDataListener;
import com.ant.cashant.model.ProductList;
import com.ant.cashant.model.ScreenEvent;
import com.ant.cashant.ui.activity.HtmlActivity;
import com.ant.cashant.ui.activity.LoginActivity;
import com.ant.cashant.ui.adpater.LoanAdapter;
import com.ant.cashant.utils.BrowsingHistory;
import com.ant.cashant.utils.SPUtil;
import com.ant.cashant.utils.ToastUtils;
import com.ant.cashant.view.SpacesItemDecoration;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 *
 * @author apple
 * 贷款大全
 */
public class LoanFragment extends BaseFragment {


    @BindView(R.id.recylerview)
    RecyclerView recylerview;
    @BindView(R.id.SwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.tv_limit)
    TextView tvLimit;
    @BindView(R.id.tv_rate)
    TextView tvRate;
    @BindView(R.id.tv_success)
    TextView tvSuccess;
    @BindView(R.id.tv_speed)
    TextView tvSpeed;
    Unbinder unbinder;
    private LoanAdapter mLoanAdapter;
    private String identity="";
    private String money="";
    public LoanFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_lottery;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        initView();
        setListener();
        getData(null, identity, money);
    }

    private void setListener() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(null, identity, money);
            }
        });
        mLoanAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ProductList.ListBean product = mLoanAdapter.getData().get(position);
                String token = SPUtil.getString(Contacts.TOKEN);
                if (TextUtils.isEmpty(token)) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    intent.putExtra("title", product.getName());
                    intent.putExtra("link", product.getH5_url());
                    intent.putExtra("id", product.getId());
                    startActivity(intent);
                } else {
                    new BrowsingHistory().execute(product.getId());
                    Intent intent = new Intent(getActivity(), HtmlActivity.class);
                    intent.putExtra("title", product.getName());
                    intent.putExtra("link", product.getH5_url());
                    startActivity(intent);
                }
            }
        });
    }

    private void getData(String param, String identity, String money) {
        Map<String, String> map = new HashMap<>();
        map.put("param",param);
        map.put("identity",identity);
        map.put("money",money);
        ApiService.GET_Get(Api.Home.PRODUCT_SCREEN, map, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject json) {
                try {
                    String data = json.getString("data");
                    Gson gson = new Gson();
                    ProductList productList = gson.fromJson(data, ProductList.class);
                    mLoanAdapter.setNewData(productList.getList());

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
                if (mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });

    }

    private void initView() {
        mLoanAdapter = new LoanAdapter(null);
        recylerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recylerview.setAdapter(mLoanAdapter);
        recylerview.addItemDecoration(new SpacesItemDecoration(10));
        //recylerview.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.VERTICAL, R.drawable.recycler_divider));
    }

    @Subscribe
    public void setRefresh(ScreenEvent screenEvent) {
        identity=screenEvent.index;
        getData(null, screenEvent.index, money);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        unbinder.unbind();
    }

    @OnClick({R.id.tv_limit, R.id.tv_rate, R.id.tv_success, R.id.tv_speed})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_limit:
                initColor(tvLimit);
                getData("1",identity,money);
                break;
            case R.id.tv_rate:
                initColor(tvRate);
                getData("2",identity,money);
                break;
            case R.id.tv_success:
                initColor(tvSuccess);
                getData("3",identity,money);
                break;
            case R.id.tv_speed:
                initColor(tvSpeed);
                getData("4",identity,money);
                break;
            default:
                 break;
        }
    }

    private void  initColor(TextView textView){
        tvLimit.setTextColor(getResources().getColor(R.color.bg_color_33));
        tvRate.setTextColor(getResources().getColor(R.color.bg_color_33));
        tvSpeed.setTextColor(getResources().getColor(R.color.bg_color_33));
        tvSuccess.setTextColor(getResources().getColor(R.color.bg_color_33));
        textView.setTextColor(getResources().getColor(R.color.colorPrimary));
    }
}
