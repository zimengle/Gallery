package com.baidu.fex.light.gallery;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class Toolbar extends WebView{

	public Toolbar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public Toolbar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public Toolbar(Context context) {
		super(context);
		init();
	}
	
	private void init() {
		clearCache(true);
		getSettings().setJavaScriptEnabled(true);
		getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		setWebChromeClient(new WebChromeClient() {
	            @Override
	            public boolean onConsoleMessage(ConsoleMessage cm) {
	                    Log.d("MyApplication",
	                                    cm.message() + " -- From line " + cm.lineNumber()
	                                                    + " of " + cm.sourceId());
	                    return true;
	            }
	    });
		setBackgroundColor(0);
	}
	
	public void onSelected(int pos,String params){
		loadUrl("javascript:X.light.gallery_callback.onSelected("+pos+",new X.light.gallery("+params+"))");
	}
	
}
