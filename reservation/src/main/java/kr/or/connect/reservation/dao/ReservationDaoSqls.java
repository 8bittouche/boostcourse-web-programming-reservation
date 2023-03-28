package kr.or.connect.reservation.dao;

public class ReservationDaoSqls {
	public static final String SELECT_RESERVATION_INFO = "SELECT reservation_info.id AS reservationInfoId, " + 
															 	"reservation_info.product_id AS productId, " + 
																"reservation_info.display_info_id AS displayInfoId, " + 
																"reservation_info.reservation_name AS reservationName, " + 
																"reservation_info.reservation_tel AS reservationTelephone, " + 
																"reservation_info.reservation_email AS reservationEmail, " + 
																"reservation_info.cancel_flag AS cancelYn, " + 
																"reservation_info.reservation_date AS reservationDate, " + 
																"reservation_info.create_date AS createDate, " + 
																"reservation_info.modify_date AS modifyDate " + 
														 "FROM reservation_info " + 
														 "WHERE reservation_info.reservation_email= :reservationEmail";

	public static final String SELECT_RESERVATION_INFO_BY_ID = "SELECT reservation_info.id AS reservationInfoId, " + 
																  	  "reservation_info.product_id AS productId, " + 
																	  "reservation_info.display_info_id AS displayInfoId, " + 
																	  "reservation_info.reservation_name AS reservationName, " + 
																	  "reservation_info.reservation_tel AS reservationTelephone, " + 
																	  "reservation_info.reservation_email AS reservationEmail, " + 
																	  "reservation_info.cancel_flag AS cancelYn, " + 
																	  "reservation_info.reservation_date AS reservationDate, " + 
																	  "reservation_info.create_date AS createDate, " + 
																	  "reservation_info.modify_date AS modifyDate " + 
															   "FROM reservation_info " + 
															   "WHERE reservation_info.id= :reservationInfoId";
	
	public static final String SELECT_RESERVATION_PRICE = "SELECT reservation_info_price.count AS count, " + 
															  	 "reservation_info_price.product_price_id AS productPriceId, " + 
																 "reservation_info_price.reservation_info_id AS reservationInfoId, " + 
																 "reservation_info_price.id AS reservationInfoPriceId, " + 
																 "product_price.price AS price " + 
														  "FROM reservation_info_price " + 
														  "JOIN  product_price ON product_price.id=reservation_info_price.product_price_id " + 
														  "WHERE reservation_info_price.reservation_info_id= :reservationInfoId";
	
	public static final String INSERT_RESERVATION_INFO_PRICE = "INSERT INTO reservation_info_price (reservation_info_id, product_price_id, count) " + 
															   "VALUES (:reservationInfoId, :productPriceId, :count)";
	
	public static final String INSERT_RESERVATION_INFO = "INSERT INTO reservation_info (product_id, display_info_id, reservation_name, reservation_tel, reservation_email, reservation_date, create_date, modify_date) " + 
														 "VALUES (:productId, :displayInfoId, :reservationName, :reservationTelephone, :reservationEmail, :reservationDate, :createDate, :modifyDate);";
	
	public static final String UPDATE_CANCEL_FLAG = "UPDATE reservation_info " + 
													"SET cancel_flag=true " + 
													"WHERE id= :reservationInfoId";
	
	public static final String SELECT_LAST_INSERT_ID = "SELECT LAST_INSERT_ID()";
}
