package kr.or.connect.todolist.dto;

public enum TodoStatus {
	TODO("DOING"), DOING("DONE"), DONE(null);

	private String nextStatus;

	private TodoStatus(String nextStatus) {
		this.nextStatus = nextStatus;
	}

	public String getNextStatus() {
		return this.nextStatus;
	}
}
