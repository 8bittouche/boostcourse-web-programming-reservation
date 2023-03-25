package kr.or.connect.reservation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReservationPrice {
	private int count;
	private int productPriceId;
	private int reservationInfoId;
	private int reservationInfoPriceId;
	private int price;
}
