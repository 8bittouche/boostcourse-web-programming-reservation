package kr.or.connect.reservation.dao;

import java.util.List;

import kr.or.connect.reservation.dao.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.ProductImage;
import kr.or.connect.reservation.dto.ProductPrice;

@Repository
@RequiredArgsConstructor
public class ProductDao {

	private final ProductMapper productMapper;
    
    public List<Product> selectProductAll(int start) {
		return productMapper.selectProductAll(start);
    }
    
    public List<Product> selectProductByCategory(int categoryId, int start) {
    	return productMapper.selectProductByCategory(categoryId, start);
    }

    public int selectCountAll() {
    	return productMapper.selectCountAll();
	}
    
    public int selectCountCategory(int categoryId) {
    	return productMapper.selectCountCategory(categoryId);
	}

    public List<ProductImage> selectProductImage(int productId) {
    	return productMapper.selectProductImage(productId);
    }
        
	public List<ProductPrice> selectProductPrice(int productId) {
		return productMapper.selectProductPrice(productId);
	}
}
