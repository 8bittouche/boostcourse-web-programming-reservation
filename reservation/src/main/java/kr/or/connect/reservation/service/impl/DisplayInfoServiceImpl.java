package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.CommentDao;
import kr.or.connect.reservation.dao.DisplayInfoDao;
import kr.or.connect.reservation.dao.ProductDao;
import kr.or.connect.reservation.dto.Comment;
import kr.or.connect.reservation.dto.CommentImage;
import kr.or.connect.reservation.dto.DisplayInfo;
import kr.or.connect.reservation.dto.DisplayInfoImage;
import kr.or.connect.reservation.dto.DisplayInfoResponse;
import kr.or.connect.reservation.dto.ProductImage;
import kr.or.connect.reservation.dto.ProductPrice;
import kr.or.connect.reservation.service.DisplayInfoService;

@Service
public class DisplayInfoServiceImpl implements DisplayInfoService {

	@Autowired
	private DisplayInfoDao displayInfoDao;
		
	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private ProductDao productDao;
	
	
	@Override
	@Transactional
	public DisplayInfoResponse getDisplayInfoResponse(int displayInfoId) {
		DisplayInfoResponse res = new DisplayInfoResponse();
		
		DisplayInfo displayInfo;
		List<ProductImage> productImages;
		DisplayInfoImage displayInfoImage;
		List<Comment> comments;
		double averageScore;
		List<ProductPrice> productPrices;
		
		
		displayInfo = displayInfoDao.selectDisplayInfo(displayInfoId);
		displayInfoImage = displayInfoDao.selectDisplayInfoImage(displayInfoId);
		
		int productId = displayInfo.getProductId();
		productImages = productDao.selectProductImage(productId);
		
		comments = commentDao.selectComment(productId);
		// 각 코멘트마다 코멘트이미지 세팅
		for(Comment comment : comments) {
			int commentId = comment.getCommentId();
			List<CommentImage> commentImages = commentDao.selectCommentImage(commentId);
			comment.setCommentImages(commentImages);
		}
				
		averageScore = commentDao.selectAverageScore(productId);
		productPrices = productDao.selectProductPrice(productId);
				
		
		res.setDisplayInfo(displayInfo);
		res.setProductImages(productImages);
		res.setDisplayInfoImage(displayInfoImage);
		res.setComments(comments);
		res.setAverageScore(averageScore);
		res.setProductPrices(productPrices);
		
		return res;
	}
}
