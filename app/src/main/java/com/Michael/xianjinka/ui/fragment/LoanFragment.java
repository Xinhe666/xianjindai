package com.Michael.xianjinka.ui.fragment;


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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mancj.slideup.SlideUp;
import com.Michael.xianjinka.R;
import com.Michael.xianjinka.base.BaseFragment;
import com.Michael.xianjinka.common.Api;
import com.Michael.xianjinka.common.ApiService;
import com.Michael.xianjinka.inter.OnRequestDataListener;
import com.Michael.xianjinka.model.Product;
import com.Michael.xianjinka.ui.activity.DescActivity;
import com.Michael.xianjinka.ui.adpater.LoanAdapter;
import com.Michael.xianjinka.utils.ToastUtils;
import com.Michael.xianjinka.view.RecycleViewDivider;

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


    @BindView(R.id.tv_recommend)
    TextView tvRecommend;
    @BindView(R.id.iv_recommend)
    ImageView ivRecommend;
    @BindView(R.id.table_select)
    RelativeLayout tableSelect;
    @BindView(R.id.tv_quick)
    TextView tvQuick;
    @BindView(R.id.table_quick)
    RelativeLayout tableQuick;
    @BindView(R.id.recylerview)
    RecyclerView recylerview;
    @BindView(R.id.bt1)
    Button bt1;
    @BindView(R.id.bt2)
    Button bt2;
    @BindView(R.id.bt3)
    Button bt3;
    @BindView(R.id.product_choose_pop)
    LinearLayout productChoosePop;
    @BindView(R.id.SwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.empty_layout)
    RelativeLayout emptyLayout;
    private LoanAdapter mLoanAdapter;

    private SlideUp slideUp;

    private RotateAnimation reverseAnimation;

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
                Product product = mLoanAdapter.getData().get(position);
                Intent intent = new Intent(getActivity(), DescActivity.class);
                intent.putExtra("title", product.getP_name());
                intent.putExtra("id", product.getId());
                startActivity(intent);
            }
        });
    }

    private void getData(String s) {
        Map<String,String>map=new HashMap<>();
        map.put("identity",s);

        ApiService.GET_SERVICE(Api.Home.PRODUCT_SCREEN, map, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject json) {
                try {
                    String data = json.getString("data");
                    List<Product> products = new Gson().fromJson(data, new TypeToken<List<Product>>() {}.getType());
                    mLoanAdapter.setNewData(products);

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
        recylerview.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.VERTICAL, R.drawable.recycler_divider));
        reverseAnimation = (RotateAnimation) AnimationUtils.loadAnimation(getContext(), R.anim.reverse_anim);
        slideUp = new SlideUp.Builder(productChoosePop)
                .withStartGravity(Gravity.TOP)
                .withLoggingEnabled(true)
                .withGesturesEnabled(false)
                .withStartState(SlideUp.State.HIDDEN)
                .build();
    }


    @OnClick({R.id.bt_default, R.id.bt1, R.id.bt2, R.id.bt3, R.id.empty_layout
            ,R.id.table_select,R.id.table_quick})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.table_select:
                ivRecommend.setImageResource(R.mipmap.down_arrow_yellow);
                tvRecommend.setTextColor(getResources().getColor(R.color.color_yellow));
                tvQuick.setTextColor(getResources().getColor(R.color.text_color_32));
                if(slideUp.isVisible()){
                    slideUp.hide();
                    ivRecommend.clearAnimation();
                }else {
                    ivRecommend.startAnimation(reverseAnimation);
                    slideUp.show();
                }

                break;
            case R.id.table_quick:
                if(slideUp.isVisible()){
                    slideUp.hide();
                }
                ivRecommend.clearAnimation();
                ivRecommend.setImageResource(R.mipmap.down_arrow_black);
                tvRecommend.setTextColor(getResources().getColor(R.color.text_color_32));
                tvQuick.setTextColor(getResources().getColor(R.color.color_yellow));
                getTopProduct();

                break;
            case R.id.bt_default:
                if(slideUp.isVisible()){
                    slideUp.hide();
                }
                getData(null);
                break;
            case R.id.bt1:
                if(slideUp.isVisible()){
                    slideUp.hide();
                }
                getData("1");
                break;
            case R.id.bt2:
                if(slideUp.isVisible()){
                    slideUp.hide();
                }
                getData("3");
                break;
            case R.id.bt3:
                if(slideUp.isVisible()){
                    slideUp.hide();
                }
                getData("4");
                break;
            case R.id.empty_layout:
                if(slideUp.isVisible()){
                    slideUp.hide();
                }
                break;
            default:
                break;
        }
    }

    private void getTopProduct() {
        ApiService.GET_SERVICE(Api.Home.TOP_PRODUCT, null, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject json) {
                try {
                    String data = json.getString("data");
                    List<Product> products = new Gson().fromJson(data, new TypeToken<List<Product>>() {}.getType());
                    mLoanAdapter.setNewData(products);

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
