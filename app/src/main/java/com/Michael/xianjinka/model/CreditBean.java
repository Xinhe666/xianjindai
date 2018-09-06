package com.Michael.xianjinka.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * - @Author:  闫世豪
 * - @Time:  2018/8/10 下午5:54
 * - @Email whynightcode@gmail.com
 */

public class CreditBean implements Parcelable{
    /**
     * logo : http://or2eh71ll.bkt.clouddn.com/152154156427944.png
     * name : 信用卡007
     * desc : 007
     * link : https://qing.u-zu.com/landing?channel=qingjie-hka1
     * lables : 好&很好&非常好
     * lables_color : #ff0000�ff40ὀff
     * tip1 : 好
     * tip2 : 很好
     * tip3 : 非常好
     * font1 : #ff0000
     * font2 : #00ff40
     * font3 : #8000ff
     */

    private String logo;
    private String name;
    private String desc;
    private String link;
    private String lables;
    private String lables_color;
    private String tip1;
    private String tip2;
    private String tip3;
    private String font1;
    private String font2;
    private String font3;

    protected CreditBean(Parcel in) {
        logo = in.readString();
        name = in.readString();
        desc = in.readString();
        link = in.readString();
        lables = in.readString();
        lables_color = in.readString();
        tip1 = in.readString();
        tip2 = in.readString();
        tip3 = in.readString();
        font1 = in.readString();
        font2 = in.readString();
        font3 = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(logo);
        dest.writeString(name);
        dest.writeString(desc);
        dest.writeString(link);
        dest.writeString(lables);
        dest.writeString(lables_color);
        dest.writeString(tip1);
        dest.writeString(tip2);
        dest.writeString(tip3);
        dest.writeString(font1);
        dest.writeString(font2);
        dest.writeString(font3);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CreditBean> CREATOR = new Creator<CreditBean>() {
        @Override
        public CreditBean createFromParcel(Parcel in) {
            return new CreditBean(in);
        }

        @Override
        public CreditBean[] newArray(int size) {
            return new CreditBean[size];
        }
    };

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLables() {
        return lables;
    }

    public void setLables(String lables) {
        this.lables = lables;
    }

    public String getLables_color() {
        return lables_color;
    }

    public void setLables_color(String lables_color) {
        this.lables_color = lables_color;
    }

    public String getTip1() {
        return tip1;
    }

    public void setTip1(String tip1) {
        this.tip1 = tip1;
    }

    public String getTip2() {
        return tip2;
    }

    public void setTip2(String tip2) {
        this.tip2 = tip2;
    }

    public String getTip3() {
        return tip3;
    }

    public void setTip3(String tip3) {
        this.tip3 = tip3;
    }

    public String getFont1() {
        return font1;
    }

    public void setFont1(String font1) {
        this.font1 = font1;
    }

    public String getFont2() {
        return font2;
    }

    public void setFont2(String font2) {
        this.font2 = font2;
    }

    public String getFont3() {
        return font3;
    }

    public void setFont3(String font3) {
        this.font3 = font3;
    }
}
