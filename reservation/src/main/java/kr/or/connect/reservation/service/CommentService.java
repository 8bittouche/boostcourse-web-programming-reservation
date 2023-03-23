package kr.or.connect.reservation.service;

import org.springframework.web.multipart.MultipartFile;

public interface CommentService {
	public boolean registerCommentInfo(int productId, int reservationInfoId, String comment, int score, MultipartFile reviewImageFile);
	public String getSaveFileName(int reservationUserCommentImageId);
}
