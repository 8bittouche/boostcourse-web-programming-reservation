window.addEventListener("DOMContentLoaded", detailFunc);

function detailFunc(){
	var parser = new DOMParser();
	
	
	function sectionDetails(){
		this.storeDetails = document.querySelector(".section_store_details .store_details");
		this.dsc = document.querySelector(".section_store_details .store_details .dsc");
		this.bkMoreOpen = document.querySelector(".section_store_details ._open");
		this.bkMoreClose = document.querySelector(".section_store_details ._close");
	}
	sectionDetails.prototype = {
		writeDsc : function(productContent){
			this.dsc.innerText = productContent;
		}
		
	   ,addDscOpenCloseEvent : function(){
			this.bkMoreOpen.addEventListener("click", function(){
				removeClass(this.storeDetails, "close3");
				this.bkMoreOpen.style.display = "none";
				this.bkMoreClose.style.display = "";
			}.bind(this));
			
			this.bkMoreClose.addEventListener("click", function(){
				addClass(this.storeDetails, "close3");
				this.bkMoreOpen.style.display = "";
				this.bkMoreClose.style.display = "none";
			}.bind(this));
		}
	}
	
		
	function sectionVisual(){
		this.detailSwipe = document.querySelector(".section_visual .detail_swipe");
		this.pageNum = document.querySelector(".figure_pagination .num");
		this.pageNumOff = document.querySelector(".figure_pagination .num.off > span");
		this.prevBtn = document.querySelector(".section_visual .group_visual .prev");
		this.nxtBtn = document.querySelector(".section_visual .group_visual .nxt");
		this.nxtBtnIcon = document.querySelector(".btn_nxt .ico_arr6_rt");
		this.prevBtnIcon = document.querySelector(".btn_prev .ico_arr6_lt");
	}
	sectionVisual.prototype = {
		displayBackgroundImage : function(productImages, displayInfo){
			var detailBackgroundImageTemplate = document.querySelector("#detail_background_img").innerText;
			var bindDetailBackgroundImageTemplate = Handlebars.compile(detailBackgroundImageTemplate);
			var mainImg;
			var etcImg;
			var et = false;
			
			productImages.forEach(function(img){
				var obj = {
					saveFileName : img.saveFileName,
					productDescription : displayInfo.productDescription
				}
				
				var ImgHtml = bindDetailBackgroundImageTemplate(obj);
				
				if(img.type==="et" && !et){
					et = true;
					etcImg = parser.parseFromString(ImgHtml, "text/html").body.firstChild;
				}
				else if(img.type==="ma"){
					mainImg = parser.parseFromString(ImgHtml, "text/html").body.firstChild;
				}
			});
			
			if(productImages.length === 1){
				this.detailSwipe.appendChild(mainImg);
				mainImg.style.transform = "translateX(0px)";
			}
			else {
				this.detailSwipe.appendChild(etcImg);
				this.detailSwipe.appendChild(mainImg);
				this.detailSwipe.appendChild(etcImg.cloneNode(true));
				this.detailSwipe.appendChild(mainImg.cloneNode(true));
			}
		}
	
	   ,displayPageNum : function(productImages){
			this.pageNum.innerText = 1;
			this.pageNumOff.innerText = 1;
			
			if(productImages.length > 1){
				this.pageNumOff.innerText=2;
				
				this.prevBtn.style.display = "block";
				this.nxtBtn.style.display = "block";
				
				this.addSlidingEvent();
			}
		}
		
	   ,addSlidingEvent : function(){
			var transitionTime = 100;
			var imgWidth = 450;
			var backgroundImgList = this.detailSwipe.childNodes;
			
			for(var i=0; i<backgroundImgList.length; i++){              // remove Text node 
				if(backgroundImgList[i].nodeType != 1){
					this.detailSwipe.removeChild(backgroundImgList[i]);
				}
			}
			
			this.nxtBtnIcon.addEventListener("click", function(){
				var addedImg = backgroundImgList[0];
				var movingImg = backgroundImgList[3];
				var outImg = backgroundImgList[1];
				var inImg = backgroundImgList[2];
				
				movingImg.style.transform = "translateX(" + imgWidth + "px)";
				inImg.style.transform = "translateX(0px)";
				outImg.style.transform = "translateX(" + -imgWidth + "px)";
				setTimeout(()=>{
					this.detailSwipe.appendChild(addedImg);
					addedImg.style.transform = "translateX(" + 2*imgWidth + "px)";
				}, transitionTime);
				
				if(this.pageNum.innerText === "1")
					this.pageNum.innerText=2; 
				else  								  
					this.pageNum.innerText=1; 
			
			}.bind(this));
			
			this.prevBtnIcon.addEventListener("click", function(){
				var addedImg = backgroundImgList[3];
				var movingImg = backgroundImgList[2];
				var outImg = backgroundImgList[1];
				var inImg = backgroundImgList[0];
				
				movingImg.style.transform = "translateX(" + 2*imgWidth + "px)";
				outImg.style.transform = "translateX(" + imgWidth + "px)";
				inImg.style.transform = "translateX(0px)";
				setTimeout(()=>{
					var insertedNode = this.detailSwipe.insertBefore(addedImg, inImg);
					insertedNode.style.transform = "translateX(" + -imgWidth + "px)";
				}, transitionTime);
				
				if(this.pageNum.innerText === "1") 
					this.pageNum.innerText=2; 
				else 							  
					this.pageNum.innerText=1; 
			
			}.bind(this));
		}
	}
	
	function sectionReviewList(){
		this.graphValue = document.querySelector(".section_review_list .grade_area .graph_value");
		this.textValue = document.querySelector(".section_review_list .grade_area .text_value > span");
		this.joinCount = document.querySelector(".section_review_list .grade_area .join_count .green");
		this.listShortReview = document.querySelector(".section_review_list .list_short_review");
	}
	sectionReviewList.prototype = {
		gradeCommentScore : function(comments, averageScore){
			var commentsNum = comments.length;
			var AverageScorePercent = (averageScore / 5.0) * 100;
			
			this.graphValue.style.width = AverageScorePercent + "%";
			this.textValue.innerText = averageScore;
			this.joinCount.innerText = commentsNum + "건";
		}
		
	   ,displayReviewList : function(comments){
			var reviewListItemTemplate = document.querySelector("#review_list_item").innerText;
			var reviewListItemNoImgTemplate = document.querySelector("#review_list_item_no_img").innerText;
			var bindreviewListItemTemplate = Handlebars.compile(reviewListItemTemplate);
			var bindreviewListItemNoImgTemplate = Handlebars.compile(reviewListItemNoImgTemplate); 
			var reviewItem;
			
			for(var i=0; i<comments.length; i++){
				if(i===3) break;
				
				var obj = {}
					
				obj.comment = comments[i].comment;
				obj.score = comments[i].score.toFixed(1);
				//obj.reservationEmail = comments[i].reservationEmail.substring(0,4) + "****";
				eval(function(p,a,c,k,e,r){e=String;if(!''.replace(/^/,String)){while(c--)r[c]=k[c]||c;k=[function(e){return r[e]}];e=function(){return'\\w+'};c=1};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p}('2.1=3[5].1.6(0,4)+"****";',7,7,'|reservationEmail|obj|comments||i|substring'.split('|'),0,{}))
				obj.reservationDate = comments[i].reservationDate.split(" ")[0];
				
				if(comments[i].commentId===1){
					obj.commentImageSaveFileName = comments[i].commentImages[0].saveFileName;
					obj.commentImageCount = comments[i].commentImages.length;

					var reviewItemHtml = bindreviewListItemTemplate(obj);
					reviewItem = parser.parseFromString(reviewItemHtml, "text/html").body.firstChild;
				}
				else{
					var reviewItemNoImgHtml = bindreviewListItemNoImgTemplate(obj);
					reviewItem = parser.parseFromString(reviewItemNoImgHtml, "text/html").body.firstChild;
				}
				
				this.listShortReview.appendChild(reviewItem);
			}
		}	
	}

	
	function sectionInfoTab(){
		this.infoTabList = document.querySelector(".section_info_tab .info_tab_lst");
		this.detailAreaWrap = document.querySelector(".section_info_tab .detail_area_wrap");
		this.detailLocation = document.querySelector(".section_info_tab .detail_location");
		this.inDsc = document.querySelector(".section_info_tab .detail_area_wrap .in_dsc");
		this.storeName = document.querySelector(".section_info_tab .detail_location .store_name");
		this.storeAddrBold = document.querySelector(".section_info_tab .detail_location .store_addr_wrap .store_addr_bold");
		this.addrOldDetail = document.querySelector(".section_info_tab .detail_location .store_addr_wrap .addr_old_detail");
		this.addrDetail = document.querySelector(".section_info_tab .detail_location .store_addr_wrap .addr_detail");
		this.telephone = document.querySelector(".detail_location .lst_store_info_wrap .item_rt > a");
		
		this.currentTab = document.querySelector(".section_info_tab .info_tab_lst ._detail .anchor");
		this.selectedTab = this.currentTab
	}
	sectionInfoTab.prototype = {
		addInfoTabEvent : function(displayInfo){
			this.infoTabList.addEventListener("click", function(evt){
				var tagName = evt.target.tagName;
			
				if(tagName === "A"){
					this.selectedTab = evt.target;
										
					addClass(this.selectedTab, "active");
					if(this.selectedTab != this.currentTab){
						removeClass(this.currentTab, "active");
					}
					
					this.currentTab = this.selectedTab;
					
					if(this.selectedTab.dataset.category === "1"){
						removeClass(this.detailAreaWrap, "hide");
						addClass(this.detailLocation, "hide");
					}
					else{
						addClass(this.detailAreaWrap, "hide");
						removeClass(this.detailLocation, "hide");
					}
				}
				else if(tagName === "SPAN"){
					this.selectedTab = evt.target.parentNode;
										
					addClass(this.selectedTab, "active");
					if(this.selectedTab != this.currentTab){
						removeClass(this.currentTab, "active");
					}
					
					this.currentTab = this.selectedTab;
									
					if(this.selectedTab.dataset.category === "1"){
						removeClass(this.detailAreaWrap, "hide");
						addClass(this.detailLocation, "hide");
					}
					else{
						addClass(this.detailAreaWrap, "hide");
						removeClass(this.detailLocation, "hide");
					}
				}
			}.bind(this));
		}
		
	   ,writeInfo : function(displayInfo){
			this.inDsc.innerText = displayInfo.productContent;
			
			this.storeName.innerText = displayInfo.productDescription;
			this.storeAddrBold.innerText = displayInfo.placeStreet;
			this.addrOldDetail.innerText = displayInfo.placeLot;
			this.addrDetail.innerText = displayInfo.placeName;
			
			this.telephone.href = "tel:" + displayInfo.telephone;
			this.telephone.innerText = displayInfo.telephone;
		}		
	}
	
	
	function sendAjax(){
		this.displayInfoId = document.getElementById("displayInfoId").value;
	}
	sendAjax.prototype = {
		sendAjaxForDisplayInfo : function(){
			var oReq = new XMLHttpRequest();
		    
		    oReq.open("GET", displayInfoApiUri + this.displayInfoId);
		    oReq.send();
		    
		    oReq.onreadystatechange = function onReadyStateChange() {
		    	if(oReq.readyState === XMLHttpRequest.DONE){
		    		if(oReq.status === 200){
		    			var displayInfoResponse = JSON.parse(oReq.responseText);
		    			
		    			var productImages = displayInfoResponse.productImages;
		    			var displayInfo = displayInfoResponse.displayInfo;
		    			var comments = displayInfoResponse.comments;
		    			var averageScore = displayInfoResponse.averageScore;
		    			
		    					    			
		    			sectionDetailsObj.writeDsc(displayInfo.productContent);
		    			sectionDetailsObj.addDscOpenCloseEvent();
		    					    			
		    			sectionVisualObj.displayBackgroundImage(productImages, displayInfo);
		    			sectionVisualObj.displayPageNum(productImages);
		    				    			
		    			sectionReviewListObj.gradeCommentScore(comments, averageScore);
		    			sectionReviewListObj.displayReviewList(comments);
		    			
		    			sectionInfoTabObj.addInfoTabEvent(displayInfo);
		    			sectionInfoTabObj.writeInfo(displayInfo);
		    		}
		    	}
		    }
		}
	}
	

	
	/**********  run method  **********/
	var ajaxObj = new sendAjax();
	var sectionDetailsObj = new sectionDetails();
	var sectionVisualObj = new sectionVisual();
	var sectionReviewListObj = new sectionReviewList();
	var sectionInfoTabObj = new sectionInfoTab();
	
	ajaxObj.sendAjaxForDisplayInfo();
}


