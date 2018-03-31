package edu.utdallas.sxb170035.library_app.data.jpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Author implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "book_isbn")
	private Book book;

	@Column(name = "author_name", nullable = false)
	private String authorName;

	public Author() {
	}

	public Author(Book book, String authorName) {
		super();
		this.book = book;
		this.authorName = authorName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Book getBook() {
		return book;
	}

	public String getAuthorName() {
		return authorName;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", book=" + book + ", authorName=" + authorName + "]";
	}

}
