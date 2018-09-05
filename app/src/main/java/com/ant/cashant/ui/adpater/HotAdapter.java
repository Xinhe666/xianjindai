package com.ant.cashant.ui.adpater;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ant.cashant.R;
import com.ant.cashant.glide.GlideCircleTransform;
import com.ant.cashant.glide.GlideRoundTransform;
import com.ant.cashant.model.Product;

import java.util.List;

/**
 *
 * @author apple
 * @date 2018/8/9
 *
 */

public class HotAdapter extends BaseQuickAdapter<Product,BaseViewHolder> {

    public HotAdapter(@Nullable List<Product> data) {
        super(R.layout.hot_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Product item) {

        helper.setText(R.id.name,item.getProduct_name());
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(mContext).load(item.getProduct_logo()).apply(options).into((ImageView)helper.getView(R.id.logo));

    }
}
