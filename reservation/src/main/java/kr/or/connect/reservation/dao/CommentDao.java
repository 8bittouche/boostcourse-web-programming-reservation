package kr.or.connect.reservation.dao;

import java.util.Date;
import java.util.List;

import kr.or.connect.reservation.dao.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Comment;
import kr.or.connect.reservation.dto.CommentImage;

@Repository
@RequiredArgsConstructor
public class CommentDao {

	private final CommentMapper commentMapper;

	public List<Comment> selectComment(int productId) {
		return commentMapper.selectComment(productId);
	}
	
	public List<CommentImage> selectCommentImage(int commentId) {
		return commentMapper.selectCommentImage(commentId);
	}
	
	public int selectCommentCount(int productId) {
		return commentMapper.selectCommentCount(productId);
	}
	
	public double selectAverageScore(int productId) {
		return commentMapper.selectAverageScore(productId);
	}
	
	public int insertComment(Comment comment) {
		comment.setCreateDate(new Date().toString());
		comment.setModifyDate(new Date().toString());

		return commentMapper.insertComment(comment);
	}
	
	public int insertCommentImage(CommentImage commentImage) {
		return commentMapper.insertCommentImage(commentImage);
	}
	
	public int insertFileInfo(CommentImage commentImage) {
		commentImage.setCreateDate(new Date().toString());
		commentImage.setModifyDate(new Date().toString());

		return commentMapper.insertFileInfo(commentImage);
	}
	
	public int selectLastInsertId() {
		return commentMapper.selectLastInsertId();
	}
	
	public String selectSaveFileName(int reservationUserCommentImageId) {
		return commentMapper.selectSaveFileName(reservationUserCommentImageId);
	}

	public String selectFileName(int reservationUserCommentImageId) {
		return commentMapper.selectFileName(reservationUserCommentImageId);
	}
	
	public int deleteComment(int reservationUserCommentId) {
		return commentMapper.deleteComment(reservationUserCommentId);
	}
	
	public int deleteCommentImage(int reservationUserCommentImageId) {
		return commentMapper.deleteCommentImage(reservationUserCommentImageId);
	}
	
	public int deleteFileInfo(int fileInfoId) {
		return commentMapper.deleteFileInfo(fileInfoId);
	}
}
