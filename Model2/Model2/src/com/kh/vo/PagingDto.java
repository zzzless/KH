package com.kh.vo;

public class PagingDto {
	private int page = 1;
	private int startRow = 1;
	private int endRow = 10;
	private int count;
	
	public PagingDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PagingDto(int page) {
		this.page = page;
		this.endRow = page * 10;
		this.startRow = endRow - 9;
	}
	
	public PagingDto(int page, int startRow, int endRow, int count) {
		super();
		this.page = page;
		this.startRow = startRow;
		this.endRow = endRow;
		this.count = count;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "PagingDto [page=" + page + ", startRow=" + startRow + ", endRow=" + endRow + ", count=" + count + "]";
	}
	
}