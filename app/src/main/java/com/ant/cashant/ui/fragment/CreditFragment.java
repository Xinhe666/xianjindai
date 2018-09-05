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
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ant.cashant.R;
import com.ant.cashant.base.BaseFragment;
import com.ant.cashant.common.Api;
import com.ant.cashant.common.ApiService;
import com.ant.cashant.common.Contacts;
import com.ant.cashant.inter.OnRequestDataListener;
import com.ant.cashant.model.CreditBean;
import com.ant.cashant.ui.activity.HtmlActivity;
import com.ant.cashant.ui.activity.login.LoginActivity;
import com.ant.cashant.ui.adpater.CreditAdapter;
import com.ant.cashant.utils.SPUtil;
import com.ant.cashant.utils.ToastUtils;
import com.ant.cashant.view.RecycleViewDivider;

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

    private final int REQUESTION_CODE=10000;
    private final int RESULT_CODE=200;

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
        mRefreshLayout.setColorSchemeResources(R.color.color_blue,R.color.colorAccent);

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
