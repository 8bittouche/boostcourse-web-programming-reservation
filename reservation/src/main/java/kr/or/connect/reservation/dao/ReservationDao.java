package kr.or.connect.reservation.dao;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.ReservationInfo;
import kr.or.connect.reservation.dto.ReservationPrice;

import static kr.or.connect.reservation.dao.ReservationDaoSqls.*;

@Repository
public class ReservationDao {
	private NamedParameterJdbcTemplate namedParameterJdbc;
	private JdbcTemplate jdbc;
	private RowMapper<ReservationInfo> reservationInfoRowMapper = BeanPropertyRowMapper.newInstance(ReservationInfo.class);
	private RowMapper<ReservationPrice> reservationPriceRowMapper = BeanPropertyRowMapper.newInstance(ReservationPrice.class);
	
	
	public ReservationDao(DataSource dataSource) {
        this.namedParameterJdbc = new NamedParameterJdbcTemplate(dataSource);
        this.jdbc = new JdbcTemplate(dataSource);
    }
	
	public List<ReservationInfo> selectReservationInfo(String reservationEmail){
		Map param = Collections.singletonMap("reservationEmail", reservationEmail);
		return namedParameterJdbc.query(SELECT_RESERVATION_INFO, param, reservationInfoRowMapper);
	}
	
	public ReservationInfo selectReservationInfoById(int reservationInfoId){
		Map param = Collections.singletonMap("reservationInfoId", reservationInfoId);
		return namedParameterJdbc.queryForObject(SELECT_RESERVATION_INFO_BY_ID, param, reservationInfoRowMapper);
	}
	
	public List<ReservationPrice> selectReservationPrice(int reservationInfoId){
		Map param = Collections.singletonMap("reservationInfoId", reservationInfoId);
		return namedParameterJdbc.query(SELECT_RESERVATION_PRICE, param, reservationPriceRowMapper);
	}
	
	
	public int insertReservationInfoPrice(ReservationPrice reservationPrice) {
		int reservationInfoId = reservationPrice.getReservationInfoId();
				
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("reservationInfoId", reservationInfoId)
													 .addValue("productPriceId", reservationPrice.getProductPriceId())
													 .addValue("count", reservationPrice.getCount());
				
		return namedParameterJdbc.update(INSERT_RESERVATION_INFO_PRICE, namedParameters);
	}
	
	
	public int insertReservationInfo(ReservationInfo reservationInfo) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("productId", reservationInfo.getProductId())
																	    .addValue("displayInfoId", reservationInfo.getDisplayInfoId())
																	    .addValue("reservationName", reservationInfo.getReservationName())
																 	    .addValue("reservationTelephone", reservationInfo.getReservationTelephone())
																 	    .addValue("reservationEmail", reservationInfo.getReservationEmail())
																	    .addValue("reservationDate", reservationInfo.getReservationDate())
																	    .addValue("createDate", new Date())
																	    .addValue("modifyDate", new Date());
		
		return namedParameterJdbc.update(INSERT_RESERVATION_INFO, namedParameters);
	}
	
	
	public int updateCancelFlag(int reservationInfoId) {
		Map param = Collections.singletonMap("reservationInfoId", reservationInfoId);
		return namedParameterJdbc.update(UPDATE_CANCEL_FLAG, param);
	}
	
	
	public int selectLastInsertId() {
		Integer identity = jdbc.queryForObject(SELECT_LAST_INSERT_ID, Integer.class);
		return identity.intValue();
	}
}
