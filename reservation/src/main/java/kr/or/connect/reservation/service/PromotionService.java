package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.ItemsAndCountResponse;
import kr.or.connect.reservation.dto.Promotion;

public interface PromotionService {
	public ItemsAndCountResponse<List<Promotion>> getPromotions();
}
