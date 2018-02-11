/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.utdallas.sxb170035.library_app.data.jpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String isbn10;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String publisher;

	@Column(nullable = false)
	private String cover;

	@Column(nullable = false)
	private Integer pages;

	protected Book() {
	}

	public Book(String isbn10, String title, String publisher, String cover, Integer pages) {
		super();
		this.isbn10 = isbn10;
		this.title = title;
		this.publisher = publisher;
		this.cover = cover;
		this.pages = pages;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getIsbn10() {
		return isbn10;
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

	@Override
	public String toString() {
		return "Book [isbn10=" + isbn10 + ", title=" + title + ", publisher=" + publisher + ", cover=" + cover
				+ ", pages=" + pages + "]";
	}

	}
