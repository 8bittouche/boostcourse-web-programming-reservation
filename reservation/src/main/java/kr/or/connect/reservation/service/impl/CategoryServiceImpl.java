package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.CategoryDao;
import kr.or.connect.reservation.dao.ProductDao;
import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.ItemsAndCountResponse;
import kr.or.connect.reservation.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryDao categoryDao;
	private final ProductDao productDao;

	@Autowired
	public CategoryServiceImpl(CategoryDao categoryDao, ProductDao productDao) {
		this.categoryDao = categoryDao;
		this.productDao = productDao;
	}

	@Override
	@Transactional
	public ItemsAndCountResponse<List<Category>> getCategories() {
		ItemsAndCountResponse<List<Category>> res = new ItemsAndCountResponse<>();
		
		List<Category> categoryList = categoryDao.selectCategoryAll();
		int totalCount = productDao.selectCountAll();
		
		res.setTotalCount(totalCount);
		res.setItems(categoryList);
				
		return res;
	}
}
