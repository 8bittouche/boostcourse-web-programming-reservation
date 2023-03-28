package kr.or.connect.reservation.dao;

import java.util.List;

import kr.or.connect.reservation.dao.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Category;

@Repository
@RequiredArgsConstructor
public class CategoryDao {

	private final CategoryMapper categoryMapper;

	public List<Category> selectCategoryAll() {
		return categoryMapper.selectCategoryAll();
	}
}
