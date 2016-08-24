package com.estsoft.futures.aradongbros.travelfriend.vo;

public class SaleInformationVo 
{
	private int no;
	private int cityList_no;
	private String title;
	private String image;
	private String link;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getCityList_no() {
		return cityList_no;
	}
	public void setCityList_no(int cityList_no) {
		this.cityList_no = cityList_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	@Override
	public String toString() {
		return "SaleInformationVo [no=" + no + ", cityList_no=" + cityList_no + ", title=" + title + ", image=" + image
				+ ", link=" + link + "]";
	}
}
