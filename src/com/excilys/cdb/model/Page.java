package com.excilys.cdb.model;

import java.util.Collections;
import java.util.List;



public class Page {
	
	private int page;
	private int pageSize;
	public <T> List<T> getPage(List<T> sourceList) {
		if(this.getPage() <= 0 || this.getPageSize() <= 0) {
			System.out.println("Page inexistante !!");
			throw new IllegalArgumentException("invalid page size: " + pageSize);

		}

		int fromIndex = (page - 1) * pageSize;
		if(sourceList == null || sourceList.size() < fromIndex){
			return Collections.emptyList();
		}

		// toIndex exclusive
		return sourceList.subList(fromIndex, Math.min(fromIndex + pageSize, sourceList.size()));
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
