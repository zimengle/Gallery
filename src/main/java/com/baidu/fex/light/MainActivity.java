package com.baidu.fex.light;

import com.baidu.fex.light.gallery.GalleryActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class MainActivity extends Activity{
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		WebView webView = new WebView(this);
		webView.clearCache(true);
		webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.addJavascriptInterface(new Object(){
			
			public boolean componentCheck(String component){
				return true;
			}
			
			public void init(String params){
				Intent intent = new Intent(MainActivity.this,GalleryActivity.class);
				intent.putExtra("params", params);
				MainActivity.this.startActivity(intent);
			}
			
		}, "native_light_gallery");
		
		webView.setWebChromeClient(new WebChromeClient() {
	            @Override
	            public boolean onConsoleMessage(ConsoleMessage cm) {
	                    Log.d("MyApplication",
	                                    cm.message() + " -- From line " + cm.lineNumber()
	                                                    + " of " + cm.sourceId());
	                    return true;
	            }
	    });
		webView.loadUrl("file:///android_asset/index.html");
		
	}
	
}
