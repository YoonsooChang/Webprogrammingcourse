package kr.or.connect.reservation.dto;

import java.util.List;

public class CategoryResponse {
	private List<Category> items;

	public CategoryResponse(List<Category> items) {
		super();
		this.items = items;
	}

	public List<Category> getItems() {
		return items;
	}

	@Override
	public String toString() {
		return "CategoryResponse [items=" + items + "]";
	}

}
