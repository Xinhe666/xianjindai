package com.xinhe.cashloan.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.xinhe.cashloan.R;
import com.xinhe.cashloan.base.BaseFragment;

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

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_credit;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


}
