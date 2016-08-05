package com.estsoft.futures.aradongbros.travelfriend.kruskal;

public class Node 
{
	int no;
	double x;
	double y;
	
	public Node() {}
	
	public Node(int no, double x, double y)
	{
		this.no = no;
		this.x = x;
		this.y = y;
	}
	
	public int getNo() {
		return no;
	} 
	public void setNo(int no) {
		this.no = no;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	@Override
	public String toString() {
		return "Node [no=" + no + ", x=" + x + ", y=" + y + "]";
	}
}
