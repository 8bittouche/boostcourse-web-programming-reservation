package kr.or.connect.reservation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class DisplayInfoResponse {
	private DisplayInfo displayInfo;
	private List<ProductImage> productImages;
	private DisplayInfoImage displayInfoImage;
	private List<Comment> comments;
	private double averageScore;
	private List<ProductPrice> productPrices;
}
