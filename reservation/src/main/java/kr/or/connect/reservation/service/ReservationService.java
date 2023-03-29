package kr.or.connect.reservation.service;

import kr.or.connect.reservation.dto.ReservationInfoResponse;
import kr.or.connect.reservation.dto.ReservationParam;

public interface ReservationService {
	ReservationInfoResponse getReservations(String reservationEmail);
	boolean addReservation(ReservationParam reservationParam);
	void cancelReservationInfo(int reservInfoId);
}
