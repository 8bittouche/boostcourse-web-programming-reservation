package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.ProductDao;
import kr.or.connect.reservation.dto.ItemsAndCountResponse;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.service.ProductService;

import static kr.or.connect.reservation.constant.Constant.LIMIT_DISPLAY_NUM;
import static kr.or.connect.reservation.constant.Constant.TOTAL_LIST_ID;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductDao productDao;

	@Autowired
	public ProductServiceImpl(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	@Transactional
	public ItemsAndCountResponse<List<Product>> getProducts(int categoryId, int start) {
		ItemsAndCountResponse<List<Product>> res = new ItemsAndCountResponse<>();
		List<Product> productList;
		int totalCount;
		
		if(categoryId == TOTAL_LIST_ID) {  
			productList = productDao.selectProductAll(start, LIMIT_DISPLAY_NUM);
			totalCount = productDao.selectCountAll();
		}
		else {  
			productList = productDao.selectProductByCategory(categoryId, start, LIMIT_DISPLAY_NUM);
			totalCount = productDao.selectCountCategory(categoryId);
		}
		
		res.setTotalCount(totalCount);
		res.setItems(productList);
		
		return res;
	}
}
