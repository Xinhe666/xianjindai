package com.xinhe.cashloan.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.xinhe.cashloan.R;
import com.xinhe.cashloan.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * @author apple
 * 信用卡
 */
public class CreditFragment extends BaseFragment {


    public CreditFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_credit;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
