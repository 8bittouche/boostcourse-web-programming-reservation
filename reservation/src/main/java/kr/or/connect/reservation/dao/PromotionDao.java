package kr.or.connect.reservation.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Promotion;

@Repository
public class PromotionDao {
	private static final String SELECT_PROMOTION_ALL = "SELECT promotion.id, "
															+ "promotion.product_id, "
															+ "file_info.save_file_name AS productImageUrl "
													 + "FROM promotion "
													 + "JOIN product_image ON product_image.type='th' AND promotion.product_id=product_image.product_id "
													 + "JOIN file_info ON file_info.id=product_image.file_id";
	
	private JdbcTemplate jdbc;
    private RowMapper<Promotion> promotionRowMapper = BeanPropertyRowMapper.newInstance(Promotion.class);
    
    
    public PromotionDao(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }
    
    public List<Promotion> selectAll() {
    	return jdbc.query(SELECT_PROMOTION_ALL, promotionRowMapper);
    }
}
