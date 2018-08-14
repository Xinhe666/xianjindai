package com.xinhe.cashloan.ui.adpater;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinhe.cashloan.R;
import com.xinhe.cashloan.glide.GlideCircleTransform;
import com.xinhe.cashloan.model.CreditBean;

import java.util.List;

/**
 * - @Author:  闫世豪
 * - @Time:  2018/8/10 下午5:53
 * - @Email whynightcode@gmail.com
 */
public class CreditAdapter extends BaseQuickAdapter<CreditBean, BaseViewHolder> {

    private final RequestOptions mRequestOptions =
            new RequestOptions()
                    .centerCrop()
                    .transform(new GlideCircleTransform())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate();

    public CreditAdapter(@Nullable List<CreditBean> data) {
        super(R.layout.item_credit_layout, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, CreditBean item) {
        helper.setText(R.id.credit_title, item.getName())
                .setText(R.id.credit_text, item.getDesc());
        Glide.with(mContext).load(item.getLogo())
                .apply(mRequestOptions)
                .into((ImageView) helper.getView(R.id.credit_logo));
        LinearLayout linearLayout = helper.getView(R.id.credit_tag);

        linearLayout.removeAllViews();

        addView(linearLayout, item.getTip1(), item.getFont1());
        addView(linearLayout, item.getTip2(), item.getFont2());
        addView(linearLayout, item.getTip3(), item.getFont3());


    }

    private void addView(LinearLayout linearLayout, String name, String color) {
        if (TextUtils.isEmpty(color)) {

        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView textView = new TextView(mContext);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(5);
        drawable.setStroke(1, Color.parseColor(color));
        textView.setBackground(drawable);
        textView.setTextColor(Color.parseColor(color));
        textView.setTextSize(11);
        textView.setText(name);
        textView.setPadding(10,0,10,0);
        layoutParams.leftMargin = 5;
        layoutParams.rightMargin = 5;
        textView.setLayoutParams(layoutParams);
        linearLayout.addView(textView);
    }
}
