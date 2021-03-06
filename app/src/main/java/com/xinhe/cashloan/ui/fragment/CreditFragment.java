package com.xinhe.cashloan.ui.fragment;


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
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xinhe.cashloan.R;
import com.xinhe.cashloan.base.BaseFragment;
import com.xinhe.cashloan.common.Api;
import com.xinhe.cashloan.common.ApiService;
import com.xinhe.cashloan.common.Contacts;
import com.xinhe.cashloan.inter.OnRequestDataListener;
import com.xinhe.cashloan.model.CreditBean;
import com.xinhe.cashloan.ui.activity.HtmlActivity;
import com.xinhe.cashloan.ui.activity.login.LoginActivity;
import com.xinhe.cashloan.ui.adpater.CreditAdapter;
import com.xinhe.cashloan.utils.SPUtil;
import com.xinhe.cashloan.utils.ToastUtils;
import com.xinhe.cashloan.view.RecycleViewDivider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 *
 * @author apple
 *         信用卡
 */
public class CreditFragment extends BaseFragment {


    @BindView(R.id.recylerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.credit_srl)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.toolbar_back)
    ImageView toolbarBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    private CreditAdapter mCreditAdapter;
    private View errorView;
    private View notDataView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_credit;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        setListener();
    }

    private void initView() {
        toolbarTitle.setText("信用卡");
        mCreditAdapter = new CreditAdapter(null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.VERTICAL, R.drawable.recycler_divider));
        mRecyclerView.setAdapter(mCreditAdapter);

        notDataView = getActivity().getLayoutInflater().inflate(R.layout.view_empty, (ViewGroup) mRecyclerView.getParent(), false);
        errorView = getActivity().getLayoutInflater().inflate(R.layout.view_error, (ViewGroup) mRecyclerView.getParent(), false);

    }

    private void setListener() {
        mRefreshLayout.setColorSchemeResources(R.color.color_yellow,R.color.colorAccent);

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });

        mCreditAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String token = SPUtil.getString(Contacts.TOKEN);
                CreditBean creditBean = mCreditAdapter.getData().get(position);
                if(!TextUtils.isEmpty(token)){
                    Intent intent=new Intent(getActivity(), HtmlActivity.class);
                    intent.putExtra("title",creditBean.getName());
                    intent.putExtra("link",creditBean.getLink());
                    startActivity(intent);
                }else {
                    Intent intent=new Intent(getActivity(), LoginActivity.class);
                    intent.putExtra("title",creditBean.getName());
                    intent.putExtra("link",creditBean.getLink());
                    startActivity(intent);
                }
            }
        });
    }

    private void initData() {
        ApiService.GET_SERVICE(Api.CREDIT, null, new OnRequestDataListener() {

            @Override
            public void requestSuccess(int code, JSONObject json) {
                try {
                    JSONArray data = json.getJSONArray("data");
                    List<CreditBean> list = new Gson().fromJson(data.toString(), new TypeToken<List<CreditBean>>() {
                    }.getType());
                    mCreditAdapter.setNewData(list);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void requestFailure(int code, String msg) {
                mCreditAdapter.setEmptyView(errorView);
            }

            @Override
            public void onFinish() {
                if(mRefreshLayout.isRefreshing()){
                    mRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

}
