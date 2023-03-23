package kr.or.connect.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.DisplayInfoResponse;
import kr.or.connect.reservation.dto.ItemsAndCountResponse;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.service.DisplayInfoService;
import kr.or.connect.reservation.service.ProductService;

@RestController
@RequestMapping(path="/api/products")
public class ProductApiController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private DisplayInfoService displayInfoService;
	
	@RequestMapping
	public ItemsAndCountResponse<List<Product>> products(@RequestParam(name="categoryId", required=false, defaultValue="0") int categoryId,
								 @RequestParam(name="start", required=false, defaultValue="0") int start) {
				
		return productService.getProducts(categoryId, start);
	}
	
	@RequestMapping(path="/{displayInfoId}")
	public DisplayInfoResponse displayInfo(@PathVariable(name="displayInfoId", required=true) int displayInfoId) {
		return displayInfoService.getDisplayInfoResponse(displayInfoId);
	}
}
