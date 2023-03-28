package kr.or.connect.reservation.dao;

public class CommentDaoSqls {
	public static final String SELECT_COMMENT = "SELECT reservation_user_comment.id AS commentId, " + 
							   				  		    "reservation_user_comment.product_id AS productId, " + 
														"reservation_user_comment.reservation_info_id AS reservationInfoId, " + 
														"reservation_user_comment.score AS score, " + 
														"reservation_user_comment.comment AS comment, " + 
														"reservation_info.reservation_name AS reservationName, " + 
														"reservation_info.reservation_tel AS reservationTelephone, " + 
														"reservation_info.reservation_email AS reservationEmail, " + 
														"reservation_info.reservation_date AS reservationDate, " + 
														"reservation_info.create_date AS createDate, " + 
														"reservation_info.modify_date AS modifyDate " + 
												 "FROM reservation_user_comment " + 
												 "JOIN reservation_info ON reservation_user_comment.reservation_info_id = reservation_info.id " + 
												 "WHERE reservation_user_comment.product_id = :productId";

	public static final String SELECT_COMMENT_IMAGE = "SELECT reservation_user_comment_image.id AS imageId, " + 
															  "reservation_user_comment_image.reservation_info_id AS reservationInfoId, " + 
															  "reservation_user_comment_image.reservation_user_comment_id AS reservationUserCommentId, " + 
															  "reservation_user_comment_image.file_id AS fileId, " + 
															  "file_info.file_name AS fileName, " + 
															  "file_info.save_file_name AS saveFileName, " + 
															  "file_info.content_type AS contentType, " + 
															  "file_info.delete_flag AS deleteFlag, " + 
															  "file_info.create_date AS createDate, " + 
															  "file_info.modify_date AS modifyDate " + 
														"FROM reservation_user_comment_image " + 
														"JOIN file_info ON reservation_user_comment_image.file_id = file_info.id " + 
														"WHERE reservation_user_comment_image.reservation_user_comment_id= :commentId";
	
	public static final String SELECT_COMMENT_COUNT = "SELECT count(*) AS count " + 
													   "FROM reservation_user_comment " + 
													   "WHERE reservation_user_comment.product_id= :productId";
	
	public static final String SELECT_AVERAGE_SCORE = "SELECT IFNULL(ROUND(AVG(score), 1), 0) AS AverageScore " + 
													   "FROM reservation_user_comment " + 
													   "WHERE reservation_user_comment.product_id= :productId";
	
	public static final String INSERT_COMMENT = "INSERT INTO reservation_user_comment (product_id, reservation_info_id, score, comment, create_date, modify_date) " + 
												 "VALUES (:productId, :reservationInfoId, :score, :comment, :createDate, :modifyDate)";
	
	public static final String INSERT_COMMENT_IMAGE = "INSERT INTO reservation_user_comment_image (reservation_info_id, reservation_user_comment_id, file_id) " + 
			   										   "VALUES (:reservationInfoId, :reservationUserCommentId, :fileId)";
	
	public static final String INSERT_FILE_INFO = "INSERT INTO file_info (file_name, save_file_name, content_type, delete_flag, create_date, modify_date) " + 
												   "VALUES (:fileName, :saveFileName, :contentType, :deleteFlag, :createDate, :modifyDate)";
	
	public static final String SELECT_LAST_INSERT_ID = "SELECT LAST_INSERT_ID()";
	
	public static final String SELECT_SAVE_FILE_NAME = "SELECT file_info.save_file_name AS saveFileName " + 
													   "FROM file_info " + 
													   "JOIN reservation_user_comment_image " + 
													   "ON file_info.id=reservation_user_comment_image.file_id " + 
													   "WHERE reservation_user_comment_image.id= :reservationUserCommentImageId";
	
	public static final String DELETE_COMMENT = "DELETE FROM reservation_user_comment " + 
												"WHERE reservation_user_comment.id= :reservationUserCommentId";
	
	public static final String DELETE_COMMENT_IMAGE = "DELETE FROM reservation_user_comment_image " + 
													  "WHERE reservation_user_comment_image.id= :reservationUserCommentImageId";
	
	public static final String DELETE_FILE_INFO = "DELETE FROM file_info " + 
												  "WHERE file_info.id= :fileInfoId";
}
