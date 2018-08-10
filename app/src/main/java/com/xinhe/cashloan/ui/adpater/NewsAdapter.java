package com.xinhe.cashloan.ui.adpater;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinhe.cashloan.R;
import com.xinhe.cashloan.model.Product;
import com.xinhe.cashloan.utils.GlideCircleTransform;

import java.util.List;

/**
 * Created by apple on 2018/8/9.
 */

public class NewsAdapter extends BaseQuickAdapter<Product,BaseViewHolder> {

    public NewsAdapter( @Nullable List<Product> data) {
        super(R.layout.news_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Product item) {

        helper.setText(R.id.name,item.getP_name())
                .setText(R.id.desc,item.getP_desc())
                .setText(R.id.apply_number,item.getApply());
        RequestOptions options = new RequestOptions()
                .fitCenter()
                .transform(new GlideCircleTransform())
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(mContext).load(item.getP_logo()).apply(options).into((ImageView)helper.getView(R.id.logo));

    }
}
