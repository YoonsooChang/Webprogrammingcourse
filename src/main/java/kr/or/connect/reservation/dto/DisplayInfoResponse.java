package kr.or.connect.reservation.dto;

import java.util.List;

public class DisplayInfoResponse {
	private double averageScore;
	private List<Comment> comments;
	private DisplayInfo displayInfo;
	private DisplayInfoImage displayInfoImage;
	private List<ProductImage> productImage;
	private List<ProductPrice> productPrice;

	public DisplayInfoResponse(double averageScore, List<Comment> comments, DisplayInfo displayInfo,
		DisplayInfoImage displayInfoImage, List<ProductImage> productImage, List<ProductPrice> productPrice) {
		super();
		this.averageScore = averageScore;
		this.comments = comments;
		this.displayInfo = displayInfo;
		this.displayInfoImage = displayInfoImage;
		this.productImage = productImage;
		this.productPrice = productPrice;
	}

	public double getAverageScore() {
		return averageScore;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public DisplayInfo getDisplayInfo() {
		return displayInfo;
	}

	public DisplayInfoImage getDisplayInfoImage() {
		return displayInfoImage;
	}

	public List<ProductImage> getProductImage() {
		return productImage;
	}

	public List<ProductPrice> getProductPrice() {
		return productPrice;
	}

	@Override
	public String toString() {
		return "DisplayInfoResponse [averageScore=" + averageScore + ", comments=" + comments + ", displayInfo="
			+ displayInfo + ", displayInfoImage=" + displayInfoImage + ", productImage=" + productImage
			+ ", productPrice=" + productPrice + "]";
	}

}
