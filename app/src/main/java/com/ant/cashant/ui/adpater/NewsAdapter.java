package com.ant.cashant.ui.adpater;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.ant.cashant.model.RecommProduct;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ant.cashant.R;
import com.ant.cashant.glide.GlideCircleTransform;
import com.ant.cashant.model.Product;

import java.util.List;

/**
 * Created by apple on 2018/8/9.
 */

public class NewsAdapter extends BaseQuickAdapter<RecommProduct,BaseViewHolder> {

    public NewsAdapter( @Nullable List<RecommProduct> data) {
        super(R.layout.news_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecommProduct item) {

        helper.setText(R.id.name,item.getProduct_name())
                .setText(R.id.desc,item.getProduct_desc());
        RequestOptions options = new RequestOptions()
                .fitCenter()
                .transform(new GlideCircleTransform())
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(mContext).load(item.getProduct_logo()).apply(options).into((ImageView)helper.getView(R.id.logo));

    }
}
