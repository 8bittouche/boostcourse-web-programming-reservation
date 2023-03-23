package kr.or.connect.reservation.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

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
	
	
	public int getDisplayInfoId() {
		return displayInfoId;
	}
	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}
	public List<ReservationPrice> getPrices() {
		return prices;
	}
	public void setPrices(List<ReservationPrice> prices) {
		this.prices = prices;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getReservationEmail() {
		return reservationEmail;
	}
	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}
	public String getReservationName() {
		return reservationName;
	}
	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}
	public String getReservationTelephone() {
		return reservationTelephone;
	}
	public void setReservationTelephone(String reservationTelephone) {
		this.reservationTelephone = reservationTelephone;
	}
	public String getReservationYearMonthDay() {
		return reservationYearMonthDay;
	}
	public void setReservationYearMonthDay(String reservationYearMonthDay) {
		this.reservationYearMonthDay = reservationYearMonthDay;
	}
	
	@Override
	public String toString() {
		return "reservationParam [displayInfoId=" + displayInfoId + ", prices=" + prices + ", productId=" + productId
				+ ", reservationEmail=" + reservationEmail + ", reservationName=" + reservationName
				+ ", reservationTelephone=" + reservationTelephone + ", reservationYearMonthDay="
				+ reservationYearMonthDay + "]";
	}
}
