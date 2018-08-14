package com.xinhe.cashloan.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.xinhe.cashloan.R;
import com.xinhe.cashloan.base.BaseActivity;
import com.xinhe.cashloan.utils.DownAPKService;
import com.xinhe.cashloan.utils.LogUtils;
import com.xinhe.cashloan.utils.NetworkUtils;
import com.xinhe.cashloan.utils.ToastUtils;


import butterknife.BindView;

/**
 * @author apple
 *
 *
 */
public class HtmlActivity extends BaseActivity {

    @BindView(R.id.toolbar_back)
    ImageView toolbarBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.web_progress_bar)
    ProgressBar webProgressBar;
    @BindView(R.id.web_container)
    FrameLayout webContainer;
    private WebView mWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWebView = new WebView(this);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        mWebView.setLayoutParams(lp);
        webContainer.addView(mWebView, 0);
        checkinternet();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_html;
    }

    private void checkinternet() {
        toolbarBack.setVisibility(View.VISIBLE);
        boolean available = NetworkUtils.isAvailable(this);
        if (available) {
            String title = getIntent().getStringExtra("title");
            toolbarTitle.setText(title);
            String link = getIntent().getStringExtra("link");

            getDate(link);

        }
        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    private void getDate(String url) {
        if (url != null) {
            WebSettings webSettings = mWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
            webSettings.setUseWideViewPort(true);
            webSettings.setLoadWithOverviewMode(true);
            webSettings.setBlockNetworkImage(false);
            webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
            webSettings.setGeolocationEnabled(true);
            webSettings.setDomStorageEnabled(true);
            webSettings.setDatabaseEnabled(true);
            webSettings.setAppCacheEnabled(true);
            webSettings.setSupportZoom(false);
            webSettings.setNeedInitialFocus(false);
            webSettings.setLoadsImagesAutomatically(true);
            webSettings.setBuiltInZoomControls(false);


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mWebView.getSettings().setMixedContentMode(
                        WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
            }
            if (Build.VERSION.SDK_INT >= 21) {
                webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            }
            mWebView.loadUrl(url);
            mWebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {

                    if (parseScheme(url)) {

                    } else {

                        WebView.HitTestResult hitTestResult = view.getHitTestResult();
                        if (!TextUtils.isEmpty(url) && hitTestResult == null) {
                            view.loadUrl(url);
                            return true;
                        }
                    }
                    return false;
                }

                @Override
                public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                    handler.proceed();

                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    String insurance = getIntent().getStringExtra("Insurance");
                    if (insurance != null) {
                        toolbarTitle.setText(view.getTitle());
                    }

                }
            });

            mWebView.setWebChromeClient(new MyWebChromeClient());

        }
    }


    public boolean parseScheme(String url) {
        //支付宝支付
        if (url.startsWith("alipays:") || url.startsWith("alipay")) {
            try {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
            } catch (Exception e) {
                e.printStackTrace();


            }
            return true;
        } else if (url.contains("qqapi")) {
            try {
                Uri uri = Uri.parse(url);
                Intent intent;
                intent = Intent.parseUri(url,
                        Intent.URI_INTENT_SCHEME);
                intent.addCategory("android.intent.category.BROWSABLE");
                intent.setComponent(null);
                startActivity(intent);
            } catch (Exception e) {
                ToastUtils.showToast( "请安装最新版腾讯QQ");
            }
            return true;
        } else if (url.contains("tmast://appdetails?")) {
            try {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(HtmlActivity.this, "请安装最新版应用宝", Toast.LENGTH_SHORT).show();
            }
            return true;
        } else if (url.endsWith(".apk")) {
            downloadApk(url);
            return true;

        } else if (url.startsWith("weixin://wap/pay?") || url.startsWith("weixin") || url.startsWith("wechat")) {
            try {

                startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));

            } catch (Exception e) {

                e.printStackTrace();
                ToastUtils.showToast(e.getMessage());
            }
            return true;
        } else {
            return false;
        }

    }

    /**
     * 应用内拦截下载
     */
    private void downloadApk(String url) {

        Intent intent = new Intent(this, DownAPKService.class);
        intent.putExtra("apk_url", url);
        startService(intent);

    }



    private class MyWebChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (newProgress == 100) {
                webProgressBar.setVisibility(View.GONE);
            } else {
                if (webProgressBar.getVisibility() != View.VISIBLE) {
                    webProgressBar.setVisibility(View.VISIBLE);
                }
                webProgressBar.setProgress(newProgress);
            }
        }
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        webContainer.removeAllViews();
        mWebView.removeAllViews();
        mWebView.destroy();
        mWebView = null;
    }
}
