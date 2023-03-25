package kr.or.connect.reservation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentResponse {
	private String comment;
	private int commentId;
	private CommentImage commentImage;
	private String createDate;
	private String modifyDate;
	private int productId;
	private int reservationInfoId;
	private int score;
}
