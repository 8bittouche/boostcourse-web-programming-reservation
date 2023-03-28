package kr.or.connect.reservation.dao;

import java.util.List;

import kr.or.connect.reservation.dao.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.ReservationInfo;
import kr.or.connect.reservation.dto.ReservationPrice;

@Repository
@RequiredArgsConstructor
public class ReservationDao {

	private final ReservationMapper reservationMapper;

	public List<ReservationInfo> selectReservationInfo(String reservationEmail){
		return reservationMapper.selectReservationInfo(reservationEmail);
	}
	
	public ReservationInfo selectReservationInfoById(int reservationInfoId){
		return reservationMapper.selectReservationInfoById(reservationInfoId);
	}
	
	public List<ReservationPrice> selectReservationPrice(int reservationInfoId){
		return reservationMapper.selectReservationPrice(reservationInfoId);
	}

	public int insertReservationInfoPrice(ReservationPrice reservationPrice) {
		return reservationMapper.insertReservationInfoPrice(reservationPrice);
	}

	public int insertReservationInfo(ReservationInfo reservationInfo) {
		return reservationMapper.insertReservationInfo(reservationInfo);
	}

	public int updateCancelFlag(int reservationInfoId) {
		return reservationMapper.updateCancelFlag(reservationInfoId);
	}

	public int selectLastInsertId() {
		return reservationMapper.selectLastInsertId();
	}
}
