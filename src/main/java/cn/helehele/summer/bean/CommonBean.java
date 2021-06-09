package cn.helehele.summer.bean;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public abstract class CommonBean {

	private Long id;
	private long updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	public ZonedDateTime getCreatedDateTime() {
		return Instant.ofEpochMilli(this.updatedAt).atZone(ZoneId.systemDefault());
	}
}
