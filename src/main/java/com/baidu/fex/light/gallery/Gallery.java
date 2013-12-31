package com.baidu.fex.light.gallery;


import uk.co.senab.photoview.sample.HackyViewPager;

import com.baidu.fex.light.R;
import com.baidu.fex.light.gallery.Params.DataSource;
import android.content.Context;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;

public class Gallery extends FrameLayout implements OnClickListener,
		OnPageChangeListener {

	private HackyViewPager mViewPager;

	private Toolbar topbar, bottombar;

	private DataSource dataSource;

	private Params params;

	public Gallery(Context context, Params params) {
		super(context);
		this.params = params;
		init();
	}

	private boolean show = true;
	private Animation slideInTop = AnimationUtils.loadAnimation(getContext(),
			R.anim.slide_in_top);
	private Animation slideOutTop = AnimationUtils.loadAnimation(getContext(),
			R.anim.slide_out_top);
	private Animation slideInBottom = AnimationUtils.loadAnimation(
			getContext(), R.anim.slide_in_bottom);
	private Animation slideOutBottom = AnimationUtils.loadAnimation(
			getContext(), R.anim.slide_out_bottom);

	
	
	private class MyWebViewClient extends WebViewClient{
		
		private int totalWebpages = 0;
		
		private int loadedWebpages = 0;
		
		@Override
		public void onPageFinished(WebView view, String url) {
			loadedWebpages ++;
			if(loadedWebpages == totalWebpages){
				onload();
			}
		}
	}
	
	private MyWebViewClient mWebViewClient = new MyWebViewClient();
	
	private void initTopBar(){
		String url = params.getToolbar().getTopbar();
		if (url != null) {
			mWebViewClient.totalWebpages++;
			topbar = (Toolbar) findViewById(R.id.topbar);
			topbar.setVisibility(View.VISIBLE);
			topbar.loadUrl(url);
			topbar.setWebViewClient(mWebViewClient);
			
		}
	}
	
	private void initBottomBar(){
		String url = params.getToolbar().getBottombar();
		if (url != null) {
			mWebViewClient.totalWebpages++;
			bottombar = (Toolbar) findViewById(R.id.bottombar);
			bottombar.setVisibility(View.VISIBLE);
			bottombar.loadUrl(url);
			bottombar.setWebViewClient(mWebViewClient);
		}
	}
	
	private void onload(){
		onPageSelected(dataSource.getSelected());
		
	}
	
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.gallery, this);
		initTopBar();
		initBottomBar();
		slideInTop.setFillAfter(true);
		slideOutTop.setFillAfter(true);
		slideInBottom.setFillAfter(true);
		slideOutBottom.setFillAfter(true);
		slideInTop.setAnimationListener(new AnimationListener() {

			public void onAnimationStart(Animation animation) {

			}

			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			public void onAnimationEnd(Animation animation) {
				Log.d("gogo", "show = true");
				show = true;

			}
		});
		slideOutTop.setAnimationListener(new AnimationListener() {

			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			public void onAnimationEnd(Animation animation) {
				Log.d("gogo", "show = false");
				show = false;

			}
		});

		mViewPager = (HackyViewPager) findViewById(R.id.viewpager);
		mViewPager.setOnPageChangeListener(this);
		setData(params.getDataSource());
		// buildViewPager();
	}

	public void onPageSelected(int position) {
		if (topbar != null) {
			topbar.onSelected(position, params.getStr());
		}
		if (bottombar != null) {
			bottombar.onSelected(position, params.getStr());
		}
	}

	public void setData(DataSource datasource) {
		this.dataSource = datasource;
		mViewPager.setAdapter(new AlbumPagerAdapter(getContext(), dataSource
				.getImages()));
		int index = datasource.getSelected();
		mViewPager.setCurrentItem(index);
		
	}

	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		// TODO Auto-generated method stub

	}

	public void onPageScrollStateChanged(int state) {
		// TODO Auto-generated method stub

	}

	private GestureDetector gestureDetector = new GestureDetector(getContext(),
			new GestureDetector.SimpleOnGestureListener() {

				public boolean onSingleTapConfirmed(MotionEvent e) {

					if (show) {
						topbar.startAnimation(slideOutTop);
						bottombar.startAnimation(slideOutBottom);
					} else {
						topbar.startAnimation(slideInTop);
						bottombar.startAnimation(slideInBottom);
					}

					return false;
				};
			});

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return gestureDetector.onTouchEvent(ev);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
