package com.estsoft.futures.aradongbros.travelfriend.kruskal;

public class Weight 
{
	int s;
	int e;
	double weight;
	
	public Weight(){}
	
	public Weight(int s, int e, double weight)
	{
		this.s = s;
		this.e = e;
		this.weight = weight;
	}

	public int getS() {
		return s;
	}

	public void setS(int s) {
		this.s = s;
	}

	public int getE() {
		return e;
	}

	public void setE(int e) {
		this.e = e;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "weight [s=" + s + ", e=" + e + ", weight=" + weight + "]";
	}
	
	
}
