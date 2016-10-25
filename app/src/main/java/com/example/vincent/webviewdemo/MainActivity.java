package com.example.vincent.webviewdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @创建者 Vincent
 * @创时间 2016/10/24 14:15
 * @描述 $ 简单浏览器
 */
public class MainActivity extends Activity implements View.OnClickListener{

    private long mExitTime;
    private Button mSearch;
    private EditText mEditText;
    private Button mBaidu;
    private Button mTaoBao;
    private Button mJingDong;
    private Button mSina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
    }

    private void initView() {
        mEditText = (EditText) findViewById(R.id.search_et);
        mSearch = (Button) findViewById(R.id.search_btn);
        mBaidu = (Button) findViewById(R.id.baidu);
        mTaoBao = (Button) findViewById(R.id.taobao);
        mSina = (Button) findViewById(R.id.sina);
        mJingDong = (Button) findViewById(R.id.jingdong);
    }

    private void initListener() {
        mSearch.setOnClickListener(this);
        mBaidu.setOnClickListener(this);
        mTaoBao.setOnClickListener(this);
        mSina.setOnClickListener(this);
        mJingDong.setOnClickListener(this);
    }


    /**
     * 按两次返回键退出应用
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) { //System.currentTimeMillis()无论何时调用，肯定大于2000
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_btn:
                String url = mEditText.getText().toString().trim();
                jump2WebView(url);
                break;
            case R.id.baidu:
                jump2WebView("www.baidu.com");
                break;
            case R.id.taobao:
                jump2WebView("www.taobao.com");
                break;
            case R.id.sina:
                jump2WebView("www.sina.cn");
                break;
            case R.id.jingdong:
                jump2WebView("www.jd.com");
                break;
            default:
                break;
        }
    }
    private void jump2WebView(String url){
        Intent intent = new Intent(this,WebViewActivity.class);
        intent.putExtra("url",url);
        startActivity(intent);
    }
}
