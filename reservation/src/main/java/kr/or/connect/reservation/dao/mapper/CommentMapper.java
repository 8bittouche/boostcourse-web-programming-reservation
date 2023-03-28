package kr.or.connect.reservation.dao.mapper;

import kr.or.connect.reservation.dto.Comment;
import kr.or.connect.reservation.dto.CommentImage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<Comment> selectComment(int productId);
    List<CommentImage> selectCommentImage(int commentId);
    int selectCommentCount(int productId);
    double selectAverageScore(int productId);
    int insertComment(Comment Comment);
    int insertCommentImage(CommentImage CommentImage);
    int insertFileInfo(CommentImage commentImage);
    int selectLastInsertId();
    String selectSaveFileName(int reservationUserCommentImageId);
    String selectFileName(int reservationUserCommentImageId);
    int deleteComment(int reservationUserCommentId);
    int deleteCommentImage(int reservationUserCommentImageId);
    int deleteFileInfo(int fileInfoId);
}
