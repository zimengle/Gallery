(function(win,doc){
	
	var Gallery = function(params){
		
		
		this.destory = function(){
			
		}
		
		this.notify = function(){
			
		}
		
		this.init = function(){
			native_light_gallery.init(JSON.stringify(params));	
		}
		
		this.getParams = function(){
			return params;
		}
		
		
	}
	
	
	win.X = {};
	
	X.light = {};
	
	X.light.gallery_callback = {
		onSelected:function(pos,gellery){}
	}
	
	X.light.gallery = Gallery;
	
	
	
})(window,document);
