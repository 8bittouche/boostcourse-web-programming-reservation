package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.ProductDao;
import kr.or.connect.reservation.dto.ItemsAndCountResponse;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	
	private static final int TOTAL_LIST_ID = 0;
	
	@Override
	@Transactional
	public ItemsAndCountResponse<List<Product>> getProducts(int categoryId, int start) {
		ItemsAndCountResponse<List<Product>> res = new ItemsAndCountResponse<>();
		List<Product> productList;
		int totalCount;
		
		if(categoryId == TOTAL_LIST_ID) {  
			productList = productDao.selectProductAll(start);
			totalCount = productDao.selectCountAll();
		}
		else {  
			productList = productDao.selectProductByCategory(categoryId, start);
			totalCount = productDao.selectCountCategory(categoryId);
		}
		
		res.setTotalCount(totalCount);
		res.setItems(productList);
		
		return res;
	}
}
