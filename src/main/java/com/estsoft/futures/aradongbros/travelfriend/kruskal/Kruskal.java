package com.estsoft.futures.aradongbros.travelfriend.kruskal;
import java.util.ArrayList;
import java.util.List;

import com.estsoft.futures.aradongbros.travelfriend.dto.AttractionDTO;

public class Kruskal 
{
	Node[] node;
	double locationX;
	double locationY;
	
	public Kruskal(){}
	
	public Kruskal(List<AttractionDTO> atrList)
	{	
		node = new Node[atrList.size()];
		
		for ( int i = 0; i < atrList.size(); i++ )
		{
			String[] location = atrList.get(i).getLocation().split(",");
			
			this.locationX = Double.parseDouble(location[0]);
			this.locationY = Double.parseDouble(location[1]);
			
			node[i] = new Node(atrList.get(i).getNo(), this.locationX, this.locationY);
		}		
	}
	
	public int[] getTravelRoot()
	{
		// 가중치 담는 1차원 배열
		Weight[] weights = new Weight[(node.length * node.length - node.length) / 2];
				
		int k = 0;
		for ( int i = 0; i < node.length; i++ )
		{
			for ( int j = 0; j < node.length; j++ )
			{
				if ( i < j ) // 각 정점들의 가중치 계산 : i -> j
				{
					weights[k] = new Weight(node[i].getNo(), node[j].getNo(), getDistance(node[i].getX(), node[i].getY(), node[j].getX(), node[j].getY()));
					
					k++; 
				}
			}
		}
		
		// 가중치값들을 오름차순으로 정렬
		sort(weights);
/*		for ( int i = 0; i < weights.length; i++ )
		{
			System.out.println(weights[i].getS() + "," + weights[i].getE() + " : " + weights[i].getWeight() );
		}*/
			
		// 가치가 작은 것 부터 경로(리스트)에 추가한다. 단, 사이클이 생기지 않도록 판별해준다.	
		List<Weight> path = new ArrayList<Weight>();
		
		DisjointSet check = new DisjointSet();
		
		for ( int i = 0; i < weights.length; i++ )
		{	
			// 사이클이 생성되는지 판별하여 리스트에 추가할지 안할지 결정
			if( !checkBranch(path, weights[i]) && !checkCycle(check, weights[i])) 
			{
				path.add(weights[i]);
			}
		}
		
		
		
		// 최단 간선 출력
	  for ( int i = 0; i < path.size(); i++ )
		{
			System.out.println("(" + path.get(i).getS() + "," + path.get(i).getE() + ") : " + path.get(i).getWeight());
		}
		System.out.println("");
		
		// 여행 경로 출력
		int[] TRAVEL_ROOT = new int[node.length];
		
		if ( node.length == 1 )
		{
			TRAVEL_ROOT[0] = node[0].getNo();
		}
		else
		{
			TRAVEL_ROOT = getRoot(path, TRAVEL_ROOT);			
		}
		
		System.out.print("전체 경로 : ");
		for ( int i = 0; i < TRAVEL_ROOT.length; i++ )
		{
			System.out.print(TRAVEL_ROOT[i] + " ");
		}
		System.out.println("");
		System.out.println("출발지 : " + TRAVEL_ROOT[0]);
		System.out.println("종착지 : " + TRAVEL_ROOT[TRAVEL_ROOT.length - 1]);
		
		return TRAVEL_ROOT;
	}
	
	// 각 정점들의 가중치 계산
	public double getDistance(double x1, double y1, double x2, double y2)
	{	
		double distance = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)); 
				
		return distance;
	}

	// 가중치 값에 따라 오름차순으로 정렬(버블 정렬)  -> 나중에 퀵정렬로 바꿔줄 것.
	public void sort(Weight[] weights)
	{
		Weight tmp;
		
		for ( int i = 0; i < weights.length - 1; i++ )
		{
			for (int j = i + 1; j < weights.length; j++ )
			{
				if ( weights[i].getWeight() > weights[j].getWeight() )
				{
					tmp = weights[i];
					weights[i] = weights[j];
					weights[j] = tmp;
				}
			}
		}
	}
	
	// 사이클이 있는지 체크한다.
	public boolean checkCycle(DisjointSet check, Weight weight)
	{		
		return check.checkCycle(weight.getS(), weight.getE());
	}
	
	// 가지가 있는지 체크한다.
	public boolean checkBranch(List<Weight> path, Weight weight)
	{
		int[] se = new int[path.size() * 2];
		for ( int i = 0; i < se.length -1 ; i += 2 )
		{
			se[i] = path.get(i/2).getS();
			se[i + 1] = path.get(i/2).getE();			
		}
		
/*		for ( int i = 0; i < se.length; i++ )
		{
			System.out.print(se[i] + " ");
		}
		System.out.println("qqq");*/
		

		int countS = 0;
		int countE = 0;
		for ( int i = 0; i < se.length; i++ )
		{
			if ( weight.getS() == se[i] )
			{
				countS++;
			}
			
			if ( weight.getE() == se[i] )
			{
				countE++;
			}
		}
		//System.out.println("weight : " + weight.toString() + countS + " : " + countE);
		if ( countS > 1 || countE > 1 )
		{
			return true;
		}
		
		return false;
	}
	
	// 여행 시작 정점 출력
	public int[] getRoot(List<Weight> path, int[] TRAVEL_ROOT) 
	{
		int start = 0;
		
		int[] se = new int[path.size() * 2];
		for ( int i = 0; i < se.length -1 ; i += 2 )
		{
			se[i] = path.get(i/2).getS();
			se[i + 1] = path.get(i/2).getE();			
		}
		
		for ( int i = 0; i < se.length - 1; i++ )
		{
			int count = 0;
			for ( int j = 0; j < se.length; j++ )
			{
				if ( se[i] == se[j] )
				{
					count++;
				}
			}
			
			if ( count == 1 )
			{
				start = se[i];
				break;
			}
		}
		
		for ( int i = 0; i < path.size(); i++ )
		{
			for ( int j = 0; j < path.size(); j++ )
			{		
				if ( start == path.get(j).getS() )
				{
					TRAVEL_ROOT[i] = start;
					start = path.get(j).getE();
					TRAVEL_ROOT[i+1] = start;
					
					path.get(j).setS(0);
					path.get(j).setE(0);
					
					break;
				}
				else if ( start == path.get(j).getE() )
				{
					TRAVEL_ROOT[i] = start;
					start = path.get(j).getS();
					TRAVEL_ROOT[i+1] = start;
					
					path.get(j).setS(0);
					path.get(j).setE(0);

					break;
				}
			}
		}
		return TRAVEL_ROOT;
	}
}
