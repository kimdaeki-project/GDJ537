package com.app.home.report.util;

import com.app.home.report.ReportVO;

import com.app.home.user.UserVO;

import lombok.Data;

public class ReportPager {
	private Long page;
	private Long startNum;
	private Long lastNum;
	private Long perPage;
	private Long perBlock;
	private Long totalPage;
	private Long startRow;
	
	private boolean pre;
	private boolean next;
	
	private String kind;
	private String search;
	private int depNum;
	private int roleNum;
	private int id;
	private int reportNum;
	
	
	
	public Long getStartNum() {
		return startNum;
	}

	public void setStartNum(Long startNum) {
		this.startNum = startNum;
	}

	public Long getLastNum() {
		return lastNum;
	}

	public void setLastNum(Long lastNum) {
		this.lastNum = lastNum;
	}

	public Long getPerPage() {
		return perPage;
	}

	public void setPerPage(Long perPage) {
		this.perPage = perPage;
	}

	public Long getPerBlock() {
		return perBlock;
	}

	public void setPerBlock(Long perBlock) {
		this.perBlock = perBlock;
	}

	public Long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

	public boolean isPre() {
		return pre;
	}

	public void setPre(boolean pre) {
		this.pre = pre;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getDepNum() {
		return depNum;
	}

	public void setDepNum(int depNum) {
		this.depNum = depNum;
	}

	public int getRoleNum() {
		return roleNum;
	}

	public void setRoleNum(int roleNum) {
		this.roleNum = roleNum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getReportNum() {
		return reportNum;
	}

	public void setReportNum(int reportNum) {
		this.reportNum = reportNum;
	}

	public void setPage(Long page) {
		this.page = page;
	}

	public void setStartRow(Long startRow) {
		this.startRow = startRow;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getKind() {
		if(this.kind == null) {
			this.kind = "";
		}
		return kind;
	}
	
	public ReportPager() {
		this.perPage = 10L;
		this.perBlock = 5L;
	}
	
	public Long getPage() {
		if(this.page == null || this.page <=0) {
			this.page = 1L;
		}
		return page;
	}
	
	//mapper??? ???????????????
	public Long getStartRow() {
		this.startRow = (this.getPage()-1)*this.getPerPage();
		return startRow;
	}
	
	
	//jsp ?????? ??? startnum
	public void getNum(Long totalCount) throws Exception{
		//1. ?????? totalCount??? ?????? ????????? ?????????
		Long totalPage = totalCount/this.getPerPage();
		if(totalCount%this.getPerPage() != 0) {
			totalPage++;
		}
		
		this.totalPage = totalPage;
		
		//2. ?????? ???????????? ?????? ?????? ?????????
		Long totalBlock = totalPage/this.getPerBlock();
		if(totalPage%this.getPerBlock() != 0) {
			totalBlock++;
		}
		
		//3. page ????????? ?????? ????????? ?????? ?????????
		Long curBlock = this.getPage()/this.getPerBlock();
		if(this.getPage()%this.getPerBlock() != 0) {
			curBlock++;
		}
		
		//4. Curblock?????? ???????????? ??? ?????????
		this.startNum = (curBlock-1)*this.getPerBlock()+1;
		this.lastNum = curBlock*this.getPerBlock();
		
		if(curBlock == totalBlock) {
			this.lastNum = totalPage;
		}
		
		if(curBlock > 1) {
			pre = true;
		}
		
		if(curBlock<totalBlock) {
			next = true;
		}
		
		if(totalCount == 0) {
			this.startNum = 1L;
			this.lastNum = 1L;
		}
		
	}

}
