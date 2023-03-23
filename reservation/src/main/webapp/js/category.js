window.addEventListener("DOMContentLoaded", categoryFunc);


function categoryFunc(){
	var categoryId;
	var totalCount=0;
	var currentCount=0;
	var currentTab;
	
	
	function sectionEventTab(){
		this.eventTabList = document.querySelector(".event .section_event_tab .event_tab_lst");
		this.categoryTabTemplate = document.querySelector("#categoryTab").innerText;
	}
	sectionEventTab.prototype = {
		addTabMenuEvent : function(){
			this.eventTabList.addEventListener("click", function(evt){
				var tagName = evt.target.tagName;
					
				if(tagName === "LI"){        ajaxObj.sendAjaxToInit(evt.target);  }
				else if(tagName === "A"){    ajaxObj.sendAjaxToInit(evt.target.parentNode);  }
				else if(tagName === "SPAN"){ ajaxObj.sendAjaxToInit(evt.target.parentNode.parentNode);  }
			});
		}
	
	   ,initCategoryTab : function(categories){
			var bindCategoryTabTemplate = Handlebars.compile(this.categoryTabTemplate);
			var items = categories.items;
			
			var totalList = {id:"0", name: "전체리스트", count: categories.totalCount}
			var totalListHTML = bindCategoryTabTemplate(totalList);
			this.eventTabList.innerHTML = totalListHTML;
						
			items.forEach(function(item){
				this.eventTabList.innerHTML += bindCategoryTabTemplate(item);
			}.bind(this))
			
			currentTab = this.eventTabList.firstElementChild;
			ajaxObj.sendAjaxToInit(currentTab);
		}
	}
	
	
	function sectionEventList(){
		this.leftEventBox = document.querySelector(".section_event_lst .wrap_event_box ul:nth-child(1)");
		this.rightEventBox = document.querySelector(".section_event_lst .wrap_event_box ul:nth-child(2)");
			
		this.categoryNumElement = document.querySelector(".pink");
			
		this.moreBtn = document.querySelector(".section_event_lst .more .btn");
		this.more = document.querySelector(".section_event_lst .more");
		this.moreParent = document.querySelector(".section_event_lst .wrap_event_box");
			
		this.categoryTemplate = document.querySelector("#itemList").innerText;
	}
	
	sectionEventList.prototype = {
		addMoreBtnEvent : function(){
			this.moreBtn.addEventListener("click", function(){
				ajaxObj.sendAjaxToMore(currentCount);
			});
		}
	
	   ,initCategoryView : function(products, selectedTab){
			var bindCategoryTemplate = Handlebars.compile(this.categoryTemplate);
			
			var items = products.items;
			var itemsLen = items.length;
			var leftInnerHTML = "";
			var rightInnerHTML = "";
					
			totalCount = products.totalCount;
			currentCount = itemsLen;
			
			
			for(var i=0; i<itemsLen; i++){
				if(i%2==0){       leftInnerHTML += bindCategoryTemplate(items[i]);   }
		        else {  		  rightInnerHTML += bindCategoryTemplate(items[i]);  }
		    }
			
			this.leftEventBox.innerHTML = leftInnerHTML;
			this.rightEventBox.innerHTML = rightInnerHTML;
			
			
			// display total count
			this.categoryNumElement.innerText = totalCount + "개";
			
			// tab green display
			currentTab.firstElementChild.style.color = "#000";
			currentTab.firstElementChild.style.borderBottom = "0px";
			
			currentTab = selectedTab;
			currentTab.firstElementChild.style.color = "#0aba16";
			currentTab.firstElementChild.style.borderBottom = "2px solid #0aba16";
			
			// add more button
			this.moreParent.appendChild(this.more);
		}
	   
	   ,displayMoreView : function(products){
			var bindCategoryTemplate = Handlebars.compile(this.categoryTemplate);
			
			var items = products.items;
			var itemsLen = items.length;
			var parser = new DOMParser();
			
			currentCount += itemsLen;
					
			
		    for(var i=0; i<itemsLen; i++){
		    	var itemHtmlString = bindCategoryTemplate(items[i]);
		    	var item = parser.parseFromString(itemHtmlString, "text/html").body.firstChild;
		    	
		    	if(i%2==0){  		this.leftEventBox.appendChild(item);   }
		        else {  			this.rightEventBox.appendChild(item);  }
		    }
		    	    
		    
		    if(currentCount >= totalCount){
		    	this.moreParent.removeChild(this.more);
		    }
		}
	}

	
	function sendAjax(){
		
	}
	sendAjax.prototype = {
		sendAjaxForCategories : function(){
			var oReq = new XMLHttpRequest();
		    
		    oReq.open("GET", categoryApiUri);
		    oReq.send();
		    
		    oReq.onreadystatechange = function onReadyStateChange() {
		    	if(oReq.readyState === XMLHttpRequest.DONE){
		    		if(oReq.status === 200){
		    			var categories = JSON.parse(oReq.responseText);
		    			
		    			sectionEventTabObj.initCategoryTab(categories);
		    		}
		    	}
		    }
		}
		
	   ,sendAjaxToInit : function(selectedTab){
			categoryId = selectedTab.dataset.category;
			
			var oReq = new XMLHttpRequest();
		    
		    oReq.open("GET", productApiUri + "?categoryId=" + categoryId + "&start=" + 0);
		    oReq.send();
		    
		    oReq.onreadystatechange = function onReadyStateChange() {
		    	if(oReq.readyState === XMLHttpRequest.DONE){
		    		if(oReq.status === 200){
		    			var products = JSON.parse(oReq.responseText);
		    			
		    			sectionEventListObj.initCategoryView(products, selectedTab);
		    		}
		    	}
		    }
		}
	   
	   ,sendAjaxToMore : function(start){
		    var oReq = new XMLHttpRequest();
		    	
		    oReq.open("GET", productApiUri + "?categoryId=" + categoryId + "&start=" + start);
		    oReq.send();
		    
		    oReq.onreadystatechange = function onReadyStateChange() {
		    	if(oReq.readyState === XMLHttpRequest.DONE){
		    		if(oReq.status === 200){
		    			var products = JSON.parse(oReq.responseText);
		    			
		    			sectionEventListObj.displayMoreView(products);
		    		}
		    	}
		    }
		}
	}
	
	
	/**********  run method  **********/
	var sectionEventTabObj = new sectionEventTab();
	var sectionEventListObj = new sectionEventList();
	var ajaxObj = new sendAjax();
	
	sectionEventTabObj.addTabMenuEvent();
	sectionEventListObj.addMoreBtnEvent();
	ajaxObj.sendAjaxForCategories();
}
