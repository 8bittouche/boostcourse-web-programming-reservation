window.addEventListener("DOMContentLoaded", reviewWriteFunc);

function countWords(){
	var guideReviewSpan = document.querySelector(".review_write_footer_wrap .guide_review > span");
	var reviewTextarea = document.querySelector(".review_contents .review_textarea");
	
	guideReviewSpan.innerText = reviewTextarea.value.length;
}

function reviewWriteFunc(){
	function writeAct(){
		this.rating = document.querySelector(".write_act .rating");
		this.starRank = document.querySelector(".write_act .star_rank");
		this.ratingRdoList = this.makeRatingRdoList();
	}
	writeAct.prototype = {
		makeRatingRdoList : function(){
			var ratingRdoList = [];
			var ratingChildNodes = this.rating.childNodes;
			
			ratingChildNodes.forEach(function(child){
				if(child.className === 'rating_rdo'){
					ratingRdoList.push(child);
				}
			});
			
			return ratingRdoList;
		}
	
	   ,addRatingEvent : function(){
			this.rating.addEventListener("click", function(evt){
				if(evt.target.tagName === 'INPUT'){
					this.ratingRdoList.forEach(function(rdo){
						if(rdo.value <= evt.target.value){
							addClass(rdo, "checked");
						}
						else{
							removeClass(rdo, "checked");
						}
					});
					
					this.starRank.innerText = evt.target.value;
					removeClass(this.starRank, "gray_star");
				}
			}.bind(this));
		}
	}
	
	
	function reviewContents(){
		this.reviewWriteInfo = document.querySelector(".review_contents .review_write_info");
		this.reviewTextarea = document.querySelector(".review_contents .review_textarea");
		
	}
	reviewContents.prototype = {
		addReviewWriteEvent : function(){
			this.reviewWriteInfo.addEventListener("click", function(evt){
				this.reviewWriteInfo.style.zIndex = -1;
				this.reviewTextarea.focus();
				this.reviewTextarea.setAttribute('minlength', '5');
				this.reviewTextarea.setAttribute('maxlength', '400');
				this.reviewTextarea.setAttribute('onkeyup', 'countWords()');
			}.bind(this));
		}
	
	   ,addReviewTextareaEvent : function(){
		   this.reviewTextarea.addEventListener("focusout", function(evt){
			   this.reviewWriteInfo.style.zIndex = 1;
		   }.bind(this));
	   	}
	}
	
	
	function reviewWriteFooterWrap(){
		this.reviewImageFileOpenInput = document.getElementById("reviewImageFileOpenInput");
		this.itemThumb = document.querySelector(".item_preview_thumbs .item_thumb");
		this.icoDel = document.querySelector(".item_preview_thumbs .ico_del");
	}
	reviewWriteFooterWrap.prototype = {
		addFileOpenInputEvent : function(){
			this.reviewImageFileOpenInput.addEventListener("change", function(evt){
				if(evt.target.files.length === 0) return;
				const reviewImage = evt.target.files[0];
								
				if(!this.validImageType(reviewImage)){
					alert("파일의 확장자가 jpeg, jpg 또는 png가 아닙니다.");
					return;
				}
				
				this.itemThumb.src = window.URL.createObjectURL(reviewImage);
				this.itemThumb.parentNode.style.display = "block";
				
				this.icoDel.addEventListener("click", function(e){
					this.itemThumb.src = "";
					this.itemThumb.parentNode.style.display = "none";
					this.reviewImageFileOpenInput.value = "";
				}.bind(this));
				
			}.bind(this));
		}
	
	   ,validImageType : function(image){
		   //var imageFileNameRegex = /[^(\\/*?:|\"<>)]+\.(png|jpg|jpeg)/i;
		   eval(function(p,a,c,k,e,r){e=String;if(!''.replace(/^/,String)){while(c--)r[c]=k[c]||c;k=[function(e){return r[e]}];e=function(){return'\\w+'};c=1};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p}('0 1=/[^(\\\\/*?:|\\"<>)]+\\.(2|3|4)/5;',6,6,'var|imageFileNameRegex|png|jpg|jpeg|i'.split('|'),0,{}))
		   var regexMatch = image.name.match(imageFileNameRegex);
		   if(regexMatch === null || regexMatch.index != 0){
			   return false;
		   }
		   return true;
	    }
	}
	
	
	function boxBkBtn(){
		this.bkBtn = document.querySelector(".bk_btn");
		this.reservationInfoId = document.getElementById("reservationInfoId").value;
		this.productId = document.getElementById("productId").value;
	}
	boxBkBtn.prototype = {
		addBkBtnEvent : function(){
			this.bkBtn.addEventListener("click", function(evt){
				var ajaxObj = new sendAjax();
				
				var comment = document.querySelector(".review_contents .review_textarea").value;
				var score = document.querySelector(".write_act .star_rank").innerText*1;
				var reviewImageFile = document.getElementById("reviewImageFileOpenInput").files[0];
				if(this.checkCommentLength(comment)){
					ajaxObj.sendAjaxToPostComment(this.reservationInfoId, this.productId, comment, score, reviewImageFile);
				}
			}.bind(this));
		}
	
	   ,checkCommentLength : function(comment){
		   var commentLength = comment.length;
		   if(5 <= commentLength && commentLength <= 400){
			   return true;
		   }
		   else{
			   alert("한줄평 길이는 최소 5자 이상 400자 이하입니다.");
			   return false;
		   }
	   }
	}
	
	
	function sendAjax(){
		
	}
	sendAjax.prototype = {
		sendAjaxToPostComment : function(reservationInfoId, productId, comment, score, reviewImageFile){
			var oReq = new XMLHttpRequest();
		    
			oReq.open("POST", reservationApiUri + "/" + reservationInfoId + "/comments" + "?comment=" + comment + "&productId=" + productId + "&score=" + score);
					    
	    	var oData = new FormData();
	    	oData.append("reviewImageFile", reviewImageFile);
	    	oReq.send(oData);
		    		    		    
		    oReq.onreadystatechange = function onReadyStateChange() {
		    	if(oReq.readyState === XMLHttpRequest.DONE){
		    		if(oReq.status === 200){
		    			if(oReq.response === 'true'){
		    				var reservationEmail = document.getElementById("reservationEmail").value;
		    				window.location.href = "/reservation/myreservation?reservationEmail=" + reservationEmail;
		    			}
		    			else{
		    				alert("리뷰 등록이 처리되지 않았습니다.");
		    			}
		    		}
		    	}
		    }
		}
	}
	
	
	var writeActObj = new writeAct();
	writeActObj.addRatingEvent();
	
	var reviewContentsObj = new reviewContents();
	reviewContentsObj.addReviewWriteEvent();
	reviewContentsObj.addReviewTextareaEvent();
	
	var reviewWriteFooterWrapObj = new reviewWriteFooterWrap();
	reviewWriteFooterWrapObj.addFileOpenInputEvent();
	
	var boxBkBtnObj = new boxBkBtn();
	boxBkBtnObj.addBkBtnEvent();
}