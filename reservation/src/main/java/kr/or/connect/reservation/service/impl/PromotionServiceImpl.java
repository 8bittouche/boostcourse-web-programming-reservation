package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.PromotionDao;
import kr.or.connect.reservation.dto.ItemsAndCountResponse;
import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService {

	@Autowired
	private PromotionDao promotionDao;
	
	@Override
	public ItemsAndCountResponse<List<Promotion>> getPromotions() {
		ItemsAndCountResponse<List<Promotion>> res = new ItemsAndCountResponse<>();
		List<Promotion> promotionList = promotionDao.selectAll();
		int totalCount = promotionList.size();
		
		res.setTotalCount(totalCount);
		res.setItems(promotionList);
		
		return res;
	}
}
