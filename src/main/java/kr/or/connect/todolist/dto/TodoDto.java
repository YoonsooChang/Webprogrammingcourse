package kr.or.connect.todolist.dto;

public class TodoDto {
	private Long id;
	private String name;
	private String regDate;
	private int sequence;
	private String title;
	private String type;

	public TodoDto() {
		super();
	}

	private TodoDto(Long id, String type) {
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

	public TodoDto(Long id, String name, String regDate, int sequence, String title, String type) {
		super();
		this.id = id;
		this.name = name;
		this.regDate = regDate;
		this.sequence = sequence;
		this.title = title;
		this.type = type;
	}

	public static TodoDto getUpdateTodoDto(Long id, String type) {
		return new TodoDto(id, type);
	}

	public static TodoDto getAddTodoDto(String name, int sequence, String title) {
		return new TodoDto(name, sequence, title);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getRegDate() {
		return regDate;
	}

	public int getSequence() {
		return sequence;
	}

	public String getTitle() {
		return title;
	}

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", name=" + name + ", regDate=" + regDate + ", sequence=" + sequence + ", title="
			+ title + ", type=" + type + "]";
	}

}
