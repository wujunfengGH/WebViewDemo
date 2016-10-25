package com.example.vincent.webviewdemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import static com.example.vincent.webviewdemo.R.id.webview;

/**
 * @创建者 Vincent
 * @创时间 2016/10/25 11:24
 * @描述 $ TODO
 */
public class WebViewActivity extends Activity {
    private WebView mWebView;
    private ProgressBar mProgressBar;
    private EditText mEditText;
    private Button mSearch;
    private String mUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        mUrl = getIntent().getStringExtra("url");
        init();
        initWebView();
        initListener();
    }

    private void init() {
        mWebView = (WebView) findViewById(webview);
        mProgressBar = (ProgressBar) findViewById(R.id.webview_pb);
        mEditText = (EditText)findViewById(R.id.search_et);
        mSearch = (Button) findViewById(R.id.search_btn);

    }

    private void initWebView() {
        mProgressBar.setVisibility(View.GONE);
        mWebView.loadUrl("https://"+mUrl);
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

    private void initListener() {
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = mEditText.getText().toString().trim();
                mWebView.loadUrl("http://"+url);
            }
        });
    }

    /**
     *  WebView按返回键返回上个页面
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack(); // goBack()表示返回WebView的上一页面
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }
}
