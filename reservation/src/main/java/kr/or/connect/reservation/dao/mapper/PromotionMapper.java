package kr.or.connect.reservation.dao.mapper;

import kr.or.connect.reservation.dto.Promotion;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PromotionMapper {
    List<Promotion> selectAll();
}
