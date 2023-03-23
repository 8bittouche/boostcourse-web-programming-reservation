window.addEventListener("DOMContentLoaded", reviewFunc);

function reviewFunc(){
	var parser = new DOMParser();
		
	
	function sectionReviewList(){
		this.graphValue = document.querySelector(".section_review_list .grade_area .graph_value");
		this.textValue = document.querySelector(".section_review_list .grade_area .text_value > span");
		this.joinCount = document.querySelector(".section_review_list .grade_area .join_count .green");
		this.listShortReview = document.querySelector(".section_review_list .list_short_review");
	}
	sectionReviewList.prototype = {
		gradeCommentScore : function(comments, averageScore){
			var commentsNum = comments.length;
			var fixedAverageScore = averageScore.toFixed(1);
			var AverageScorePercent = (fixedAverageScore / 5.0) * 100;
			
			this.graphValue.style.width = AverageScorePercent + "%";
			this.textValue.innerText = fixedAverageScore;
			this.joinCount.innerText = commentsNum + "건";
		}
		
	   ,displayReviewList : function(comments, displayInfo){
			var reviewListItemTemplate = document.querySelector("#review_list_item").innerText;
			var reviewListItemNoImgTemplate = document.querySelector("#review_list_item_no_img").innerText;
			var bindreviewListItemTemplate = Handlebars.compile(reviewListItemTemplate);
			var bindreviewListItemNoImgTemplate = Handlebars.compile(reviewListItemNoImgTemplate); 
			var reviewItem;
			
			var obj = {}
			obj.productDescription = displayInfo.productDescription;
			
			comments.forEach(function(c){
				obj.comment = c.comment;
				obj.score = c.score.toFixed(1);
				//obj.reservationEmail = c.reservationEmail.substring(0,4) + "****";
				eval(function(p,a,c,k,e,r){e=String;if(!''.replace(/^/,String)){while(c--)r[c]=k[c]||c;k=[function(e){return r[e]}];e=function(){return'\\w+'};c=1};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p}('2.1=3.1.5(0,4)+"****";',6,6,'|reservationEmail|obj|c||substring'.split('|'),0,{}))
				obj.reservationDate = c.reservationDate.split(" ")[0];
				
				if(c.commentImages.length > 0){
					obj.reservationUserCommentImageId = c.commentImages[0].imageId;
					obj.commentImageCount = c.commentImages.length;

					var reviewItemHtml = bindreviewListItemTemplate(obj);
					reviewItem = parser.parseFromString(reviewItemHtml, "text/html").body.firstChild;
				}
				else{
					var reviewItemNoImgHtml = bindreviewListItemNoImgTemplate(obj);
					reviewItem = parser.parseFromString(reviewItemNoImgHtml, "text/html").body.firstChild;
				}
				
				this.listShortReview.appendChild(reviewItem);
			}.bind(this));
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
		    			
		    			var displayInfo = displayInfoResponse.displayInfo;
		    			var comments = displayInfoResponse.comments;
		    			var averageScore = displayInfoResponse.averageScore;
		    				    			
		    			sectionReviewListObj.gradeCommentScore(comments, averageScore);
		    			sectionReviewListObj.displayReviewList(comments, displayInfo);
		    		}
		    	}
		    }
		}
	}

	
	/**********  run method  **********/
	var ajaxObj = new sendAjax();
	var sectionReviewListObj = new sectionReviewList();
	
	ajaxObj.sendAjaxForDisplayInfo();
}