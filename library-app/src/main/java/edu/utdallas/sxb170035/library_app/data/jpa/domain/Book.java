package edu.utdallas.sxb170035.library_app.data.jpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String isbn;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String publisher;

	@Column(nullable = false)
	private String cover;

	@Column(nullable = false)
	private Integer pages;
	
	@Column(name="IS_CHECKED_OUT",nullable = false)
	private boolean isCheckedOut;

	public Book() {
	}

	public Book(String isbn, String title, String publisher, String cover, Integer pages,boolean isCheckedOut) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.publisher = publisher;
		this.cover = cover;
		this.pages = pages;
		this.isCheckedOut=isCheckedOut;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getCover() {
		return cover;
	}

	public Integer getPages() {
		return pages;
	}

	public boolean isCheckedOut() {
		return isCheckedOut;
	}

	public void setCheckedOut(boolean isCheckedOut) {
		this.isCheckedOut = isCheckedOut;
	}

	@Override
	public String toString() {
		return "Book [isbn10=" + isbn + ", title=" + title + ", publisher=" + publisher + ", cover=" + cover
				+ ", pages=" + pages + "]";
	}

	}
