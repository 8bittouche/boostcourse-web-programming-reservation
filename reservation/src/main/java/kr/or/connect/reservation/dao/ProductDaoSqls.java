package kr.or.connect.reservation.dao;

public class ProductDaoSqls {
	private static final int ONEPAGE_DISPLAY_NUM = 4;
	public static final String SELECT_PRODUCT_ALL = "SELECT display_info.id AS displayInfoId, "
													     + "display_info.place_name, "
														 + "product.content AS productContent, "
														 + "product.description AS productDescription, "
														 + "product.id AS productId, "
														 + "file_info.save_file_name AS productImageUrl " + 
													"FROM product " + 
													"JOIN product_image ON product_image.type='th' AND product.id=product_image.product_id " + 
													"JOIN file_info ON file_info.id=product_image.file_id " + 
													"JOIN display_info ON product.id=display_info.product_id " + 
													"LIMIT :start, " + ONEPAGE_DISPLAY_NUM;

	public static final String SELECT_PRODUCT_BY_CATEGORY = "SELECT display_info.id AS displayInfoId, "
																 + "display_info.place_name, "
																 + "product.content AS productContent, "
																 + "product.description AS productDescription, "
																 + "product.id AS productId, "
																 + "file_info.save_file_name AS productImageUrl " + 
															"FROM product " + 
															"JOIN product_image ON product_image.type='th' AND product.category_id= :categoryId AND product.id=product_image.product_id " + 
															"JOIN file_info ON file_info.id=product_image.file_id " + 
															"JOIN display_info ON product.id=display_info.product_id " + 
															"LIMIT :start, " + ONEPAGE_DISPLAY_NUM;
	
	public static final String SELECT_COUNT_ALL = "SELECT count(*) AS count " + 
												  "FROM display_info";
	
	public static final String SELECT_COUNT_CATEGORY = "SELECT count(*) AS count " + 
													   "FROM display_info " + 
													   "JOIN product ON display_info.product_id = product.id " + 
													   "WHERE product.category_id = :categoryId";
	
	public static final String SELECT_PRODUCT_IMAGE = "SELECT product_image.product_id AS productId, " + 
												 			 "product_image.id AS productImageId, " + 
															 "product_image.type AS type, " + 
															 "file_info.id AS fileInfoId, " + 
															 "file_info.file_name AS fileName, " + 
															 "file_info.save_file_name AS saveFileName, " + 
															 "file_info.content_type AS contentType, " + 
															 "file_info.delete_flag AS deleteFlag, " + 
															 "file_info.create_date AS createDate, " + 
															 "file_info.modify_date AS modifyDate " + 
													 "FROM product_image " + 
													 "JOIN file_info ON product_image.file_id = file_info.id " + 
													 "WHERE product_image.product_id= :productId AND (product_image.type='ma' OR product_image.type='et')";
	
	public static final String SELECT_PRODUCT_PRICE = "SELECT product_price.id AS productPriceId, " + 
															 "product_price.product_id AS productId, " + 
															 "product_price.price_type_name AS priceTypeName, " + 
															 "product_price.price AS price, " + 
															 "product_price.discount_rate AS discountRate, " + 
															 "product_price.create_date AS createDate, " + 
															 "product_price.modify_date AS modifyDate " + 
												 	  "FROM product_price " + 
													  "WHERE product_price.product_id = :productId";
}
