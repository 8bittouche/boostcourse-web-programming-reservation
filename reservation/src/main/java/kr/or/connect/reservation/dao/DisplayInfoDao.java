package kr.or.connect.reservation.dao;

import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.DisplayInfo;
import kr.or.connect.reservation.dto.DisplayInfoImage;

@Repository
public class DisplayInfoDao {
	private static final String SELECT_DISPLAY_INFO = "SELECT display_info.product_id AS productId, " + 
															"product.category_id AS categoryId, " + 
															"display_info.id AS displayInfoId, " + 
															"category.name AS categoryName, " + 
															"product.description AS productDescription, " + 
															"product.content AS productContent, " + 
															"product.event AS productEvent, " + 
															"display_info.opening_hours AS openingHours, " + 
															"display_info.place_name AS placeName, " + 
															"display_info.place_lot AS placeLot, " + 
															"display_info.place_street AS placeStreet, " + 
															"display_info.tel AS telephone, " + 
															"display_info.homepage AS homepage, " + 
															"display_info.email AS email, " + 
															"display_info.create_date AS createDate, " + 
															"display_info.modify_date AS modifyDate " + 
													"FROM display_info " + 
													"JOIN product ON display_info.product_id = product.id " + 
													"JOIN category ON product.category_id = category.id " + 
													"WHERE display_info.id=:displayInfoId";
	
	private static final String SELECT_DISPLAY_INFO_IMAGE = "SELECT display_info_image.id AS displayInfoImageId, " + 
															   	   "display_info.id AS displayInfoId, " + 
																   "file_info.id AS fileId, " + 
																   "file_info.file_name AS fileName, " + 
																   "file_info.save_file_name AS saveFileName, " + 
																   "file_info.content_type AS contentType, " + 
																   "file_info.delete_flag AS deleteFlag, " + 
																   "file_info.create_date AS createDate, " + 
																   "file_info.modify_date AS modifyDate " + 
															"FROM display_info_image " + 
															"JOIN display_info ON display_info_image.display_info_id = display_info.id " + 
															"JOIN file_info ON display_info_image.file_id = file_info.id " + 
															"WHERE display_info.id = :displayInfoId";
	
	
	private NamedParameterJdbcTemplate namedParameterJdbc;
	private RowMapper<DisplayInfo> displayInfoRowMapper = BeanPropertyRowMapper.newInstance(DisplayInfo.class);
	private RowMapper<DisplayInfoImage> displayInfoImageRowMapper = BeanPropertyRowMapper.newInstance(DisplayInfoImage.class);
	
    public DisplayInfoDao(DataSource dataSource) {
        this.namedParameterJdbc = new NamedParameterJdbcTemplate(dataSource);
    }
    
    public DisplayInfo selectDisplayInfo(int displayInfoId) {
    	Map param = Collections.singletonMap("displayInfoId", displayInfoId);
    	return namedParameterJdbc.queryForObject(SELECT_DISPLAY_INFO, param, displayInfoRowMapper);
    }
    
    public DisplayInfoImage selectDisplayInfoImage(int displayInfoId) {
		Map param = Collections.singletonMap("displayInfoId", displayInfoId);
		return namedParameterJdbc.queryForObject(SELECT_DISPLAY_INFO_IMAGE, param, displayInfoImageRowMapper);
	}
}
