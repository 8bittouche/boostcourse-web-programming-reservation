package kr.or.connect.reservation.dao;

import java.util.List;

import kr.or.connect.reservation.dao.mapper.PromotionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Promotion;

@Repository
@RequiredArgsConstructor
public class PromotionDao {

	private final PromotionMapper promotionMapper;

    public List<Promotion> selectAll() {
		return promotionMapper.selectAll();
    }
}
