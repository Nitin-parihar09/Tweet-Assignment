package com.SpringBoot.Model;

import javax.persistence.Entity;
import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "Tweet")
public class Tweet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	private LocalDateTime timestamp;

	@Column(nullable = false)
	private Long user_id;

	public Tweet() {
		super();
	}

	public Tweet(Long id, String content, LocalDateTime timestamp, Long uId) {
		super();
		this.id = id;
		this.content = content;
		this.timestamp = timestamp;
		this.user_id = uId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	@PrePersist
	public void prePersist() {
		timestamp = LocalDateTime.now();
	}

}
