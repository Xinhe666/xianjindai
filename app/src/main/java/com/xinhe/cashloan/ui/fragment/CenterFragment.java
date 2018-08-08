package com.xinhe.cashloan.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xinhe.cashloan.R;
import com.xinhe.cashloan.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class CenterFragment extends BaseFragment {


    public CenterFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_center;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
