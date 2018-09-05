package com.ant.cashant.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import com.ant.cashant.model.RecommProduct;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ant.cashant.App;
import com.ant.cashant.R;
import com.ant.cashant.base.BaseFragment;
import com.ant.cashant.base.DiBean;
import com.ant.cashant.common.Api;
import com.ant.cashant.common.ApiService;
import com.ant.cashant.inter.OnRequestDataListener;
import com.ant.cashant.model.Banner;
import com.ant.cashant.model.Product;
import com.ant.cashant.ui.activity.DescActivity;
import com.ant.cashant.ui.activity.HtmlActivity;
import com.ant.cashant.ui.activity.MainActivity;
import com.ant.cashant.ui.adpater.HotAdapter;
import com.ant.cashant.ui.adpater.NewsAdapter;
import com.ant.cashant.utils.LogUtils;
import com.ant.cashant.utils.ToastUtils;
import com.ant.cashant.view.RecycleViewDivider;
import com.ant.cashant.view.SpacesItemDecoration;
import com.ant.cashant.view.refresh.EasyRefreshLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
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
    private String mBankLink;
    private List<Product> products;
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
        setListener();
        getData();
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
                MainActivity.navigationController.setSelect(1);
            }
        });
        mRecommen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(products!=null&&products.size()!=0){
                    Product product = products.get(0);
                    Intent intent = new Intent(getActivity(), DescActivity.class);
                    intent.putExtra("title", product.getProduct_name());
                    intent.putExtra("id", product.getId());
                    startActivity(intent);
                }

            }
        });
        mCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(mBankLink)){
                    Intent intent = new Intent(getActivity(), HtmlActivity.class);
                    intent.putExtra("title", "我要办卡");
                    intent.putExtra("link", mBankLink);
                    startActivity(intent);
                }else {
                    ToastUtils.showToast("系统升级中...");
                }
            }
        });

        mHotAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Product product = mHotAdapter.getData().get(position);
                Intent intent = new Intent(getActivity(), DescActivity.class);
                intent.putExtra("title", product.getProduct_name());
                intent.putExtra("id", product.getId());
                startActivity(intent);
            }
        });
        mNewsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RecommProduct product = mNewsAdapter.getData().get(position);
                Intent intent = new Intent(getActivity(), DescActivity.class);
                intent.putExtra("title", product.getProduct_name());
                intent.putExtra("id", product.getId());
                startActivity(intent);

            }
        });
     /*   foot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.navigationController.setSelect(1);
            }
        });*/
    }
    private RelativeLayout foot;
    private void initView() {
        View view = getLayoutInflater().inflate(R.layout.foot_layout, null);
         foot = view.findViewById(R.id.foot_layout);
        mNewsAdapter = new NewsAdapter(null);
        mHotAdapter=new HotAdapter(null);
        mRecylerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecylerview.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.VERTICAL, R.drawable.recycler_divider));
       // mNewsAdapter.addFooterView(view);
        mNewsAdapter.addHeaderView(getHeader());
        mRecylerview.setAdapter(mNewsAdapter);
        mHotRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        mHotRecyclerView.addItemDecoration(new SpacesItemDecoration(10));
        mHotRecyclerView.setAdapter(mHotAdapter);
    }

    private RecyclerView mHotRecyclerView;
    private LinearLayout mLoan,mRecommen,mCredit;
    private BGABanner mBGABanner;
    private ViewFlipper  flipper;
    private View getHeader() {
        View view = getLayoutInflater().inflate(R.layout.home_header_layout, null);
        mHotRecyclerView=view.findViewById(R.id.hot_recycler);
        mLoan=view.findViewById(R.id.layout_loan);
        mRecommen=view.findViewById(R.id.layout_recommen);
        flipper=view.findViewById(R.id.ViewFlipper);
        for (int i = 0; i < 5; i++) {
            View viewer = LayoutInflater.from(getActivity()).inflate(R.layout.layout_custom, null);
            flipper.addView(viewer);
        }
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
                        .load(model.getUrl())
                        .apply(options)
                        .into(itemView);


            }
        });
        mBGABanner.setDelegate(new BGABanner.Delegate<ImageView, Banner>() {
            @Override
            public void onBannerItemClick(BGABanner banner, ImageView itemView, Banner model, int position) {
                Intent intent = new Intent(getActivity(), HtmlActivity.class);
                intent.putExtra("link", model.getH5_link());
                intent.putExtra("title", model.getName());
                startActivity(intent);
            }
        });
        return view;
    }

    private void getData() {
        /**新品**/
        ApiService.GET_Get(Api.Home.HOT_PRODUCT, null,new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject json) {
                try {
                    String data = json.getString("data");
                    List<RecommProduct> mRecommendList = new Gson().fromJson(data, new TypeToken<List<RecommProduct>>() {
                    }.getType());
                     mNewsAdapter.setNewData(mRecommendList);
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
        /**Banner**/

        ApiService.GET_Get(Api.Home.BANNER,null, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject json) {
                try {
                    String   data = json.getString("data");
                    List<Banner> mRecommendList = new Gson().fromJson(data, new TypeToken<List<Banner>>() {
                    }.getType());
                    mBGABanner.setData(mRecommendList,null);
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
        /**热门推荐**/

        ApiService.GET_Get(Api.Home.KILL, null,new OnRequestDataListener() {
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
        /**我要办卡**/
        ApiService.GET_SERVICE(Api.Home.BANK, null, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject json) {
                try {
                    JSONObject data = json.getJSONObject("data");
                    mBankLink = data.getString("card");

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
        /**精品推荐**/
        ApiService.GET_SERVICE(Api.Home.RECOMMEND, null, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject json) {
                try {
                    String data = json.getString("data");
                    products = new Gson().fromJson(data, new TypeToken<List<Product>>() {}.getType());
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
