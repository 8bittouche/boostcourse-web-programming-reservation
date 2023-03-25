package kr.or.connect.reservation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReservationInfo {
	private int reservationInfoId;
	private int productId;
	private int displayInfoId;
	private String reservationName;
	private String reservationTelephone;
	private String reservationEmail;
	private boolean cancelYn;
	private String reservationDate;
	private String createDate;
	private String modifyDate;
	private DisplayInfo displayInfo;
	private int totalPrice;
}
