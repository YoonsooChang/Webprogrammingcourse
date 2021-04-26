package kr.or.connect.reservation.dto;

public class Promotion {
	private int id;
	private int productId;
	private int productImageId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getProductImageId() {
		return productImageId;
	}

	public void setProductImageId(int productImageId) {
		this.productImageId = productImageId;
	}

	@Override
	public String toString() {
		return "Promotion [id=" + id + ", productId=" + productId + ", productImageId=" + productImageId + "]";
	}

}
