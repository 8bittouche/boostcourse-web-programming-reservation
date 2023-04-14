package kr.or.connect.reservation.controller;

import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kr.or.connect.reservation.service.CommentService;
import static kr.or.connect.reservation.constant.Constant.ROOT_FOLDER;

@Controller
public class ImageDownloadController {
	
	private final CommentService commentService;

	@Autowired
	public ImageDownloadController(CommentService commentService) {
		this.commentService = commentService;
	}

	@GetMapping("/download/{folderName}/{fileName:.+}")
	public void imageDownload(@PathVariable(name="folderName") String folderName, 
						      @PathVariable(name="fileName") String fileName, HttpServletResponse response) {
		
		int lastDotIndex = fileName.lastIndexOf(".");
		String fileExtension = fileName.substring(lastDotIndex+1);
		String saveFileName = ROOT_FOLDER + folderName + "/" + fileName; 
		String contentType = "image/" + fileExtension;
				
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Type", contentType);
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");
        
        try(
            FileInputStream fis = new FileInputStream(saveFileName);
            OutputStream out = response.getOutputStream();
	    ){
    	    int readCount = 0;
    	    byte[] buffer = new byte[1024];
    	    while((readCount = fis.read(buffer)) != -1){
        		out.write(buffer,0,readCount);
    	    }
        }catch(Exception ex){
            throw new RuntimeException("File Download Error");
        }
	}
	
	@GetMapping("/download/{reservationUserCommentImageId}")
	public void reservationUserCommentImageDownload(@PathVariable(name="reservationUserCommentImageId") int reservationUserCommentImageId, 
																									HttpServletResponse response) {
		
		String fileName = commentService.getFileName(reservationUserCommentImageId);
		int lastDotIndex = fileName.lastIndexOf(".");
		String fileExtension = fileName.substring(lastDotIndex+1);
		String saveFileName = ROOT_FOLDER + fileName;
		String contentType = "image/" + fileExtension;
				
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Type", contentType);
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");
        
        try(
            FileInputStream fis = new FileInputStream(saveFileName);
            OutputStream out = response.getOutputStream();
	    ){
    	    int readCount = 0;
    	    byte[] buffer = new byte[1024];
    	    while((readCount = fis.read(buffer)) != -1){
        		out.write(buffer,0,readCount);
    	    }
        }catch(Exception ex){
            throw new RuntimeException("File Download Error");
        }
	}
}
