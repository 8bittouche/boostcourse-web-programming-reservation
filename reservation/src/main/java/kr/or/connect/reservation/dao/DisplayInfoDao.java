package kr.or.connect.reservation.dao;

import kr.or.connect.reservation.dao.mapper.DisplayInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.DisplayInfo;
import kr.or.connect.reservation.dto.DisplayInfoImage;

@Repository
@RequiredArgsConstructor
public class DisplayInfoDao {

	private final DisplayInfoMapper displayInfoMapper;

	public DisplayInfo selectDisplayInfo(int displayInfoId) {
		return displayInfoMapper.selectDisplayInfo(displayInfoId);
	}

	public DisplayInfoImage selectDisplayInfoImage(int displayInfoId) {
		return displayInfoMapper.selectDisplayInfoImage(displayInfoId);
	}
}
