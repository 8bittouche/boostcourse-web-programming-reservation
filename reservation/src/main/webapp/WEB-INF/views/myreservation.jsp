<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="utf-8">
	<meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
	<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
	<title>네이버 예약</title>
	<link href="css/style.css" rel="stylesheet">

</head>

<body>
	<div id="container">
		<div class="header">
			<header class="header_tit">
				<h1 class="logo">
					<a href="https://m.naver.com/" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
					<a href="mainpage" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
				</h1>
				<a href="myreservation?reservationEmail=${sessionScope.reservationEmail }" class="btn_my"> <span title="내예약" class="viewReservation">${sessionScope.reservationEmail }</span> </a>
			</header>
		</div>
		<hr>
		<div class="ct">
			<input type="hidden" id="reservationEmail" value=${param.reservationEmail } />
			<div class="section_my">
				<!-- 예약 현황 -->
				<div class="my_summary">
					<ul class="summary_board">
						<li class="item">
							<a class="link_summary_board"> <i class="spr_book2 ico_book2"></i> <em class="tit">전체</em> <span class="figure">6</span> </a>
						</li>
						<li class="item">
							<a href="#confirmedCard" class="link_summary_board"> <i class="spr_book2 ico_book_ss"></i> <em class="tit">이용예정</em> <span class="figure">2</span> </a>
						</li>
						<li class="item">
							<a href="#usedCard" class="link_summary_board"> <i class="spr_book2 ico_check"></i> <em class="tit">이용완료</em> <span class="figure">2</span> </a>
						</li>
						<li class="item">
							<a href="#canceledCard" class="link_summary_board"> <i class="spr_book2 ico_back"></i> <em class="tit">취소·환불</em> <span class="figure">2</span> </a>
						</li>
					</ul>
				</div>
				<!--// 예약 현황 -->

				<!-- 내 예약 리스트 -->
				<div class="wrap_mylist">
					<ul class="list_cards" ng-if="bookedLists.length > 0">
						<!--[D] 예약확정: .confirmed, 취소된 예약&이용완료: .used 추가 card -->
						<li class="card" style="display: none;">
							<div class=link_booking_details>
								<div class="card_header">
									<div class="left"></div>
									<div class="middle">
										<!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book2 -->
										<i class="spr_book2 ico_clock"></i>
										<span class="tit">예약 신청중</span>
									</div>
									<div class="right"></div>
								</div>
							</div>
						</li>
						<li class="card confirmed" id="confirmedCard">
							<div class="link_booking_details">
								<div class="card_header">
									<div class="left"></div>
									<div class="middle">
										<!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book -->
										<i class="spr_book2 ico_check2"></i>
										<span class="tit">예약 확정</span>
									</div>
									<div class="right"></div>
								</div>
							</div>
						</li>
						<li class="card used" id="usedCard">
							<div class="link_booking_details">
								<div class="card_header">
									<div class="left"></div>
									<div class="middle">
										<!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book -->
										<i class="spr_book2 ico_check2"></i>
										<span class="tit">이용 완료</span>
									</div>
									<div class="right"></div>
								</div>
							</div>
						</li>
						<li class="card used cancel" id="canceledCard">
							<div class="link_booking_details">
								<div class="card_header">
									<div class="left"></div>
									<div class="middle">
										<!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book -->
										<i class="spr_book2 ico_cancel"></i>
										<span class="tit">취소된 예약</span>
									</div>
									<div class="right"></div>
								</div>
							</div>
						</li>
					</ul>
				</div>
					<!--// 내 예약 리스트 -->

					<!-- 예약 리스트 없음 -->
					<div class="err"> <i class="spr_book ico_info_nolist"></i>
						<h1 class="tit">예약 리스트가 없습니다</h1>
					</div>
					<!--// 예약 리스트 없음 -->
				</div>
			</div>
			<hr>
		</div>
		<footer>
			<div class="gototop">
				<div class="lnk_top"><span class="lnk_top_text" style="cursor:pointer;" onclick="window.scrollTo({top: 0, behavior: 'smooth'});">TOP</span></div>
			</div>
			<div id="footer" class="footer">
				<p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및 환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
				<span class="copyright">© NAVER Corp.</span>
			</div>
		</footer>

		<!-- 취소 팝업 -->
		<!-- [D] 활성화 display:block, 아니오 버튼 or 닫기 버튼 클릭 시 숨김 display:none; -->
		<div class="popup_booking_wrapper" style="display:none;">
			<div class="dimm_dark" style="display:block"></div>
			<div class="popup_booking refund">
				<h1 class="pop_tit">
					<span></span>
					<small class="sm"></small>
				</h1>
				<div class="nomember_alert">
					<p>취소하시겠습니까?</p>
				</div>
				<div class="pop_bottom_btnarea">
					<div class="btn_gray" tabindex="1">
						<a class="btn_bottom"><span>아니오</span></a>
					</div>
					<div class="btn_green" tabindex="2">
						<a class="btn_bottom"><span>예</span></a>
					</div>
				</div>
				<!-- 닫기 -->
				<a class="popup_btn_close" title="close" tabindex="3">
					<i class="spr_book2 ico_cls"></i>
				</a>
				<!--// 닫기 -->
			</div>
		</div>
		<!--// 취소 팝업 -->
