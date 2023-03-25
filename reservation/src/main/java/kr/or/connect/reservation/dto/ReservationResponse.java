package kr.or.connect.reservation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ReservationResponse {
	private boolean cancelYn;
	private String createDate;
	private int displayInfoId;
	private String modifyDate;
	private List<ReservationPrice> prices;
	private int productId;
	private String reservationDate;
	private String reservationEmail;
	private int reservationInfoId;
	private String reservationName;
	private String reservationTelephone;
}
