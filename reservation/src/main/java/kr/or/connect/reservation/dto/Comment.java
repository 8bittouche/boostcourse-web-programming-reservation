package kr.or.connect.reservation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Comment {
	private int commentId;
	private int productId;
	private int reservationInfoId;
	private int score;
	private String comment;
	private String reservationName;
	private String reservationTelephone;
	private String reservationEmail;
	private String reservationDate;
	private String createDate;
	private String modifyDate;
	private List<CommentImage> commentImages;
}
