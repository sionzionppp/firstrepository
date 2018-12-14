package com.biz.shop;

import java.util.ArrayList;

public class ShopVO {
	//shop_info
	private int sseq;
	private String sname;	//사용자 입력 상호명
	private String sinfo;
	private String lat;			//지도 위도
	private String lng;			//지도 경도
	private String placename; 	//지도에서 검색된 상호명
	private String regid;
	private String regdate;
	
	
	//페이징을위한 startSeq, endSeq
	private int currentPage;
	private int startSeq;
	private int endSeq;
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getStartSeq() {
		return startSeq;
	}
	public void setStartSeq(int startSeq) {
		this.startSeq = startSeq;
	}
	public int getEndSeq() {
		return endSeq;
	}
	public void setEndSeq(int endSeq) {
		this.endSeq = endSeq;
	}
	//파라미터 전달을 위한 vo
	private String distance;
	private int topn = 4;
	
	
	//메인페이지 :  대표이미지1장
	private ShopPicVO shopPicVO;
	
	//상세보기 페이지  : 이미지목록 n장
	private ArrayList<ShopPicVO> pvolist;
		
	//상세보기 페이지  : 댓글목록 n개
	private ArrayList<ReplyVO> rvolist;	
	public ArrayList<ReplyVO> getRvolist() {
		return rvolist;
	}
	public void setRvolist(ArrayList<ReplyVO> rvolist) {
		this.rvolist = rvolist;
	}
	
	
	public ShopPicVO getShopPicVO() {
		return shopPicVO;
	}
	public void setShopPicVO(ShopPicVO shopPicVO) {
		this.shopPicVO = shopPicVO;
	}
	
	public ArrayList<ShopPicVO> getPvolist() {
		return pvolist;
	}
	public void setPvolist(ArrayList<ShopPicVO> pvolist) {
		this.pvolist = pvolist;
	}

	
	public int getTopn() {
		return topn;
	}
	public void setTopn(int topn) {
		this.topn = topn;
	}

	public String getPlacename() {
		return placename;
	}
	public void setPlacename(String placename) {
		this.placename = placename;
	}

	//sseq
	
	public int getSseq() {
		return sseq;
	}
	public void setSseq(int sseq) {
		this.sseq = sseq;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSinfo() {
		return sinfo;
	}
	public void setSinfo(String sinfo) {
		this.sinfo = sinfo;
	}
	
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getRegid() {
		return regid;
	}
	public void setRegid(String regid) {
		this.regid = regid;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	
	
	
	
}
