package kr.or.connect.reservation.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.ProductImage;
import kr.or.connect.reservation.dto.ProductPrice;
import static kr.or.connect.reservation.dao.ProductDaoSqls.*;

@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate namedParameterJdbc;
	private JdbcTemplate jdbc;
    private RowMapper<Product> productRowMapper = BeanPropertyRowMapper.newInstance(Product.class);
    private RowMapper<ProductImage> productImageRowMapper = BeanPropertyRowMapper.newInstance(ProductImage.class);
    private RowMapper<ProductPrice> productPriceRowMapper = BeanPropertyRowMapper.newInstance(ProductPrice.class);
    
    public ProductDao(DataSource dataSource) {
        this.namedParameterJdbc = new NamedParameterJdbcTemplate(dataSource);
        this.jdbc = new JdbcTemplate(dataSource);
    }
    
    public List<Product> selectProductAll(int start) {
    	Map param = Collections.singletonMap("start", start);
		return namedParameterJdbc.query(SELECT_PRODUCT_ALL, param, productRowMapper);
    }
    
    public List<Product> selectProductByCategory(int categoryId, int start) {
    	Map<String, Integer> params = new HashMap<>();
    	params.put("categoryId", categoryId);
    	params.put("start", start);
    	return namedParameterJdbc.query(SELECT_PRODUCT_BY_CATEGORY, params, productRowMapper);
    }
    
   
    public int selectCountAll() {
    	Integer totalCount = jdbc.queryForObject(SELECT_COUNT_ALL, Integer.class);
    	return totalCount.intValue();
	}
    
    public int selectCountCategory(int categoryId) {
    	Map param = Collections.singletonMap("categoryId", categoryId);
    	Integer categoryCount = namedParameterJdbc.queryForObject(SELECT_COUNT_CATEGORY, param, Integer.class);
    	return categoryCount.intValue();
	}
    
    
    public List<ProductImage> selectProductImage(int productId) {
    	Map param = Collections.singletonMap("productId", productId);
    	return namedParameterJdbc.query(SELECT_PRODUCT_IMAGE, param, productImageRowMapper);
    }
        
	public List<ProductPrice> selectProductPrice(int productId) {
		Map param = Collections.singletonMap("productId", productId);
		return namedParameterJdbc.query(SELECT_PRODUCT_PRICE, param, productPriceRowMapper);
	}
}
