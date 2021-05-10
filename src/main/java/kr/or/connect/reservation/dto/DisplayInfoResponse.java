package kr.or.connect.reservation.dto;

import java.util.ArrayList;
import java.util.List;

public class DisplayInfoResponse {
	private List<Comment> comments;
	private DisplayInfo displayInfo;
	private DisplayInfoImage displayInfoImage;
	private List<ProductImage> productImages;
	private List<ProductPrice> productPrices;

	public static class Builder {
		private List<Comment> comments = new ArrayList<>();
		private final DisplayInfo displayInfo;
		private DisplayInfoImage displayInfoImage = new DisplayInfoImage();
		private final List<ProductImage> productImages;
		private final List<ProductPrice> productPrices;

		public Builder(DisplayInfo displayInfo, List<ProductImage> productImages, List<ProductPrice> productPrices) {
			this.displayInfo = displayInfo;
			this.productImages = productImages;
			this.productPrices = productPrices;
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
		displayInfo = builder.displayInfo;
		displayInfoImage = builder.displayInfoImage;
		productImages = builder.productImages;
		productPrices = builder.productPrices;
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
		return "DisplayInfoResponse [comments=" + comments + ", displayInfo="
			+ displayInfo + ", displayInfoImage=" + displayInfoImage + ", productImage=" + productImages
			+ ", productPrice=" + productPrices + "]";
	}

}
