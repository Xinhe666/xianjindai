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
import android.view.WindowInsets;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.ant.cashant.common.Contacts;
import com.ant.cashant.glide.GlideRoundTransform;
import com.ant.cashant.model.PromontionsList;
import com.ant.cashant.model.RecommProduct;
import com.ant.cashant.model.ScreenEvent;
import com.ant.cashant.ui.activity.LoginActivity;
import com.ant.cashant.utils.BrowsingHistory;
import com.ant.cashant.utils.SPUtil;
import com.avos.avoscloud.LogUtil;
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

import org.greenrobot.eventbus.EventBus;
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
    List<PromontionsList.ListBean> list ;
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
                EventBus.getDefault().post(new ScreenEvent("1"));
            }
        });
        mRecommen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.navigationController.setSelect(1);
                EventBus.getDefault().post(new ScreenEvent("1"));
            }
        });
        mCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.navigationController.setSelect(1);
                EventBus.getDefault().post(new ScreenEvent("1"));
            }
        });

        mHotAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Product product = mHotAdapter.getData().get(position);
                String token = SPUtil.getString( Contacts.TOKEN);
                if(TextUtils.isEmpty(token)){
                    Intent intent=new Intent(getActivity(), LoginActivity.class);
                    intent.putExtra("title",product.getProduct_name());
                    intent.putExtra("link",product.getH5_link());
                    intent.putExtra("id",product.getProduct_id());
                    startActivity(intent);
                }else {
                    new BrowsingHistory().execute(product.getProduct_id(),Contacts.PRODUCT_TYPE);
                    Intent intent=new Intent(getActivity(), HtmlActivity.class);
                    intent.putExtra("title",product.getProduct_name());
                    intent.putExtra("link",product.getH5_link());
                    startActivity(intent);
                }

            }
        });
        mNewsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RecommProduct product = mNewsAdapter.getData().get(position);
                String token = SPUtil.getString( Contacts.TOKEN);
                if(TextUtils.isEmpty(token)){
                    Intent intent=new Intent(getActivity(), LoginActivity.class);
                    intent.putExtra("title",product.getProduct_name());
                    intent.putExtra("link",product.getH5_link());
                    intent.putExtra("id",product.getProduct_id());
                    startActivity(intent);
                }else {
                    new BrowsingHistory().execute(product.getProduct_id(),Contacts.PRODUCT_TYPE);
                    Intent intent=new Intent(getActivity(), HtmlActivity.class);
                    intent.putExtra("title",product.getProduct_name());
                    intent.putExtra("link",product.getH5_link());
                    startActivity(intent);
                }
            }
        });


        flipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int displayedChild = flipper.getDisplayedChild();
                PromontionsList.ListBean listBean = list.get(displayedChild);
                new BrowsingHistory().execute(listBean.getProduct_id(),Contacts.PRODUCT_TYPE);
                Intent intent=new Intent(getActivity(), HtmlActivity.class);
                intent.putExtra("title",listBean.getProduct_name());
                intent.putExtra("link",listBean.getH5_link());
                startActivity(intent);
            }
        });


    }
    private void initView() {
        View view = getLayoutInflater().inflate(R.layout.foot_layout, null);
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
    private ViewFlipper  flipper;
    private View getHeader() {
        View view = getLayoutInflater().inflate(R.layout.home_header_layout, null);
        mHotRecyclerView=view.findViewById(R.id.hot_recycler);
        mLoan=view.findViewById(R.id.layout_loan);
        mRecommen=view.findViewById(R.id.layout_recommen);
        flipper=view.findViewById(R.id.ViewFlipper);

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

        final RequestOptions options = new RequestOptions()
                .centerCrop()
                .transform(new GlideRoundTransform(5))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        /**精品推荐**/



        ApiService.GET_Get(Api.Home.PROMOTIONS, null, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject json) {
                flipper.removeAllViews();
                try {
                    String data = json.getString("data");

                    Gson gson=new Gson();
                    PromontionsList promontionsList = gson.fromJson(data, PromontionsList.class);

                     list = promontionsList.getList();
                    for (int i = 0; i < list.size(); i++) {
                        View viewer = LayoutInflater.from(getActivity()).inflate(R.layout.layout_custom, null);
                        TextView  mName=viewer.findViewById(R.id.name);
                        TextView  mText= viewer.findViewById(R.id.text);
                        ImageView mImageView=viewer.findViewById(R.id.logo);
                        PromontionsList.ListBean listBean = list.get(i);
                        mName.setText(listBean.getProduct_name());
                        mText.setText(listBean.getText());
                        Glide.with(App.getApp()).load(listBean.getProduct_logo()).apply(options).into(mImageView);
                        flipper.addView(viewer);
                    }
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
