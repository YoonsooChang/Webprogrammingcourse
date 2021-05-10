package kr.or.connect.reservation.dto;

import java.time.LocalDate;

public class CommentImage {
	private String contentType;
	private LocalDate createDate;
	private boolean deleteFlag;
	private int fileId;
	private String fileName;
	private int imageId;
	private LocalDate modifyDate;
	private int reservationInfoId;
	private int reservationUserCommentId;
	private String saveFileName;

	public static class Builder {
		private final int fileId;
		private final int imageId;

		private String contentType = "";
		private LocalDate createDate = null;
		private boolean deleteFlag = false;
		private String fileName = "";
		private LocalDate modifyDate = null;
		private int reservationInfoId = 0;
		private int reservationUserCommentId = 0;
		private String saveFileName = "";

		public Builder(int imageid, int fileId) {
			this.imageId = imageid;
			this.fileId = fileId;
		}

		public Builder createDate(LocalDate val) {
			createDate = val;
			return this;
		}

		public Builder deleteFlag(boolean val) {
			deleteFlag = val;
			return this;
		}

		public Builder modifyDate(LocalDate val) {
			modifyDate = val;
			return this;
		}

		public Builder fileName(String val) {
			fileName = val;
			return this;
		}

		public Builder contentType(String val) {
			contentType = val;
			return this;
		}

		public Builder reservationInfoId(int val) {
			reservationInfoId = val;
			return this;
		}

		public Builder reservationUserCommentId(int val) {
			reservationUserCommentId = val;
			return this;
		}

		public Builder saveFileName(String val) {
			saveFileName = val;
			return this;
		}

		public CommentImage build() {
			return new CommentImage(this);
		}
	}

	public CommentImage(Builder builder) {
		contentType = builder.contentType;
		createDate = builder.createDate;
		deleteFlag = builder.deleteFlag;
		fileId = builder.fileId;
		fileName = builder.fileName;
		imageId = builder.imageId;
		modifyDate = builder.modifyDate;
		reservationInfoId = builder.reservationInfoId;
		reservationUserCommentId = builder.reservationUserCommentId;
		saveFileName = builder.saveFileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public LocalDate getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(LocalDate modifyDate) {
		this.modifyDate = modifyDate;
	}

	public int getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public int getReservationUserCommentId() {
		return reservationUserCommentId;
	}

	public void setReservationUserCommentId(int reservationUserCommentId) {
		this.reservationUserCommentId = reservationUserCommentId;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	@Override
	public String toString() {
		return "CommentImage [contentType=" + contentType + ", createDate=" + createDate + ", deleteFlag=" + deleteFlag
			+ ", fileId=" + fileId + ", fileName=" + fileName + ", imageId=" + imageId + ", modifyDate=" + modifyDate
			+ ", reservationInfoId=" + reservationInfoId + ", reservationUserCommentId=" + reservationUserCommentId
			+ ", saveFileName=" + saveFileName + "]";
	}

}
