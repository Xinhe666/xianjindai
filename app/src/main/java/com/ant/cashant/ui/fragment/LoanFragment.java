package com.ant.cashant.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ant.cashant.model.ProductList;
import com.ant.cashant.view.SpacesItemDecoration;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mancj.slideup.SlideUp;
import com.ant.cashant.R;
import com.ant.cashant.base.BaseFragment;
import com.ant.cashant.common.Api;
import com.ant.cashant.common.ApiService;
import com.ant.cashant.inter.OnRequestDataListener;
import com.ant.cashant.model.Product;
import com.ant.cashant.ui.activity.DescActivity;
import com.ant.cashant.ui.adpater.LoanAdapter;
import com.ant.cashant.utils.ToastUtils;
import com.ant.cashant.view.RecycleViewDivider;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 *
 * @author apple
 *         贷款大全
 */
public class LoanFragment extends BaseFragment {


    @BindView(R.id.recylerview)
    RecyclerView recylerview;
    @BindView(R.id.SwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private LoanAdapter mLoanAdapter;

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
        initView();
        setListener();
        getData(null);
    }

    private void setListener() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(null);
            }
        });
        mLoanAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ProductList.ListBean product = mLoanAdapter.getData().get(position);
                Intent intent = new Intent(getActivity(), DescActivity.class);
                intent.putExtra("title", product.getName());
                intent.putExtra("id", product.getId());
                startActivity(intent);
            }
        });
    }

    private void getData(String s) {
        Map<String,String>map=new HashMap<>();
       // map.put("identity",s);

        ApiService.GET_Get(Api.Home.PRODUCT_SCREEN, map, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject json) {
                try {
                    String data = json.getString("data");
                    Gson gson=new Gson();
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
                if(mSwipeRefreshLayout.isRefreshing()){
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



}
