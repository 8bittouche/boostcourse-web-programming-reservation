package kr.or.connect.reservation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductImage {
	private int productId;
	private int productImageId;
	private String type;
	private int fileInfoId;
	private String fileName;
	private String saveFileName;
	private String contentType;
	private boolean deleteFlag;
	private String createDate;
	private String modifyDate;
}
