package kr.or.connect.reservation.service;

import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.reservation.dto.ReservationInfoResponse;
import kr.or.connect.reservation.dto.ReservationParam;

public interface ReservationService {
	public ReservationInfoResponse getReservations(String reservationEmail);
	public boolean addReservation(ReservationParam reservationParam);
	public void cancelReservationInfo(int reservInfoId);
}
