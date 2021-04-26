package kr.or.connect.reservation.dto;

import java.util.List;

public class ProductResponse {
	private List<Product> items;
	private int totalCount;

	public ProductResponse(List<Product> items, int totalCount) {
		super();
		this.items = items;
		this.totalCount = totalCount;
	}

	public List<Product> getItems() {
		return items;
	}

	public int getTotalCount() {
		return totalCount;
	}

	@Override
	public String toString() {
		return "ProductResponse [items=" + items + ", totalCount=" + totalCount + "]";
	}

}
