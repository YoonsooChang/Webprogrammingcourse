package kr.or.connect.reservation.dto;

import java.time.LocalDateTime;

public class FileParam {
	private String fileName;
	private String saveFileName;
	private String contentType;
	private int deleteFlag;
	private LocalDateTime modifyDate;
	private LocalDateTime createDate;

	public static class Builder {
		private String fileName;
		private String saveFileName;
		private String contentType;
		private int deleteFlag;
		private LocalDateTime modifyDate;
		private LocalDateTime createDate;

		public Builder() {
			super();
		}

		public Builder fileName(String fileName) {
			this.fileName = fileName;
			return this;
		}

		public Builder saveFileName(String saveFileName) {
			this.saveFileName = saveFileName;
			return this;
		}

		public Builder contentType(String contentType) {
			this.contentType = contentType;
			return this;
		}

		public Builder deleteFlag(int deleteFlag) {
			this.deleteFlag = deleteFlag;
			return this;
		}

		public Builder createDate(LocalDateTime time) {
			createDate = time;
			return this;
		}

		public Builder modifyDate(LocalDateTime time) {
			modifyDate = time;
			return this;
		}

		public FileParam build() {
			return new FileParam(this);
		}

	}

	public FileParam() {
		super();
	}

	public FileParam(Builder builder) {
		fileName = builder.fileName;
		saveFileName = builder.saveFileName;
		contentType = builder.contentType;
		deleteFlag = builder.deleteFlag;
		modifyDate = builder.modifyDate;
		createDate = builder.createDate;
	}

	public String getFileName() {
		return fileName;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public String getContentType() {
		return contentType;
	}

	public int getDeleteFlag() {
		return deleteFlag;
	}

	public LocalDateTime getModifyDate() {
		return modifyDate;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public void setModifyDate(LocalDateTime modifyDate) {
		this.modifyDate = modifyDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

}
