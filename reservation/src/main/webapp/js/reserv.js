
window.addEventListener("DOMContentLoaded", reservFunc);

function reservFunc(){
	var parser = new DOMParser();
	
	
	function groupVisual(displayInfo, productImages){
		this.productDescription = displayInfo.productDescription;
		this.productImageFileName = productImages[0].saveFileName;
				
		this.title = document.querySelector(".top_title .title");
		this.visualImg = document.querySelector(".group_visual .visual_img");
	}
	groupVisual.prototype = {
		writeTitle : function(){
			this.title.innerText = this.productDescription;
		}
		
	   ,displayProductImage : function(){
		   var visualImgItemTemplate = document.querySelector("#visual_img_item").innerText;
		   var bindVisualImgItemTemplate = Handlebars.compile(visualImgItemTemplate);
		   
		   obj = {}
		   obj.saveFileName = this.productImageFileName;
		   obj.productDescription = this.productDescription;
		   		   
		   var ImgItemHtml = bindVisualImgItemTemplate(obj);
		   var ImgItem = parser.parseFromString(ImgItemHtml, "text/html").body.firstChild;
		   
		   this.visualImg.appendChild(ImgItem);
		} 
	}
	
	
	function sectionStoreDetails(displayInfo, productPrices){
		this.placeStreet = displayInfo.placeStreet;
		this.placeName = displayInfo.placeName;
		this.openingHours = displayInfo.openingHours;
		this.productPrices = productPrices;
		
		this.placeNameDsc = document.querySelector(".section_store_details .placeName");
		this.openingHoursDsc = document.querySelector(".section_store_details .openingHours");
		this.pricesDsc = document.querySelector(".section_store_details .prices");
	}
	sectionStoreDetails.prototype = {
		writeInfo : function(){
			// place name
			this.placeNameDsc.innerText = this.placeStreet + ", " + this.placeName;
			
			// opening hours
			var openingHoursList = this.openingHours.split("-");
			var openingHoursDscString = "";
			for(var i=1; i<openingHoursList.length; i++){
				openingHoursDscString += "- " + openingHoursList[i];
			}
			this.openingHoursDsc.innerText = openingHoursDscString;
			
			// prices
			var pricesDscString="";
			this.productPrices.forEach(function(i){
				if(i.priceTypeName === 'A'){
					pricesDscString += "성인(만 19~64세) : " + i.price + "원\n";
				}
				else if(i.priceTypeName === 'Y'){
					pricesDscString += "청소년(만 13~18세) : " + i.price + "원\n";
				}
				else if(i.priceTypeName === 'B'){
					pricesDscString += "어린이(만 4~12세) : " + i.price + "원\n";
				}
				else if(i.priceTypeName === 'S' && i.discountRate != 0.00){
					pricesDscString += "세트 : " + i.price + "원  / 할인가 : " + i.discountRate + "%\n";
				}
				else if(i.priceTypeName === 'D'){
					pricesDscString += "장애인 : " + i.price + "원\n";
				}
				else if(i.priceTypeName === 'C'){
					pricesDscString += "지역주민 : " + i.price + "원\n";
				}
				else if(i.priceTypeName === 'E'){
					pricesDscString += "어얼리버드 : " + i.price + "원\n";
				}
				else if(i.priceTypeName === 'V'){
					pricesDscString += "VIP : " + i.price + "원\n";
				}
				else if(i.priceTypeName === 'R'){
					pricesDscString += "R석 : " + i.price + "원\n";
				}
				else if(i.priceTypeName === 'S'){
					pricesDscString += "S석 : " + i.price + "원\n";
				}
			});
			this.pricesDsc.innerText = pricesDscString;
		}
	}
	
	
	function sectionBookingTicket(productPrices){
		this.productPrices = productPrices;
		this.ticketBody = document.querySelector(".section_booking_ticket .ticket_body");
	}
	sectionBookingTicket.prototype = {
		displayTicketBody : function(){
			var ticketBodyQtyTemplate = document.querySelector("#ticket_body_qty").innerText;
		    var bindTicketBodyQtyTemplate = Handlebars.compile(ticketBodyQtyTemplate);
		   
		    this.productPrices.forEach(function(i){
		    	obj = {}
		    	obj.priceType = i.priceTypeName;
		    	obj.price = i.price;
			    obj.discountRate = i.discountRate;
			    
			    if(i.priceTypeName === 'A'){
		    		obj.priceTypeName = "성인";
		    	}
		    	else if(i.priceTypeName === 'Y'){
		    		obj.priceTypeName = "청소년";
		    	}
		    	else if(i.priceTypeName === 'B'){
		    		obj.priceTypeName = "어린이";
		    	}
		    	else if(i.priceTypeName === 'S' && i.discountRate != 0.00){
		    		obj.priceTypeName = "세트";
		    	}
		    	else if(i.priceTypeName === 'D'){
		    		obj.priceTypeName = "장애인";
		    	}
		    	else if(i.priceTypeName === 'C'){
		    		obj.priceTypeName = "지역주민";
		    	}
				else if(i.priceTypeName === 'E'){
					obj.priceTypeName = "어얼리버드";
		    	}
				else if(i.priceTypeName === 'V'){
					obj.priceTypeName = "VIP";
				}
				else if(i.priceTypeName === 'R'){
					obj.priceTypeName = "R석";
				}
				else if(i.priceTypeName === 'S'){
					obj.priceTypeName = "S석";
				}
			    			    
			    var qtyHtml = bindTicketBodyQtyTemplate(obj);
			    var qtyItem = parser.parseFromString(qtyHtml, "text/html").body.firstChild;
			   
			    this.ticketBody.appendChild(qtyItem);
		    }.bind(this));
		}
	
	   ,addPlusMinusBtnEvent : function(){
		   this.ticketBody.addEventListener("click", function(evt){
			   var evtTarget = evt.target;
			   
			   if(evtTarget.tagName === 'A'){
				   var clearFix = evtTarget.parentNode;
				   var clearFixChilds = clearFix.childNodes;
				   var priceType = clearFixChilds[1].value;
				   var icoMinus3 = clearFixChilds[3];
				   var countControlInput = clearFix.childNodes[5];
				   var countControlInputValue = countControlInput.value; 
				   var evtClassName = evtTarget.className;
				   var price = document.querySelector(".price .price_" + priceType).innerText;
				   var totalPriceElement = document.querySelector(".total_price .total_price_" + priceType);
				   var indivisualPrice = totalPriceElement.parentNode.parentNode;
				   var totalCountElement = document.querySelector(".inline_form #totalCount");
				   var totalCount = totalCountElement.innerText * 1;
				   
				   if(evtClassName.indexOf('ico_minus') != -1){
					   if(countControlInputValue > 0){
						   countControlInput.value = --countControlInputValue;
						   totalCount--;
					   }
					   if(countControlInputValue === 0){
						   addClass(evtTarget, "disabled");
						   addClass(countControlInput, "disabled");
					   }
				   }
				   else if(evtClassName.indexOf('ico_plus') != -1){
					   countControlInput.value = ++countControlInputValue;
					   totalCount++;
					   if(countControlInputValue > 0){
						   removeClass(icoMinus3, "disabled");
						   removeClass(countControlInput, "disabled");
					   }
				   }
				   
				   var totalPrice = price * countControlInputValue;
				   totalPriceElement.innerText = totalPrice;
				   if(totalPrice > 0){
					   addClass(indivisualPrice, "on_color");
				   }
				   else{
					   removeClass(indivisualPrice, "on_color");
				   }
				   				   
				   totalCountElement.innerText = totalCount;
			   }
		   }.bind(this));
	   }
	}
	
	
	function sectionBookingForm(productPrices){
		this.productPrices = productPrices;
		this.inputName = document.getElementById("name");
		this.nameInvalidMsg = document.getElementById("name_invalid_msg");
		this.inputTel = document.getElementById("tel");
		this.telInvalidMsg = document.getElementById("tel_invalid_msg");
		this.inputEmail = document.getElementById("email");
		this.emailInvalidMsg = document.getElementById("email_invalid_msg");
		this.chk3 = document.getElementById("chk3");
		this.totalCountElement = document.getElementById("totalCount");
		this.sectionBookingAgreement = document.querySelector(".section_booking_agreement");
		this.chkTxtLabel = document.querySelector(".section_booking_agreement .chk_txt_label");
		this.bkBtnWrap = document.querySelector(".box_bk_btn .bk_btn_wrap");
	}
	sectionBookingForm.prototype = {
		addInvalidMsgEvent : function(){
//			var telRegex = /\d{2,3}-\d{3,4}-\d{4}/;
//			var emailRegex = /^[0-9a-zA-Z]([-_]?[0-9a-zA-z])*@[0-9a-zA-Z]([-_]?[0-9a-zA-z])*(\.[a-zA-z]{2,3}){1,2}$/i;
			eval(function(p,a,c,k,e,r){e=function(c){return c.toString(a)};if(!''.replace(/^/,String)){while(c--)r[e(c)]=k[c]||e(c);k=[function(e){return r[e]}];e=function(){return'\\w+'};c=1};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p}('8 c=/\\d{2,3}-\\d{3,4}-\\d{4}/;8 e=/^[0-6-5-9]([-b]?[0-6-5-7])*@[0-6-5-9]([-b]?[0-6-5-7])*(\\.[a-5-7]{2,3}){1,2}$/f;',16,16,'|||||zA|9a|z|var|Z||_|telRegex||emailRegex|i'.split('|'),0,{}))
			
			this.inputTel.addEventListener("blur", function(evt){
				if(this.inputTel.value.match(telRegex) === null || this.inputTel.value === ''){
					this.inputTel.style.zIndex = -1;
					this.telInvalidMsg.style.zIndex = 1;
				}
				this.activateBkBtn();
			}.bind(this));
			
			this.inputTel.addEventListener("focus", function(evt){
				this.inputTel.style.zIndex = 1;
				this.inputTel.focus();
				this.telInvalidMsg.style.zIndex = -1;
			}.bind(this));
			
			this.telInvalidMsg.addEventListener("click", function(evt){
				this.inputTel.style.zIndex = 1;
				this.inputTel.focus();
				this.telInvalidMsg.style.zIndex = -1;
			}.bind(this));
			
			
			this.inputEmail.addEventListener("blur", function(evt){
				if(this.inputEmail.value.match(emailRegex) === null || this.inputEmail.value === ''){
					this.inputEmail.style.zIndex = -1;
					this.emailInvalidMsg.style.zIndex = 1;
				}
				this.activateBkBtn();
			}.bind(this));
			
			this.inputEmail.addEventListener("focus", function(evt){
				this.inputEmail.style.zIndex = 1;
				this.inputEmail.focus();
				this.emailInvalidMsg.style.zIndex = -1;
			}.bind(this));
			
			this.emailInvalidMsg.addEventListener("click", function(evt){
				this.inputEmail.style.zIndex = 1;
				this.inputEmail.focus();
				this.emailInvalidMsg.style.zIndex = -1;
			}.bind(this));
			
			
			this.inputName.addEventListener("blur", function(evt){
				if(this.inputName.value === ''){
					this.inputName.style.zIndex = -1;
					this.nameInvalidMsg.style.zIndex = 1;
				}
				this.activateBkBtn();
			}.bind(this));
			
			this.inputName.addEventListener("focus", function(evt){
				this.inputName.style.zIndex = 1;
				this.inputName.focus();
				this.nameInvalidMsg.style.zIndex = -1;
			}.bind(this));
			
			this.nameInvalidMsg.addEventListener("click", function(evt){
				this.inputName.style.zIndex = 1;
				this.inputName.focus();
				this.nameInvalidMsg.style.zIndex = -1;
			}.bind(this));
			
			
			this.chk3.addEventListener("change", function(evt){
				this.activateBkBtn();
			}.bind(this));
			
			
			this.totalCountElement.addEventListener("DOMSubtreeModified", function(evt){
				this.activateBkBtn();
			}.bind(this));
		}
	   
	   ,validateForm : function(){
			var totalCount =  this.totalCountElement.innerText * 1;
				
			if(totalCount === 0){
				return false;
			}
			else if(this.nameInvalidMsg.style.zIndex === '1' || this.inputName.value === ''){
				return false;
			}
			else if(this.telInvalidMsg.style.zIndex === '1'|| this.inputTel.value === ''){
				return false;
			}
			else if(this.emailInvalidMsg.style.zIndex === '1'|| this.inputEmail.value === ''){
				return false;
			}
			else if(!(this.chk3.checked)){
				return false;
			}
			
			return true;
		}
	
	   ,activateBkBtn : function(){
		   if(this.validateForm()){
		 	   removeClass(this.bkBtnWrap, "disable");
		   }
		   else{
			   addClass(this.bkBtnWrap, "disable");
		   }
	    }
	   
	   ,addAgreementOpenCloseEvent : function(){
			this.sectionBookingAgreement.addEventListener("click", function(evt){
				var evtTarget = evt.target;
				
				if(evtTarget.className === 'btn_text' || evtTarget.className === 'fn fn-down2' || evtTarget.className === 'fn fn-up2'){
					var agreement = evtTarget.parentNode.parentNode;
					var btnAgreement = evtTarget.parentNode;
					var fn = btnAgreement.childNodes[3];
					
					if(agreement.className === 'agreement'){
						addClass(agreement, "open");
						addClass(fn, "fn-up2");
					}
					else if(agreement.className === 'agreement open'){
						removeClass(agreement, "open");
						removeClass(fn, "fn-up2");
						addClass(fn, "fn-down2");
					}
				}
			});
		}
	   
	   ,addBkBtnEvent : function(){
		   this.bkBtnWrap.addEventListener("click", function(evt){
			   if(this.bkBtnWrap.className === "bk_btn_wrap"){
				   var reservationParam = this.makeReservationParam();
				   var ajaxObj = new sendAjax();
				   
				   ajaxObj.sendAjaxToPostReservation(reservationParam);
			   }
		   }.bind(this));
		   
		   this.bkBtnWrap.addEventListener("keyup", function(evt){
			   if(this.bkBtnWrap.className === "bk_btn_wrap" && evt.keyCode === 13){
				   var reservationParam = this.makeReservationParam();
				   var ajaxObj = new sendAjax();
				   
				   ajaxObj.sendAjaxToPostReservation(reservationParam);
			   }
		   }.bind(this));
	   }
	  
	 ,makeReservationParam : function(){
		 var reservationParam = {}
		 
		 reservationParam.displayInfoId = document.getElementById("displayInfoId").value;
		 reservationParam.productId = this.productPrices[0].productId;
		 reservationParam.reservationEmail = this.inputEmail.value;
		 reservationParam.reservationName = this.inputName.value;
		 reservationParam.reservationTelephone = this.inputTel.value;
		 reservationParam.reservationYearMonthDay = document.querySelector(".inline_txt").innerText.split(',')[0];
		 
		 var prices = []
		 this.productPrices.forEach(function(i){
			 var priceObj = {}
			 
			 priceObj.count = document.getElementById("count_control_input_"+i.priceTypeName).value;
			 priceObj.productPriceId = i.productPriceId;
			 priceObj.reservationInfoId = 0;
			 priceObj.reservationInfoPriceId = 0;
			 priceObj.price = i.price;
			 
			 prices.push(priceObj);
		 }.bind(this));
		 reservationParam.prices = prices;
		 
		 return reservationParam;
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
		    			var productImages = displayInfoResponse.productImages;
		    			var productPrices = displayInfoResponse.productPrices;
		    					    			
		    			var groupVisualObj = new groupVisual(displayInfo, productImages);
		    			groupVisualObj.writeTitle();
		    			groupVisualObj.displayProductImage();
		    			
		    			var sectionStoreDetailsObj = new sectionStoreDetails(displayInfo, productPrices);
		    			sectionStoreDetailsObj.writeInfo();
		    			
		    			var sectionBookingTicketObj = new sectionBookingTicket(productPrices);
		    			sectionBookingTicketObj.displayTicketBody();
		    			sectionBookingTicketObj.addPlusMinusBtnEvent();
		    			
		    			var sectionBookingFormObj = new sectionBookingForm(productPrices);
		    			sectionBookingFormObj.addAgreementOpenCloseEvent();
		    			sectionBookingFormObj.addBkBtnEvent();
		    			sectionBookingFormObj.addInvalidMsgEvent();
		    		}
		    	}
		    }
		}
	
	   ,sendAjaxToPostReservation : function(reservationParam){
		    var oReq = new XMLHttpRequest();
		    
		    oReq.open("POST", reservationApiUri);
		    		    
		    oReq.setRequestHeader("Content-type", "application/json");
		    oReq.send(JSON.stringify(reservationParam));
		    
		    oReq.onreadystatechange = function onReadyStateChange() {
		    	if(oReq.readyState === XMLHttpRequest.DONE){
		    		if(oReq.status === 200){
		    			if(oReq.response === 'true'){
		    				window.location.href = "/reservation/mainpage";
		    			}
		    			else{
		    				alert("예약이 처리되지 않았습니다.");
		    			}
		    		}
		    	}
		    }
	   }
	}
	
	/**********  run method  **********/
	
	var ajaxObj = new sendAjax();
	ajaxObj.sendAjaxForDisplayInfo();
}
