package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.ItemsAndCountResponse;

public interface CategoryService {
	public ItemsAndCountResponse<List<Category>> getCategories();
}
