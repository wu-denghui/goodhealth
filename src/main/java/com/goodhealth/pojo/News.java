package com.goodhealth.pojo;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;


/**
 * The persistent class for the new database table.
 * 
 */
@Entity
@Table(name="news")
public class News implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="new_id")
	private int newId;

	@Column(name="new_author")
	@NotBlank(message="发布者不得为空")
	private String newAuthor;

	@Column(name="new_date")
	@NotBlank(message="发布时间不得为空")
	private String   newDate;

	@Column(name="new_detail")
	@NotBlank(message="链接不得为空")
	private String newDetail;

	@Column(name="new_title")
	@NotBlank(message="标题不得为空")
	private String newTitle;

	public News() {
	}

	public int getNewId() {
		return this.newId;
	}

	public void setNewId(int newId) {
		this.newId = newId;
	}

	public String getNewAuthor() {
		return this.newAuthor;
	}

	public void setNewAuthor(String newAuthor) {
		this.newAuthor = newAuthor;
	}

	public String getNewDate() {
		return this.newDate;
	}

	public void setNewDate(String newDate) {
		this.newDate = newDate;
	}

	public String getNewDetail() {
		return this.newDetail;
	}

	public void setNewDetail(String newDetail) {
		this.newDetail = newDetail;
	}

	public String getNewTitle() {
		return this.newTitle;
	}

	public void setNewTitle(String newTitle) {
		this.newTitle = newTitle;
	}

}