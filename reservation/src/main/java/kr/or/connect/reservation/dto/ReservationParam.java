package kr.or.connect.reservation.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReservationParam {
	@JsonProperty("displayInfoId")
	private int displayInfoId;
	
	@JsonProperty("prices")
	private List<ReservationPrice> prices;
	
	@JsonProperty("productId")
	private int productId;
	
	@JsonProperty("reservationEmail")
	private String reservationEmail;
	
	@JsonProperty("reservationName")
	private String reservationName;
	
	@JsonProperty("reservationTelephone")
	private String reservationTelephone;
	
	@JsonProperty("reservationYearMonthDay")
	private String reservationYearMonthDay;
}