<script type="text/x-handlebars-template" id="confirmed_card_item">
								<article class="card_item" id="card_item_{{reservationInfoId}}">
									<a class="link_booking_details">
										<div class="card_body">
											<div class="left"></div>
											<div class="middle">
												<div class="card_detail">
													<em class="booking_number">No.0000000</em>
													<h4 class="tit">{{productDescription}}</h4>
													<ul class="detail">
														<li class="item">
															<span class="item_tit">예약일</span>
															<em class="item_dsc">
																{{reservationDate}}
															</em>
														</li>
														<li class="item">
															<span class="item_tit">내역</span>
															<em class="item_dsc">
																내역이 없습니다.
															</em>
														</li>
														<li class="item">
															<span class="item_tit">장소</span>
															<em class="item_dsc">
																{{placeName}}
															</em>
														</li>
														<li class="item">
															<span class="item_tit">업체</span>
															<em class="item_dsc">
																업체명이 없습니다.
															</em>
														</li>
													</ul>
													<div class="price_summary">
														<span class="price_tit">결제 예정금액</span>
														<em class="price_amount">
															<span>{{totalPrice}}</span>
															<span class="unit">원</span>
														</em>
													</div>
													<!-- [D] 예약 신청중, 예약 확정 만 취소가능, 취소 버튼 클릭 시 취소 팝업 활성화 -->
													<div class="booking_cancel" id="booking_cancel_{{reservationInfoId}}">
														<button class="btn" id="btn_{{reservationInfoId}}"><span class="cancel" id="cancel_{{reservationInfoId}}">취소</span></button>
													</div>

												</div>
											</div>
											<div class="right"></div>
										</div>
										<div class="card_footer">
											<div class="left"></div>
											<div class="middle"></div>
											<div class="right"></div>
										</div>
									</a>
									<a class="fn fn-share1 naver-splugin btn_goto_share" title="공유하기"></a>
								</article>
</script>
<script type="text/x-handlebars-template" id="canceled_card_item">
								<article class="card_item" id="card_item_{{reservationInfoId}}">
									<a class="link_booking_details">
										<div class="card_body">
											<div class="left"></div>
											<div class="middle">
												<div class="card_detail">
													<em class="booking_number">No.0000000</em>
													<h4 class="tit">{{productDescription}}</h4>
													<ul class="detail">
														<li class="item">
															<span class="item_tit">예약일</span>
															<em class="item_dsc">
																{{reservationDate}}
															</em>
														</li>
														<li class="item">
															<span class="item_tit">내역</span>
															<em class="item_dsc">
																내역이 없습니다.
															</em>
														</li>
														<li class="item">
															<span class="item_tit">장소</span>
															<em class="item_dsc">
																{{placeName}}
															</em>
														</li>
														<li class="item">
															<span class="item_tit">업체</span>
															<em class="item_dsc">
																업체명이 없습니다.
															</em>
														</li>
													</ul>
													<div class="price_summary">
														<span class="price_tit">결제 예정금액</span>
														<em class="price_amount">
															<span>{{totalPrice}}</span>
															<span class="unit">원</span>
														</em>
													</div>
													

												</div>
											</div>
											<div class="right"></div>
										</div>
										<div class="card_footer">
											<div class="left"></div>
											<div class="middle"></div>
											<div class="right"></div>
										</div>
									</a>
									<a class="fn fn-share1 naver-splugin btn_goto_share" title="공유하기"></a>
								</article>
</script>
<script type="text/x-handlebars-template" id="used_card_item">
								<article class="card_item" id="card_item_{{reservationInfoId}}">
									<a href="#" class="link_booking_details">
										<div class="card_body">
											<div class="left"></div>
											<div class="middle">
												<div class="card_detail">
													<em class="booking_number">No.0000000</em>
													<h4 class="tit">{{productDescription}}</h4>
													<ul class="detail">
														<li class="item">
															<span class="item_tit">예약일</span>
															<em class="item_dsc">
																{{reservationDate}}
															</em>
														</li>
														<li class="item">
															<span class="item_tit">내역</span>
															<em class="item_dsc">
																내역이 없습니다.
															</em>
														</li>
														<li class="item">
															<span class="item_tit">장소</span>
															<em class="item_dsc">
																{{placeName}}
															</em>
														</li>
														<li class="item">
															<span class="item_tit">업체</span>
															<em class="item_dsc">
																업체명이 없습니다.
															</em>
														</li>
													</ul>
													<div class="price_summary">
														<span class="price_tit">결제 예정금액</span>
														<em class="price_amount">
															<span>{{totalPrice}}</span>
															<span class="unit">원</span>
														</em>
													</div>
													<div class="booking_cancel">
														<a href="reviewWrite?title={{productDescription}}&reservationInfoId={{reservationInfoId}}&productId={{productId}}"><button class="btn"><span>예매자 리뷰 남기기</span></button></a>
													</div>
												</div>
											</div>
											<div class="right"></div>
										</div>
										<div class="card_footer">
											<div class="left"></div>
											<div class="middle"></div>
											<div class="right"></div>
										</div>
									</a>
								</article>
</script>
<script type="text/javascript" src="js/myreservation.js"></script>
<script type="text/javascript" src="js/apiUrl.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.1.2/handlebars.min.js"></script>
</body>
</html>