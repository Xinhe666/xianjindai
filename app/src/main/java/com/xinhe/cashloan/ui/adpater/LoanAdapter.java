package com.xinhe.cashloan.ui.adpater;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinhe.cashloan.R;
import com.xinhe.cashloan.glide.GlideCircleTransform;
import com.xinhe.cashloan.model.Product;

import java.util.List;

/**
 * Created by apple on 2018/8/14.
 */

public class LoanAdapter extends BaseQuickAdapter<Product,BaseViewHolder> {

    public LoanAdapter( @Nullable List<Product> data) {
        super(R.layout.loan_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Product item) {
        RequestOptions options = new RequestOptions()
                .fitCenter()
                .transform(new GlideCircleTransform())
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(mContext).load(item.getP_logo()).apply(options).into((ImageView)helper.getView(R.id.logo));

        String interestAlgorithm = item.getInterest_algorithm();

        helper.setText(R.id.name,item.getP_name())
                .setText(R.id.desc,"额度"+item.getMinimum_amount()+"-"+item.getMaximum_amount()+"元"+
                        "，最快"+item.getFastest_time()+"放款");
        helper .setText(R.id.rate,
                "0".equals(interestAlgorithm)?"利率"+item.getMin_algorithm()+"/天"
                :"利率"+item.getMin_algorithm()+"/月")
                .setTextColor(R.id.rate,mContext.getResources().getColor(R.color.color_yellow));

    }
}
