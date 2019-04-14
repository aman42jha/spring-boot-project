package com.login.model.pojo;

import java.util.List;

import com.login.model.Login;

public class UsersDataPojo {
	private int currentPage;
	private int currentPageSize;
	private int totalPages;
	private Long totalElements;
	private List<Login> content;
	
	public UsersDataPojo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsersDataPojo(int currentPage, int currentPageSize, int totalPages, Long totalElements, List<Login> content) {
		super();
		this.currentPage = currentPage;
		this.currentPageSize = currentPageSize;
		this.totalPages = totalPages;
		this.totalElements = totalElements;
		this.content = content;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getCurrentPageSize() {
		return currentPageSize;
	}

	public void setCurrentPageSize(int currentPageSize) {
		this.currentPageSize = currentPageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public Long getTotalElements() {
		return totalElements;
	}

	public List<Login> getContent() {
		return content;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + currentPage;
		result = prime * result + currentPageSize;
		result = prime * result + ((totalElements == null) ? 0 : totalElements.hashCode());
		result = prime * result + totalPages;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsersDataPojo other = (UsersDataPojo) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (currentPage != other.currentPage)
			return false;
		if (currentPageSize != other.currentPageSize)
			return false;
		if (totalElements == null) {
			if (other.totalElements != null)
				return false;
		} else if (!totalElements.equals(other.totalElements))
			return false;
		if (totalPages != other.totalPages)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UsersDataPojo [currentPage=" + currentPage + ", currentPageSize=" + currentPageSize + ", totalPages="
				+ totalPages + ", totalElements=" + totalElements + ", content=" + content + "]";
	}
}
