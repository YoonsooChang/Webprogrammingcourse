package kr.or.connect.reservation.dto;

import java.util.List;

public class DisplayInfoResponse {
	private int commentTotalCount;
	private double averageScore;
	private List<Comment> comments;
	private DisplayInfo displayInfo;
	private DisplayInfoImage displayInfoImage;
	private List<ProductImage> productImages;
	private List<ProductPrice> productPrices;

	public DisplayInfoResponse(int commentTotalCount, double averageScore, List<Comment> comments,
		DisplayInfo displayInfo, DisplayInfoImage displayInfoImage, List<ProductImage> productImages,
		List<ProductPrice> productPrices) {
		super();
		this.commentTotalCount = commentTotalCount;
		this.averageScore = averageScore;
		this.comments = comments;
		this.displayInfo = displayInfo;
		this.displayInfoImage = displayInfoImage;
		this.productImages = productImages;
		this.productPrices = productPrices;
	}

	public int getCommentTotalCount() {
		return commentTotalCount;
	}

	public double getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(double averageScore) {
		this.averageScore = averageScore;
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

	public List<ProductImage> getProductImages() {
		return productImages;
	}

	public List<ProductPrice> getProductPrices() {
		return productPrices;
	}

	@Override
	public String toString() {
		return "DisplayInfoResponse [commentTotalCount=" + commentTotalCount + ", averageScore=" + averageScore
			+ ", comments=" + comments + ", displayInfo=" + displayInfo + ", displayInfoImage=" + displayInfoImage
			+ ", productImages=" + productImages + ", productPrices=" + productPrices + "]";
	}

}
