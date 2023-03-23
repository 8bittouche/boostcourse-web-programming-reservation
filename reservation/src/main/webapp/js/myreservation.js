window.addEventListener("DOMContentLoaded", myreservationFunc);

function myreservationFunc(){
	var parser = new DOMParser();
		
	var linkSummaryBoardFigures = document.querySelectorAll(".link_summary_board .figure");
	var totalFigure = linkSummaryBoardFigures[0];
	var confirmedFigure = linkSummaryBoardFigures[1];
	var usedFigure = linkSummaryBoardFigures[2];
	var canceledFigure = linkSummaryBoardFigures[3];
	
	
	function mySummary(reservationInfoResponse){
		this.reservationInfoResponse = reservationInfoResponse;
	}
	mySummary.prototype = {
		displaySummary : function(){
			var reservations = this.reservationInfoResponse.reservations;
			var reservSize = this.reservationInfoResponse.size;
						
			var confirmedCount = 0;
			var usedCount = 0;
			var canceledCount = 0;
			
			reservations.forEach(function(reserv){
				if(reserv.cancelYn){	  
					canceledCount++;   
				}
				else{			  		  
					if(new Date(reserv.reservationDate) >= new Date().setHours(0,0,0,0)){
						confirmedCount++;
					}
					else{
						usedCount++;
					}
				} 
			});
			
			totalFigure.innerText = reservSize;
			confirmedFigure.innerText = confirmedCount;
			canceledFigure.innerText = canceledCount;
			usedFigure.innerText = usedCount;
		}
	}
	
	
	function wrapMyList(reservationInfoResponse){
		this.reservationInfoResponse = reservationInfoResponse;
		this.confirmedCard = document.getElementById("confirmedCard");
		this.usedCard = document.getElementById("usedCard");
		this.canceledCard = document.getElementById("canceledCard");
		this.confirmedCardItemTemplate = document.querySelector("#confirmed_card_item").innerText;
		this.canceledCardItemTemplate = document.querySelector("#canceled_card_item").innerText;
		this.usedCardItemTemplate = document.querySelector("#used_card_item").innerText;
	}
	wrapMyList.prototype = {
		addCardItem : function(){
			var bindConfirmedCardItemTemplate = Handlebars.compile(this.confirmedCardItemTemplate);
			var bindCanceledCardItemTemplate = Handlebars.compile(this.canceledCardItemTemplate);
			var bindUsedCardItemTemplate = Handlebars.compile(this.usedCardItemTemplate);
			var reservations = this.reservationInfoResponse.reservations;
			
			reservations.forEach(function(reserv){
				obj = {}
				obj.reservationInfoId = reserv.reservationInfoId;
				obj.productDescription = reserv.displayInfo.productDescription;
				obj.placeName = reserv.displayInfo.placeName;
				obj.totalPrice = reserv.totalPrice;
				obj.reservationDate = reserv.reservationDate.split(' ')[0];
				obj.productId = reserv.productId;
				
				var cardItemHtml;
				var cardItem;
				if(reserv.cancelYn){	
					cardItemHtml = bindCanceledCardItemTemplate(obj);  
					cardItem = parser.parseFromString(cardItemHtml, "text/html").body.firstChild;
					this.canceledCard.appendChild(cardItem);
				}
				else{
					if(new Date(reserv.reservationDate) >= new Date().setHours(0,0,0,0)){
						cardItemHtml = bindConfirmedCardItemTemplate(obj);	
						cardItem = parser.parseFromString(cardItemHtml, "text/html").body.firstChild;
						this.confirmedCard.appendChild(cardItem);
					}
					else{
						cardItemHtml = bindUsedCardItemTemplate(obj);	
						cardItem = parser.parseFromString(cardItemHtml, "text/html").body.firstChild;
						this.usedCard.appendChild(cardItem);
					}
					
				}
			}.bind(this));
		}
	
	   ,addCancelEvent : function(){
		   this.confirmedCard.addEventListener("click", function(evt){
			   if(evt.target.className === 'btn' || evt.target.className === 'cancel'){
				   var reservationInfoId = evt.target.id.split('_')[1];
				   
				   popupBookingWrapperObj.activatePopupBooking(reservationInfoId);
			   }
		   }.bind(this))
	   }
	}
	
	
	function popupBookingWrapper(){
		this.canceledCard = document.getElementById("canceledCard");
		this.popupBookingWrapperDiv = document.querySelector(".popup_booking_wrapper");
		this.popTit = document.querySelector(".popup_booking_wrapper .pop_tit");
		this.titleElement = this.popTit.childNodes[0];
		this.reservationDateElement = this.popTit.childNodes[1];
		this.btnNo = document.querySelector(".popup_booking_wrapper .btn_gray");
		this.btnYes = document.querySelector(".popup_booking_wrapper .btn_green");
		this.btnClose = document.querySelector(".popup_booking_wrapper .popup_btn_close .ico_cls");
		this.noSpan = document.querySelector(".popup_booking_wrapper .btn_gray .btn_bottom > span");
		this.yesSpan = document.querySelector(".popup_booking_wrapper .btn_green .btn_bottom > span");
		this.bookingCancel;
		this.cardDetail;
		this.canceledItem;
		this.reservationInfoId;
	}
	popupBookingWrapper.prototype = {
		activatePopupBooking : function(reservationInfoId){
			var cardItemId = "card_item_"+reservationInfoId;
		    var title = document.querySelector("#"+cardItemId + " .tit").innerText;
		    var reservationDate = document.querySelector("#"+cardItemId + " .detail > li:nth-child(1) .item_dsc").innerText;
		    
		    this.bookingCancel = document.getElementById("booking_cancel_"+reservationInfoId);
		    this.cardDetail = this.bookingCancel.parentNode;
		    this.canceledItem = document.getElementById(cardItemId);
		    this.reservationInfoId = reservationInfoId;
		    this.titleElement.innerText = title;
			this.reservationDateElement = reservationDate;
			
			this.popupBookingWrapperDiv.style.display = "block";
			this.btnNo.focus();
		}
	
	   ,addPopupBookingEvent : function(){
		   document.addEventListener("keydown", function(evt){
				if(this.popupBookingWrapperDiv.style.display === "block" && evt.keyCode === 27){
					this.popupBookingWrapperDiv.style.display = "none";	
				}
			}.bind(this));
						
			
			this.btnClose.addEventListener("click", function(evt){
				this.popupBookingWrapperDiv.style.display = "none";
			}.bind(this));
			
			this.btnClose.addEventListener("keydown", function(evt){
				if(document.activeElement === this.btnClose && evt.keyCode === 13){
					this.popupBookingWrapperDiv.style.display = "none";
				}
			}.bind(this));
			
			this.btnClose.addEventListener("focus", function(evt){
				this.btnNo.tabIndex = "2";
				this.btnYes.tabIndex = "3";
				this.btnClose.tabIndex = "1";
				this.noSpan.style.color = "#222";
				this.yesSpan.style.color = "#222";
			}.bind(this));
			
			
			this.btnNo.addEventListener("click", function(evt){
				this.popupBookingWrapperDiv.style.display = "none";
			}.bind(this));
			
			this.btnNo.addEventListener("keydown", function(evt){
				if(document.activeElement === this.btnNo && evt.keyCode === 13){
					this.popupBookingWrapperDiv.style.display = "none";
				}
			}.bind(this));
			
			this.btnNo.addEventListener("focus", function(evt){
				this.btnNo.tabIndex = "1";
				this.btnYes.tabIndex = "2";
				this.btnClose.tabIndex = "3";
				this.noSpan.style.color = "#0aba16";
				this.yesSpan.style.color = "#222";
			}.bind(this));
					
			
			this.btnYes.addEventListener("click", function(evt){
			   this.cardDetail.removeChild(this.bookingCancel);       // remove cancel btn
			   this.canceledCard.appendChild(this.canceledItem);
			   
			   confirmedFigure.innerText--;
			   canceledFigure.innerText++;
			   
			   var ajaxObj = new sendAjax();
			   ajaxObj.sendAjaxToCancel(this.reservationInfoId);
				   
				this.popupBookingWrapperDiv.style.display = "none";
			}.bind(this));
				
			this.btnYes.addEventListener("keydown", function(evt){
				if(document.activeElement === this.btnYes && evt.keyCode === 13){
				   this.cardDetail.removeChild(this.bookingCancel);       // remove cancel btn
				   this.canceledCard.appendChild(this.canceledItem);
				   
				   confirmedFigure.innerText--;
				   canceledFigure.innerText++;
				   
				   var ajaxObj = new sendAjax();
				   ajaxObj.sendAjaxToCancel(this.reservationInfoId);
					   
				   this.popupBookingWrapperDiv.style.display = "none";
				}
			}.bind(this));
						
			this.btnYes.addEventListener("focus", function(evt){
				this.btnNo.tabIndex = "3";
				this.btnYes.tabIndex = "1";
				this.btnClose.tabIndex = "2";
				this.noSpan.style.color = "#222";
				this.yesSpan.style.color = "#0aba16";
			}.bind(this));
	   }
	}
	
	
	function sendAjax(){
		this.reservationEmail = document.getElementById("reservationEmail").value;
	}
	sendAjax.prototype = {
		sendAjaxForReservations : function(reservationEmail){
			var oReq = new XMLHttpRequest();
		    
			oReq.open("GET", reservationApiUri + "?reservationEmail=" + this.reservationEmail);
		    oReq.send();
		    
		    oReq.onreadystatechange = function onReadyStateChange() {
		    	if(oReq.readyState === XMLHttpRequest.DONE){
		    		if(oReq.status === 200){
		    			var reservationInfoResponse = JSON.parse(oReq.responseText);
		    			
		    			var mySummaryObj = new mySummary(reservationInfoResponse);
		    			mySummaryObj.displaySummary();
		    			
		    			var wrapMyListObj = new wrapMyList(reservationInfoResponse);
		    			wrapMyListObj.addCardItem();
		    			wrapMyListObj.addCancelEvent();
		    		}
		    	}
		    }
		}
	
	   ,sendAjaxToCancel : function(reservationInfoId){
		   var oReq = new XMLHttpRequest();
		    
			oReq.open("PUT", reservationApiUri + "/" + reservationInfoId);
		    oReq.send();
	   }
	}
	
	
	/**********  run method  **********/
	
	var popupBookingWrapperObj = new popupBookingWrapper();
	popupBookingWrapperObj.addPopupBookingEvent();
	
	var ajaxObj = new sendAjax();
	ajaxObj.sendAjaxForReservations();
}

