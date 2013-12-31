package com.baidu.fex.light.gallery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class GalleryActivity extends Activity {

	private Gallery gallery;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Params params = new Params(getIntent().getStringExtra("params"));
        
        gallery = new Gallery(this, params);
        
        setContentView(gallery);
    }
    
    @Override
    protected void onNewIntent(Intent intent) {
    	super.onNewIntent(intent);
    	
    	
    }

    

}

