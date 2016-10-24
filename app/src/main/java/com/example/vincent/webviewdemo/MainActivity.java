package com.example.vincent.webviewdemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

/**
 * @创建者 Vincent
 * @创时间 2016/10/24 14:15
 * @描述 $ 简单浏览器
 */
public class MainActivity extends Activity {

    private WebView mWebView;
    private ProgressBar mProgressBar;
    private EditText mEditText;
    private Button mSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initWebView();
        initListener();
    }

    private void initListener() {
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = mEditText.getText().toString().trim();
                mWebView.loadUrl("http://"+url);
            }
        });
    }

    private void init() {
        mWebView = (WebView) findViewById(R.id.webview);
        mProgressBar = (ProgressBar) findViewById(R.id.webview_pb);
        mEditText = (EditText)findViewById(R.id.search_et);
        mSearch = (Button) findViewById(R.id.search_btn);
    }

    private void initWebView() {
        mProgressBar.setVisibility(View.GONE);
        mWebView.loadUrl("https://www.baidu.com/");
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;// 自己处理html里面link
            }
        });
        //监听 webview 的生命周期
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });

        //监听webview 的更新状态
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mProgressBar.setProgress(newProgress);
            }
        });

    }

}
