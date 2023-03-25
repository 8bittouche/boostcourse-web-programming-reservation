package kr.or.connect.reservation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductPrice {
	private int productPriceId;
	private int productId;
	private String priceTypeName;
	private int price;
	private int discountRate;
	private String createDate;
	private String modifyDate;
}
