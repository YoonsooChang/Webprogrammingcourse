package kr.or.connect.reservation.dto;

import java.util.List;

public class CommentResponse {
	private double averageScore;
	private List<Comment> item;

	public CommentResponse(double averageScore, List<Comment> item) {
		super();
		this.averageScore = averageScore;
		this.item = item;
	}

	public double getAverageScore() {
		return averageScore;
	}

	public List<Comment> getItem() {
		return item;
	}

	@Override
	public String toString() {
		return "CommentResponse [averageScore=" + averageScore + ", item=" + item + "]";
	}

}
