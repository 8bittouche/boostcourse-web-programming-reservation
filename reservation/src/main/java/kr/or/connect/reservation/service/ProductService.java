package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.ItemsAndCountResponse;
import kr.or.connect.reservation.dto.Product;

public interface ProductService {
	ItemsAndCountResponse<List<Product>> getProducts(int categoryId, int start);
}
