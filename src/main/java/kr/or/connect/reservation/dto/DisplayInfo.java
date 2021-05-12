package kr.or.connect.reservation.dto;

import java.time.LocalDate;

public class DisplayInfo {
	private int categoryId;
	private String categoryName;
	private LocalDate createDate;
	private int displayInfoId;
	private String email;
	private String homepage;
	private LocalDate modifyDate;
	private String openingHours;
	private String placeLot;
	private String placeName;
	private String placeStreet;
	private String productContent;
	private String productDescription;
	private String productEvent;
	private int productId;
	private String telephone;

	public static class Builder {
		private final int categoryId;
		private final int displayInfoId;
		private final int productId;

		private LocalDate createDate;
		private LocalDate modifyDate;

		private String categoryName;
		private String email;
		private String homepage;
		private String openingHours;
		private String placeLot;
		private String placeName;
		private String placeStreet;
		private String productContent;
		private String productDescription;
		private String productEvent;
		private String telephone;

		public Builder(int categoryId, int displayInfoId, int productId) {
			this.categoryId = categoryId;
			this.displayInfoId = displayInfoId;
			this.productId = productId;
		}

		public Builder createDate(LocalDate date) {
			createDate = date;
			return this;
		}

		public Builder modifyDate(LocalDate date) {
			modifyDate = date;
			return this;
		}

		public Builder categoryName(String name) {
			categoryName = name;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder homepage(String homepage) {
			this.homepage = homepage;
			return this;
		}

		public Builder openingHours(String openingHours) {
			this.openingHours = openingHours;
			return this;
		}

		public Builder placeLot(String placeLot) {
			this.placeLot = placeLot;
			return this;
		}

		public Builder placeName(String placeName) {
			this.placeName = placeName;
			return this;
		}

		public Builder placeStreet(String placeStreet) {
			this.placeStreet = placeStreet;
			return this;
		}

		public Builder productContent(String productContent) {
			this.productContent = productContent;
			return this;
		}

		public Builder productDescription(String productDescription) {
			this.productDescription = productDescription;
			return this;
		}

		public Builder productEvent(String productEvent) {
			this.productEvent = productEvent;
			return this;
		}

		public Builder telephone(String telephone) {
			this.telephone = telephone;
			return this;
		}

		public DisplayInfo build() {
			return new DisplayInfo(this);
		}
	}

	public DisplayInfo() {
		super();
	}

	public DisplayInfo(Builder builder) {
		categoryId = builder.categoryId;
		categoryName = builder.categoryName;
		createDate = builder.createDate;
		displayInfoId = builder.displayInfoId;
		email = builder.email;
		homepage = builder.homepage;
		modifyDate = builder.modifyDate;
		openingHours = builder.openingHours;
		placeLot = builder.placeLot;
		placeName = builder.placeName;
		placeStreet = builder.placeStreet;
		productContent = builder.productContent;
		productDescription = builder.productDescription;
		productEvent = builder.productEvent;
		productId = builder.productId;
		telephone = builder.telephone;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public int getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public LocalDate getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(LocalDate modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
	}

	public String getPlaceLot() {
		return placeLot;
	}

	public void setPlaceLot(String placeLot) {
		this.placeLot = placeLot;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getPlaceStreet() {
		return placeStreet;
	}

	public void setPlaceStreet(String placeStreet) {
		this.placeStreet = placeStreet;
	}

	public String getProductContent() {
		return productContent;
	}

	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductEvent() {
		return productEvent;
	}

	public void setProductEvent(String productEvent) {
		this.productEvent = productEvent;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Override
	public String toString() {
		return "DisplayInfo [categoryId=" + categoryId + ", categoryName=" + categoryName + ", createDate=" + createDate
			+ ", displayInfoId=" + displayInfoId + ", email=" + email + ", homepage=" + homepage + ", modifyDate="
			+ modifyDate + ", openingHours=" + openingHours + ", placeLot=" + placeLot + ", placeName=" + placeName
			+ ", placeStreet=" + placeStreet + ", productContent=" + productContent + ", productDescription="
			+ productDescription + ", productEvent=" + productEvent + ", productId=" + productId + ", telephone="
			+ telephone + "]";
	}

}
