package kr.or.connect.reservation.dto;

import java.time.LocalDateTime;

public class ProductImage {
	private String contentType;
	private LocalDateTime createDate;
	private Boolean deleteFlag;
	private Integer fildInfoId;
	private String fileName;
	private LocalDateTime modifyDate;
	private Integer productId;
	private Integer productImageId;
	private String saveFileName;
	private String type;

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Integer getFildInfoId() {
		return fildInfoId;
	}

	public void setFildInfoId(Integer fildInfoId) {
		this.fildInfoId = fildInfoId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public LocalDateTime getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(LocalDateTime modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getProductImageId() {
		return productImageId;
	}

	public void setProductImageId(Integer productImageId) {
		this.productImageId = productImageId;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ProductImage [contentType=" + contentType + ", createDate=" + createDate + ", deleteFlag=" + deleteFlag
			+ ", fildInfoId=" + fildInfoId + ", fileName=" + fileName + ", modifyDate=" + modifyDate + ", productId="
			+ productId + ", productImageId=" + productImageId + ", saveFileName=" + saveFileName + ", type=" + type
			+ "]";
	}

}
