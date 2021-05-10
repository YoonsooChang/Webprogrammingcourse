package kr.or.connect.reservation.dto;

import java.util.ArrayList;
import java.util.List;

public class DisplayInfoResponse {
	private int commentTotalCount;
	private double averageScore;
	private List<Comment> comments;
	private DisplayInfo displayInfo;
	private DisplayInfoImage displayInfoImage;
	private List<ProductImage> productImages;
	private List<ProductPrice> productPrices;

	public static class Builder {
		private List<Comment> comments = new ArrayList<>();
		private final DisplayInfo displayInfo;
		private DisplayInfoImage displayInfoImage = new DisplayInfoImage();
		private double averageScore = 0.0;
		private int commentTotalCount = 0;
		private final List<ProductImage> productImages;
		private final List<ProductPrice> productPrices;

		public Builder(DisplayInfo displayInfo, List<ProductImage> productImages, List<ProductPrice> productPrices) {
			this.displayInfo = displayInfo;
			this.productImages = productImages;
			this.productPrices = productPrices;
		}

		public Builder averageScore(double score) {
			this.averageScore = score;
			return this;
		}

		public Builder commentTotalCount(int count) {
			this.commentTotalCount = count;
			return this;
		}

		public Builder commentList(List<Comment> list) {
			comments = list;
			return this;
		}

		public Builder displayInfoImage(DisplayInfoImage image) {
			displayInfoImage = image;
			return this;
		}

		public DisplayInfoResponse build() {
			return new DisplayInfoResponse(this);
		}
	}

	public DisplayInfoResponse(Builder builder) {
		comments = builder.comments;
		averageScore = builder.averageScore;
		commentTotalCount = builder.commentTotalCount;
		displayInfo = builder.displayInfo;
		displayInfoImage = builder.displayInfoImage;
		productImages = builder.productImages;
		productPrices = builder.productPrices;
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
