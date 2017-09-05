package com.dasol.domain;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageData {
	private int pageGroupSize = 7; // 페이지 그룹의 사이즈
	private int totCount; // 전체 게시글 수
	private int totPages; // 전체 페이지 수
	private int startPage; // 페이지 그룹 내의 첫번째 페이지
	private int endPage; // 페이지 그룹 내의 마지막 페이지
	private boolean prev; // 이전 게시글 페이지가 있는가
	private boolean next; // 다음 게시글 페이지가 있는가
	private Criteria cri; // 페이지 기준 정보 page, perPageNum

	public PageData(int totCount, Criteria cri) {
		this.totCount = totCount;
		this.cri = cri;
		calData();
	}

	private void calData() {
		totPages = totCount / cri.getPerPageNum();
		if(totCount % cri.getPerPageNum() > 0) {
			totPages++;
		}
		
		int updatePage = pageGroupSize / 2;
		
		if(cri.getPage() <= Math.ceil(updatePage)) { // 첫번째 페이지 그룹에서 현재페이지가 중간에 도달하지 못하였을 때
//			endPage = (int) (Math.ceil(cri.getPage() / (double) pageGroupSize) * pageGroupSize);
			endPage = pageGroupSize;
			startPage = (endPage - pageGroupSize) + 1;   
		} else {
			startPage = cri.getPage() - updatePage;
			endPage = cri.getPage() + updatePage;
		}
		
		if(endPage > totPages) {
			endPage = totPages;
		}
		
		prev = cri.getPage() == 1 ? false : true;
		next = cri.getPage() == totPages ? false : true;
	}
	
	// 중복코드 제거
	public String makeQuery(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.build();
		return uriComponents.toUriString();
	}
	
	public String makeCitySearch(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.queryParam("city", ((SearchCriteria)cri).getCity())
				.build();
		return uriComponents.toUriString();
	}
	
	public String makeCategorySearch(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.queryParam("city", ((SearchCriteria)cri).getCity())
				.queryParam("category", ((SearchCriteria)cri).getCategory())
				.build();
		return uriComponents.toUriString();
	}
	
	public String makeAllQuery(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("city", ((SearchCriteria)cri).getCity())
				.queryParam("category", ((SearchCriteria)cri).getCategory())
				.queryParam("sort", ((SearchCriteria)cri).getSort())
				.queryParam("perPageNum", cri.getPerPageNum())
				.queryParam("page", page)
				.build();
		return uriComponents.toUriString();
	}
	
	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	public void setTotCount(int totCount) {
		this.totCount = totCount;
	}

	public int getPageGroupSize() {
		return pageGroupSize;
	}

	public void setPageGroupSize(int pageGroupSize) {
		this.pageGroupSize = pageGroupSize;
	}

	public int getTotPages() {
		return totPages;
	}

	public void setTotPages(int totPages) {
		this.totPages = totPages;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getTotCount() {
		return totCount;
	}

	public Criteria getCri() {
		return cri;
	}

	@Override
	public String toString() {
		return "PageData [pageGroupSize=" + pageGroupSize + ", totCount=" + totCount + ", totPages=" + totPages
				+ ", startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next + ", cri="
				+ cri + "]";
	}

}
