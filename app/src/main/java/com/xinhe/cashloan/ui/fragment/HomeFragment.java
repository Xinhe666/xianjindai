package com.xinhe.cashloan.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.xinhe.cashloan.App;
import com.xinhe.cashloan.R;
import com.xinhe.cashloan.base.BaseFragment;
import com.xinhe.cashloan.common.Api;
import com.xinhe.cashloan.common.ApiService;
import com.xinhe.cashloan.inter.OnRequestDataListener;
import com.xinhe.cashloan.model.Banner;
import com.xinhe.cashloan.model.Product;
import com.xinhe.cashloan.ui.activity.HtmlActivity;
import com.xinhe.cashloan.ui.adpater.HotAdapter;
import com.xinhe.cashloan.ui.adpater.NewsAdapter;
import com.xinhe.cashloan.utils.ToastUtils;
import com.xinhe.cashloan.view.RecycleViewDivider;
import com.xinhe.cashloan.view.SpacesItemDecoration;
import com.xinhe.cashloan.view.refresh.EasyRefreshLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.bgabanner.BGABanner;


/**
 * A simple {@link Fragment} subclass.
 *
 * @author apple
 */
public class HomeFragment extends BaseFragment {


    @BindView(R.id.recylerview)
    RecyclerView mRecylerview;
    @BindView(R.id.easyRefresh)
    EasyRefreshLayout mEasyRefresh;

    private NewsAdapter mNewsAdapter;
    private HotAdapter mHotAdapter;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        getData();
        setListener();
    }



    private void setListener() {
        mEasyRefresh.setEnableLoadMore(false);
        mEasyRefresh.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {

            }

            @Override
            public void onRefreshing() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData();

                    }
                }, 1000);
            }
        });
        mLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mRecommen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mHotAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Product product = mHotAdapter.getData().get(position);
                Intent intent = new Intent(getActivity(), HtmlActivity.class);
                intent.putExtra("link", product.getId());
                startActivity(intent);
            }
        });
        mNewsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Product product = mNewsAdapter.getData().get(position);
                Intent intent = new Intent(getActivity(), HtmlActivity.class);
                intent.putExtra("link", product.getId());
                startActivity(intent);

            }
        });
    }

    private void initView() {
        mNewsAdapter = new NewsAdapter(null);
        mHotAdapter=new HotAdapter(null);
        mRecylerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecylerview.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.VERTICAL, R.drawable.recycler_divider));
        mNewsAdapter.addHeaderView(getHeader());
        mRecylerview.setAdapter(mNewsAdapter);

        mHotRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        mHotRecyclerView.addItemDecoration(new SpacesItemDecoration(10));
        mHotRecyclerView.setAdapter(mHotAdapter);
    }

    private RecyclerView mHotRecyclerView;
    private LinearLayout mLoan,mRecommen,mCredit;
    private BGABanner mBGABanner;
    private View getHeader() {
        View view = getLayoutInflater().inflate(R.layout.home_header_layout, null);
        mHotRecyclerView=view.findViewById(R.id.hot_recycler);
        mLoan=view.findViewById(R.id.layout_loan);
        mRecommen=view.findViewById(R.id.layout_recommen);
        mCredit=view.findViewById(R.id.layout_credit);
        mBGABanner=view.findViewById(R.id.banner_fresco_demo_content);
        mBGABanner.setAdapter(new BGABanner.Adapter<ImageView, Banner>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, Banner model, int position) {

                RequestOptions options = new RequestOptions()
                        .dontAnimate()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL);
                Glide.with(App.getApp())
                        .load(model.getPictrue())
                        .apply(options)
                        .into(itemView);


            }
        });
        mBGABanner.setDelegate(new BGABanner.Delegate<ImageView, Banner>() {
            @Override
            public void onBannerItemClick(BGABanner banner, ImageView itemView, Banner model, int position) {
                Intent intent = new Intent(getActivity(), HtmlActivity.class);
                intent.putExtra("link", model.getApp());
                intent.putExtra("title", model.getAdvername());
                startActivity(intent);

            }
        });
        return view;
    }

    private void getData() {
        ApiService.GET_SERVICE(Api.Home.NEW_PRODUCT, new JSONObject(), new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject json) {
                try {
                    String data = json.getString("data");
                    Gson gson = new Gson();
                    Product[] products = gson.fromJson(data, Product[].class);
                    if (products.length != 0) {
                        List<Product> products1 = Arrays.asList(products);
                        mNewsAdapter.setNewData(products1);
                    }
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
                if(mEasyRefresh.isRefreshing()){
                    mEasyRefresh.refreshComplete();
                }
            }
        });

        ApiService.GET_SERVICE(Api.Home.BANNER, new JSONObject(), new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject json) {
                try {
                    String   data = json.getString("data");
                    Gson gson = new Gson();
                    Banner[] Banners = gson.fromJson(data, Banner[].class);
                    List<Banner> banners = Arrays.asList(Banners);
                    mBGABanner.setData(banners,null);

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
        ApiService.GET_SERVICE(Api.Home.HOT_PRODUCT, new JSONObject(), new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject json) {
                try {
                    String data = json.getString("data");
                    Gson gson = new Gson();
                    Product[] products = gson.fromJson(data, Product[].class);
                    if (products.length != 0) {
                        List<Product> products1 = Arrays.asList(products);
                        mHotAdapter.setNewData(products1);
                    }
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

            }
        });
    }
}
