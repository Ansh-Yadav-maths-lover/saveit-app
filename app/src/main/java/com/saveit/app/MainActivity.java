package com.saveit.app;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.webkit.CookieManager;
import android.view.Window;
import android.graphics.Color;

public class MainActivity extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Full-screen, no title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        webView = new WebView(this);
        webView.setBackgroundColor(Color.parseColor("#0d0f14"));
        setContentView(webView);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setMediaPlaybackRequiresUserGesture(false);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);

        // Allow downloads to open externally
        webView.setDownloadListener((url, userAgent, contentDisposition, mimeType, contentLength) -> {
            android.content.Intent intent = new android.content.Intent(android.content.Intent.ACTION_VIEW,
                    android.net.Uri.parse(url));
            startActivity(intent);
        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // Open external links in browser
                if (!url.startsWith("file://")) {
                    android.content.Intent intent = new android.content.Intent(
                            android.content.Intent.ACTION_VIEW, android.net.Uri.parse(url));
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        webView.setWebChromeClient(new WebChromeClient());

        CookieManager.getInstance().setAcceptCookie(true);
        CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);

        webView.loadUrl("file:///android_asset/index.html");
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
