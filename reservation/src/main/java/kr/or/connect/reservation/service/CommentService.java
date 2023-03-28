package kr.or.connect.reservation.service;

import org.springframework.web.multipart.MultipartFile;

public interface CommentService {
	boolean registerCommentInfo(int productId, int reservationInfoId, String comment, int score, MultipartFile reviewImageFile);
	String getSaveFileName(int reservationUserCommentImageId);
	String getFileName(int reservationUserCommentImageId);
}
