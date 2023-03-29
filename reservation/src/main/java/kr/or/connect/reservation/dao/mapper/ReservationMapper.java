package kr.or.connect.reservation.dao.mapper;

import kr.or.connect.reservation.dto.ReservationInfo;
import kr.or.connect.reservation.dto.ReservationPrice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {
    List<ReservationInfo> selectReservationInfo(String reservationEmail);
    ReservationInfo selectReservationInfoById(int reservationInfoId);
    List<ReservationPrice> selectReservationPrice(int reservationInfoId);
    int insertReservationInfoPrice(ReservationPrice reservationPrice);
    int insertReservationInfo(ReservationInfo reservationInfo);
    int updateCancelFlag(int reservationInfoId);
    int selectLastInsertId();
}
