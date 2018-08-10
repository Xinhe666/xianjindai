package com.xinhe.cashloan.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xinhe.cashloan.R;
import com.xinhe.cashloan.base.BaseFragment;
import com.xinhe.cashloan.common.Api;
import com.xinhe.cashloan.common.ApiService;
import com.xinhe.cashloan.inter.OnRequestDataListener;
import com.xinhe.cashloan.model.CreditBean;
import com.xinhe.cashloan.ui.adpater.CreditAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 *
 * @author apple
 * 信用卡
 */
public class CreditFragment extends BaseFragment {


    @BindView(R.id.recylerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.credit_srl)
    SwipeRefreshLayout mRefreshLayout;
    private CreditAdapter mCreditAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_credit;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        mCreditAdapter = new CreditAdapter(null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mCreditAdapter);
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

            }

            @Override
            public void onFinish() {

            }
        });
    }

}
