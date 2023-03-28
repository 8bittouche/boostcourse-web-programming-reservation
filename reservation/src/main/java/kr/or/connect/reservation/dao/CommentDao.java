package kr.or.connect.reservation.dao;


import static kr.or.connect.reservation.dao.CommentDaoSqls.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Comment;
import kr.or.connect.reservation.dto.CommentImage;

@Repository
public class CommentDao {

	private NamedParameterJdbcTemplate namedParameterJdbc;
	private JdbcTemplate jdbc;
	private RowMapper<Comment> commentRowMapper = BeanPropertyRowMapper.newInstance(Comment.class);
	private RowMapper<CommentImage> commentImageRowMapper = BeanPropertyRowMapper.newInstance(CommentImage.class);
	
	public CommentDao(DataSource dataSource) {
		this.namedParameterJdbc = new NamedParameterJdbcTemplate(dataSource);
		this.jdbc = new JdbcTemplate(dataSource);
	}
	
	public List<Comment> selectComment(int productId) {
		Map param = Collections.singletonMap("productId", productId);
		return namedParameterJdbc.query(SELECT_COMMENT, param, commentRowMapper);
	}
	
	public List<CommentImage> selectCommentImage(int commentId) {
		Map param = Collections.singletonMap("commentId", commentId);
		return namedParameterJdbc.query(SELECT_COMMENT_IMAGE, param, commentImageRowMapper);
	}
	
	public int selectCommentCount(int productId) {
		Map param = Collections.singletonMap("productId", productId);
		Integer commentCount = namedParameterJdbc.queryForObject(SELECT_COMMENT_COUNT, param, Integer.class);
		return commentCount.intValue();
	}
	
	public double selectAverageScore(int productId) {
		Map param = Collections.singletonMap("productId", productId);
		Double commentCount = namedParameterJdbc.queryForObject(SELECT_AVERAGE_SCORE, param, Double.class);
		return commentCount.doubleValue();
	}
	
	public int insertComment(Comment Comment) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("productId", Comment.getProductId())
																	    .addValue("reservationInfoId", Comment.getReservationInfoId())
																	    .addValue("score", Comment.getScore())
																	    .addValue("comment", Comment.getComment())
																	    .addValue("createDate", new Date())
																	    .addValue("modifyDate", new Date());
		
		return namedParameterJdbc.update(INSERT_COMMENT, namedParameters);
	}
	
	public int insertCommentImage(CommentImage CommentImage) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("reservationInfoId", CommentImage.getReservationInfoId())
																	    .addValue("reservationUserCommentId", CommentImage.getReservationUserCommentId())
																	    .addValue("fileId", CommentImage.getFileId());
		
		return namedParameterJdbc.update(INSERT_COMMENT_IMAGE, namedParameters);
	}
	
	public int insertFileInfo(CommentImage commentImage) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("fileName", commentImage.getFileName())
																	    .addValue("saveFileName", commentImage.getSaveFileName())
																	    .addValue("contentType", commentImage.getContentType())
																	    .addValue("deleteFlag", commentImage.isDeleteFlag())
																	    .addValue("createDate", new Date())
																	    .addValue("modifyDate", new Date());
		
		return namedParameterJdbc.update(INSERT_FILE_INFO, namedParameters);
	}
	
	public int selectLastInsertId() {
		Integer identity = jdbc.queryForObject(SELECT_LAST_INSERT_ID, Integer.class);
		return identity.intValue();
	}
	
	public String selectSaveFileName(int reservationUserCommentImageId) {
		Map param = Collections.singletonMap("reservationUserCommentImageId", reservationUserCommentImageId);
		String saveFileName = namedParameterJdbc.queryForObject(SELECT_SAVE_FILE_NAME, param, String.class);
		return saveFileName.toString();
	}
	
	public int deleteComment(int reservationUserCommentId) {
		Map param = Collections.singletonMap("reservationUserCommentId", reservationUserCommentId);
		return namedParameterJdbc.update(DELETE_COMMENT, param);
	}
	
	public int deleteCommentImage(int reservationUserCommentImageId) {
		Map param = Collections.singletonMap("reservationUserCommentImageId", reservationUserCommentImageId);
		return namedParameterJdbc.update(DELETE_COMMENT_IMAGE, param);
	}
	
	public int deleteFileInfo(int fileInfoId) {
		Map param = Collections.singletonMap("fileInfoId", fileInfoId);
		return namedParameterJdbc.update(DELETE_FILE_INFO, param);
	}
}
