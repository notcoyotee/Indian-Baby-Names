package com.RajeshRengasamy.IndianBabyNames;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class IndianBabyNamesActivity extends Activity {
	WebView mWebView;
    ProgressDialog mProgress;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mProgress = ProgressDialog.show(this, "Loading", "Please wait for a moment...");
        mWebView = (WebView) findViewById(R.id.webView1);
        mWebView.setWebViewClient(new BabyNames());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.loadUrl("file:///android_asset/www/index.html");
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();            
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    private class BabyNames extends WebViewClient {
    	
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
        	System.out.println("URL: " + url);
        	view.loadUrl("javascript:changeLocation('" + url + "')");
            return true;
        }
    	public void onPageFinished(WebView view, String url) {
    		if(mProgress.isShowing()) {
    			mProgress.dismiss();
    		}
    	}
    }
}