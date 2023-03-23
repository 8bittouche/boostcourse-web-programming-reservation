package kr.or.connect.reservation.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Category;

@Repository
public class CategoryDao {
	private static final String SELECT_CATEGORY_ALL = "SELECT count(*) AS count, "
														   + "category.id, "
														   + "category.name " + 
													  "FROM category " + 
													  "JOIN product ON product.category_id=category.id " + 
													  "JOIN display_info ON display_info.product_id=product.id " + 
													  "GROUP BY category.id";
		
	private NamedParameterJdbcTemplate jdbc;
    private RowMapper<Category> categoryRowMapper = BeanPropertyRowMapper.newInstance(Category.class);
    
    
    public CategoryDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }
    
    public List<Category> selectCategoryAll() {
    	return jdbc.query(SELECT_CATEGORY_ALL, categoryRowMapper);
    }
}
