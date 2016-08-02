package com.estsoft.futures.aradongbros.travelfriend.vo;

public class TrainInfoVo
{
	private int no;
	private int trainNum;
	private String trainModel;
	
	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public int getTrainNum() {
		return trainNum;
	}
	
	public void setTrainNum(int trainNum) {
		this.trainNum = trainNum;
	}
	
	public String getTrainModel() {
		return trainModel;
	}
	
	public void setTrainModel(String trainModel) {
		this.trainModel = trainModel;
	}
	
	@Override
	public String toString() {
		return "TrainInfoVo [no=" + no + ", trainNum=" + trainNum + ", trainModel=" + trainModel + "]";
	}
}
