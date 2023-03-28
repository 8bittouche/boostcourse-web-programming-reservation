package kr.or.connect.reservation.dao.mapper;

import kr.or.connect.reservation.dto.DisplayInfo;
import kr.or.connect.reservation.dto.DisplayInfoImage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DisplayInfoMapper {
    DisplayInfo selectDisplayInfo(int displayInfoId);
    DisplayInfoImage selectDisplayInfoImage(int displayInfoId);
}
