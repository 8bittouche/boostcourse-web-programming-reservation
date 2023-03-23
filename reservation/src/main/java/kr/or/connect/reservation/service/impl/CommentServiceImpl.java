package kr.or.connect.reservation.service.impl;

import static kr.or.connect.reservation.constant.Constant.ROOT_FOLDER;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.reservation.dao.CommentDao;
import kr.or.connect.reservation.dto.Comment;
import kr.or.connect.reservation.dto.CommentImage;
import kr.or.connect.reservation.service.CommentService;



@Service
public class CommentServiceImpl implements CommentService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final int REVIEW_MAX_LENGTH = 400;
	private final static Set<String> POSSIBLE_FILE_EXTENSION_SET = Collections.unmodifiableSet(new HashSet<String>(Arrays.asList("png", "jpg", "jpeg")));
	
	
	@Autowired
	CommentDao commentDao;
	
	@Override
	@Transactional
	public boolean registerCommentInfo(int productId, int reservationInfoId, String comment, int score, MultipartFile reviewImageFile) {
		
		if(addComment(productId, reservationInfoId, comment, score)==false) {  
			return false;
		}
		
		if(reviewImageFile == null) {
			return true;
		}
		
		int reservationUserCommentId = commentDao.selectLastInsertId();
		
		
		if(addFileInfo(reviewImageFile)==false) {
			return false;
		}		
		int fileInfoId = commentDao.selectLastInsertId();
		
		
		if(addCommentImage(reservationUserCommentId, fileInfoId, reservationInfoId)==false) {
			return false;
		}
		int reservationUserCommentImageId = commentDao.selectLastInsertId();
				
		
		try {
			uploadFile(reviewImageFile);
		}catch(Exception e) {
			commentDao.deleteCommentImage(reservationUserCommentImageId);
			commentDao.deleteComment(reservationUserCommentId);
			commentDao.deleteFileInfo(fileInfoId);
			logger.debug("exception msg", e);
			return false;
		}
		
		return true;
	}
	
	
	@Override
	public String getSaveFileName(int reservationUserCommentImageId) {
		return commentDao.selectSaveFileName(reservationUserCommentImageId);
	}
	
	
	private boolean addComment(int productId, int reservationInfoId, String comment, int score) {
		Comment commentDto = new Comment();
		
		String validComment = validateCommentLength(comment);
		if(validComment == null) {
			return false;
		}
		else {
			commentDto.setComment(validComment);
		}
		
		commentDto.setProductId(productId);
		commentDto.setReservationInfoId(reservationInfoId);
		commentDto.setScore(score);
		
		try {
			commentDao.insertComment(commentDto);
		}catch(Exception e) {
			logger.debug("exception msg", e);
			return false;
		}
		
		return true;
	}
	
	private boolean addFileInfo(MultipartFile reviewImageFile) {
		CommentImage commentImage = new CommentImage();
		
		commentImage.setFileName(reviewImageFile.getOriginalFilename());
		commentImage.setSaveFileName(reviewImageFile.getOriginalFilename());
		commentImage.setContentType(reviewImageFile.getContentType());
		commentImage.setDeleteFlag(false);
		
		try {
			commentDao.insertFileInfo(commentImage);
		}catch(Exception e) {
			logger.debug("exception msg", e);
			return false;
		}
		
		return true;
	}
	
	private boolean addCommentImage(int reservationUserCommentId, int fileInfoId, int reservationInfoId) {
		CommentImage commentImage = new CommentImage();
		
		commentImage.setReservationUserCommentId(reservationUserCommentId);
		commentImage.setFileId(fileInfoId);
		commentImage.setReservationInfoId(reservationInfoId);
		
		try {
			commentDao.insertCommentImage(commentImage);
		}catch(Exception e) {
			logger.debug("exception msg", e);
			return false;
		}
		
		return true;
	}
	
	private boolean uploadFile(MultipartFile reviewImageFile) {
		try{
			String originalFileName = reviewImageFile.getOriginalFilename();
			if(validateUploadFileName(originalFileName)==false) {
				return false;
			}
			
			int fileIdx=2;
			int lastDotIndex = originalFileName.lastIndexOf(".");
			String fileName = originalFileName.substring(0, lastDotIndex);
			String fileExtension = originalFileName.substring(lastDotIndex+1);
			File serverFile = new File(ROOT_FOLDER + fileName + "." + fileExtension);
			
			while(serverFile.exists()) {
				serverFile = new File(ROOT_FOLDER + fileName + "(" + fileIdx + ")." + fileExtension);
				fileIdx++;
			}
			
			try(FileOutputStream fos = new FileOutputStream(ROOT_FOLDER + serverFile.getName());
				InputStream is = reviewImageFile.getInputStream()){
				
				int readCount = 0;
		        byte[] buffer = new byte[1024];
		        while((readCount = is.read(buffer)) != -1){
		            fos.write(buffer,0,readCount);
		        }
			}catch(IOException e) {
				throw new IOException();
			}
				      
        }catch(Exception ex){
            throw new RuntimeException("file Save Error");
        }
		
		return true;
	}
	
	
	private String validateCommentLength(String comment) {
		int commentLength = comment.length();
		if(5 <= commentLength && commentLength <= REVIEW_MAX_LENGTH) {
			return comment;
		}
		else if(commentLength > REVIEW_MAX_LENGTH){
			return comment.substring(0, REVIEW_MAX_LENGTH);
		}
		else {
			return null;
		}
	}
	
	private boolean validateUploadFileName(String originalFileName) {
		if(originalFileName == null) {
			return false;
		}
		
		CharSequence[] exChar = {":", "\\", "/", "*", "?", "<", ">", "|"};
		for(CharSequence c : exChar) {
			if(originalFileName.contains(c)) {
				return false;
			}
		}
		
		String originalFileNameLowerCase = originalFileName.toLowerCase();
		String fileExtenstion = Stream.of(originalFileNameLowerCase.split("."))
								      .map (s -> new String(s))
								      .reduce((a, b) -> b)
								      .orElse(null);
		
		if (POSSIBLE_FILE_EXTENSION_SET.contains(fileExtenstion)) {
			return false;
		}
		
		return true;
	}
}
