package kr.or.connect.reservation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DisplayInfoImage {
	private int displayInfoImageId;
	private int displayInfoId;
	private int fileId;
	private String fileName;
	private String saveFileName;
	private String contentType;
	private boolean deleteFlag;
	private String createDate;
	private String modifyDate;
}
