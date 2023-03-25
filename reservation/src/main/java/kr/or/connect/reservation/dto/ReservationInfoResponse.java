package kr.or.connect.reservation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ReservationInfoResponse {
	private List<ReservationInfo> reservations;
	private int size;
}
