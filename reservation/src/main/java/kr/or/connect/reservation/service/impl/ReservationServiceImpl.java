package kr.or.connect.reservation.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.DisplayInfoDao;
import kr.or.connect.reservation.dao.ReservationDao;
import kr.or.connect.reservation.dto.DisplayInfo;
import kr.or.connect.reservation.dto.ReservationInfo;
import kr.or.connect.reservation.dto.ReservationInfoResponse;
import kr.or.connect.reservation.dto.ReservationParam;
import kr.or.connect.reservation.dto.ReservationPrice;
import kr.or.connect.reservation.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ReservationDao reservationDao;
	
	@Autowired
	private DisplayInfoDao displayInfoDao;
		
		
	@Override
	public ReservationInfoResponse getReservations(String reservationEmail) {
		ReservationInfoResponse res = new ReservationInfoResponse();
		
		int size;
		List<ReservationInfo> reservations = reservationDao.selectReservationInfo(reservationEmail)
										.stream()
										.map(reservation -> setDisplayInfo(reservation))
										.map(reservation -> setTotalPrice(reservation))
										.collect(Collectors.toList());
		
		size = reservations.size();
		
		res.setReservations(reservations);
		res.setSize(size);
		
		return res;
	}
	
	
	@Override
	@Transactional
	public boolean addReservation(ReservationParam reservationParam) {
			
		ReservationInfo reservationInfo = new ReservationInfo();
		
		int productId = reservationParam.getProductId();
		int displayInfoId = reservationParam.getDisplayInfoId();
		String reservationName = reservationParam.getReservationName();
		String reservationTelephone = reservationParam.getReservationTelephone();
		String reservationEmail = reservationParam.getReservationEmail();
		String reservationDate = reservationParam.getReservationYearMonthDay();
		List<ReservationPrice> prices = reservationParam.getPrices();
		
		reservationInfo.setProductId(productId);
		reservationInfo.setDisplayInfoId(displayInfoId);
		reservationInfo.setReservationName(reservationName);
		reservationInfo.setReservationTelephone(reservationTelephone);
		reservationInfo.setReservationEmail(reservationEmail);
		reservationInfo.setReservationDate(reservationDate);
		
		try {
			reservationDao.insertReservationInfo(reservationInfo);
		}catch(Exception e) {
			logger.debug("exception msg", e);
			return false;
		}
		
		int reservationInfoId = reservationDao.selectLastInsertId();
		for(ReservationPrice reservationPrice : prices) {
			reservationPrice.setReservationInfoId(reservationInfoId);
			
			try{
				reservationDao.insertReservationInfoPrice(reservationPrice);
			}catch(Exception e) {
				logger.debug("exception msg", e);
				return false;
			}
						
			int reservationInfoPriceId = reservationDao.selectLastInsertId();
			reservationPrice.setReservationInfoPriceId(reservationInfoPriceId);
		}
		
		return true;
	}
	
	
	@Override
	public void cancelReservationInfo(int reservInfoId) {
		reservationDao.updateCancelFlag(reservInfoId);
	}

		
	private ReservationInfo setDisplayInfo(ReservationInfo reservation) {
		int displayInfoId = reservation.getDisplayInfoId();
		DisplayInfo displayInfo = displayInfoDao.selectDisplayInfo(displayInfoId);
		reservation.setDisplayInfo(displayInfo);
		
		return reservation;
	}
	
	private ReservationInfo setTotalPrice(ReservationInfo reservation) {
		int totalPrice = 0;
		int reservInfoId = reservation.getReservationInfoId();
		List<ReservationPrice> reservationPriceList = reservationDao.selectReservationPrice(reservInfoId);
		for(ReservationPrice reservationPrice : reservationPriceList) {
			totalPrice += reservationPrice.getPrice();
		}
		
		reservation.setTotalPrice(totalPrice);
		
		return reservation;
	}
}
