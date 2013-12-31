package com.baidu.fex.light.gallery;

import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

public class Params {
	
	public static class Toolbar{
		private String topbar;
		
		private String bottombar;
		
		private String visible;
		
		private String effect;

		public String getTopbar() {
			return topbar;
		}

		public String getBottombar() {
			return bottombar;
		}

		public String getVisible() {
			return visible;
		}

		public String getEffect() {
			return effect;
		}
	}
	
	public static class Style{
		private String background;
		public String getBackground() {
			return background;
		}
	}
	
	public static class DataSource{
		
		private List<String> images;
		
		private int selected;
		
		private Object extra;

		public List<String> getImages() {
			return images;
		}

		public int getSelected() {
			return selected;
		}

		public Object getExtra() {
			return extra;
		}
		
	}
	
	private String str;
	
	private Toolbar toolbar;
	
	private Style style;
	
	private DataSource dataSource;
	
	public Params(String params) {
		str = params;
		Gson gson = new Gson();
		Params p = gson.fromJson(params, Params.class);
		toolbar = p.toolbar;
		style = p.style;
		dataSource = p.dataSource;
	}

	public Toolbar getToolbar() {
		return toolbar;
	}

	public Style getStyle() {
		return style;
	}

	public DataSource getDataSource() {
		return dataSource;
	}
	
	public String getStr() {
		return str;
	}
	
}
