window.addEventListener("DOMContentLoaded", promotionFunc);

function promotionFunc(){
	
	function sendAjax(){}
	sendAjax.prototype = {
		sendAjaxForPromotions : function(){
			var oReq = new XMLHttpRequest();
		    
		    oReq.open("GET", promotionApiUri);
		    oReq.send();
		    
		    oReq.onreadystatechange = function onReadyStateChange() {
		    	if(oReq.readyState === XMLHttpRequest.DONE){
		    		if(oReq.status === 200){
		    			var promotions = JSON.parse(oReq.responseText);
		    			var promotionItems = promotions.items;
		    			
		    			containerVisualObj.displayPromotionImg(promotionItems);
		    		}
		    	}
		    }
		}
	   
	   ,sendAjaxForImageDownload : function(item){
		   var oReq = new XMLHttpRequest();
		    
		    oReq.open("GET", imageDownloadUri + "?productImageUrl=" + item.productImageUrl);
		    oReq.send();
		    
		    oReq.onreadystatechange = function onReadyStateChange() {
		    	if(oReq.readyState === XMLHttpRequest.DONE){
		    		if(oReq.status === 200){
		    			var blob = new Blob([oReq.response], {
		    												type : oReq.getResponseHeader("Content-Type")
		    			});
		    			console.log(blob);
		    			var imgUrl = window.URL.createObjectURL(blob);
		    			console.log(imgUrl);
		    			containerVisualObj.displayPromotionImg(promotionItems);
		    		}
		    	}
		    }
	   }
	}
	
	
	function containerVisual(){
		this.slideContainer = document.querySelector(".container_visual .visual_img");
		this.promotionTemplate = document.querySelector("#promotionItem").innerText;
		this.imgWidth = "450px";
	}
	containerVisual.prototype = {
		displayPromotionImg : function(promotionItems){
			bindPromotionTemplate = Handlebars.compile(this.promotionTemplate);
			
			promotionItems.forEach(function(item){
				this.slideContainer.innerHTML += bindPromotionTemplate(item);
			}.bind(this))
			
			var slideList = this.slideContainer.childNodes;
			for(var i=0; i<slideList.length; i++){              // remove Text node 
				if(slideList[i].nodeType != 1){
					this.slideContainer.removeChild(slideList[i]);
				}
			}
					
			setInterval(this.slidingImplFunc(), 1500);
		}
	
	   ,slidingImplFunc : function(){
			var slidingOutImg = this.slideContainer.firstChild;
			var slidingInImg = slidingOutImg.nextSibling;
			var transitionTime = 700;
			
			function slidingImpl(){
				slidingOutImg.style.transform = "translateX(-" + this.imgWidth +")";
				slidingInImg.style.transform = "translateX(0px)";
				
				setTimeout(function(){
					this.slideContainer.appendChild(slidingOutImg);
					slidingOutImg.style.transform = "translateX(" + this.imgWidth +")";
					
					slidingOutImg = slidingInImg;
					slidingInImg = slidingOutImg.nextSibling;
				}.bind(this), transitionTime);
			}
			
			return slidingImpl.bind(this);
		}
	}
	
	
	
	/**********  run method  **********/
	
	var ajaxObj = new sendAjax();
	var containerVisualObj = new containerVisual();
	
	ajaxObj.sendAjaxForPromotions();
	
}