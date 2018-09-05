package com.ant.cashant.ui.adpater;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.ant.cashant.model.ProductList;
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
 *
 * @author apple
 * @date 2018/8/14
 *
 */

public class LoanAdapter extends BaseQuickAdapter<ProductList.ListBean,BaseViewHolder> {

    public LoanAdapter( @Nullable List<ProductList.ListBean> data) {
        super(R.layout.loan_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductList.ListBean item) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(mContext).load(item.getLogo()).apply(options).into((ImageView)helper.getView(R.id.logo));
        helper.setText(R.id.name,item.getName())
                .setText(R.id.tv_desc,item.getIntroduct())
                .setText(R.id.apply,item.getPosition_sort()+"人已申请");
   /*     String interestAlgorithm = item.getInterest_algorithm();
        helper.setText(R.id.name,item.getP_name())
                .setText(R.id.desc,"额度"+item.getMinimum_amount()+"-"+item.getMaximum_amount()+"元"+
                        "，最快"+item.getFastest_time()+"放款");
        helper .setText(R.id.rate,
                "0".equals(interestAlgorithm)?"利率"+item.getMin_algorithm()+"/天"
                :"利率"+item.getMin_algorithm()+"/月")
                .setTextColor(R.id.rate,mContext.getResources().getColor(R.color.color_blue));
*/
    }
}
