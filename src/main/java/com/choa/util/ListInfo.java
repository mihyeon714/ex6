package com.choa.util;

public class ListInfo {
	
	private Integer curPage;//null이 올 수 있으니 reference Type사용
	private Integer perPage;
	
	
	
	//row
	private String search;
	private String find;
	//getter만
	private int startRow;
	private int lastRow;
	
	//paging
	private int totalBlock;
	private int curBlock;
	private int startNum;
	private int lastNum;
	private int perBlock = 5;
	



	//page
	public void makePage(int totalCount){
		//1. totalCount
		//2. totalPage
		int totalPage = 0;
		//int perBlock=5;//변동없음을 가정
		
		if(totalCount%this.getPerPage() == 0){
			totalPage=totalCount/this.getPerPage();
		}else {
			totalPage=totalCount/this.getPerPage()+1;
		}
		//3. totalBlock
		if( totalPage%this.getPerBlock() == 0){
			this.totalBlock = totalPage/this.getPerBlock();
		}else {
			this.totalBlock = totalPage/this.getPerBlock()+1;
		}
		//4. curBlock
		if( this.getCurPage()%this.getPerBlock() == 0 ){
			this.curBlock= this.getCurPage()/this.getPerBlock();
		}else {
			this.curBlock= this.getCurPage()/this.getPerBlock()+1;
		}
		
		//5. startNum, lastNum
		this.startNum = ( this.curBlock - 1 ) * this.getPerBlock()+1;
		this.lastNum = this.curBlock*this.getPerBlock();
		if( this.curBlock == this.totalBlock ){
			this.lastNum = totalPage;
		}
	}
	
	
	
	
	//makeRow
	public void makeRow(){
		startRow=(this.getCurPage()-1)*this.getPerPage()+1;
		lastRow=this.getCurPage()*this.getPerPage();
	}
	
	
	
	
	public int getPerBlock() {
		if(this.perBlock == 0){
			perBlock=5;
		}
		return perBlock;
	}


	public int getTotalBlock() {
		return totalBlock;
	}

	public int getCurBlock() {
		return curBlock;
	}

	public int getStartNum() {
		return startNum;
	}

	public int getLastNum() {
		return lastNum;
	}

	public int getStartRow() {
		return startRow;
	}
	public int getLastRow() {
		return lastRow;
	}
	
	public Integer getPerPage() {
		if(perPage==null){
			perPage=10;
		}
		return perPage;
	}
	public void setPerPage(Integer perPage) {
		this.perPage = perPage;
	}
	
	public Integer getCurPage() {
		if(curPage==null){
			curPage=1;
		}
		return curPage;
	}
	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getFind() {
		return find;
	}
	public void setFind(String find) {
		this.find = find;
	}
	

}
