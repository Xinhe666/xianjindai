package com.ant.cashant.ui.adpater;

import android.widget.ImageView;

import com.ant.cashant.model.RecommProduct;
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
 * @date 2017/5/22
 */

public class ListProductAdapter extends BaseQuickAdapter<RecommProduct,BaseViewHolder> {

    public ListProductAdapter(List<RecommProduct> data) {
        super(R.layout.news_item, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, RecommProduct item) {
        helper.setText(R.id.name,item.getProduct_name())
                .setText(R.id.desc,item.getProduct_desc());
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .transform(new GlideRoundTransform(5))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(mContext).load(item.getProduct_logo()).apply(options).into((ImageView)helper.getView(R.id.logo));



    }

}
