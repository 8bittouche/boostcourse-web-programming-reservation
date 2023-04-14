package kr.or.connect.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.reservation.dto.ReservationInfoResponse;
import kr.or.connect.reservation.dto.ReservationParam;
import kr.or.connect.reservation.service.CommentService;
import kr.or.connect.reservation.service.ReservationService;

@RestController
@RequestMapping(path="/api/reservations")
public class ReservationApiController {

	private final ReservationService reservationService;
	private final CommentService commentService;

	@Autowired
	public ReservationApiController(ReservationService reservationService, CommentService commentService) {
		this.reservationService = reservationService;
		this.commentService = commentService;
	}

	@RequestMapping
	public ReservationInfoResponse reservations(@RequestParam(name="reservationEmail", required=true, defaultValue="0") String reservationEmail) {
		return reservationService.getReservations(reservationEmail);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public boolean postReservation(@RequestBody ReservationParam reservationParam){
		return reservationService.addReservation(reservationParam);
	}
	
	@RequestMapping(path="/{reservationInfoId}", method=RequestMethod.PUT)
	public void cancelReservation(@PathVariable(name="reservationInfoId", required=true) int reservationInfoId) {
		reservationService.cancelReservationInfo(reservationInfoId);
	}
	
	@RequestMapping(path="/{reservationInfoId}/comments", method=RequestMethod.POST)
	public boolean postComment(@PathVariable(name="reservationInfoId", required=true) int reservationInfoId, 
								   @RequestParam(name="comment", required=true) String comment,
								   @RequestParam(name="productId", required=true) int productId,
								   @RequestParam(name="score", required=true) int score,
								   @RequestParam("reviewImageFile") Object reviewImageFile){
		
		if(reviewImageFile instanceof MultipartFile) {
			return commentService.registerCommentInfo(productId, reservationInfoId, comment, score, (MultipartFile)reviewImageFile);	
		}else {
			return commentService.registerCommentInfo(productId, reservationInfoId, comment, score, null);
		}
	}
}
