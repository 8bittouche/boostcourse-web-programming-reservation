package kr.or.connect.reservation.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.connect.reservation.dto.ReservationInfoResponse;
import kr.or.connect.reservation.service.ReservationService;

@Controller
public class MainController {
	
	@Autowired
	ReservationService reservationService;
	
	
	@GetMapping(path="/mainpage")
	public void mainpage() {}
	
	
	@GetMapping(path="/detail")
	public void detail(@RequestParam(name="id", required=false, defaultValue="0") int displayInfoId) {}
		
	
	@GetMapping(path="/bookinglogin")
	public void bookinglogin() {}
	
	
	@GetMapping(path="/myreservation")
	public void myreservation(@RequestParam(name="reservationEmail", required=true, defaultValue="0") String reservationEmail, HttpSession session) {
		ReservationInfoResponse reservationInfoResponse = reservationService.getReservations(reservationEmail);
		int size = reservationInfoResponse.getSize();
		
		if(size > 0) {
			session.setAttribute("reservationEmail", reservationEmail);
		}
	}
	
	
	@GetMapping(path="/reserve")
	public void reserve(@RequestParam(name="id", required=false, defaultValue="0") int displayInfoId, ModelMap map) {
		String reservationDate  = makeReservationDate();
		map.addAttribute("reservationDate", reservationDate);
	}
	
	
	@GetMapping(path="/review")
	public void review(@RequestParam(name="id", required=false, defaultValue="0") int displayInfoId) {}
	
	
	@GetMapping(path="/reviewWrite")
	public void reviewWrite(@RequestParam(name="title", required=false, defaultValue="No Title") String title,
							@RequestParam(name="reservationInfoId", required=false, defaultValue="0") int reservationInfoId,
							@RequestParam(name="productId", required=false, defaultValue="0") int productId) {}
	
	
	private String makeReservationDate() {
		Random random = new Random();
		LocalDate randomDate = LocalDate.now().plusDays(random.nextInt(5));
	    String formatRandomDate = randomDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
	
	    return formatRandomDate;
	}
}
