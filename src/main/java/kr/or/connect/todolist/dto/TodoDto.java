package kr.or.connect.todolist.dto;

import java.time.LocalDate;

public class TodoDto {
	private Long id;
	private String name;
	private LocalDate regDate;
	private int sequence;
	private String title;
	private TodoStatus type;

	public TodoDto() {
		super();
	}

	private TodoDto(Long id, TodoStatus type) {
		super();
		this.id = id;
		this.type = type;
	}

	private TodoDto(String name, int sequence, String title) {
		super();
		this.name = name;
		this.sequence = sequence;
		this.title = title;
	}

	public TodoDto(Long id, String name, LocalDate regDate, int sequence, String title, TodoStatus type) {
		super();
		this.id = id;
		this.name = name;
		this.regDate = regDate;
		this.sequence = sequence;
		this.title = title;
		this.type = type;
	}

	public static TodoDto createUpdateTodoDto(Long id, TodoStatus type) {
		return new TodoDto(id, type);
	}

	public static TodoDto createAddTodoDto(String name, int sequence, String title) {
		return new TodoDto(name, sequence, title);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LocalDate getRegDate() {
		return regDate;
	}

	public int getSequence() {
		return sequence;
	}

	public String getTitle() {
		return title;
	}

	public TodoStatus getType() {
		return type;
	}

	@Override
	public String toString() {
		return "TodoDto [id=" + id + ", name=" + name + ", regDate=" + regDate + ", sequence=" + sequence + ", title="
			+ title + ", type=" + type + "]";
	}

}
