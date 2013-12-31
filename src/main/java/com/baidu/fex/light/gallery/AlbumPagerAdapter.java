package com.baidu.fex.light.gallery;

import java.util.List;

import uk.co.senab.photoview.PhotoView;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.FrameLayout.LayoutParams;

import com.baidu.fex.light.R;
import com.baidu.fex.light.Utils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

public class AlbumPagerAdapter extends PagerAdapter{
	private List<String> urls;
	
	private ImageLoader imageLoader;
	
	private Context mContext;
	
	public AlbumPagerAdapter(Context context,List<String> urls) {
		this.mContext = context;
		this.urls = urls;
		imageLoader = Utils.getImageLoader(context);
	}

	@Override
	public int getCount() {
		return urls.size();
	}

	@Override
	public View instantiateItem(ViewGroup container, int position) {
		View item = LayoutInflater.from(mContext).inflate(R.layout.gallery_item, null);
		container.addView(item, new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		PhotoView photoView = (PhotoView) item.findViewById(R.id.photoView);
		final ProgressBar progressBar = (ProgressBar) item.findViewById(R.id.loading);
		imageLoader.displayImage(urls.get(position), photoView,new SimpleImageLoadingListener(){
			@Override
			public void onLoadingStarted(String imageUri, View view) {
				progressBar.setVisibility(View.VISIBLE);
			}
			@Override
			public void onLoadingComplete(String imageUri, View view,
					Bitmap loadedImage) {
				progressBar.setVisibility(View.GONE);
			}
			@Override
			public void onLoadingCancelled(String imageUri, View view) {
				progressBar.setVisibility(View.GONE);
			}
			@Override
			public void onLoadingFailed(String imageUri, View view,
					FailReason failReason) {
				progressBar.setVisibility(View.GONE);
			}
		});
		//牛逼的预加载
		if(position+1 < urls.size()){
			imageLoader.loadImage(urls.get(position+1), null);
		}
		if(position > 0 && position-1 < urls.size()){
			imageLoader.loadImage(urls.get(position-1), null);
		}
		
		
		return item;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}
}
